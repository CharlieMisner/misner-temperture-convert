package com.cmisner.temperatureconverter.services

import com.cmisner.temperatureconverter.entities.TemperatureReadingEntity
import com.cmisner.temperatureconverter.models.Temperature
import com.cmisner.temperatureconverter.models.TemperatureUnit
import com.cmisner.temperatureconverter.repositories.TemperatureReadingRepository
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class TemperatureConversionServiceTest {

    private val temperatureReadingRepository: TemperatureReadingRepository = mockk()
    private lateinit var temperatureConversionService: TemperatureConversionService

    @BeforeEach
    fun init(){
        temperatureConversionService = TemperatureConversionService(temperatureReadingRepository)
    }

    @Test
    fun `SHOULD convert temperature to Fahrenheit WHEN Celsius temperature provided`(){
        val inputTemperature = Temperature(31.0, TemperatureUnit.CELSIUS)
        val expectedConvertedTemperature = Temperature(87.8, TemperatureUnit.FAHRENHEIT)

        val mockRepositoryResponse = TemperatureReadingEntity(TemperatureUnit.CELSIUS, 30.0, 87.8)
        every { temperatureReadingRepository.save(any()) } returns mockRepositoryResponse

        val convertedTemperature = temperatureConversionService.convertTemperature(inputTemperature)
        assertEquals(expectedConvertedTemperature, convertedTemperature)
    }

    @Test
    fun `SHOULD convert temperature to Celsius WHEN Fahrenheit temperature provided`(){
        val inputTemperature = Temperature(55.0, TemperatureUnit.FAHRENHEIT)
        val expectedConvertedTemperature = Temperature(12.8, TemperatureUnit.CELSIUS)

        val mockRepositoryResponse = TemperatureReadingEntity(TemperatureUnit.FAHRENHEIT, 12.8, 55.0)
        every { temperatureReadingRepository.save(any()) } returns mockRepositoryResponse

        val convertedTemperature = temperatureConversionService.convertTemperature(inputTemperature)
        assertEquals(expectedConvertedTemperature, convertedTemperature)
    }
}