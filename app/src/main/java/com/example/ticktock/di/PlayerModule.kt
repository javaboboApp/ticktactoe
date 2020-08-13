package com.example.ticktock.di

import com.example.ticktock.models.Player
import com.example.ticktock.utils.Constants.PLAYER_1
import org.koin.dsl.module

val playerModule = module{ single { PLAYER_1} }