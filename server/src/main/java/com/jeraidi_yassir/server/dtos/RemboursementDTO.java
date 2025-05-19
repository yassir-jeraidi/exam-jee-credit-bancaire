package com.jeraidi_yassir.server.dtos;

import lombok.Data;
import java.time.LocalDate;

@Data
public class RemboursementDTO {
    private Long id;
    private LocalDate dateRemboursement;
    private Double montant;
    private Long creditId;
}