package com.example.ticktock.ui.tictactoe

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.ticktock.R
import com.example.ticktock.databinding.TicTacToeFragmentBinding
import com.example.ticktock.models.Board.Board
import com.example.ticktock.models.game.GameEvent
import com.example.ticktock.models.game.GameEvent.*
import kotlinx.android.synthetic.main.tic_tac_toe_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class TicTacToeFragment : Fragment() {
    private val ticTacToeViewModel: TicTacToeViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val ticTacToebinding: TicTacToeFragmentBinding =
            setUpMutiBinding(inflater, container)


        return ticTacToebinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpMenu()
        subscribeObserver()
    }

    private fun subscribeObserver() {
        ticTacToeViewModel.result.observe(viewLifecycleOwner, Observer { gameEvent ->
            when (gameEvent) {

                is RESETED -> {
                    showMsg(getString(R.string.reseted_msg))

                }
                is GAME_FINISHED -> {
                    if (gameEvent.state == Board.State.DRAW) {
                        showMsg(getString(R.string.game_finished_was_draw))
                    }
                    showMsg(getString(R.string.game_finished_won_msg) + gameEvent.player.name)

                }
                is ERROR -> {
                    showMsg(getString(R.string.game_error_msg) + gameEvent.msg)

                }

            }
        })
    }

    private fun showMsg(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()
    }

    private fun setUpMenu() {
        toolbar.setOnMenuItemClickListener {
            ticTacToeViewModel.reset()
            true
        }
    }

    private fun setUpMutiBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): TicTacToeFragmentBinding {
        // Inflate the layout for this fragment
        val ticTacToebinding: TicTacToeFragmentBinding =
            TicTacToeFragmentBinding.inflate(inflater, container, false)

        ticTacToebinding.setLifecycleOwner(this)

        ticTacToebinding.ticTacToeViewModel = ticTacToeViewModel



        return ticTacToebinding
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when (id) {
            R.id.reset -> {
                ticTacToeViewModel.reset()
                return true
            }
        }
        return super.onOptionsItemSelected(item) // important line
    }
}