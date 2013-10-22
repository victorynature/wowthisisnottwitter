package com.codepath.apps.mytwitterapp.models;

import java.io.Serializable;

import org.json.JSONObject;

public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5350079628928635290L;
	private String name;
	private Long id;
	private String screen_name;
	private String profile_image_url;
	private String profile_background_image_url;
	private int statuses_count;
	private int followers_count;
	private int friends_count;
	
    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public String getScreenName() {
        return screen_name;
    }

    public String getProfileImageUrl(){
    	return profile_image_url;
    }
    public String getProfileBackgroundImageUrl() {
        return profile_background_image_url;
    }

    public int getNumTweets() {
        return statuses_count;
    }

    public int getFollowersCount() {
        return followers_count;
    }

    public int getFriendsCount() {
        return friends_count;
    }

    public static User fromJson(JSONObject json) {
        User u = new User();

        try {
            u.name = json.getString("name");
            u.id=json.getLong("id");
            u.screen_name=json.getString("screen_name");
            u.profile_image_url=json.getString("profile_image_url");
            u.profile_background_image_url=json.getString("profile_background_image_url");
        	u.statuses_count=json.getInt("statuses_count");
        	u.followers_count=json.getInt("followers_count");
        	u.friends_count=json.getInt("friends_count");
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return u;
    }

}