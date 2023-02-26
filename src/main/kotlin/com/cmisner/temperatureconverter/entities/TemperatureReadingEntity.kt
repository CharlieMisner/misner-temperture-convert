package com.cmisner.temperatureconverter.entities

import com.cmisner.temperatureconverter.models.TemperatureUnit
import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.sql.Timestamp
import java.util.*

@Entity
@EntityListeners(AuditingEntityListener::class)
@Table(name="temperature_readings")
class TemperatureReadingEntity(
    @Enumerated(EnumType.STRING)
    val receivedTemperatureUnit: TemperatureUnit,
    val celsiusTemperature: Double,
    val fahrenheightTemperture: Double,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @CreatedDate
    var created_date: Timestamp? = null
)