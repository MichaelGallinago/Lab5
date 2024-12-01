package net.micg.lab5.domain

import net.micg.lab5.data.HttpResponseState

interface GetCurrentBrightnessUseCase {
    suspend operator fun invoke(): HttpResponseState<Int>
}
