package com.jeraidi_yassir.server.entities;

import com.jeraidi_yassir.server.enums.TypeBien;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "credits_immobiliers")
@Data
public class CreditImmobilier extends Credit {
    @Enumerated(EnumType.STRING)
    private TypeBien typeBien;
}

