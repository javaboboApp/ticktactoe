package com.example.ticktock.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ticktock.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {
    private val homeActivityViewModel: HomeActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        homeActivityViewModel.madeMove()
    }
}