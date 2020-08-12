package com.example.ticktock.di

import com.example.ticktock.models.Player
import org.koin.dsl.module

val playerModule = module{ factory { Player() } }