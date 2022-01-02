package com.example.android.guesstheword.screens.score

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ScoreViewModelFactory(score: Int) {


    //What makes this a view model factory is we have extended ViewModelProvider.Factory
    //Important: the view model factory should take in as parameters any arguments you want to pass
    //in to your ViewModel (Score implementation "androidx.fragment:fragment-ktx:1.2.0"
    class ScoreViewModelFactory(private val finalScore: Int) : ViewModelProvider.Factory {

        //This function overrides the create method. This method is called when the ViewModel is
        //created. Notice that it is supposed to return a ViewModel.
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ViewModel::class.java)) {
                // TODO Construct and return the ScoreViewModel

                return ScoreViewModel(finalScore) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}