package com.fok.partyhardy;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ClientActivity extends Activity {

	private ClientTasker task;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.client);
	}
	
	/*
	 * 145.24.243.220
	 * 
	 */
	public void onConnectPressed(View view) {
		TextView ip = (TextView) findViewById(R.id.ipAdress);
		task = new ClientTasker(ip.getText().toString());
		new Thread(task).start();
	}
	
	public void sendTestPacket(View view) {
		task.getSender().send(new PacketHelloWorld("Hello World!"));
	}
}
