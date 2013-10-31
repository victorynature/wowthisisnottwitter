package com.codepath.apps.mytwitterapp.fragments;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.codepath.apps.mytwitterapp.EndlessScrollListener;
import com.codepath.apps.mytwitterapp.MyTwitterApp;

import com.codepath.apps.mytwitterapp.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import eu.erikw.PullToRefreshListView.OnRefreshListener;
import android.os.Bundle;
import android.util.Log;

public class HomeTimelineFragment extends TweetsListFragment {
	
	 @Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		lvTweets.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
               
                fetchTimelineAsync(0);
            }
        });
		lvTweets.setOnScrollListener(new EndlessScrollListener() {
			 @Override
			 public void onLoadMore(int page, int totalItemsCount) {
		            
				 loadTimeline(page);
				 
			 }
		});
		adapter.clear();
		loadTimeline(0);
	}

	
    public void fetchTimelineAsync(int page) {
    	MyTwitterApp.getRestClient().getHomeTimeline(0, new JsonHttpResponseHandler() {
            public void onSuccess(JSONArray json) {
            	ArrayList<Tweet> tweets = Tweet.fromJson(json);
            	getAdapter().addAll(tweets);
            	lvTweets.onRefreshComplete();
            }

            public void onFailure(Throwable e) {
                Log.d("DEBUG", "Fetch timeline error: " + e.toString());
            }
        });
    }
	
	
	private void loadTimeline(int page) {
		MyTwitterApp.getRestClient().getHomeTimeline(page, new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(JSONArray jsonTweets){
				ArrayList<Tweet> tweets = Tweet.fromJson(jsonTweets);
            	getAdapter().addAll(tweets);
				adapter.addAll(Tweet.fromJson(jsonTweets));
				
			}
			
			public void onFailure(Throwable e, JSONObject error) {
			    Log.e("ERROR", e.toString());
			}
		});
		
	}
}
