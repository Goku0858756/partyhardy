package com.fok.partyhardy;

import com.sirolf2009.networking.Packet;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;

@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
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
	
	static {
		Packet.registerPacket(0, PacketHelloWorld.class);
	}
}
