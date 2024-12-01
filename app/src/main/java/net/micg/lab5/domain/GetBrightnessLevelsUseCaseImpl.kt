package net.micg.lab5.domain

import net.micg.lab5.data.LampRepository
import javax.inject.Inject

class GetBrightnessLevelsUseCaseImpl @Inject constructor(
    private val lampRepository: LampRepository
) : GetBrightnessLevelsUseCase {
    override suspend operator fun invoke() = lampRepository.getBrightnessLevels()
}
