package com.fok.partyhardy;

import java.io.IOException;
import java.net.ServerSocket;

import com.sirolf2009.networking.Client;
import com.sirolf2009.networking.Connector;
import com.sirolf2009.networking.ICommunicator;
import com.sirolf2009.networking.IHost;

import android.os.AsyncTask;

public class HostTasker extends AsyncTask<String, Void, String> implements IHost {

	private ServerSocket socket;
	private Connector connector;
	
	@Override
	protected String doInBackground(String... params) {
		try {
			connector = new Connector(this);
			socket = new ServerSocket(6000);
			new Thread(connector).start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return socket.toString();
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

}
