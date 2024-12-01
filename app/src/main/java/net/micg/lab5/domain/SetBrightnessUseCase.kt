package net.micg.lab5.domain

import net.micg.lab5.data.HttpResponseState

interface SetBrightnessUseCase {
    suspend operator fun invoke(level: Int): HttpResponseState<Boolean>
}
