package com.ivoyant.payment_service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Booking ID must be provided")
    @Positive(message = "Booking ID must be a positive number")
    private Integer bookingId;

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be greater than 0")
    private Double amount;

    @Pattern(
            regexp = "^(SUCCESS|FAILED|PENDING|REFUNDED)$",
            message = "Status must be one of: SUCCESS, FAILED, PENDING, REFUNDED"
    )
    private String status;

    private LocalDateTime date;
}
