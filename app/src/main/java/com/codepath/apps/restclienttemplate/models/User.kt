package com.codepath.apps.restclienttemplate.models

import org.json.JSONObject

class User {
    var name: String = "" // name of user
    var screenName: String = ""
    var publicImageUrl: String = ""

    companion object {
        fun fromJson(jsonObject: JSONObject): User { // method parses JSON string and converts to User object
            val user = User()
            user.name = jsonObject.getString("name") // taken straight from the json file key!
            user.screenName = jsonObject.getString("screen_name")
            user.publicImageUrl = jsonObject.getString("profile_image_url_https")

            return user
        }
    }
}