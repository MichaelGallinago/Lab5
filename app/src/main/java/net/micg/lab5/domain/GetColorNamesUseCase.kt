package net.micg.lab5.domain

import net.micg.lab5.data.HttpResponseState

interface GetColorNamesUseCase {
    suspend operator fun invoke(): HttpResponseState<List<String>>
}
