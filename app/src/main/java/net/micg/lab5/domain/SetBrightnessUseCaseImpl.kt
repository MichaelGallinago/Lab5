package net.micg.lab5.domain

import net.micg.lab5.data.LampRepository
import javax.inject.Inject

class SetBrightnessUseCaseImpl @Inject constructor(
    private val lampRepository: LampRepository
) : SetBrightnessUseCase {
    override suspend operator fun invoke(level: Int) = lampRepository.setBrightness(level)
}
