package com.cmisner.temperatureconverter.services

import com.cmisner.temperatureconverter.models.AverageTemperatures
import com.cmisner.temperatureconverter.models.Temperature
import com.cmisner.temperatureconverter.models.TemperatureUnit
import com.cmisner.temperatureconverter.repositories.TemperatureReadingRepository
import mu.KotlinLogging
import org.springframework.stereotype.Service
import java.sql.Timestamp
import java.time.LocalDateTime

@Service
class TemperatureAverageService(private val temperatureReadingRepository: TemperatureReadingRepository) {

    private val logger = KotlinLogging.logger {  }

    /**
     * Retreives the past 7 days temperature readings and returns averages.
     */
    fun get7DayAverageTemperature(): AverageTemperatures{
        val sevenDaysAgo = Timestamp.valueOf(LocalDateTime.now().minusDays(7))
        val sevenDaysTempReadings = temperatureReadingRepository.findAllWithCreationDateAfter(sevenDaysAgo)
        if (sevenDaysTempReadings.isEmpty()){
            logger.error { "There are no temperature readings in the last week" }
        }
        var celsiusSum = 0.0
        var fahrenheitSum = 0.0
        sevenDaysTempReadings.forEach {
            celsiusSum += it.celsiusTemperature
            fahrenheitSum += it.fahrenheightTemperture
        }
        return AverageTemperatures(
            Temperature(roundTemperature(celsiusSum / sevenDaysTempReadings.size.toDouble()), TemperatureUnit.CELSIUS),
            Temperature(roundTemperature(fahrenheitSum / sevenDaysTempReadings.size.toDouble()), TemperatureUnit.FAHRENHEIT),
        )
    }
}
