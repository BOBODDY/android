package com.nickel3956.iglesias;

import android.app.Activity;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import java.util.Random;

public class RandomActivity extends Activity{
	private int sounds[] = {R.raw.car_driving_and_brakes, R.raw.car_driving, R.raw.cop_siren, R.raw.crazy_laugh, R.raw.indian_sound, R.raw.scream, R.raw.stomach_growl_1, R.raw.stomach_growl_2, R.raw.suv_horn,R.raw.are_you_the_one_ringing_that_bell, R.raw.chicos, R.raw.hello_sir_hiiiiii, R.raw.hes_a_sexy_bitch, R.raw.i_hear_the_bell, R.raw.i_say_we_must_go_try_chicos, R.raw.iglasias_with_a_i, R.raw.im_gonna_go_to_jail, R.raw.my_grandma_lived_to_be_100_years_old, R.raw.nacho_libre_is_tripping, R.raw.oh_my_god_yes, R.raw.thank_you_very_much, R.raw.what_the_hell_is_chicos_australian, R.raw.what_the_hell_you_want, R.raw.whats_the_i_stand_for, R.raw.you_know_what_time_it_is, R.raw.cool_transition, R.raw.pop_rocks,R.raw.ubelievable,R.raw.back_to_whoring};
	
	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		super.setVolumeControlStream(AudioManager.STREAM_MUSIC);
		Button randomButton = new Button(this);
		randomButton.setText("Surprise me!");
		randomButton.setOnClickListener(new ButtonListener());
		
		setContentView(randomButton);
	}
	
	protected void onResume() {
		super.onResume();
	}
	
	private class ButtonListener implements OnClickListener {
		public void onClick(View v) {
			Random rand = new Random();
			int random = rand.nextInt(sounds.length);
			
			Music.play(getApplicationContext(), sounds[random]);
		}
	}
}
