package net.micg.lab5.presenter

import androidx.lifecycle.ViewModel
import net.micg.lab5.domain.GetBrightnessLevelsUseCase
import net.micg.lab5.domain.GetColorNamesUseCase
import net.micg.lab5.domain.GetColorsUseCase
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
    private val getColorsUseCase: GetColorsUseCase,
    private val setColorUseCase: SetColorUseCase,
    private val getColorNamesUseCase: GetColorNamesUseCase,
    private val getCurrentColorUseCase: GetCurrentColorUseCase,
    private val getBrightnessLevelsUseCase: GetBrightnessLevelsUseCase,
    private val setBrightnessUseCase: SetBrightnessUseCase,
    private val getCurrentBrightnessUseCase: GetCurrentBrightnessUseCase
) : ViewModel() {
    
}
