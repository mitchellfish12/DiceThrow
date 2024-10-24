package edu.temple.dicethrow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DieViewModule : ViewModel() {
    private val dieRoll = MutableLiveData<Int>()

    fun getDieRoll() : LiveData<Int> {
        return dieRoll
    }

    fun setDieRoll(newRoll: Int) {
        dieRoll.value = newRoll
    }
}