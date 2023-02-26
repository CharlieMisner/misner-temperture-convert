package com.cmisner.temperatureconverter.services

import com.cmisner.temperatureconverter.dto.ConvertedReadingDTO
import com.cmisner.temperatureconverter.entities.TemperatureReadingEntity
import com.cmisner.temperatureconverter.enums.TemperatureUnit
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
    fun convertCelsiusToFahrenheit(celsiusReading: Double): ConvertedReadingDTO{
        val fahrenheitConversion: Double = celsiusReading * (5/9) + 32
        logger.info { "Converted $celsiusReading degrees celsius to $fahrenheitConversion degrees fahrenheit" }
        temperatureReadingRepository.save(TemperatureReadingEntity(0, TemperatureUnit.CELSIUS, celsiusReading, fahrenheitConversion, null))
        return ConvertedReadingDTO(fahrenheitConversion, TemperatureUnit.FAHRENHEIT)
    }

    fun convertFahrenheitToCelsius(fahrenheitReading: Double): ConvertedReadingDTO {
        val celsiusConversion: Double = fahrenheitReading * (9/5) - 32
        logger.info { "Converted $fahrenheitReading degrees fahrenheit to $celsiusConversion degrees celsius" }
        temperatureReadingRepository.save(TemperatureReadingEntity(0, TemperatureUnit.FAHRENHEIT, celsiusConversion, fahrenheitReading, null))
        return ConvertedReadingDTO(celsiusConversion, TemperatureUnit.CELSIUS)
    }
}