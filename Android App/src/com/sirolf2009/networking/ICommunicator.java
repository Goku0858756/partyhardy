package com.sirolf2009.networking;

public interface ICommunicator {
	
	public Object getSocket();
	public boolean isRemote();
	public boolean isConnected();
	public void disconnect();

}
