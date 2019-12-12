package com.farouk.travelcar.global

import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar


fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun ProgressBar.show() {
    visibility = View.VISIBLE
}

fun ProgressBar.hide() {
    visibility = View.GONE
}

fun View.snackbar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_LONG).also { snackbar ->
        snackbar.setAction("Ok") {
            snackbar.dismiss()
        }
    }.show()
}
fun Uri.getPathString(context: Context): String {
    var path: String = ""

    context.contentResolver.query(
        this, arrayOf(MediaStore.Images.Media.DATA),
        null, null, null
    )?.apply {
        val columnIndex = getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        moveToFirst()
        path = getString(columnIndex)
        close()
    }

    return path
}
