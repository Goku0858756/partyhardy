package com.fok.partyhardy;

import java.io.IOException;
import java.net.ServerSocket;

import android.app.Activity;
import android.os.Bundle;
import com.sirolf2009.networking.Client;
import com.sirolf2009.networking.Connector;
import com.sirolf2009.networking.IHost;

public class HostActivity extends Activity implements IHost {

	private ServerSocket socket;
	private Connector connector;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_host);
		try {
			connector = new Connector(this);
			socket = new ServerSocket(6000);
			new Thread(connector).start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		/*ListView list = (ListView) findViewById(R.id.listView1);
		list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
				ListView list = (ListView) view;
				TextView label = new TextView(getApplicationContext());
				label.setText("HOLY SHAAAAT");
				list.addHeaderView(label);
			}
		});*/
	}
	
	@Override
	public void onDestroy() {
		disconnect();
	}

	@Override
	public void disconnect() {
		try {
			connector.disconnect();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onClientConnect(Client client) {
	}

	@Override
	public Object getSocket() {
		return socket;
	}

	@Override
	public boolean isRemote() {
		return true;
	}

	@Override
	public boolean isConnected() {
		return false;
	}

}
