package com.cmisner.temperatureconverter.services

import com.cmisner.temperatureconverter.entities.TemperatureReadingEntity
import com.cmisner.temperatureconverter.models.TemperatureUnit
import com.cmisner.temperatureconverter.repositories.TemperatureReadingRepository
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class TemperatureAverageServiceTest{
    private val temperatureReadingRepository: TemperatureReadingRepository = mockk()
    private lateinit var temperatureAverageService: TemperatureAverageService

    @BeforeEach
    fun init(){
        temperatureAverageService = TemperatureAverageService(temperatureReadingRepository)
    }

    @Test
    fun `SHOULD provide average of last 7 days WHEN requested`(){
        val temp1 = TemperatureReadingEntity(TemperatureUnit.CELSIUS, 30.0, 87.8)
        val temp2 = TemperatureReadingEntity(TemperatureUnit.FAHRENHEIT, 12.8, 55.0)
        val temp3 = TemperatureReadingEntity(TemperatureUnit.FAHRENHEIT, 0.0, 32.0)

        every { temperatureReadingRepository.findAllWithCreationDateAfter(any())} returns listOf(temp1, temp2, temp3)

        val averageTemperature = temperatureAverageService.get7DayAverageTemperature()
        assertEquals(14.3, averageTemperature.celsiusTemperatureAverage.value)
        assertEquals(58.3, averageTemperature.fahrenheitTemperatureAverage.value)
    }
}