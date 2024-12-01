package net.micg.lab5.domain

import net.micg.lab5.data.Color
import net.micg.lab5.data.HttpResponseState

interface GetColorsUseCase {
    suspend operator fun invoke(): HttpResponseState<List<Color>>
}
