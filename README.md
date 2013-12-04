# Wow it is not Twitter!

Twitter client app, featuring OAuth authentication, timeline, and the major features of the standard Twitter app, including posting, viewing other users' timelines, and browsing timeline with infinite scroll.

![My image1](/img/twitter_home.png)
![My image2](/img/twitter_mentions.png)
![My image3](/img/twitter_profile.png)
![My image4](/img/twitter_user_timeline.png)
![My image5](/img/twitter_tweet.png)


## License

Apache Version 2.0


## Acknowledgements

This project uses the Twitter API and RestClientTemplate.

It also uses many other open source libraries such as:
* CodePath Rest-Client-Template
* scribe-java
* Android Async HTTP
* codepath-oauth
* UniversalImageLoader
* pull-to-refresh 

## Building

The build requires [Gradle](http://www.gradleware.com/)
v1.6 and the [Android SDK](http://developer.android.com/sdk/index.html)
to be installed in your development environment. In addition you'll need to set
the `ANDROID_HOME` environment variable to the location of your SDK:

    export ANDROID_HOME=/opt/tools/android-sdk

After satisfying those requirements, the build is pretty simple:

* Run `gradle assemble` from the root directory to build the APK only
* Run `gradle build` from the root directory to build the app and also run
  the integration tests, this requires a connected Android device or running
  emulator.

## User Stories

* User can sign in using OAuth login flow
* User can view last 25 tweets from their home timeline
* User should be able to see the user, body and timestamp for tweet
* User can compose a new tweet
* User can click a “Compose” icon in the Action Bar on the top right
* User can enter a message and hit a button to Post
* User should be taken back to home timeline with new tweet visible
* User can load more tweets once they reach the bottom of the list
* User can switch between Timeline and Mention views using tabs.
* User can view their home timeline tweets.
* User can view the recent mentions of their username.
* User can scroll to bottom of either of these lists and new tweets will load ("infinite scroll")
* User can click icon on Action Bar to view their profile
* User can see picture, tagline, # of followers, # of following, and tweets on their profile.
* User can click on the profile image in any tweet to see that user's profile.
* User can see picture, tagline, # of followers, # of following, and tweets of clicked user.
* Profile should include that user's timeline
