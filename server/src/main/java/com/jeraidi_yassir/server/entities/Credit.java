package com.jeraidi_yassir.server.entities;

import com.jeraidi_yassir.server.enums.StatutCredit;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "credits")
@Data
public abstract class Credit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_demande")
    private LocalDate dateDemande;

    @Enumerated(EnumType.STRING)
    private StatutCredit statut;

    @Column(name = "date_acceptation")
    private LocalDate dateAcceptation;

    private Double montant;

    @Column(name = "duree_remboursement")
    private Integer dureeRemboursement;

    @Column(name = "taux_interet")
    private Double tauxInteret;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(mappedBy = "credit", cascade = CascadeType.ALL)
    private List<Remboursement> remboursements;

}

