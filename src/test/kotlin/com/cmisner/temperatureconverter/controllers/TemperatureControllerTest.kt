package com.cmisner.temperatureconverter.controllers

import com.cmisner.temperatureconverter.models.AverageTemperatures
import com.cmisner.temperatureconverter.models.Temperature
import com.cmisner.temperatureconverter.models.TemperatureUnit
import com.cmisner.temperatureconverter.services.TemperatureAverageService
import com.cmisner.temperatureconverter.services.TemperatureConversionService
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest
internal class TemperatureControllerTest(
    @Autowired val mockMvc: MockMvc,
) {

    @MockkBean lateinit var temperatureConversionService: TemperatureConversionService
    @MockkBean lateinit var temperatureAverageService: TemperatureAverageService
    val mapper = jacksonObjectMapper()

    @Test
    fun `SHOULD respond with OK and converted temperature WHEN temperature requested`(){
        val inputTemperature = Temperature(31.0, TemperatureUnit.CELSIUS)
        val expectedConvertedTemperature = Temperature(87.8, TemperatureUnit.FAHRENHEIT)

        every { temperatureConversionService.convertTemperature(inputTemperature) } returns expectedConvertedTemperature

        mockMvc.perform(post("/convert-temperature").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(inputTemperature)))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.value").value(87.8))
    }
    @Test
    fun `SHOULD respond with OK and average temperatures WHEN average requested`(){
        val expectedConvertedTemperature = AverageTemperatures(
            Temperature(31.0, TemperatureUnit.CELSIUS),
            Temperature(87.8, TemperatureUnit.FAHRENHEIT)
        )

        every { temperatureAverageService.get7DayAverageTemperature() } returns expectedConvertedTemperature

        mockMvc.perform(get("/seven-day-average").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.celsiusTemperatureAverage.value").value(31.0))
    }
}