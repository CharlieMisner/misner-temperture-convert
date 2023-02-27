package com.cmisner.temperatureconverter.repositories

import com.cmisner.temperatureconverter.entities.TemperatureReadingEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.sql.Timestamp

interface TemperatureReadingRepository : JpaRepository<TemperatureReadingEntity, Long>{
    @Query("from TemperatureReadingEntity temp where temp.createdDate >= :createdDate")
    fun findAllWithCreationDateAfter(
        @Param("createdDate") createdDate: Timestamp
    ): List<TemperatureReadingEntity>
}