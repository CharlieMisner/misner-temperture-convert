package com.cmisner.temperatureconverter.controllers

import com.cmisner.temperatureconverter.dto.ConvertedReadingDTO
import com.cmisner.temperatureconverter.services.TemperatureConversionService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class TemperatureConversionController(private val temperatureConversionService: TemperatureConversionService) {
    @PostMapping("/convert-celsius-to-fahrenehit")
    fun convertCelsiusToFahrenheit(@RequestBody celsiusReading: Double): ConvertedReadingDTO {
        return temperatureConversionService.convertCelsiusToFahrenheit(celsiusReading)
    }

    @PostMapping("/convert-fahrenheit-to-celsius")
    fun convertFahrenheitToCelsius(@RequestBody fahrenheitReading: Double): ConvertedReadingDTO {
        return temperatureConversionService.convertFahrenheitToCelsius(fahrenheitReading)
    }
}