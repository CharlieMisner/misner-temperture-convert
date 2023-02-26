package com.cmisner.temperatureconverter.services

import com.cmisner.temperatureconverter.dto.ConvertedReadingDTO
import com.cmisner.temperatureconverter.enums.TemperatureUnit
import mu.KotlinLogging
import org.springframework.stereotype.Service

/**
 * @author cmisner
 *
 * Responsible for converting temperature readings between Celsius and Fahrenheit
 */
@Service
class TemperatureConversionService() {
    private val logger = KotlinLogging.logger {  }

    /**
     * Ingests Celsius reading and converts it to Fahrenheit, stores a record of the reading, and returns the converted value.
     *
     * @param celsiusReading: Double
     */
    fun convertCelsiusToFahrenheit(celsiusReading: Double): ConvertedReadingDTO{
        val fahrenheitConversion: Double = celsiusReading * 9 / 5 + 32
        logger.info { "Converted $celsiusReading degrees celsius to $fahrenheitConversion degrees fahrenheit" }
//        temperatureReadingRepository.save(TemperatureReadingEntity(0, TemperatureUnit.CELSIUS, celsiusReading, fahrenheitConversion, null))
        return ConvertedReadingDTO(fahrenheitConversion, TemperatureUnit.FAHRENHEIT)
    }

    /**
     * Ingests Fahrenheit reading and converts it to Celsius, stores a record of the reading, and returns the converted value.
     *
     * @param celsiusReading: Double
     */
    fun convertFahrenheitToCelsius(fahrenheitReading: Double): ConvertedReadingDTO {
        val celsiusConversion: Double = fahrenheitReading * 5 / 9 - 32
        logger.info { "Converted $fahrenheitReading degrees fahrenheit to $celsiusConversion degrees celsius" }
//        temperatureReadingRepository.save(TemperatureReadingEntity(0, TemperatureUnit.FAHRENHEIT, celsiusConversion, fahrenheitReading, null))
        return ConvertedReadingDTO(celsiusConversion, TemperatureUnit.CELSIUS)
    }
}