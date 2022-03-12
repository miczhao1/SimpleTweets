package com.codepath.apps.restclienttemplate

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codepath.apps.restclienttemplate.models.Tweet


class TweetsAdapter(val tweets: ArrayList<Tweet>) : RecyclerView.Adapter<TweetsAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // item_tweet.xml, reference each view
        val ivProfileImage = itemView.findViewById<ImageView>(R.id.ivProfileImage)
        val tvUsername = itemView.findViewById<TextView>(R.id.tvUsername)
        val tvTweetBody = itemView.findViewById<TextView>(R.id.tvTweetBody)
        val tvTime = itemView.findViewById<TextView>(R.id.tvTime)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TweetsAdapter.ViewHolder {
        // nflating layout that we want to use for each of our items in RecyclerView
        val context = parent.context
        val inflater = LayoutInflater.from(context)


        // Inflate our item layout
        val view = inflater.inflate(R.layout.item_tweet, parent, false)

        return ViewHolder(view)
    }

    // Populating data into the item thorugh ViewHolder
    override fun onBindViewHolder(holder: TweetsAdapter.ViewHolder, position: Int) {
        // Get the data model based on the position
        val tweet: Tweet = tweets.get(position)
        // Set item views based on views and data model
        holder.tvUsername.text = tweet.user?.name // ? - if null
        holder.tvTweetBody.text = tweet.body
        holder.tvTime.text = tweet.createdAt

        Glide.with(holder.itemView).load(tweet.user?.publicImageUrl).into(holder.ivProfileImage) // Glide makes image loading easy!

    }

    override fun getItemCount(): Int { // how many Views are going to be in our RecyclerView
        return tweets.size
    } // Turns all Tweet objects to something RecyclerView can actually display


    // Clean all elements of the recycler
    fun clear() {
        tweets.clear()
        notifyDataSetChanged()
    }

    // Add a list of items -- change to type used
    fun addAll(tweetList: List<Tweet>) {
        tweets.addAll(tweetList)
        notifyDataSetChanged()
    }
}