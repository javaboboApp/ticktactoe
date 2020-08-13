package com.example.ticktock.di

import com.example.ticktock.ui.tictactoe.TicTacToeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module { viewModel {
    TicTacToeViewModel(
        get()
    )
} }