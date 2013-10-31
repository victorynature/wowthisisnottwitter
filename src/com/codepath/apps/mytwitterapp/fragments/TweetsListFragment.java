package com.codepath.apps.mytwitterapp.fragments;

import java.util.ArrayList;


import com.codepath.apps.mytwitterapp.R;

import com.codepath.apps.mytwitterapp.TweetsAdapter;
import com.codepath.apps.mytwitterapp.models.Tweet;


import eu.erikw.PullToRefreshListView;



import android.os.Bundle;

import android.support.v4.app.Fragment;

import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;


public class TweetsListFragment extends Fragment {
	TweetsAdapter adapter;
	PullToRefreshListView lvTweets;
	
	@Override
	public View onCreateView(LayoutInflater inf, ViewGroup parent, Bundle savedInstanceState) {
		return inf.inflate(R.layout.fragment_tweets_list, parent, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		ArrayList<Tweet> tweets = new ArrayList<Tweet>();
		lvTweets = (PullToRefreshListView) getActivity().findViewById(R.id.lvTweets);
		
		adapter = new TweetsAdapter(getActivity(), tweets);
		lvTweets.setAdapter(adapter);
	}
	
	public TweetsAdapter getAdapter(){
		return adapter;
	}
}
