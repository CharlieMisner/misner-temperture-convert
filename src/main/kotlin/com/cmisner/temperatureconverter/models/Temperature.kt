package com.cmisner.temperatureconverter.models

import com.cmisner.temperatureconverter.enums.TemperatureUnit

data class Temperature(
    val temperature: Double,
    val temperatureUnit: TemperatureUnit
)