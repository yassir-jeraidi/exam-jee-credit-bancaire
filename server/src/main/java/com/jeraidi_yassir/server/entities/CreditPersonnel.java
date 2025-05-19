package com.jeraidi_yassir.server.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "credits_personnels")
@Data
public class CreditPersonnel extends Credit {
    private String motif;
}