package com.fok.partyhardy;

import java.util.Random;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.os.Build;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class MainActivity extends Activity {

	/**
	 * IP adress Floris: 145.24.200.53
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_home);
	}

	public void onNextPressed(View view) {
		view.setAlpha(new Random().nextFloat());
		Intent intent = new Intent(this, HostActivity.class);
		startActivity(intent);
	}
}
