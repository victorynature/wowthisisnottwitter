AndroidTwitterAppClient
=======================
Build a simple Twitter client that supports viewing a Twitter timeline and composing a new tweet.

    

License

Apache Version 2.0

Acknowledgements

This project uses the Twitter API and RestClientTemplate.

It also uses many other open source libraries such as:

CodePath Rest-Client-Template
scribe-java
Android Async HTTP
codepath-oauth
UniversalImageLoader
pull-to-refresh 

User Stories:

1. User can sign in using OAuth login flow
2. User can view last 25 tweets from their home timeline
- User should be able to see the user, body and timestamp for tweet
3. User can compose a new tweet
- User can click a “Compose” icon in the Action Bar on the top right
- User will have a Compose view opened
- User can enter a message and hit a button to Post
- User should be taken back to home timeline with new tweet visible
4. User can load more tweets once they reach the bottom of the list
- Using "Load More" Button or "Lazy Endless" Scrolling
5. User can refresh timeline by pulling down (i.e pull-to-refresh)
