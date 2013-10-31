package com.codepath.apps.mytwitterapp;




import android.content.Context;
import android.content.Intent;



import android.view.View;
import android.view.View.OnClickListener;


import com.codepath.apps.mytwitterapp.models.User;


public class ProfileImageOnClickListener implements OnClickListener {
        User user;
        Context context;
        TweetsAdapter adapter;
        
        
        public ProfileImageOnClickListener(Context c, User u) {
                user = u;
                context = c;
        }

        @Override
        public void onClick(View v) {
                // TODO Auto-generated method stub
        		String username=user.getScreenName();

                Intent i = new Intent(context, ProfileActivity.class);
                i.putExtra("username", username);
                context.startActivity(i);
                
                
                
                
        }
}
