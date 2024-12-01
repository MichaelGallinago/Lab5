package net.micg.lab5.domain

import net.micg.lab5.data.HttpResponseState.*
import net.micg.lab5.data.LampRepository
import net.micg.lab5.domain.StringUtils.camelcase
import javax.inject.Inject

class GetCurrentColorUseCaseImpl @Inject constructor(
    private val lampRepository: LampRepository,
) : GetCurrentColorUseCase {
    override suspend operator fun invoke() = when(val response = lampRepository.getCurrentColor()) {
        is Success -> Success(response.value.name.camelcase())
        is Failure -> Failure(response.message)
    }
}
