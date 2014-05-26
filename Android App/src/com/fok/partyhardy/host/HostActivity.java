package com.fok.partyhardy.host;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import org.apache.http.conn.util.InetAddressUtils;
import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.fok.partyhardy.R;
import com.fok.partyhardy.client.ClientTasker;

@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
public class HostActivity extends Activity {

	public static HostActivity instance;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		instance = this;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.host_home);
	}

	@Override
	public void onStop() {
		Log.i("PartyHardyHost", "Stopping");
		super.onStop();
	}

	public void startHosting(View view) {/*
		Log.i("PartyHardyHost", "Creating Temp Local Client");
		new Thread(new ClientTasker(getIPAddress(true))).start();*/
	}

	public static String getIPAddress(boolean useIPv4) {
		try {
			List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
			for (NetworkInterface intf : interfaces) {
				List<InetAddress> addrs = Collections.list(intf.getInetAddresses());
				for (InetAddress addr : addrs) {
					if (!addr.isLoopbackAddress()) {
						String sAddr = addr.getHostAddress().toUpperCase(Locale.ENGLISH);
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
