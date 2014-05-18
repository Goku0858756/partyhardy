package com.sirolf2009.networking;

public interface IClient extends ICommunicator {

	public Sender getSender();
	public void setSender(Sender sender);
	public Receiver getReceiver();
	public void setReceiver(Receiver receiver);

}
