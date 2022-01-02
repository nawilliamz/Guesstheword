package com.example.android.guesstheword.screens.game

import android.os.CountDownTimer
import android.text.format.DateUtils
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel:ViewModel() {

    companion object {
        // These represent different important times
        // This is when the game is over
        const val DONE = 0L
        // This is the number of milliseconds in a second
        const val ONE_SECOND = 1000L
        // This is the total time of the game
        const val COUNTDOWN_TIME = 10000L
    }

    private val timer:CountDownTimer

    // The current word
    private var _word = MutableLiveData<String>()
    //Setting the public-facing version of our LiveData
    val word:LiveData<String>
        get() = _word

    // The current score
    private var _score = MutableLiveData<Int>()
    //This is the public-facing version of our LiveData. Because we're using LiveData for the type
    //here, it is only going to be exposed externally as LiveData
    val score:LiveData<Int>
        //We're using a backing property to create a custom getter
        get() = _score

    // The list of words - the front of the list is the next word to guess
    private lateinit var wordList: MutableList<String>


    //Make a new LiveData variable that represents a 'game closed' event
    private val _eventGameFinish = MutableLiveData<Boolean>()
    val eventGameFinish : LiveData<Boolean>
        get() = _eventGameFinish


    private val _currentTime = MutableLiveData<Long>()
    val currentTime : LiveData<Long>
        get() = _currentTime


    init {
        _eventGameFinish.value = false
        Log.i("GameViewModel", "GameViewModel created")
        resetList()
        nextWord()
        _score.value = 0
        _currentTime.value = 0


        timer = object : CountDownTimer(COUNTDOWN_TIME, ONE_SECOND) {

            override fun onTick(millisUntilFinished: Long) {
                // TODO implement what should happen each tick of the timer
                //increment _currentTime by 1 (the ViewModel field is being observed in GameFragmenet
                //and will display in the timer field on the screen based on that code
                _currentTime.value = (_currentTime.value)?.plus(1)

            }

            override fun onFinish() {
                // TODO implement what should happen when the timer finishes


                _eventGameFinish.value = true
            }
        }

        //Pass in the number of milliseconds and it will format the Long correctly as a time
        DateUtils.formatElapsedTime(COUNTDOWN_TIME)
        timer.start()
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("GameViewModel", "GameViewModel destroyed")

        //You should always cancel a timer
        timer.cancel()


    }

    private fun resetList() {
        wordList = mutableListOf(
            "queen",
            "hospital",
            "basketball",
            "cat",
            "change",
            "snail",
            "soup",
            "calendar",
            "sad",
            "desk",
            "guitar",
            "home",
            "railway",
            "zebra",
            "jelly",
            "car",
            "crow",
            "trade",
            "bag",
            "roll",
            "bubble"
        )
        wordList.shuffle()
    }

    private fun nextWord() {
        //Select and remove a word from the list
        if (wordList.isEmpty()) {

            //need to re-add the words to word list to start a new game
            for (word:String in wordList) {
                wordList.add(word)

            }
            resetList()
//            _eventGameFinish.value = true
//            gameFinished()
        }

        //remoteAt() removed the element at index 0 from wordList
        //importantly remoteAt() returns the element that was removed
        _word.value= wordList.removeAt(0)

        //Remove since calling these in onCreateView in GameFragment
//        updateWordText()
//        updateScoreText()
    }

    fun onSkip() {
        _score.value = (score.value)?.minus(1)
        nextWord()
    }

    fun onCorrect() {
        _score.value = (score.value)?.plus(1)
        nextWord()
    }

    fun onGameFinishComplete () {
        _eventGameFinish.value = false
    }


}