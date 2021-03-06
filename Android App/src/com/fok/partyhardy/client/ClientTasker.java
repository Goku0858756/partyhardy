package com.fok.partyhardy.client;

import java.io.IOException;
import java.net.Socket;

import android.util.Log;
import com.fok.partyhardy.Friend;
import com.sirolf2009.networking.Connector;
import com.sirolf2009.networking.IClient;
import com.sirolf2009.networking.Receiver;
import com.sirolf2009.networking.Sender;

public class ClientTasker implements IClient, Runnable {

	private Sender sender;
	private Receiver receiver;
	private Socket socket;
	private Connector connector;
	private String ip;
	private Friend friend;

	public ClientTasker(String ip, Friend friend) {
		this.ip = ip;
		this.friend = friend;
	}

	@Override
	public void run() {
		try {
			connector = new Connector(this);
			Log.i("PartyHardyClient", "connecting client -> host");
			socket = new Socket(ip, 6000);
			Log.i("PartyHardyClient", "connected "+socket);
			new Thread(connector).start();
			Log.i("PartyHardyClient", "connector started");
			friend.setHasConnection(true);
		} catch (IOException e) {
			friend.setHasConnection(false);
		}
	}

	@Override
	public Object getSocket() {
		return socket;
	}

	@Override
	public boolean isRemote() {
		return false;
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
	public Sender getSender() {
		return sender;
	}

	@Override
	public void setSender(Sender sender) {
		this.sender = sender;
	}

	@Override
	public Receiver getReceiver() {
		return receiver;
	}

	@Override
	public void setReceiver(Receiver receiver) {
		this.receiver = receiver;
	}
}
