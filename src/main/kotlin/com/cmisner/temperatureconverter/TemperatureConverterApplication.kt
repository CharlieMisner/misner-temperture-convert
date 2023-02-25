package com.cmisner.temperatureconverter

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TemperatureConverterApplication

fun main(args: Array<String>) {
	runApplication<TemperatureConverterApplication>(*args)
}
