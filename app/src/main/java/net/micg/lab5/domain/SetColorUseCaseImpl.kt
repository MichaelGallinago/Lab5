package net.micg.lab5.domain

import net.micg.lab5.data.HttpResponseState
import net.micg.lab5.data.LampRepository
import javax.inject.Inject

class SetColorUseCaseImpl @Inject constructor(
    private val lampRepository: LampRepository
) : SetColorUseCase {
    override suspend operator fun invoke(color: String): HttpResponseState<Boolean> =
        lampRepository.setColor(color)
}
