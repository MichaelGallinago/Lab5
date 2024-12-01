package net.micg.lab5.domain

import net.micg.lab5.data.Color
import net.micg.lab5.data.HttpResponseState
import net.micg.lab5.data.LampRepository
import javax.inject.Inject

class GetCurrentColorUseCaseImpl @Inject constructor(
    private val lampRepository: LampRepository
) : GetCurrentColorUseCase {
    override suspend operator fun invoke(): HttpResponseState<Color> =
        lampRepository.getCurrentColor()
}
