package cinema.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public record PurchaseRequest(
        @Min(1)
        @Max(9)
        int row,
        @Min(1)
        @Max(9)
        int column
) { }
