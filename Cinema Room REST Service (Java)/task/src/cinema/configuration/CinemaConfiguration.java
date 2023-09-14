package cinema.configuration;

import cinema.dto.CinemaDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CinemaConfiguration {
    @Value("${cinema.rows}")
    private int cinemaRows;

    @Value("${cinema.cols}")
    private int cinemaCols;

    @Bean("singleton")
    public CinemaDTO cinemaRoom(SeatsComponent seatsComponent) {
        return new CinemaDTO(cinemaRows, cinemaCols, seatsComponent.getSeats());
    }
}
