package com.example.librarymanagement.dto.request;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookUpdateStockDTO {
    @Min(value = 0, message = "stock must be greater than or equal to 0")
    private Integer stock;
}
