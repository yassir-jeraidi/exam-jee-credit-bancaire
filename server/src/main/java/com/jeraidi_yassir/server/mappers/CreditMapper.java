package com.jeraidi_yassir.server.mappers;

import com.jeraidi_yassir.server.dtos.ClientDTO;
import com.jeraidi_yassir.server.entities.Client;
import com.jeraidi_yassir.server.dtos.CreditDTO;
import com.jeraidi_yassir.server.entities.Credit;
import com.jeraidi_yassir.server.entities.CreditImmobilier;
import com.jeraidi_yassir.server.entities.CreditPersonnel;
import com.jeraidi_yassir.server.entities.CreditProfessionnel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface CreditMapper {
    @Mapping(target = "clientId", source = "client.id")
    @Mapping(target = "type", source = ".", qualifiedByName = "getCreditType")
    CreditDTO toDto(Credit credit);

    @Mapping(target = "client", ignore = true)
    default Credit toEntity(CreditDTO creditDTO) {
        if (creditDTO == null) {
            return null;
        }

        Credit credit = switch (creditDTO.getType()) {
            case "PERSONNEL" -> new CreditPersonnel();
            case "IMMOBILIER" -> new CreditImmobilier();
            case "PROFESSIONNEL" -> new CreditProfessionnel();
            default -> throw new IllegalArgumentException("Unknown credit type: " + creditDTO.getType());
        };

        credit.setId(creditDTO.getId());
        credit.setDateDemande(creditDTO.getDateDemande());
        credit.setStatut(creditDTO.getStatut());
        credit.setDateAcceptation(creditDTO.getDateAcceptation());
        credit.setMontant(creditDTO.getMontant());
        credit.setDureeRemboursement(creditDTO.getDureeRemboursement());
        credit.setTauxInteret(creditDTO.getTauxInteret());

        return credit;
    }

    @Named("getCreditType")
    default String getCreditType(Credit credit) {
        if (credit instanceof CreditPersonnel) {
            return "PERSONNEL";
        } else if (credit instanceof CreditImmobilier) {
            return "IMMOBILIER";
        } else if (credit instanceof CreditProfessionnel) {
            return "PROFESSIONNEL";
        }
        return null;
    }
}