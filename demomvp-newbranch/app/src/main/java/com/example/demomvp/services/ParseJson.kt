package com.example.demomvp.services

import com.example.demomvp.models.Food
import com.example.demomvp.utils.Constants
import org.json.JSONArray
import org.json.JSONObject

class ParseJson {
    fun parseJsonFood(jsonObject: JSONObject): Food {
        jsonObject.run {
            return Food(
                getInt(Constants.GET_ID),
                getInt(Constants.GET_CATEGORY),
                getInt(Constants.GET_SPECIALTIES),
                getString(Constants.GET_TITLE),
                getString(Constants.GET_THUMBNAIL),
                getString(Constants.GET_DESCRIPTION)
            )
        }
    }

    fun parseJsonArray(jsonObject: JSONObject): MutableList<Food> {
        val result: MutableList<Food> = mutableListOf()

        jsonObject.getJSONArray(Constants.GET_DATA).let { array ->
            for (it in 0 until array.length()) {
                result.add(parseJsonFood(array.getJSONObject(it)))
            }
        }

        return result
    }
}