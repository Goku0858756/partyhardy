package com.fok.partyhardy;

import java.io.IOException;
import java.net.Socket;

import com.sirolf2009.networking.Connector;
import com.sirolf2009.networking.IClient;
import com.sirolf2009.networking.Receiver;
import com.sirolf2009.networking.Sender;

import android.os.AsyncTask;

public class ClientTasker extends AsyncTask<String, Void, String> implements IClient {

	private Sender sender;
	private Receiver receiver;
	private Socket socket;
	private Connector connector;
	
	@Override
	protected String doInBackground(String... params) {
		try {
			connector = new Connector(this);
			socket = new Socket("145.24.200.53", 6000);
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
