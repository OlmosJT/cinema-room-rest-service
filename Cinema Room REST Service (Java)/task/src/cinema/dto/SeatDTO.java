package cinema.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class SeatDTO {
        private final int row;
        private final int column;
        private final int price;
        @JsonIgnore
        private String token;

        public SeatDTO(int row, int column, int price) {
                this.row = row;
                this.column = column;
                this.price = price;
                this.token = "";
        }

        public void clearToken() { this.setToken(""); }
}
