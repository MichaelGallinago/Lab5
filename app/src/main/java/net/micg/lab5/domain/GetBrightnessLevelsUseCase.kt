package net.micg.lab5.domain

import net.micg.lab5.data.BrightnessLevels
import net.micg.lab5.data.HttpResponseState

interface GetBrightnessLevelsUseCase {
    suspend operator fun invoke(): HttpResponseState<BrightnessLevels>
}
