package com.fok.partyhardy;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ClientActivity extends Activity {

	private ClientTasker task;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.client);
		task = new ClientTasker();
		task.execute();
		TextView text = (TextView) findViewById(R.id.textView1);
		if(text != null) {
			text.setText(task.getSocket()+"");
		}
	}
}
