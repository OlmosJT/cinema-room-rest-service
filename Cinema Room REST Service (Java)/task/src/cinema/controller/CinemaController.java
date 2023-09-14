package cinema.controller;

import cinema.dto.CinemaDTO;
import cinema.dto.PurchaseRequest;
import cinema.dto.PurchaseResponse;
import cinema.dto.TicketRefundRequest;
import cinema.service.CinemaService;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CinemaController {
    private final CinemaService service;

    public CinemaController(CinemaService service) {
        this.service = service;
    }

    @GetMapping("/seats")
    public CinemaDTO getCinemaInfo() {
        return service.getCinemaInfoWithAvailableSeats();
    }

    @PostMapping("/purchase")
    @Validated
    public ResponseEntity<?> purchaseSeat(@RequestBody PurchaseRequest request) {
        return new ResponseEntity<PurchaseResponse>(
                service.purchaseSeat(request.row(), request.column()),
                HttpStatus.OK
        );
    }

    @PostMapping("/return")
    public ResponseEntity<?> refundTicket(@RequestBody TicketRefundRequest request) {
        var body = service.returnTicket(request.token());
        return new ResponseEntity<>(
                body,
                HttpStatus.OK
        );
    }

}
