package net.micg.lab5.domain

import net.micg.lab5.data.HttpResponseState

interface GetCurrentColorUseCase {
    suspend operator fun invoke(): HttpResponseState<String>
}
