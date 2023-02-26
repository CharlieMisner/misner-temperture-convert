package com.cmisner.temperatureconverter.dto

import com.cmisner.temperatureconverter.enums.TemperatureUnit

data class ConvertedReadingDTO(
    val temperature: Double,
    val temperatureUnit: TemperatureUnit
)