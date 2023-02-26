package com.cmisner.temperatureconverter.controllers

import com.cmisner.temperatureconverter.models.Temperature
import com.cmisner.temperatureconverter.models.TemperatureUnit
import com.cmisner.temperatureconverter.services.TemperatureConversionService
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


//@ExtendWith(SpringExtension::class)
//@WebMvcTest(TemperatureController::class)
@WebMvcTest
internal class TemperatureControllerTest(
    @Autowired val mockMvc: MockMvc,
) {

    @MockkBean lateinit var temperatureConversionService: TemperatureConversionService
    val mapper = jacksonObjectMapper()

    @Test
    fun `SHOULD convert temperature to Fahrenheit WHEN Celsius temperature provided`(){
        val inputTemperature = Temperature(31.0, TemperatureUnit.CELSIUS)
        val expectedConvertedTemperature = Temperature(87.8, TemperatureUnit.FAHRENHEIT)

        every { temperatureConversionService.convertTemperature(inputTemperature) } returns expectedConvertedTemperature

        mockMvc.perform(post("/convert-temperature").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(inputTemperature)))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.temperature").value(87.8))
    }
}