package cinema.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.List;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record CinemaDTO(
        int totalRows,
        int totalColumns,
        List<SeatDTO> availableSeats
) {

}
