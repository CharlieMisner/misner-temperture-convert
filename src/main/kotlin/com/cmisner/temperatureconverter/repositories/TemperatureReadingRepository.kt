package com.cmisner.temperatureconverter.repositories

import com.cmisner.temperatureconverter.entities.TemperatureReadingEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TemperatureReadingRepository : JpaRepository<TemperatureReadingEntity, Long>