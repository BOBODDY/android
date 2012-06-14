package com.nickel3956.task;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RecentTaskInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Jesse
 */
public class MainActivity extends Activity {

    public static final int MAX_RECENT_TASKS = 100;
    private static final String HOME_SCREEN_PACKAGE = "com.android.launcher";
    private static final String HOME_SCREEN_NAME = "com.android.launcher.Launcher";
    private List<Intent> taskIntents;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        LinearLayout root = (LinearLayout) findViewById(R.id.documentroot);
        //At this time, Underground uses just the two basic system themes, for max compatibility
        //So we need to get the preference from the previous session, and init properly.
        //For some reason, we also need to set the root color--the Theme won't do it.
        SharedPreferences prefs = getPreferences(0);
//        if (prefs.getString("theme", "white").equals("black")) {
//            setTheme(android.R.style.Theme_Black_NoTitleBar);
//            root.setBackgroundColor(0xFF000000);
//        } else {
//            setTheme(android.R.style.Theme_Light_NoTitleBar);
        root.setBackgroundColor(0xFFFFFFFF);
        final View view = findViewById(R.id.activeFilter);
        CheckBox cb = (CheckBox) view;
        cb.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton cb, boolean bln) {
                MainActivity.this.reloadTasks();
            }

        });

//        }
//        root.setBackgroundColor(0xCCCCCCCC);
//        registerForContextMenu(this.getgetListView());
    }

    /*
     * onNewIntent() is where we handle the second Home button press.
     *
     * (non-Javadoc)
     * @see android.app.Activity#onNewIntent(android.content.Intent)
     */
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (Intent.ACTION_MAIN.equals(intent.getAction())) {
            //This test is to establish whether we're already in the foreground, and should bow out.
            if ((intent.getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) {
                //AppAdapter adapter = (AppAdapter) getListView().getAdapter();
//                sqlHelper = new DatabaseHelper(this);
//                db = sqlHelper.getWritableDatabase();
//                Cursor c = db.rawQuery("SELECT * FROM favorites WHERE caption = 'home' AND builtin = 1", null);
//                c.moveToFirst();
                try {
                    startActivity(getHomeLauncherIntent());
                    //android.os.Process.killProcess(android.os.Process.myPid());
                    finish();
                } catch (Exception error) {
                    //If it fails, chances are we have the wrong launcher info, so try to update the DB record.
//                    Log.w("UNDERGROUND", error.toString());
//                    updateHomeItem();
                }
            }
        }
    }

    /**
     * Filter a list of intents by which tasks/activities are currently running
     *
     * @param intents
     * @return
     */
    private List<Intent> filterByRunningTasks(List<Intent> intents) {
        ActivityManager mgr = (ActivityManager) this.getSystemService(Context.ACTIVITY_SERVICE);
        List<Intent> filtered = new ArrayList<Intent>();
        Set<String> runningActivityStrings = new HashSet<String>();
        //assemble a list of the running tasks, based on the intents size
        //NOTE: this is a bit of a hack...but it assumes that the intents passed in
        // (likely recent tasks) will be a larger set than the running tasks
        //..otherwise, there may be some running tasks that are excluded
        for (RunningTaskInfo task : mgr.getRunningTasks(intents.size())) {
            runningActivityStrings.add(task.topActivity.flattenToString());
        }
        for (Intent intent : intents) {
            if (runningActivityStrings.contains(intent.getComponent().flattenToString())) {
                filtered.add(intent);
            }
        }
        return filtered;
    }

    /**
     * Get a home launcher intent, to launch the default home screen app.
     *
     * @return A new intent that will launch the default home screen.
     */
    private Intent getHomeLauncherIntent() {
        //Attempt to launch an Intent using the stored Home entry in the DB
        Intent launch = new Intent(Intent.ACTION_MAIN);
        launch.setClassName(MainActivity.HOME_SCREEN_PACKAGE, MainActivity.HOME_SCREEN_NAME);
        launch.addCategory(Intent.CATEGORY_LAUNCHER);
        launch.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return launch;
    }

    @Override
    protected void onResume() {
        super.onResume();

        reloadTasks();
    }

    private void reloadTasks() {
        ActivityManager mgr = (ActivityManager) this.getSystemService(Context.ACTIVITY_SERVICE);

        this.taskIntents = new ArrayList<Intent>();
//TODO: this will populate the view with running tasks, using recent task info to
        // get the original intents....another way to display info is to display the recent tasks, which
        // will cover tasks that Android has shut down.  This is particularly useful withd ocuments to go, as
        // word and pdf are in different activities, and therefore can be displaued/launched independently
        final List<RecentTaskInfo> recentTasks = mgr.getRecentTasks(MAX_RECENT_TASKS, 0);
        List<Intent> intents = new ArrayList<Intent>();
        for (RecentTaskInfo task : recentTasks) {
            if (task.baseIntent != null && task.baseIntent.getComponent() != null) {
                Intent intent = new Intent(task.baseIntent);
                if (task.origActivity != null) {
                    intent.setComponent(task.origActivity);
                }
                intent.setFlags((intent.getFlags() & ~Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED)
                        | Intent.FLAG_ACTIVITY_NEW_TASK);

                intents.add(intent);
            }
        }
        CheckBox cb = (CheckBox) findViewById(R.id.activeFilter);
        if (cb.isChecked()) {
            intents = filterByRunningTasks(intents);
        }
        this.taskIntents = intents;
        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new IntentIconAdapter(this, intents));
        gridview.setOnItemClickListener(new OnItemClickListener() {

            public void onItemClick(AdapterView<?> av, View view, int position, long id) {
                onGridItemClick(position);
            }
        });
    }

    protected void onGridItemClick(int position) {
        try {
            debugOut("Doin Stuff");
            Intent intent = this.taskIntents.get(position);
            if (intent == null) {
                debugOut("Actvitiy is null");
            }
            intent.addFlags(Intent.FLAG_ACTIVITY_LAUNCHED_FROM_HISTORY);
            fixIntentForDocumentsToGo(intent);
            debugOut("launching " + intent.getComponent().getClassName());
            //intent.addFlags(Intent.FLAG_ACTIVITY_LAUNCHED_FROM_HISTORY);
            startActivity(intent);
            finish();
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT);
        }
    }

    /**
     * Documents to go PDF viewer uses an inner class (classname$innerclass)
     * and for some reason this doesn't work with the above launch code.  It will
     * work if you remove the inner class name and launch the main class.
     *
     * TODO: this is bizarre, because the code here is more or less exactly what
     * the built in recently application dialog does...and that works.  Need to
     * look into this, but for the time being, we can hack it to get it to work.
     *
     * http://android.git.kernel.org/?p=platform/frameworks/policies/base.git;a=blob;f=phone/com/android/internal/policy/impl/RecentApplicationsDialog.java;h=4c260ea2433f8b4640103308525971518a98fe8e;hb=HEAD
     * @param intent
     * @return
     */
    private Intent fixIntentForDocumentsToGo(Intent intent) {
        //sTODO: PDFs are broken
        //            intent.setClassName(activity.getPackageName(), activity.getClassName());
        //            intent.addCategory(Intent.CATEGORY_LAUNCHER);
        //TODO BRING TO FRONT sometimes works, sometimes fails...play with this
        //            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        int inx = intent.getComponent().getClassName().lastIndexOf("$");
        String name = "";
        if (inx >= 0) {
            name = intent.getComponent().getClassName().substring(0, inx);
            intent.setClassName(intent.getComponent().getPackageName(), name);
        }
        return intent;
    }

    private void debugOut(String string) {
        ((TextView) findViewById(R.id.debug)).setText(string);
    }
