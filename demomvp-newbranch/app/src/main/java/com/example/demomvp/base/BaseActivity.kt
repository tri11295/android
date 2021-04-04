package com.example.demomvp.base

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        onInit()
        onEvent()
    }

    abstract fun getLayoutId(): Int

    abstract fun onInit()

    abstract fun onEvent()

    private fun showAlertDialog(msg: String) {
        AlertDialog.Builder(this)
            .setMessage(msg)
            .setPositiveButton(android.R.string.ok, null)
            .show()
    }

    private fun showAlertDialog(@StringRes resId: Int) {
        showAlertDialog(getString(resId))
    }

    private fun showMessageOptionDialog(
        message: String,
        listener: DialogInterface.OnClickListener?
    ) {
        AlertDialog.Builder(this)
            .setMessage(message)
            .setCancelable(false)
            .setPositiveButton(android.R.string.yes) { dialog, id ->
                listener?.onClick(dialog, id)
            }
            .setNegativeButton(
                android.R.string.no
            ) { dialog, id -> dialog.cancel() }.show()
    }

    private fun showMessage(title: String?, message: String) {
        if (isFinishing) {
            return
        }
        showDialogMessage(title!!, message)
    }

    private fun showDialogMessage(title: String, message: String) {
        if (!isFinishing) {
            AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton(
                    android.R.string.ok
                ) { dialog, which -> dialog.cancel() }.show()
        }
    }
}
