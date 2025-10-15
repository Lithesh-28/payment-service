package com.ivoyant.payment_service.entity;

import jakarta.persistence.*;
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

    private Integer bookingId;
    private Double amount;
    private String status;             // SUCCESS, FAILED, PENDING
    private LocalDateTime date;
}

