package com.nickel3956.task;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

/**
 *
 * @author Jesse
 */
public class ResolveInfoIconAdapter extends BaseAdapter {

    private Context mContext;
    private final List<ResolveInfo> applications;

    public ResolveInfoIconAdapter(Context c,
            List<ResolveInfo> applications) {
        this.mContext = c;
        this.applications = applications;
    }

    public int getCount() {
        return this.applications.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        View v;
        TextView tv;
        ImageView iv;
        v = setupView(convertView);

        tv = (TextView) v.findViewById(R.id.icon_text);
        iv = (ImageView) v.findViewById(R.id.icon_image);

        ResolveInfo info = applications.get(position);
        final PackageManager pm = this.mContext.getPackageManager();
        final ActivityInfo activityInfo = info.activityInfo;
        final String title = activityInfo.loadLabel(pm).toString();

        Drawable icon = activityInfo.loadIcon(pm);

        if (title != null && title.length() > 0 && icon != null) {
            iv.setImageDrawable(icon);
            tv.setText(title);
        }
        return v;
    }

    private View setupView(View convertView) {
        View v;
        ImageView iv;
        final LayoutInflater li = (LayoutInflater) this.mContext.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            v = li.inflate(R.layout.icon, null);

            iv = (ImageView) v.findViewById(R.id.icon_image);
            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
            iv.setPadding(8, 8, 8, 8);
        } else {
            v = convertView;
        }
        return v;
    }
    // references to our images
//    private Integer[] mThumbIds = {
//            R.drawable.sample_2, R.drawable.sample_3,
//            R.drawable.sample_4, R.drawable.sample_5,
//            R.drawable.sample_6, R.drawable.sample_7,
//            R.drawable.sample_0, R.drawable.sample_1,
//            R.drawable.sample_2, R.drawable.sample_3,
//            R.drawable.sample_4, R.drawable.sample_5,
//            R.drawable.sample_6, R.drawable.sample_7,
//            R.drawable.sample_0, R.drawable.sample_1,
//            R.drawable.sample_2, R.drawable.sample_3,
//            R.drawable.sample_4, R.drawable.sample_5,
//            R.drawable.sample_6, R.drawable.sample_7
//    };
}
