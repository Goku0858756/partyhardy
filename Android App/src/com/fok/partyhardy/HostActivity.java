package com.fok.partyhardy;

import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class HostActivity extends Activity {
	
	private HostTasker tasker;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_host);
		tasker = new HostTasker();
		try {
			TextView text = (TextView) findViewById(R.id.textView1);
			text.setText(tasker.execute().get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void onDestroy() {
		tasker.disconnect();
	}

}
