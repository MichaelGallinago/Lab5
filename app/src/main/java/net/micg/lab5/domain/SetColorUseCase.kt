package net.micg.lab5.domain

import net.micg.lab5.data.HttpResponseState

interface SetColorUseCase {
    suspend operator fun invoke(color: String): HttpResponseState<Boolean>
}
