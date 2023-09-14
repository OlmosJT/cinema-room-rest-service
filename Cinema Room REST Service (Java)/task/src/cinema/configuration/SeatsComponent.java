package cinema.configuration;

import cinema.dto.SeatDTO;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class SeatsComponent {
    private final List<SeatDTO> seats = Collections.synchronizedList(new ArrayList<>());

    @Value("${cinema.rows}")
    private int cinemaRows;

    @Value("${cinema.cols}")
    private int cinemaCols;

    @PostConstruct
    public void init() {
        for (int row = 1; row <= cinemaRows; row++) {
            for (int col = 1; col <= cinemaCols; col++) {
                int price = (row <= 4) ? 10: 8;
                seats.add(new SeatDTO(row, col, price));
            }
        }
    }

    @PreDestroy
    public void destroy() {
        seats.clear();
    }

    public List<SeatDTO> getSeats() {
        return new ArrayList<>(seats);
    }
}
