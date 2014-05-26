package com.fok.partyhardy.packets;

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

import com.fok.partyhardy.R;
import com.fok.partyhardy.client.ClientActivity;
import com.fok.partyhardy.host.HostActivity;
import com.sirolf2009.networking.ICommunicator;
import com.sirolf2009.networking.Packet;

@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
public class PacketPartyInvite extends Packet {

	private String title;
	private String text;
	private String partyIP;

	/**
	 * Serialization only, do not use
	 */
	public PacketPartyInvite() {
	}

	public PacketPartyInvite(String title, String text, String partyIP) {
		this.title = title;
		this.text = text;
		this.partyIP = partyIP;
	}

	protected void write(PrintWriter out) {
		Log.i("PartyHardyClient", "sending");
		out.println(title);
		out.println(text);
		out.println(partyIP);
	}
	public void receive(BufferedReader in) throws IOException {
		Log.i("PartyHardyHost", "receiving");
		title = in.readLine();
		text = in.readLine();
		partyIP = in.readLine();
		Log.d("PartyHardy", "Received "+title+" "+text+" "+partyIP);
	}

	public void receivedOnClient(ICommunicator client) {
		Log.i("PartyHardyHost", "packet received on host");
		buildNotification(true);
	}
	public void receivedOnServer(ICommunicator host) {
		Log.i("PartyHardyHost", "packet received on host");
		buildNotification(true);
	}

	private void buildNotification(boolean isRemote) {
		Context context = HostActivity.instance;
		
		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(HostActivity.instance)
		.setSmallIcon(R.drawable.ic_launcher)
		.setContentTitle(title)
		.setContentText(text);

		Intent resultIntent = new Intent(context, ClientActivity.class);
		resultIntent.putExtra("PartyNotificationIP", partyIP);
		TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
		stackBuilder.addParentStack(ClientActivity.class);
		stackBuilder.addNextIntent(resultIntent);
		PendingIntent resultPendingIntent =	stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
		mBuilder.setContentIntent(resultPendingIntent);
		NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		mNotificationManager.notify(0, mBuilder.build());
	}

}
