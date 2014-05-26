package com.sirolf2009.networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import android.util.Log;

public class Connector implements Runnable {

	private ICommunicator communicator;
	private boolean isAlive;

	public Connector(ICommunicator communicator) {
		this.communicator = communicator;
		isAlive = true;
	}

	@Override
	public synchronized void run() {
		if(isAlive) {
			if(communicator.isRemote()) {
				Log.i("PartyHardyHost", "listening for connections");
				while(true) {
					try {
						Client newClient = new Client(((ServerSocket)communicator.getSocket()).accept());
						Log.i("PartyHardyHost", newClient+" connecting");
						((IHost)communicator).onClientConnect(newClient);
					} catch (IOException e) {
						e.printStackTrace();
						Log.e("PartyHardyHost", e.getMessage());
						break;
					}
				}
			} else {
				IClient client = (IClient) communicator;
				client.setSender(new Sender((Socket) communicator.getSocket()));
				client.setReceiver(new Receiver(communicator));
				new Thread(client.getSender()).start();
				new Thread(client.getReceiver()).start();
			}
		}
	}

	public void disconnect() {
		isAlive = false;
	}

}
