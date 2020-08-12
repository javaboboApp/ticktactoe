package com.example.ticktock.di

import com.example.ticktock.models.Board
import org.koin.dsl.module

val boardModule = module { factory { Board() } }