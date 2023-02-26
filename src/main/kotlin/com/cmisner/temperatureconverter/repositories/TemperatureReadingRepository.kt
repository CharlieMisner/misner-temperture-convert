package com.cmisner.temperatureconverter.repositories

import com.cmisner.temperatureconverter.entities.TemperatureReadingEntity
import org.springframework.data.jpa.repository.JpaRepository

interface TemperatureReadingRepository : JpaRepository<TemperatureReadingEntity, Long>