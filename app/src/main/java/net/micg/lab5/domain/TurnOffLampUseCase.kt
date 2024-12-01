package net.micg.lab5.domain

import net.micg.lab5.data.HttpResponseState

interface TurnOffLampUseCase {
    suspend operator fun invoke(): HttpResponseState<Boolean>
}
