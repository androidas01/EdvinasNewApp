package edvinasnew.app.main

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import edvinasnew.app.utils.SingleLiveData

class MainViewModel constructor(
    preferences: SharedPreferences
) : ViewModel() {

    private val _showTutorial = SingleLiveData<Unit>()
    val showTutorial: LiveData<Unit> get() = _showTutorial

    init {
        if (!preferences.getBoolean("tutorial_is_shown", false)) {
            _showTutorial.postValue(Unit)
            preferences.edit().putBoolean("tutorial_is_shown", true).apply()
        }
    }
}