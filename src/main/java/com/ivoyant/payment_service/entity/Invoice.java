package com.ivoyant.payment_service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Booking ID is required for invoice generation")
    @Positive(message = "Booking ID must be positive")
    private Integer bookingId;

    @NotBlank(message = "Invoice URL cannot be blank")
    private String invoicePdfUrl;
}


