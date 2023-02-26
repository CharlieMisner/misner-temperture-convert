package com.cmisner.temperatureconverter.services

import org.junit.jupiter.api.Test

class TemperatureConversionServiceTest {

    private val temperatureConversionService: TemperatureConversionService = TemperatureConversionService()

    @Test
    fun `SHOULD convert temperature to Fahrenheit WHEN Celsius temperature provided`(){
        temperatureConversionService.convertCelsiusToFahrenheit(30.0)
    }
}