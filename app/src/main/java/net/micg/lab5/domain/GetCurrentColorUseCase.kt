package net.micg.lab5.domain

import net.micg.lab5.data.Color
import net.micg.lab5.data.HttpResponseState

interface GetCurrentColorUseCase {
    suspend operator fun invoke(): HttpResponseState<Color>
}
