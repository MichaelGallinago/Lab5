package net.micg.lab5.data

import retrofit2.Call
import retrofit2.Response
import retrofit2.awaitResponse
import javax.inject.Inject

class LampRepositoryImpl @Inject constructor(
    private val api: LampApi,
) : LampRepository {
    override suspend fun turnOn() = handleApiResponse(api::turnOn)
    override suspend fun turnOff() = handleApiResponse(api::turnOff)
    override suspend fun getState() = handleApiResponse(api::getState)

    override suspend fun getColors() = handleApiResponse(api::getColors)
    override suspend fun setColor(color: String) = handleApiResponse { api.setColor(color) }
    override suspend fun getColorNames() = handleApiResponse(api::getColorNames)
    override suspend fun getCurrentColor() = handleApiResponse(api::getCurrentColor)

    override suspend fun getBrightnessLevels() = handleApiResponse(api::getBrightnessLevels)
    override suspend fun setBrightness(level: Int) = handleApiResponse { api.setBrightness(level) }
    override suspend fun getCurrentBrightness() = handleApiResponse(api::getCurrentBrightness)

    private suspend fun <T> handleApiResponse(
        apiCall: suspend () -> Call<T>,
    ) = kotlin.runCatching {
        apiCall().awaitResponse()
    }.fold(
        onSuccess = { response -> onSuccess(response) },
        onFailure = { throwable -> onFailure(throwable) }
    )

    private fun <T> onSuccess(response: Response<T>): HttpResponseState<T> {
        return if (!response.isSuccessful) {
            HttpResponseState.Failure(response.message())
        } else {
            HttpResponseState.Success(
                response.body() ?: throw IllegalArgumentException("Response body is null")
            )
        }
    }

    private fun onFailure(throwable: Throwable) =
        HttpResponseState.Failure(throwable.message ?: "failure")
}
