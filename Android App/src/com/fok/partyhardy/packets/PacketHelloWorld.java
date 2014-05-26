package com.fok.partyhardy.packets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import android.annotation.TargetApi;
import android.os.Build;
import android.util.Log;

import com.sirolf2009.networking.ICommunicator;
import com.sirolf2009.networking.Packet;

@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
public class PacketHelloWorld extends Packet {

	private String message;
	
	/**
	 * Serialization only, do not use
	 */
	public PacketHelloWorld() {
	}
	
	public PacketHelloWorld(String message) {
		this.message = message;
	}
	
	protected void write(PrintWriter out) {
		Log.i("PartyHardyClient", "sending");
		out.println(message);
	}
	public void receive(BufferedReader in) throws IOException {
		Log.i("PartyHardyHost", "receiving");
		this.message = in.readLine();
	}
	
	public void receivedOnClient(ICommunicator client) {
		
	}
	public void receivedOnServer(ICommunicator host) {
		Log.i("PartyHardyHost", "packet received on host");
	}

}
