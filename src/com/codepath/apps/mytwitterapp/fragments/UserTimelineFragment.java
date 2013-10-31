package com.codepath.apps.mytwitterapp.fragments;

import java.util.ArrayList;

import org.json.JSONArray;


import android.os.Bundle;


import com.codepath.apps.mytwitterapp.MyTwitterApp;

import com.codepath.apps.mytwitterapp.models.Tweet;

import com.loopj.android.http.JsonHttpResponseHandler;

public class UserTimelineFragment extends TweetsListFragment {
	String username;
	
	 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	
	public void loadUserTimeline(String screenName) {
       username = screenName;
		
		MyTwitterApp.getRestClient().getUserTimeline(username, new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(JSONArray jsonTweets){
			
				ArrayList<Tweet> userTweets = Tweet.fromJson(jsonTweets);
				getAdapter().addAll(userTweets);
				
			}
		});
	}

	
	
	
}
