package net.micg.lab5.domain

import net.micg.lab5.data.LampRepository
import javax.inject.Inject

class GetColorsUseCaseImpl @Inject constructor(
    private val lampRepository: LampRepository
) : GetColorsUseCase {
    override suspend operator fun invoke() = lampRepository.getColors()
}
