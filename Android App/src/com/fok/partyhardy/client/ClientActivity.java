package com.fok.partyhardy.client;

import com.fok.partyhardy.R;
import com.fok.partyhardy.packets.PacketHelloWorld;
import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
public class ClientActivity extends Activity {

	private ClientTasker task;
	public static ClientActivity instance;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		instance = this;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.client);
		
		Bundle args = getIntent().getExtras();
		if(args != null && !args.getString("PartyNotificationIP").isEmpty()) {
			Toast.makeText(this, "Party at "+args.getString("PartyNotificationIP"), Toast.LENGTH_LONG).show();
		}
	}
	
	/*
	 * 145.24.243.220
	 * 
	 */
	public void onConnectPressed(View view) {
		/*TextView ip = (TextView) findViewById(R.id.ipAdress);
		task = new ClientTasker(ip.getText().toString());
		new Thread(task).start();*/
	}
	
	public void sendTestPacket(View view) {
		task.getSender().send(new PacketHelloWorld("Hello World!"));
	}
}
