package com.fok.partyhardy;

import android.util.Log;

import com.fok.partyhardy.client.ClientTasker;

public class Friend {
	
	private String name;
	private String ip;
	private ClientTasker connection;
	private boolean hasConnection;

	public Friend(String name) {
		this.name = name;
	}
	
	public void updateFriendIP() {
		//TODO
		//Requires database
		ip="localhost";
	}
	
	public void connectToFriend() {
		Log.i("PartyHardy", "connecting to "+name+" connection: "+hasConnection);
		connection = new ClientTasker(ip, this);
		new Thread(connection).start();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean hasConnection() {
		return hasConnection;
	}

	public void setHasConnection(boolean hasConnection) {
		this.hasConnection = hasConnection;
	}

}
