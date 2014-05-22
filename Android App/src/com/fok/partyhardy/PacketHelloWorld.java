package com.fok.partyhardy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import android.annotation.TargetApi;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import com.sirolf2009.networking.ICommunicator;
import com.sirolf2009.networking.Packet;

@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
public class PacketHelloWorld extends Packet {

	private String message;
	
	/**
	 * Serialization only, do not use
	 */
	public PacketHelloWorld() {
	}
	
	public PacketHelloWorld(String message) {
		this.message = message;
	}
	
	protected void write(PrintWriter out) {
		Log.i("PartyHardyClient", "sending");
		out.println(message);
	}
	public void receive(BufferedReader in) throws IOException {
		Log.i("PartyHardyHost", "receiving");
		this.message = in.readLine();
	}
	
	public void receivedOnClient(ICommunicator client) {
		
	}
	public void receivedOnServer(ICommunicator host) {
		Log.i("PartyHardyHost", "packet received on host");

		NotificationCompat.Builder mBuilder =
		        new NotificationCompat.Builder(HostActivity.instance)
		        .setSmallIcon(R.drawable.ic_launcher)
		        .setContentTitle("Party Invite")
		        .setContentText("Hello World is hosting a party called Hello Party");
		Intent resultIntent = new Intent(HostActivity.instance, MainActivity.class);
		TaskStackBuilder stackBuilder = TaskStackBuilder.create(HostActivity.instance);
		stackBuilder.addParentStack(MainActivity.class);
		stackBuilder.addNextIntent(resultIntent);
		PendingIntent resultPendingIntent =
		        stackBuilder.getPendingIntent(
		            0,
		            PendingIntent.FLAG_UPDATE_CURRENT
		        );
		mBuilder.setContentIntent(resultPendingIntent);
		NotificationManager mNotificationManager =
		    (NotificationManager) HostActivity.instance.getSystemService(Context.NOTIFICATION_SERVICE);
		mNotificationManager.notify(0, mBuilder.build());
	}

}
