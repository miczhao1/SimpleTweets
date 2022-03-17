package com.codepath.apps.restclienttemplate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.codepath.apps.restclienttemplate.models.Tweet
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers

class ComposeActivity : AppCompatActivity() { // hook up these views after setting up activity_compose.xml layout file

    // create views
    lateinit var etCompose: EditText
    lateinit var btnTweet: Button
    lateinit var charCount: TextView
    lateinit var client: TwitterClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compose)

        etCompose = findViewById(R.id.etTweetCompose)
        btnTweet = findViewById(R.id.btnTweet)

        client = TwitterApplication.getRestClient(this) // initializing

        // when user clicks on button:
        btnTweet.setOnClickListener {
            // grab content of eTCompose (what did the user type?)
            val tweetContent = etCompose.text.toString() // hold content of edittext field

            // make sure tweet isn't empty and tweet is under character count
            if (tweetContent.isEmpty()) {
                Toast.makeText(this, "Empty tweets not allowed!", Toast.LENGTH_SHORT).show() // can also display Snackbar message
            } else if (tweetContent.length > 280) {
                Toast.makeText(this, "Tweet is too long! The limit is 280 characters", Toast.LENGTH_SHORT).show()
            } else {

                // make api call to Twitter to publish tweet
                client.publishTweet(tweetContent, object : JsonHttpResponseHandler() {
                    override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {
                        // Send tweet back to TimelineActivity to display it!
                        Log.i(TAG, "Successfully published tweet!")
                        // close activity, go back to TimelineActivity, and see the published tweet

                        val tweet = Tweet.fromJson(json.jsonObject)
                        // pass tweet back to TimelineActivity
                        val intent = Intent()
                        intent.putExtra("tweet", tweet) // put stuff in intent
                        setResult(RESULT_OK, intent) // success
                        finish() // close and end up back in TimelineActivity to handle this info
                    }

                    override fun onFailure(
                        statusCode: Int,
                        headers: Headers?,
                        response: String?,
                        throwable: Throwable?
                    ) {
                        Log.e(TAG, "Failed to publish tweet", throwable)
                    }

                })
            }

        }

    }

    companion object {
        val TAG = "ComposeActivity"
    }
}