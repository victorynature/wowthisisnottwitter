package com.codepath.apps.mytwitterapp;


import org.json.JSONObject;

import com.codepath.apps.mytwitterapp.fragments.HomeTimelineFragment;
import com.codepath.apps.mytwitterapp.fragments.MentionsFragment;
import com.codepath.apps.mytwitterapp.fragments.TweetsListFragment;
import com.codepath.apps.mytwitterapp.models.Tweet;
import com.codepath.apps.mytwitterapp.models.User;
import com.loopj.android.http.JsonHttpResponseHandler;


import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class TimelineActivity extends FragmentActivity implements TabListener{

	String username;
	TweetsListFragment fragmentTweets;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_timeline);
		setupNavigationTabs();
		passCredentials();
	}
	
	private void setupNavigationTabs() {
		ActionBar actionBar=getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		actionBar.setDisplayShowTitleEnabled(true);
		
		Tab tabHome=actionBar.newTab().setText("Home").
				setTag("HomeTimelineFragment").
				setIcon(R.drawable.ic_home).setTabListener(this);
		Tab tabMentions=actionBar.newTab().setText("Mentions").
				setTag("MentionsTimelineFragment").
				setIcon(R.drawable.ic_mention).setTabListener(this);
		
		actionBar.addTab(tabHome);
		actionBar.addTab(tabMentions);
		actionBar.selectTab(tabHome);
		
	}

	private void passCredentials(){
		MyTwitterApp.getRestClient().getCredentials(new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(JSONObject jsonUser){
				User user = User.fromJson(jsonUser);
				username = user.getScreenName();
				String userUrl=user.getProfileImageUrl();
				SharedPreferences pref =  PreferenceManager.getDefaultSharedPreferences(TimelineActivity.this);
				Editor edit = pref.edit();
				edit.putString("username", username);
				edit.putString("userImage", userUrl);
		
				edit.commit();
				setTitle("@"+username);
			   			
			}
			public void onFailure(Throwable e, JSONObject error) {
			    Log.e("ERROR", e.toString());
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
    	} else if(id==R.id.action_profile){
    		Intent intent = new Intent(this, ProfileActivity.class);
    		intent.putExtra("username", username);
    		startActivityForResult(intent, 1);
    	}
       
    	return true;
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	if(requestCode==1 && resultCode==RESULT_OK){
    		
    		Tweet twVal=(Tweet) data.getExtras().getSerializable("tweet");
    		getActionBar().setSelectedNavigationItem(0);
    		fragmentTweets.getAdapter().insert(twVal, 0);
    		       
    	}
    }

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		FragmentManager manager = getSupportFragmentManager();
		android.support.v4.app.FragmentTransaction fts = manager.beginTransaction();
		
		if(tab.getTag()=="HomeTimelineFragment"){
			fragmentTweets = new HomeTimelineFragment();
			fts.replace(R.id.frame_container, fragmentTweets);
		}else{
			fragmentTweets = new MentionsFragment();
			fts.replace(R.id.frame_container, fragmentTweets);
		}
		fts.commit();
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	} 
	
	public void onProfileView(MenuItem mi){
		Intent intent = new Intent(this, ProfileActivity.class);
		startActivity(intent);
	}

}
