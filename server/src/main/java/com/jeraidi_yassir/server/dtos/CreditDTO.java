package com.jeraidi_yassir.server.dtos;

import com.jeraidi_yassir.server.enums.StatutCredit;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreditDTO {
    private Long id;
    private LocalDate dateDemande;
    private StatutCredit statut;
    private LocalDate dateAcceptation;
    private Double montant;
    private Integer dureeRemboursement;
    private Double tauxInteret;
    private Long clientId;
    private String type; // "PERSONNEL", "IMMOBILIER", or "PROFESSIONNEL"
}
