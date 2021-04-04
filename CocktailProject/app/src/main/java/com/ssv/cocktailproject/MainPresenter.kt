package com.ssv.cocktailproject

import android.os.Handler
import android.os.Looper
import com.example.demomvp.services.ApiService
import com.example.demomvp.services.ParseJson
import com.example.demomvp.utils.Constants
import com.ssv.cooktailproject.models.Cocktail
import org.json.JSONObject

class MainPresenter : MainContract.Presenter {
    private var viewMain: MainContract.View? = null
    private var data: MutableList<Cocktail> = mutableListOf()

    override fun getAllCocktails() {
        val thread = Thread {
            with(ApiService().httpGet(Constants.BASE_URL )) {
                val handler = Handler(Looper.getMainLooper())
                handler.post {
                    val jsonObject = JSONObject(this)
                    if (jsonObject != null){
                        viewMain?.onSuccess(ParseJson().parseJsonArray(jsonObject))
                    }
                    else{
                        viewMain?.onError("Error Load All Cocktail Data")
                    }
                }
            }
        }
        thread.start()
    }

    override fun loadMoreCocktails() {
        TODO("Not yet implemented")
    }

    override fun onStart() = getAllCocktails()

    override fun setView(view: MainContract.View?) {
        viewMain = view
    }
}
