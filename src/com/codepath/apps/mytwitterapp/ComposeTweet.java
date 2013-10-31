package com.codepath.apps.mytwitterapp;


import org.json.JSONObject;

import com.codepath.apps.mytwitterapp.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


public class ComposeTweet extends Activity {
	EditText etPost;
	Button btnTweet;
	TextView tvUser;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_compose_tweet);
		etPost=(EditText)findViewById(R.id.etPost);
		btnTweet = (Button)findViewById(R.id.btnTweet);
		
		SharedPreferences pref =   
			    PreferenceManager.getDefaultSharedPreferences(this);
		String username = pref.getString("username", "blank");
		
		String userUrl=pref.getString("userImage", "blank");
		
		
		tvUser = (TextView) findViewById(R.id.tvUser);
		tvUser.setText("@"+username);
		
		ImageView imageView = (ImageView) findViewById(R.id.ivProfileImg);
		ImageLoader.getInstance().displayImage(userUrl, imageView);
	}
	
	public void onTweet(View v){
		String post = etPost.getText().toString();
		
		MyTwitterApp.getRestClient().updateTweet(post, new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(JSONObject jsonTweet){
				Tweet tweet = Tweet.fromJson(jsonTweet);
				
				Intent resultIntent = new Intent();
				resultIntent.putExtra("tweet",  tweet);
				setResult(RESULT_OK, resultIntent);
				finish();
				
			}
		});
		
	}

	public void onCancel(View v){
		this.finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.compose_tweet, menu);
		return true;
	}

}
