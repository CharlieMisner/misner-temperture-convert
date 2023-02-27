package com.cmisner.temperatureconverter.controllers

import com.cmisner.temperatureconverter.models.AverageTemperatures
import com.cmisner.temperatureconverter.models.Temperature
import com.cmisner.temperatureconverter.services.TemperatureAverageService
import com.cmisner.temperatureconverter.services.TemperatureConversionService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class TemperatureController(
    private val temperatureConversionService: TemperatureConversionService,
    private val temperatureAverageService: TemperatureAverageService
) {

    /**
     * Accepts requests with a temperature reading of either and Fahrenheit or Celsius and returns the converted value.
     * This endpoint also saves a record of the reading, beacuse this is a state changing operation, it is POST endpoint.
     *
     * @param temperature: Temperature
     */
    @PostMapping("/convert-temperature")
    fun convertTemperature(@RequestBody temperature: Temperature): Temperature {
        return temperatureConversionService.convertTemperature(temperature)
    }

    /**
     * Returns the average of the last seven days temperature readings.
     */
    @GetMapping("/seven-day-average")
    fun getSevenDayAverageTemperature(): AverageTemperatures{
        return temperatureAverageService.get7DayAverageTemperature()
    }
}