package cinema.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class CustomErrorMessage {
    private int statusCode;
    private LocalDateTime timestamp;
    private String error;
    private String message;

}
