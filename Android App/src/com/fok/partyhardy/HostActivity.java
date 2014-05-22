package com.fok.partyhardy;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;

import org.apache.http.conn.util.InetAddressUtils;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class HostActivity extends Activity {

	private HostTasker tasker;
	public static HostActivity instance;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		instance = this;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_host);

		Log.i("PartyHardyHost", "Execute on "+getIPAddress(true));
		tasker = new HostTasker();
		new Thread(tasker).start();
	}

	@Override
	public void onStop() {
		Log.i("PartyHardyHost", "Stopping");
		super.onStop();
		tasker.disconnect();
	}

	public static String getIPAddress(boolean useIPv4) {
		try {
			List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
			for (NetworkInterface intf : interfaces) {
				List<InetAddress> addrs = Collections.list(intf.getInetAddresses());
				for (InetAddress addr : addrs) {
					if (!addr.isLoopbackAddress()) {
						String sAddr = addr.getHostAddress().toUpperCase();
						boolean isIPv4 = InetAddressUtils.isIPv4Address(sAddr); 
						if (useIPv4) {
							if (isIPv4) 
								return sAddr;
						} else {
							if (!isIPv4) {
								int delim = sAddr.indexOf('%'); // drop ip6 port suffix
								return delim<0 ? sAddr : sAddr.substring(0, delim);
							}
						}
					}
				}
			}
		} catch (Exception ex) { } // for now eat exceptions
		return "";
	}

}
