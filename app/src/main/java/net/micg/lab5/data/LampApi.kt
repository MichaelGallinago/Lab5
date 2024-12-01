package net.micg.lab5.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface LampApi {
    //OnOffController

    /**Метод для включения лампочки*/
    @POST("/state/on")
    fun turnOn(): Call<Boolean>

    /**Метод для отключения лампочки*/
    @POST("/state/off")
    fun turnOff(): Call<Boolean>

    /**Метод для определения состояния*/
    @GET("/state/")
    fun getState(): Call<Boolean>

    //ColorController

    /**Метод для получения возможных цветов с их разъяснениями*/
    @GET("/color/")
    fun getColors(): Call<List<Color>>

    /**Метод для задания цвета лампочки*/
    @POST("/color/")
    fun setColor(@Query("color") color: String): Call<Boolean>

    /**Метод для получения возможных наименований*/
    @GET("/color/names_only")
    fun getColorNames(): Call<List<String>>

    /**Метод для определения цвета*/
    @GET("/color/current")
    fun getCurrentColor(): Call<Color>

    //BrightnessController

    /**Метод для получения возможных уровней яркости лампочки*/
    @GET("/brightness/")
    fun getBrightnessLevels(): Call<BrightnessLevels>

    /**Метод для изменения уровня яркости лампочки*/
    @POST("/brightness/")
    fun setBrightness(@Query("level") level: Int): Call<Boolean>

    /**Метод для определения уровня яркости*/
    @GET("/brightness/current")
    fun getCurrentBrightness(): Call<Int>
}
