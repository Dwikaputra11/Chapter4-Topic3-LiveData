package com.example.chapter4_topic3_livedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.chapter4_topic3_livedata.fragments.HomeFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(R.id.frContainer, HomeFragment())
            .commit()
    }
}