package net.micg.lab5.domain

import net.micg.lab5.data.LampRepository
import javax.inject.Inject

class TurnOnLampUseCaseImpl @Inject constructor(
    private val lampRepository: LampRepository
) : TurnOnLampUseCase {
    override suspend operator fun invoke() = lampRepository.turnOn()
}
