package com.jeraidi_yassir.server.mappers;

import com.jeraidi_yassir.server.dtos.RemboursementDTO;
import com.jeraidi_yassir.server.entities.Remboursement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RemboursementMapper {
    @Mapping(target = "creditId", source = "credit.id")
    RemboursementDTO toDto(Remboursement remboursement);
    
    @Mapping(target = "credit", ignore = true)
    Remboursement toEntity(RemboursementDTO remboursementDTO);
} 