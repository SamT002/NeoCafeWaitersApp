package com.example.neocafewaiterapplication.tools

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.squareup.picasso.Picasso

fun View.visible(){
    this.visibility = View.VISIBLE
}

fun View.gone(){
    this.visibility = View.GONE
}

fun String.showToast(context:Context, duration:Int){
    Toast.makeText(context, this, duration).show()
}

fun String.logging(){
    Log.i("TAG", this)
}

fun ImageView.loadWithPicassoUri(context: Context, uri:String){
    Picasso.with(context)
        .load(uri)
        .into(this)
}


fun ImageView.loadWithPicassoResource(context: Context, uri:Int){
    Picasso.with(context)
        .load(uri)
        .into(this)

}