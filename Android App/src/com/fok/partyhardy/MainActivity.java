package com.fok.partyhardy;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.fok.partyhardy.client.ClientActivity;
import com.fok.partyhardy.packets.PacketHelloWorld;
import com.fok.partyhardy.packets.PacketPartyInvite;
import com.sirolf2009.networking.Packet;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
public class MainActivity extends Activity {

	private ServiceNetwork networkManager;
	private List<Friend> friends;
	private AppState appState;
	
	private FriendListArrayAdapter adapter;

	public static MainActivity instance;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		instance = this;
		setContentView(R.layout.fragment_home);
		setAppState(AppState.HOST);
		Log.i("PartyHardy", "\n--------------------\nNew Instance "+new SimpleDateFormat("hh:mm").format(new Date())+"\n--------------------");

		//TODO
		//Update ip adress in database

		setNetworkManager(new ServiceNetwork());
		startService(new Intent(this, ServiceNetwork.class));

		final Handler friendConnector=new Handler();
		friends = new ArrayList<Friend>();
		friends.add(new Friend("TEST FRIEND 1"));
		friends.add(new Friend("TEST FRIEND 2"));
		friends.add(new Friend("TEST FRIEND 3"));
		friends.add(new Friend("TEST FRIEND 4"));
		friends.add(new Friend("TEST FRIEND 5"));
		friends.add(new Friend("TEST FRIEND 6"));
		friends.add(new Friend("TEST FRIEND 7"));
		friends.add(new Friend("TEST FRIEND 8"));
		friends.add(new Friend("TEST FRIEND 9"));
		friends.add(new Friend("TEST FRIEND 10"));
		friendConnector.post(new Runnable(){
			@Override
			public void run() {
				//TODO get friends from storage
				for(Friend friend : friends) {
					friend.updateFriendIP();
					if(!friend.hasConnection()) {
						friend.connectToFriend();
					}
				}
				friendConnector.postDelayed(this,50000);
			}

		});
	}

	public void onHostPressed(View view) {
		appState = AppState.HOST;
		setContentView(R.layout.host_home);
	}

	public void onClientPressed(View view) {
		appState = AppState.CLIENT;
		startActivity(new Intent(this, ClientActivity.class));
	}

	public void createNewParty(View view) {
		setContentView(R.layout.host_create_party);

		List<String> friendList = new ArrayList<String>();
		for(Friend friend : friends) {
			friendList.add(friend.getName());
		}

		ListView list = (ListView) findViewById(R.id.friendList);
		adapter = new FriendListArrayAdapter(this, friendList);
		list.setAdapter(adapter);
		list.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				Toast.makeText(getApplicationContext(),
						"Click ListItem Number " + position, Toast.LENGTH_LONG).show();
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {}
		});
	}
	
	public void startHosting(View view) {
		Party party = new Party();
		String partyName = ((TextView)findViewById(R.id.partyName)).getText().toString();
		Log.i("PartyHardy", "Creating party called: "+partyName);
		for(int i = 0; i < adapter.invites.size(); i++) {
			if(adapter.invites.get(i)) {
				Log.i("PartyHardy", "invited: "+adapter.values.get(i));
			}
		}
	}

	public ServiceNetwork getNetworkManager() {
		return networkManager;
	}

	public void setNetworkManager(ServiceNetwork networkManager) {
		this.networkManager = networkManager;
	}

	public AppState getAppState() {
		return appState;
	}

	public void setAppState(AppState appState) {
		this.appState = appState;
	}

	private class FriendListArrayAdapter extends ArrayAdapter<String> {
		private final Context context;
		private final List<String> values;
		private final List<Boolean> invites;

		public FriendListArrayAdapter(Context context, List<String> values) {
			super(context, R.layout.host_invitelist_header, values);
			this.context = context;
			this.values = values;
			invites = new ArrayList<Boolean>(values.size());
			for(int i = 0; i < values.size(); i++) {
				invites.add(false);
			}
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View rowView = inflater.inflate(R.layout.host_invitelist_header, parent, false);
			TextView textView = (TextView) rowView.findViewById(R.id.lblListHeader);
			textView.setText(values.get(position));
			CheckBox checkBox = (CheckBox) rowView.findViewById(R.id.checkBox1);
			checkBox.setChecked(invites.get(position));
			checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					invites.set(position, isChecked);
				}
			});
			return rowView;
		}
	}

	static {
		Packet.registerPacket(0, PacketHelloWorld.class);
		Packet.registerPacket(1, PacketPartyInvite.class);
	}

	public enum AppState {
		HOME, HOST, CLIENT
	}
}
