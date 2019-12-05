package com.example.likedislike

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*
import java.util.prefs.Preferences

class MainActivity : AppCompatActivity() {
    //Module-level variables
    //var countLike: Int = 0

    lateinit var counterViewModel: CounterViewModel
    lateinit var sharePreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("MainActivity","OnCreate")

        //Initialise the counter view model
        counterViewModel = ViewModelProviders.of(this).get(CounterViewModel::class.java)

        //Initialise the share preferences
        //sharePreferences = getSharedPreferences("")
        sharePreferences = getPreferences(Context.MODE_PRIVATE)



        imageViewLike.setOnClickListener {
            counterViewModel.countLike++
            textViewLike.text = counterViewModel.countLike.toString()
        }

        imageViewDislike.setOnClickListener {
            counterViewModel.countDislike++
            textViewDislike.text = counterViewModel.countDislike.toString()
        }
    }

    override fun onStart() {
        Log.d("MainActivity","OnStart")
        super.onStart()
    }

    override fun onResume() {
        Log.d("MainActivity","OnResume")
        //Retrieve Sharer Preference value(S)
        counterViewModel.countLike = sharePreferences.getInt(getString(R.string.Like),0)
        counterViewModel.countDislike = sharePreferences.getInt(getString(R.string.Dislike),0)

        textViewLike.text = counterViewModel.countLike.toString()
        textViewDislike.text = counterViewModel.countDislike.toString()
        super.onResume()
    }

    override fun onPause() {
        Log.d("MainActivity","OnPause")
        with(sharePreferences.edit()){
            putInt(getString(R.string.Like),counterViewModel.countLike)
            putInt(getString(R.string.Dislike),counterViewModel.countDislike)
            commit()
        }
        super.onPause()
    }

    override fun onStop() {
        Log.d("MainActivity","OnStop")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d("MainActivity","OnDestroy")
        super.onDestroy()
    }
}
