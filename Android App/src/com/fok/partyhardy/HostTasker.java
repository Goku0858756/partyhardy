package com.fok.partyhardy;

import java.io.IOException;
import java.net.ServerSocket;
import com.sirolf2009.networking.Client;
import com.sirolf2009.networking.Connector;
import com.sirolf2009.networking.IHost;

import android.util.Log;

public class HostTasker implements IHost, Runnable {


	private ServerSocket socket;
	private Connector connector;
	
	@Override
	public void run() {
		startHosting();
	}
	
	protected void startHosting() {
		try {
			Log.i("PartyHardyHost", "initializing");
			connector = new Connector(this);
			Log.i("PartyHardyHost", "starting hosting");
			socket = new ServerSocket(6000);
			Log.i("PartyHardyHost", "hosting on "+socket);
			new Thread(connector).start();
		} catch (IOException e) {
			e.printStackTrace();
			Log.e("PartyHardyHost", e.getMessage());
		}
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
		connector.disconnect();
	}
}