//    /**
//     * Note: copied from android source: http://android.git.kernel.org/?p=platform/frameworks/policies/base.git;a=blob;f=phone/com/android/internal/policy/impl/RecentApplicationsDialog.java;h=4c260ea2433f8b4640103308525971518a98fe8e;hb=HEAD
//     * This is from the normal task switch dialog com.android.internal.policy.impl.RecentApplicationsDialog
//     * Reload the 6 buttons with recent activities
//     */
//    private void reloadButtons() {
//
//        final ActivityManager am = (ActivityManager) this.getSystemService(Context.ACTIVITY_SERVICE);
//        final PackageManager pm = getPackageManager();
//        final List<ActivityManager.RecentTaskInfo> recentTasks =
//                am.getRecentTasks(MAX_RECENT_TASKS, 0 /*ActivityManager.RECENT_IGNORE_UNAVAILABLE*/);
//
//        ActivityInfo homeInfo =
//                new Intent(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_HOME).resolveActivityInfo(pm, 0);
//
////        IconUtilities iconUtilities = new IconUtilities(getContext());
//
//        // Performance note:  Our android performance guide says to prefer Iterator when
//        // using a List class, but because we know that getRecentTasks() always returns
//        // an ArrayList<>, we'll use a simple index instead.
//        int index = 0;
//        int numTasks = recentTasks.size();
//        for (int i = 0; i < numTasks && (index < NUM_BUTTONS); ++i) {
//            final ActivityManager.RecentTaskInfo info = recentTasks.get(i);
////
////            // for debug purposes only, disallow first result to create empty lists
////            if (DBG_FORCE_EMPTY_LIST && (i == 0)) {
////                continue;
////            }
////
////            Intent intent = new Intent(info.baseIntent);
////            if (info.origActivity != null) {
////                intent.setComponent(info.origActivity);
////            }
////
////            // Skip the current home activity.
////            if (homeInfo != null) {
////                if (homeInfo.packageName.equals(
////                        intent.getComponent().getPackageName())
////                        && homeInfo.name.equals(
////                        intent.getComponent().getClassName())) {
////                    continue;
////                }
////            }
////
////            intent.setFlags((intent.getFlags() & ~Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED)
////                    | Intent.FLAG_ACTIVITY_NEW_TASK);
////            final ResolveInfo resolveInfo = pm.resolveActivity(intent, 0);
////            if (resolveInfo != null) {
////                final ActivityInfo activityInfo = resolveInfo.activityInfo;
////                final String title = activityInfo.loadLabel(pm).toString();
////                Drawable icon = activityInfo.loadIcon(pm);
////
////                if (title != null && title.length() > 0 && icon != null) {
////                    final TextView tv = mIcons[index];
////                    tv.setText(title);
////                    icon = iconUtilities.createIconDrawable(icon);
////                    tv.setCompoundDrawables(null, icon, null, null);
////                    tv.setTag(intent);
////                    tv.setVisibility(View.VISIBLE);
////                    tv.setPressed(false);
////                    tv.clearFocus();
////                    ++index;
////                }
////            }
////        }
////
////        // handle the case of "no icons to show"
////        mNoAppsText.setVisibility((index == 0) ? View.VISIBLE : View.GONE);
////
////        // hide the rest
////        for (; index < NUM_BUTTONS; ++index) {
////            mIcons[index].setVisibility(View.GONE);
////        }
////    }
}

