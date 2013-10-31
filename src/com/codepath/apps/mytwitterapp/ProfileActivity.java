package com.codepath.apps.mytwitterapp;


import org.json.JSONObject;


import com.codepath.apps.mytwitterapp.fragments.UserTimelineFragment;

import com.codepath.apps.mytwitterapp.models.User;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.os.Bundle;

import android.content.SharedPreferences;

import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;



public class ProfileActivity extends FragmentActivity {
	String username;
	UserTimelineFragment fragmentTweets;
	SharedPreferences prefs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		fragmentTweets = (UserTimelineFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentUserTimeline);
		
		username = getIntent().getStringExtra("username");
		
		
		fragmentTweets.loadUserTimeline(username);
		loadProfileInfo(username);
	}



	private void loadProfileInfo(String username) {
		
			MyTwitterApp.getRestClient().getUserInfo(username, new JsonHttpResponseHandler(){
				@Override
				public void onSuccess(JSONObject json){
					User user = User.fromJson(json);
					getActionBar().setTitle("@"+user.getScreenName());
					//updateUserInfo();
					populateProfileHeader(user);
				   			
				}
				
				public void onFailure(Throwable e, JSONObject error) {
				    Log.e("ERROR", e.toString());
				}
			});
	
		
	}
	
	
	private void populateProfileHeader(User user) {
		TextView tvName = (TextView) findViewById(R.id.tvName);
		TextView tvTagline = (TextView) findViewById(R.id.tvTagline);
		TextView tvFollowers = (TextView) findViewById(R.id.tvFollowers);
		TextView tvFollowing = (TextView) findViewById(R.id.tvFollowing);
		ImageView ivProfileImage = (ImageView) findViewById(R.id.ivProfileImage);
		tvName.setText(user.getName());
		tvTagline.setText(user.getTagline());
		tvFollowers.setText(user.getFollowersCount() +" Followers");
		tvFollowing.setText(user.getFriendsCount() + " Following");
		ImageLoader.getInstance().displayImage(user.getProfileImageUrl(), ivProfileImage);	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.profile, menu);
		return true;
	}

}
