package com.jeraidi_yassir.server.mappers;

import com.jeraidi_yassir.server.dtos.ClientDTO;
import com.jeraidi_yassir.server.dtos.CreditDTO;
import com.jeraidi_yassir.server.entities.Client;
import com.jeraidi_yassir.server.entities.Credit;
import com.jeraidi_yassir.server.entities.CreditImmobilier;
import com.jeraidi_yassir.server.entities.CreditPersonnel;
import com.jeraidi_yassir.server.entities.CreditProfessionnel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = { CreditMapper.class })
public interface ClientMapper {
    @Mapping(target = "credits", source = "credits", qualifiedByName = "mapCredits")
    ClientDTO toDto(Client client);

    Client toEntity(ClientDTO clientDTO);

    @Named("mapCredits")
    default List<CreditDTO> mapCredits(List<Credit> credits) {
        if (credits == null) {
            return new ArrayList<>();
        }
        return credits.stream()
                .map(credit -> {
                    CreditDTO creditDTO = new CreditDTO();
                    creditDTO.setId(credit.getId());
                    creditDTO.setDateDemande(credit.getDateDemande());
                    creditDTO.setStatut(credit.getStatut());
                    creditDTO.setDateAcceptation(credit.getDateAcceptation());
                    creditDTO.setMontant(credit.getMontant());
                    creditDTO.setDureeRemboursement(credit.getDureeRemboursement());
                    creditDTO.setTauxInteret(credit.getTauxInteret());
                    creditDTO.setClientId(credit.getClient().getId());
                    if (credit instanceof CreditPersonnel) {
                        creditDTO.setType("PERSONNEL");
                    } else if (credit instanceof CreditImmobilier) {
                        creditDTO.setType("IMMOBILIER");
                    } else if (credit instanceof CreditProfessionnel) {
                        creditDTO.setType("PROFESSIONNEL");
                    }
                    return creditDTO;
                })
                .collect(Collectors.toList());
    }
}