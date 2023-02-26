package com.cmisner.temperatureconverter.controllers

import com.cmisner.temperatureconverter.enums.TemperatureUnit
import com.cmisner.temperatureconverter.models.Temperature
import com.cmisner.temperatureconverter.services.TemperatureConversionService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class TemperatureConversionController(private val temperatureConversionService: TemperatureConversionService) {
    @PostMapping("/convert-celsius-to-fahrenheit")
    fun convertCelsiusToFahrenheit(@RequestBody celsiusReading: Double): Temperature {
        return temperatureConversionService.convertTemperature(Temperature(celsiusReading, TemperatureUnit.CELSIUS))
    }

    @PostMapping("/convert-fahrenheit-to-celsius")
    fun convertFahrenheitToCelsius(@RequestBody fahrenheitReading: Double): Temperature {
        return temperatureConversionService.convertTemperature(Temperature(fahrenheitReading, TemperatureUnit.FAHRENHEIT))
    }

    @GetMapping("/health-check")
    fun test(): String{
        return "OK!"
    }
}