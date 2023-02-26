package com.cmisner.temperatureconverter.entities

import com.cmisner.temperatureconverter.enums.TemperatureUnit
import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import java.time.OffsetDateTime

@Entity
@Table(name="temperature_readings")
class TemperatureReadingEntity(
    @Id
    @GeneratedValue
    val id: Long = 0,
    @Enumerated(EnumType.STRING)
    val receivedTemperatureUnit: TemperatureUnit,
    val celsiusTemperature: Double,
    val fahrenheightTemperture: Double,
    @CreatedDate
    val created_at: OffsetDateTime?
)