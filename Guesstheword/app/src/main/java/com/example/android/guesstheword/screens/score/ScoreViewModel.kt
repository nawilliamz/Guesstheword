package com.example.android.guesstheword.screens.score

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner

class ScoreViewModel (finalScore:Int): ViewModel() {
    init {
        Log.i("ScoreViewModel", "Final score is $finalScore")
    }

}