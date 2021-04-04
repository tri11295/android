package com.example.demomvp

import android.app.ProgressDialog
import android.content.Context
import android.os.AsyncTask
import android.os.Handler
import android.widget.Toast
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class ConnectPresenter(private var connectInterFace: ConnectInterface) {

    var currentPage = 1
    val URL_STRING = "https://blogurl-3f73f.firebaseapp.com/"

    public fun connect(context: Context){
        GetRespondJSON(context).execute()
    }

    public fun loadMore(context: Context, listBattle : MutableList<Battle?>, battleAdapter: BattleAdapter, responseArray : JSONArray) {
        listBattle.add(null)
        battleAdapter.notifyItemInserted(listBattle.size - 1)

        val handler = Handler()
        handler.postDelayed({
            currentPage++
            Toast.makeText(context, "Load page $currentPage", Toast.LENGTH_SHORT).show()
            listBattle.removeAt(listBattle.size - 1)
            val scrollPosition: Int = listBattle.size
            battleAdapter.notifyItemRemoved(scrollPosition)

            var currentSize = scrollPosition
            val nextLimit = currentSize + 6
            while (currentSize < nextLimit && currentSize < responseArray.length()) {
                try {
                    val battleObj: JSONObject = responseArray.getJSONObject(currentSize)
                    val battle = Battle()
                    battle.name = battleObj.getString("name")
                    battle.location = battleObj.getString("location")
                    battle.attackerKing = battleObj.getString("attacker_king")
                    battle.defenderKing = battleObj.getString("defender_king")
                    listBattle.add(battle)
                    currentSize++
                } catch (e: JSONException) {
                    e.printStackTrace()
                    connectInterFace.connectError()
                }
            }
            connectInterFace.loadMoreSuccess(listBattle,currentSize)

        }, 2000)
    }

    private fun creatingURLConnection( urlApi : String?): String? {
        var response = ""
        val conn: HttpURLConnection
        val jsonResults = StringBuilder()
        try {
            //setting URL to connect with
            val url = URL(urlApi)

            //creating connection
            val conn = url.openConnection()

            //converting response into String
            val inputString = InputStreamReader(conn.inputStream)
            var read: Int
            val buff = CharArray(1024)
            while (inputString.read(buff).also { read = it } != -1) {
                jsonResults.append(buff, 0, read)
            }
            response = jsonResults.toString()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return response
    }

    inner class GetRespondJSON(context: Context) : AsyncTask<Void, Void?, String?>() {
        var progressDialog = ProgressDialog(context)
        override fun onPreExecute() {
            super.onPreExecute()
            progressDialog.setCancelable(false)
            progressDialog.setMessage("Please wait. Fetching data..")
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER)
            progressDialog.setProgress(0)
            progressDialog.show()
        }

        override fun doInBackground(vararg params: Void?): String? {
            return creatingURLConnection(URL_STRING)
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            progressDialog.dismiss()
            if (result != null && !result.equals("")){
                val responseArray = JSONArray(result)
                try {
                    if (responseArray.length() > 0) {
                        val listBattle : MutableList<Battle?> = mutableListOf()

                        for (i in 0..5) {
                            val battleObj: JSONObject = responseArray.getJSONObject(i)
                            val battle = Battle()
                            battle.name = battleObj.getString("name")
                            battle.location = battleObj.getString("location")
                            battle.attackerKing = battleObj.getString("attacker_king")
                            battle.defenderKing = battleObj.getString("defender_king")
                            listBattle.add(battle)
                        }
                        connectInterFace.connectSuccess(listBattle, responseArray)
                    }
                }catch (e: Exception){
                    connectInterFace.connectError()
                    e.printStackTrace()
                }
            }
        }
    }

}