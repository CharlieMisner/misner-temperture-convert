package com.cmisner.temperatureconverter.services

/**
 * Rounds temperature value to one decimal place for readability, e.g. 12.7778 -> 12.8
 *
 * @param temperature: Double
 */
fun roundTemperature(temperature: Double): Double{
    return String.format("%.1f", temperature).toDouble()
}