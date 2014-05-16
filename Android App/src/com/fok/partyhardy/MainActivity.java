package com.fok.partyhardy;

import java.util.Random;

import com.fok.partyhardy.fragments.FragmentHome;

import android.support.v7.app.ActionBarActivity;
import android.annotation.TargetApi;
import android.os.Bundle;
import android.view.View;
import android.os.Build;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction().add(R.id.container, new FragmentHome()).commit();
		}
	}

	public void onNextPressed(View view) {
		view.setAlpha(new Random().nextFloat());
	}
}
