package com.fok.partyhardy;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class MainActivity extends Activity {

	/**
	 * IP adress sirolf2009: 145.24.200.53
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_home);
	}

	public void onHostPressed(View view) {
		startActivity(new Intent(this, HostActivity.class));
	}
	
	public void onClientPressed(View view) {
		startActivity(new Intent(this, ClientActivity.class));
	}
}
