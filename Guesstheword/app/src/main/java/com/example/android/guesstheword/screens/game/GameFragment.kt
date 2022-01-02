/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.guesstheword.screens.game

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.example.android.guesstheword.R
import com.example.android.guesstheword.databinding.GameFragmentBinding

/**
 * Fragment where the game is played
 */
class GameFragment : Fragment() {

    private lateinit var viewModel: GameViewModel



    private lateinit var binding: GameFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.game_fragment,
                container,
                false
        )

        Log.i("MainFragment", "called ViewModelProvider")
        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)


        binding.correctButton.setOnClickListener {
            viewModel.onCorrect()
//            updateScoreText()
//            updateWordText()
        }
        binding.skipButton.setOnClickListener {
            viewModel.onSkip()
//            updateScoreText()
//            updateWordText()
        }

        //Sets GameFragment as an observer of the LiveData that contains our score in
        //the ViewModel. When score is updated, the score in the TextView UI is updated
        //to display the new score
        //Note: viewLifecycleOwner is the lifecycle owner of the view that dislayes the score
        //In this case, that lifecycle owner is GameFragment because it is here in this fragment
        //where binding is used to link to the TextView that displays the score in the UI
        viewModel.score.observe(viewLifecycleOwner, Observer { newScore ->
            binding.scoreText.text = viewModel.score.value.toString()
        })

        viewModel.word.observe(viewLifecycleOwner, Observer { newWord ->
            binding.wordText.text = viewModel.word.value.toString()
        })

        viewModel.currentTime.observe(viewLifecycleOwner, Observer { newTime ->
            binding.timerText.text = viewModel.currentTime.value.toString()
        })

        viewModel.eventGameFinish.observe(viewLifecycleOwner, Observer { hasFinished ->

            if (hasFinished) {
                gameFinished()
                viewModel.onGameFinishComplete()
            } else {

            }
        })

//        updateScoreText()
//        updateWordText()
        return binding.root

    }

    /**
     * Resets the list of words and randomizes the order
     */


    /**
     * Called when the game is finished
     */
    private fun gameFinished() {

        //What is happening in the ()? What's the purpose of this code?
        val action = GameFragmentDirections.actionGameToScore(viewModel.score.value ?: 0)
        findNavController(this).navigate(action)
    }





    /** Methods for updating the UI **/

//    private fun updateWordText() {
//        binding.wordText.text = viewModel.word.toString()
//
//    }
//
//    private fun updateScoreText() {
//        binding.scoreText.text = viewModel.score.toString()
//    }
}
