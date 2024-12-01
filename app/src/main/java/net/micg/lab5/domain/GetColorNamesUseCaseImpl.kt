package net.micg.lab5.domain

import net.micg.lab5.data.HttpResponseState.*
import net.micg.lab5.data.LampRepository
import net.micg.lab5.domain.StringUtils.camelcase
import javax.inject.Inject

class GetColorNamesUseCaseImpl @Inject constructor(
    private val lampRepository: LampRepository
) : GetColorNamesUseCase {
    override suspend operator fun invoke() = when(val response = lampRepository.getColorNames()) {
        is Success -> Success(response.value.map { it.camelcase() })
        is Failure -> Failure(response.message)
    }
}
