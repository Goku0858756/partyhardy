package com.fok.partyhardy.host;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

import com.fok.partyhardy.ServiceNetwork;
import com.sirolf2009.networking.Client;
import com.sirolf2009.networking.Connector;
import com.sirolf2009.networking.IHost;

import android.util.Log;
import android.widget.Toast;

public class HostTasker implements IHost, Runnable {

	private ServerSocket socket;
	private Connector connector;
	
	private List<Client> clients;
	private ServiceNetwork networkManager;
	
	public HostTasker(ServiceNetwork networkManager) {
		this.networkManager = networkManager;
		setClients(new ArrayList<Client>());
	}
	
	@Override
	public void run() {
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
		getClients().add(client);
		//Toast.makeText(HostActivity.instance, "Client connected "+client.socket.getLocalAddress(), Toast.LENGTH_LONG).show();
	}

	public List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}

	public List<String> getClientsAsString() {
		List<String> stringClients = new ArrayList<String>();
		for(Client client : clients) {
			stringClients.add(client.toString());
		}
		return stringClients;
	}
}
