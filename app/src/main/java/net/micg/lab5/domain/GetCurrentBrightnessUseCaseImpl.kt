package net.micg.lab5.domain

import net.micg.lab5.data.HttpResponseState
import net.micg.lab5.data.LampRepository
import javax.inject.Inject

class GetCurrentBrightnessUseCaseImpl @Inject constructor(
    private val lampRepository: LampRepository
) : GetCurrentBrightnessUseCase {
    override suspend operator fun invoke(): HttpResponseState<Int> =
        lampRepository.getCurrentBrightness()
}
