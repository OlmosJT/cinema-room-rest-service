package cinema.service;

import cinema.dto.CinemaDTO;
import cinema.dto.PurchaseResponse;
import cinema.dto.SeatDTO;
import cinema.exception.SeatNotFoundException;
import cinema.exception.TicketNotFoundException;
import cinema.exception.WrongTokenException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CinemaService {
    private final CinemaDTO cinema;


    public CinemaService(CinemaDTO cinema) {
        this.cinema = cinema;
    }


    public CinemaDTO getCinemaInfoWithAvailableSeats() {
        List<SeatDTO> availableSeats = cinema.availableSeats().stream()
                .filter(seat -> seat.getToken().isEmpty())
                .toList();

        return new CinemaDTO(cinema.totalRows(), cinema.totalColumns(), availableSeats);
    }

    public PurchaseResponse purchaseSeat(int row, int column) {
        Optional<SeatDTO> seatOptional = cinema.availableSeats().stream()
                .filter(seat -> seat.getRow() == row && seat.getColumn() == column)
                .findFirst();

        if(seatOptional.isEmpty()) {
            throw new SeatNotFoundException("The number of a row or a column is out of bounds!");
        } else if (!seatOptional.get().getToken().isEmpty()) {
            throw new TicketNotFoundException("The ticket has been already purchased!");
        }

        seatOptional.get().setToken(UUID.randomUUID().toString());

        return new PurchaseResponse(seatOptional.get().getToken(), seatOptional.get());
    }

    public Map<String, Object> returnTicket(String token) {
        Map<String, Object> response = new HashMap<>();

        SeatDTO seat = cinema.availableSeats().stream()
                        .filter(s -> Objects.equals(s.getToken(), token))
                        .findFirst()
                        .orElseThrow(()-> new WrongTokenException("Wrong token!"));
        seat.clearToken();
        response.put("returned_ticket", seat);
        return response;
    }
}
