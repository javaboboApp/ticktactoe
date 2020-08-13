package com.example.ticktock.ui.tictactoe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.ticktock.R
import com.example.ticktock.databinding.TicTacToeFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class TicTacToeFragment : Fragment() {
   private val  ticTacToeViewModel: TicTacToeViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val ticTacToebinding: TicTacToeFragmentBinding =
            TicTacToeFragmentBinding.inflate(inflater, container, false)

        ticTacToebinding.setLifecycleOwner(this)

        ticTacToebinding.ticTacToeViewModel = ticTacToeViewModel

        return ticTacToebinding.root
    }


}