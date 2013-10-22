package com.codepath.apps.mytwitterapp;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.codepath.apps.mytwitterapp.models.Tweet;
import com.codepath.apps.mytwitterapp.models.User;
import com.loopj.android.http.JsonHttpResponseHandler;

import eu.erikw.PullToRefreshListView;
import eu.erikw.PullToRefreshListView.OnRefreshListener;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class TimelineActivity extends Activity {

	String username;
	PullToRefreshListView lvTweets;
	TweetsAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_timeline);
		ArrayList<Tweet> tweets = new ArrayList<Tweet>();
		lvTweets = (PullToRefreshListView) findViewById(R.id.lvTweets);
		
		adapter = new TweetsAdapter(getBaseContext(), tweets);
		lvTweets.setAdapter(adapter);
		lvTweets.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to refresh the list contents
                // Make sure you call listView.onRefreshComplete()
                // once the loading is done. This can be done from here or any
                // place such as when the network request has completed successfully.
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
		passCredentials();
		
	}
	
    public void fetchTimelineAsync(int page) {
    	MyTwitterApp.getRestClient().getHomeTimeline(0, new JsonHttpResponseHandler() {
            public void onSuccess(JSONArray json) {
      
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
				adapter.addAll(Tweet.fromJson(jsonTweets));
				
			}
		});
		
	}
	
	private void passCredentials(){
		MyTwitterApp.getRestClient().getCredentials(new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(JSONObject jsonUser){
				User user = User.fromJson(jsonUser);
				String username = user.getScreenName();
				String userUrl=user.getProfileImageUrl();
				SharedPreferences pref =  PreferenceManager.getDefaultSharedPreferences(TimelineActivity.this);
				Editor edit = pref.edit();
				edit.putString("username", username);
				edit.putString("userImage", userUrl);
		
				edit.commit();
				setTitle("@"+username);
			   			
			}
		});
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.timeline, menu);
		return true;
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	
    	int id =item.getItemId();
    	
    	if(id==R.id.new_tweet){
    		Intent intent = new Intent(this, ComposeTweet.class);
    		intent.putExtra("username", "@"+username); 
    		startActivityForResult(intent, 1);
    	}
    	
    	return true;
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	if(requestCode==1 && resultCode==RESULT_OK){
    		
    		Tweet twVal=(Tweet) data.getExtras().getSerializable("tweet");
    		adapter.insert(twVal, 0);
    		       
    	}
    } 

}
