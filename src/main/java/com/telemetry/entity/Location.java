package com.telemetry.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "locations", indexes = {
    @Index(name = "idx_device_timestamp", columnList = "deviceId, recordedAt")
})
public class Location extends PanacheEntity {

    @Column(nullable = false)
    public String deviceId;

    @Column(nullable = false)
    public Double latitude;

    @Column(nullable = false)
    public Double longitude;

    @Column(nullable = false)
    public LocalDateTime recordedAt;

    public static List<Location> findByHour(String deviceId, LocalDate date, int hour) {
        LocalDateTime start = date.atTime(hour, 0, 0);
        LocalDateTime end = start.plusHours(1);

        if (deviceId != null && !deviceId.isBlank()) {
            return list("deviceId = ?1 and recordedAt >= ?2 and recordedAt < ?3 order by recordedAt asc",
                    deviceId, start, end);
        }
        return list("recordedAt >= ?1 and recordedAt < ?2 order by recordedAt asc", start, end);
    }
}
