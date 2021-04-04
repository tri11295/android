package com.ssv.cocktailproject

import com.example.demomvp.base.BasePresenter
import com.ssv.cooktailproject.models.Cocktail

interface MainContract {
    interface View {
        fun onSuccess(cocktails: MutableList<Cocktail>)
        fun onError(error: String)
        fun onLoading()
    }

    interface Presenter : BasePresenter<View> {
        fun getAllCocktails()
        fun loadMoreCocktails()
    }
}
