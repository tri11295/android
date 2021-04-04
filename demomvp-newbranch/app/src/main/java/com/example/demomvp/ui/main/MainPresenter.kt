package com.example.demomvp.ui.main

import android.os.Handler
import android.os.Looper
import com.example.demomvp.models.Food
import com.example.demomvp.services.ApiService
import com.example.demomvp.services.ParseJson
import com.example.demomvp.utils.Constants
import org.json.JSONObject

class MainPresenter : MainContract.Presenter {
    private var viewMain: MainContract.View? = null
    private var data: MutableList<Food> = mutableListOf()

    override fun getFoods() {
        val thread = Thread {
            with(ApiService().httpGet(Constants.BASE_URL + "food/all")) {
                val handler = Handler(Looper.getMainLooper())
                handler.post {
                    val jsonObject = JSONObject(this)
                    if (jsonObject.getInt(Constants.GET_STATUS) == 200) {
                        viewMain?.onSuccess(ParseJson().parseJsonArray(jsonObject))
                    } else {
                        viewMain?.onError(jsonObject.getString(Constants.GET_MESSAGE))
                    }
                }
            }
        }
        thread.start()
    }

    override fun onStart() = getFoods()

    override fun setView(view: MainContract.View?) {
        viewMain = view
    }
}
