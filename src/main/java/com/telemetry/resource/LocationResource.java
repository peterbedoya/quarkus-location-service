package com.telemetry.resource;

import com.telemetry.dto.LocationRequest;
import com.telemetry.entity.Location;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Path("/api/locations")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LocationResource {

    @POST
    @Transactional
    public Response saveLocation(@Valid LocationRequest request) {
        Location location = new Location();
        location.deviceId = request.deviceId();
        location.latitude = request.latitude();
        location.longitude = request.longitude();
        location.recordedAt = (request.recordedAt() != null) ? request.recordedAt() : LocalDateTime.now();

        location.persist();
        return Response.status(Response.Status.CREATED).entity(location).build();
    }

    @GET
    @Path("/by-hour")
    public List<Location> getByHour(
            @QueryParam("deviceId") String deviceId,
            @QueryParam("date") String dateStr,
            @QueryParam("hour") @DefaultValue("0") int hour) {

        if (dateStr == null || dateStr.isBlank()) {
            throw new BadRequestException("El parámetro 'date' (formato YYYY-MM-DD) es obligatorio.");
        }
        if (hour < 0 || hour > 23) {
            throw new BadRequestException("El parámetro 'hour' debe estar entre 0 y 23.");
        }

        LocalDate date = LocalDate.parse(dateStr);
        return Location.findByHour(deviceId, date, hour);
    }
}
