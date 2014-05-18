package com.sirolf2009.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class Packet {

	public static Map<Integer, Class<?>> packetsIDtoPacket = new HashMap<Integer, Class<?>>();
	public static Map<Class<?>, Integer> packetsPackettoID = new HashMap<Class<?>, Integer>();

	public Packet() {}
	
	public void send(PrintWriter out) {
		out.println(packetsPackettoID.get(this.getClass()));
		write(out);
		out.flush();
	}
	
	protected void write(PrintWriter out) {}
	public void receive(BufferedReader in) throws IOException {}
	
	public void receivedOnClient(ICommunicator client) {}
	public void receivedOnServer(ICommunicator host) {}
	
	public static void registerPacket(int ID, Class<?> packet) {
		packetsIDtoPacket.put(ID, packet);
		packetsPackettoID.put(packet, ID);
	}

}
