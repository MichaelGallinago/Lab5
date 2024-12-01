package net.micg.lab5.domain

import net.micg.lab5.data.HttpResponseState
import net.micg.lab5.data.LampRepository
import javax.inject.Inject

class GetColorNamesUseCaseImpl @Inject constructor(
    private val lampRepository: LampRepository
) : GetColorNamesUseCase {
    override suspend operator fun invoke(): HttpResponseState<List<String>> =
        lampRepository.getColorNames()
}
