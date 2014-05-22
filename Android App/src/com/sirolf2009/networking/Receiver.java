package com.sirolf2009.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;

import android.util.Log;

public class Receiver implements Runnable {

	BufferedReader in;
	ICommunicator communicator;

	public Receiver(ICommunicator communicator) {
		try {
			this.communicator = communicator;
			in = new BufferedReader(new InputStreamReader(((Socket)communicator.getSocket()).getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Receiver(Client client) {
		try {
			communicator = client;
			in = new BufferedReader(new InputStreamReader(client.socket.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while(true) {
			try {
				String packetIDString = in.readLine();
				int packetID = Integer.parseInt(packetIDString);
				if(Packet.packetsIDtoPacket.get(packetID) == null) {
					System.err.println("Unknown packet ID: "+packetID);
				} else {
					Packet packet = (Packet) Packet.packetsIDtoPacket.get(packetID).newInstance();
					packet.receive(in);
					Log.d("PartyHardy", communicator+" "+communicator.isRemote());
					if(!communicator.isRemote()) {
						packet.receivedOnClient(communicator);
					} else {
						packet.receivedOnServer(communicator);
					}
				}
				Thread.sleep(20);
			} catch (SocketException e) {
				System.err.println("Connection has been lost.");
				e.printStackTrace();
				if(!communicator.isRemote()) {
					System.exit(0);
				}
				break;
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}

}
