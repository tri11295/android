package com.example.demomvp
import org.json.JSONArray

interface ConnectInterface {
    fun connectSuccess(listBattle: MutableList<Battle?>, jSonArray : JSONArray)
    fun connectError()
    fun loadMoreSuccess(newListBattle: MutableList<Battle?>, currentSize : Int)
    fun loadMoreError()
}