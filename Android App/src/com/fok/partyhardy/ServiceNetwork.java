package com.fok.partyhardy;

import com.fok.partyhardy.host.HostTasker;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class ServiceNetwork extends Service {
	
	private HostTasker host;

	public ServiceNetwork() {
	}
	
	@Override
    public void onCreate() {
		host = new HostTasker(this);
		new Thread(host).start();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("LocalService", "Received start id " + startId + ": " + intent);
        Toast.makeText(this, "Connection Manager Started :D", Toast.LENGTH_LONG).show();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
    	host.disconnect();
        Toast.makeText(this, "Connection Manager Stopped D:", Toast.LENGTH_LONG).show();
    }
    
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

}
