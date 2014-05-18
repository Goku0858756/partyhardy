package com.sirolf2009.networking;

import java.net.Socket;

public class Client implements IClient {
	
	public Socket socket;
	public Sender sender;
	public Receiver receiver;
	public boolean isConnected;

	public Client(Socket socket) {
		this.socket = socket;
		sender = new Sender(socket);
		receiver = new Receiver(this);
		new Thread(sender, socket.toString() + " sender").start();
		new Thread(receiver, socket.toString() + " receiver").start();
	}

	@Override
	public Sender getSender() {
		return sender;
	}

	@Override
	public Receiver getReceiver() {
		return receiver;
	}

	@Override
	public Socket getSocket() {
		return socket;
	}

	@Override
	public boolean isRemote() {
		return true;
	}

	@Override
	public boolean isConnected() {
		return isConnected;
	}

	@Override
	public void disconnect() {
		isConnected = false;
	}

	@Override
	public void setSender(Sender sender) {
		this.sender = sender;
	}

	@Override
	public void setReceiver(Receiver receiver) {
		this.receiver = receiver;
	}

}
