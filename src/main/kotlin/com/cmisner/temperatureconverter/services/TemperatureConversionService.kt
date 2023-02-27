package com.cmisner.temperatureconverter.services

import com.cmisner.temperatureconverter.entities.TemperatureReadingEntity
import com.cmisner.temperatureconverter.models.Temperature
import com.cmisner.temperatureconverter.models.TemperatureUnit
import com.cmisner.temperatureconverter.repositories.TemperatureReadingRepository
import mu.KotlinLogging
import org.springframework.stereotype.Service

/**
 * @author cmisner
 *
 * Responsible for converting temperature readings between Celsius and Fahrenheit
 */
@Service
class TemperatureConversionService(private val temperatureReadingRepository: TemperatureReadingRepository) {
    private val logger = KotlinLogging.logger {  }

    /**
     * Takes a temperature reading of either and Fahrenheit or Celsius and returns the converted value.
     *
     * @param temperature: Temperature
     */
    fun convertTemperature(temperature: Temperature): Temperature {
        if (temperature.temperatureUnit == TemperatureUnit.CELSIUS){
            return convertCelsiusToFahrenheit(temperature)
        } else {
            return convertFahrenheitToCelsius(temperature)
        }
    }

    /**
     * Takes Celsius reading and converts it to Fahrenheit, stores a record of the reading, and returns the converted value.
     *
     * @param celsiusReading: Double
     */
    private fun convertCelsiusToFahrenheit(celsiusReading: Temperature): Temperature{
        val fahrenheitConversion: Double = roundTemperature(celsiusReading.value * 9.0 / 5.0 + 32.0)
        logger.info { "Converted ${celsiusReading.value} degrees celsius to $fahrenheitConversion degrees fahrenheit" }
        try {
            temperatureReadingRepository.save(TemperatureReadingEntity(TemperatureUnit.CELSIUS, celsiusReading.value, fahrenheitConversion))
        } catch (e: Exception) {
            logger.error("There was an error saving a celsius temperature reading to the database")
        }
        return Temperature(fahrenheitConversion, TemperatureUnit.FAHRENHEIT)
    }

    /**
     * Takes Fahrenheit reading and converts it to Celsius, stores a record of the reading, and returns the converted value.
     *
     * @param celsiusReading: Double
     */
    private fun convertFahrenheitToCelsius(fahrenheitReading: Temperature): Temperature {
        val celsiusConversion: Double = roundTemperature((fahrenheitReading.value - 32) * 5 / 9)
        logger.info { "Converted ${fahrenheitReading.value} degrees fahrenheit to $celsiusConversion degrees celsius" }
        try {
            temperatureReadingRepository.save(TemperatureReadingEntity(TemperatureUnit.FAHRENHEIT, celsiusConversion, fahrenheitReading.value))
        } catch (e: Exception){
            logger.error("There was an error saving a fahrenheit temperature reading to the database")
        }
        return Temperature(celsiusConversion, TemperatureUnit.CELSIUS)
    }
}