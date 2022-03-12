package com.codepath.apps.restclienttemplate.models

import org.json.JSONArray
import org.json.JSONObject

class Tweet { // display what the tweet actually says (tweet body)

    var body: String = ""
    var createdAt: String = ""
    var user: User? = null

    companion object { // turns one specific JSON object into a tweet
        // one json object turns into tweet object
        fun fromJson(jsonObject: JSONObject) : Tweet {
            val tweet = Tweet() // lets create a new tweet!
            tweet.body = jsonObject.getString("text") // key for body
            tweet.createdAt = jsonObject.getString("created_at")
            tweet.user = User.fromJson(jsonObject.getJSONObject("user")) // use fromJson method
            return tweet
        }

        // converts a whole json array full of a bunch of recent tweets (we chose 25 tweets!)
        fun fromJsonArray(jsonArray: JSONArray): List <Tweet> {
            val tweets = ArrayList<Tweet>()
            for (i in 0 until jsonArray.length()) { // .. include
                tweets.add(fromJson(jsonArray.getJSONObject(i)))
            }
            return tweets
        }
    }
}