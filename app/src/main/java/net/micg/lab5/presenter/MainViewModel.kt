package net.micg.lab5.presenter

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.micg.lab5.data.BrightnessLevels
import net.micg.lab5.data.HttpResponseState
import net.micg.lab5.domain.GetBrightnessLevelsUseCase
import net.micg.lab5.domain.GetColorNamesUseCase
import net.micg.lab5.domain.GetCurrentBrightnessUseCase
import net.micg.lab5.domain.GetCurrentColorUseCase
import net.micg.lab5.domain.GetLampStateUseCase
import net.micg.lab5.domain.SetBrightnessUseCase
import net.micg.lab5.domain.SetColorUseCase
import net.micg.lab5.domain.TurnOffLampUseCase
import net.micg.lab5.domain.TurnOnLampUseCase
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val turnOnLampUseCase: TurnOnLampUseCase,
    private val turnOffLampUseCase: TurnOffLampUseCase,
    private val getLampStateUseCase: GetLampStateUseCase,
    private val setColorUseCase: SetColorUseCase,
    private val getColorNamesUseCase: GetColorNamesUseCase,
    private val getCurrentColorUseCase: GetCurrentColorUseCase,
    private val getBrightnessLevelsUseCase: GetBrightnessLevelsUseCase,
    private val setBrightnessUseCase: SetBrightnessUseCase,
    private val getCurrentBrightnessUseCase: GetCurrentBrightnessUseCase,
) : ViewModel() {
    private val _lampState = MutableLiveData<Boolean>()
    val lampState: LiveData<Boolean> get() = _lampState

    private val _colorName = MutableLiveData<String>()
    val colorName: LiveData<String> get() = _colorName

    private val _colorNames = MutableLiveData<List<String>>()
    val colorNames: LiveData<List<String>> get() = _colorNames

    private val _brightness = MutableLiveData<Int>()
    val brightness: LiveData<Int> get() = _brightness

    private val _brightnessLevels = MutableLiveData<BrightnessLevels>()
    val brightnessLevels: LiveData<BrightnessLevels> get() = _brightnessLevels

    fun initData() {
        syncColors()
        syncBrightnessLevels()
        syncData()
    }

    fun syncData() {
        syncLampState()
        syncBrightness()
        syncColor()
    }

    fun switchLampState(state: Boolean) = viewModelScope.launch(Dispatchers.IO) {
        if (state) turnOnLampUseCase() else turnOffLampUseCase()
    }

    fun setBrightness(brightness: Int) = viewModelScope.launch(Dispatchers.IO) {
        setBrightnessUseCase(brightness)
    }

    fun setColor(name: String) = viewModelScope.launch(Dispatchers.IO) {
        if (name == EMPTY_COLOR) return@launch
        setColorUseCase(name)
    }

    private fun syncLampState() = syncLiveData(_lampState) { getLampStateUseCase() }
    private fun syncBrightness() = syncLiveData(_brightness) { getCurrentBrightnessUseCase() }
    private fun syncColor() = syncLiveData(_colorName) { getCurrentColorUseCase() }

    private fun syncColors() = syncLiveData(_colorNames) { getColorNamesUseCase() }
    private fun syncBrightnessLevels() =
        syncLiveData(_brightnessLevels) { getBrightnessLevelsUseCase() }

    private fun <T> syncLiveData(
        liveData: MutableLiveData<T>,
        useCase: suspend () -> HttpResponseState<T>,
    ) = viewModelScope.launch(Dispatchers.IO) {
        when (val result = useCase()) {
            is HttpResponseState.Success -> liveData.postValue(result.value)
            is HttpResponseState.Failure -> logFailure(result.message)
        }
    }

    private fun logFailure(message: String) = Log.d("http_failure", message)

    companion object {
        const val EMPTY_COLOR = "Empty"
    }
}
