package net.micg.lab5.domain

import net.micg.lab5.data.LampRepository
import javax.inject.Inject

class TurnOffLampUseCaseImpl @Inject constructor(
    private val lampRepository: LampRepository
) : TurnOffLampUseCase {
    override suspend operator fun invoke() = lampRepository.turnOff()
}
