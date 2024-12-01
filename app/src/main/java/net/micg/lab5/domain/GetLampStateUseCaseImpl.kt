package net.micg.lab5.domain

import net.micg.lab5.data.LampRepository
import javax.inject.Inject

class GetLampStateUseCaseImpl @Inject constructor(
    private val lampRepository: LampRepository
) : GetLampStateUseCase {
    override suspend operator fun invoke() = lampRepository.getState()
}
