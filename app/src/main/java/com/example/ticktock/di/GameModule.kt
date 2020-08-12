package com.example.ticktock.di

import com.example.ticktock.models.Game
import org.koin.dsl.module

val gameModule = module { factory { Game(get(), get()) } }