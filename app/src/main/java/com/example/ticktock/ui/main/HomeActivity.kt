package com.example.ticktock.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ticktock.R
import com.example.ticktock.ui.tictactoe.TicTacToeFragment

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.nav_host_container, TicTacToeFragment())
                .commit()
        }

    }
}