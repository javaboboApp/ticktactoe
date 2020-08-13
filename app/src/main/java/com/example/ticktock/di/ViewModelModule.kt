package com.example.ticktock.di

import com.example.ticktock.ui.HomeActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module { viewModel { HomeActivityViewModel(get()) } }