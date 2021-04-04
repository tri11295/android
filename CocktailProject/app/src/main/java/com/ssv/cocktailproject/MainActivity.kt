package com.ssv.cocktailproject

import android.os.Handler
import android.os.Looper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.demomvp.base.BaseActivity
import com.example.demomvp.extensions.toGone
import com.example.demomvp.extensions.toVisible
import com.example.demomvp.utils.OnLoadMoreListener
import com.example.demomvp.utils.RecyclerViewLoadMoreScroll
import com.ssv.cooktailproject.models.Cocktail
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.concurrent.schedule

class MainActivity : BaseActivity(), MainContract.View {

    lateinit var mainPresenter: MainPresenter
    lateinit var mainAdapter: MainAdapter
    lateinit var scrollListener: RecyclerViewLoadMoreScroll
    var listAllCocktails : MutableList<Cocktail> = mutableListOf()
    var listFirstCocktails : MutableList<Cocktail> = mutableListOf()

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun onInit() {
        mainAdapter = MainAdapter(this,listFirstCocktails)
        val linearLayoutManager = LinearLayoutManager(this)
        scrollListener = RecyclerViewLoadMoreScroll(linearLayoutManager)
        rcvCocktails.apply {
            layoutManager = linearLayoutManager
            adapter = mainAdapter
        }
        initData()
    }

    private fun initData(){
        mainPresenter = MainPresenter().apply {
            setView(this@MainActivity)
            onStart()
        }
    }

    override fun onEvent() {
        swipeRefreshLayout.setOnRefreshListener {
            mainPresenter.getAllCocktails()
            Timer("refresh", false).schedule(2000) {
                swipeRefreshLayout.isRefreshing = false
            }
        }

    }

    override fun onSuccess(cocktails: MutableList<Cocktail>) {
        progressBarLoad.toGone()
        tvError.toGone()
        listAllCocktails.addAll(cocktails)
        for (i in 0..9){
            listFirstCocktails.add(listAllCocktails[i])
        }
        mainAdapter.notifyDataSetChanged()
    }

    override fun onError(error: String) {
        progressBarLoad.toGone()
        swipeRefreshLayout.toGone()
        tvError.toVisible()
        tvError.setText(error)
    }

    override fun onLoading() {
        progressBarLoad.toVisible()
        tvError.toGone()
        swipeRefreshLayout.toGone()
    }

}