package com.cmisner.temperatureconverter.controllers

import com.cmisner.temperatureconverter.models.Temperature
import com.cmisner.temperatureconverter.services.TemperatureConversionService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class TemperatureConversionController(private val temperatureConversionService: TemperatureConversionService) {
    @PostMapping("/convert-temperature")
    fun convertTemperature(@RequestBody temperature: Temperature): Temperature {
        return temperatureConversionService.convertTemperature(temperature)
    }
}