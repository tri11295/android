package com.example.demomvp
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray

class MainActivity : AppCompatActivity(),ConnectInterface {

    var isLoading = false
    var listBattle = mutableListOf<Battle?>()
    var responseArray = JSONArray()
    var battleAdapter = BattleAdapter(listBattle)
    var connectPresenter = ConnectPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnGetConect.setOnClickListener(View.OnClickListener {
            getConnection()

            initScrollListener()
        })
    }

    private fun getConnection(){
        btnGetConect.visibility = View.GONE
        connectPresenter.connect(this)
    }

    private fun initScrollListener() {

        rcvBattle.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?
                if (!isLoading) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == listBattle.size - 1) {
                        connectPresenter.loadMore(this@MainActivity, listBattle, battleAdapter, responseArray)
                        isLoading = true
                    }
                }
            }
        })
    }

    override fun connectSuccess(listBattle: MutableList<Battle?> , jSonArray : JSONArray) {
        this.responseArray = jSonArray
        this.listBattle = listBattle
        this.battleAdapter.setData(listBattle)
        val linearLayoutManager = LinearLayoutManager(this)
        rcvBattle.layoutManager = linearLayoutManager
        rcvBattle.adapter = battleAdapter
    }

    override fun connectError() {
        Toast.makeText(this,"Connect Error",Toast.LENGTH_SHORT).show()
    }

    override fun loadMoreSuccess(newListBattle: MutableList<Battle?>, currentSize : Int) {
        this.battleAdapter.setData(newListBattle)
        battleAdapter.notifyDataSetChanged()
        if (currentSize < responseArray.length()) {
            isLoading = false
        }
    }

    override fun loadMoreError() {
        Toast.makeText(this,"Load More Error",Toast.LENGTH_LONG).show()
    }
}

