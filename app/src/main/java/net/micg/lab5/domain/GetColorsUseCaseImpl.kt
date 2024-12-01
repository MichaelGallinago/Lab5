package net.micg.lab5.domain

import net.micg.lab5.data.Color
import net.micg.lab5.data.HttpResponseState
import net.micg.lab5.data.LampRepository
import javax.inject.Inject

class GetColorsUseCaseImpl @Inject constructor(
    private val lampRepository: LampRepository
) : GetColorsUseCase {
    override suspend operator fun invoke(): HttpResponseState<List<Color>> =
        lampRepository.getColors()
}
