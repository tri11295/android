package com.ssv.practicekotlin

import android.app.ActionBar
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv_name.setText("tri")

        btnDoub.setOnClickListener(View.OnClickListener {
            tv_name.setTextOrHide(null)
        })
    }

    fun TextView.setTextOrHide(s: String?) {
        return if (s.isNullOrEmpty() || s.isNullOrBlank()) {
            this.visibility = View.GONE
        } else {
            this.text = s
        }
    }
}