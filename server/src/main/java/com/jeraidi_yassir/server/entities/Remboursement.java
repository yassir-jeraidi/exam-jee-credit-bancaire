package com.jeraidi_yassir.server.entities;

import com.jeraidi_yassir.server.enums.TypeRemboursement;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OptimisticLocking;

import java.time.LocalDate;

@Entity
@Table(name = "remboursements")
@Data
public class Remboursement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_remboursement")
    private LocalDate dateRemboursement;

    private Double montant;

    @Enumerated(EnumType.STRING)
    private TypeRemboursement type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "credit_id")
    private Credit credit;

    @Version
    private Long version;
}
