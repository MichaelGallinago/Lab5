package net.micg.lab5.data

interface LampRepository {
    suspend fun turnOn(): HttpResponseState<Boolean>
    suspend fun turnOff(): HttpResponseState<Boolean>
    suspend fun getState(): HttpResponseState<Boolean>
    suspend fun getColors(): HttpResponseState<List<Color>>
    suspend fun setColor(color: String): HttpResponseState<Boolean>
    suspend fun getColorNames(): HttpResponseState<List<String>>
    suspend fun getCurrentColor(): HttpResponseState<Color>
    suspend fun getBrightnessLevels(): HttpResponseState<BrightnessLevels>
    suspend fun setBrightness(level: Int): HttpResponseState<Boolean>
    suspend fun getCurrentBrightness(): HttpResponseState<Int>
}
