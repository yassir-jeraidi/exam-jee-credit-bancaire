package com.jeraidi_yassir.server.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "credits_professionnels")
@Data
public class CreditProfessionnel extends Credit {
    private String motif;

    @Column(name = "raison_sociale")
    private String raisonSociale;

}