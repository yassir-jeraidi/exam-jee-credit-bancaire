package com.jeraidi_yassir.server.services;

import com.jeraidi_yassir.server.dtos.RemboursementDTO;
import java.util.List;

public interface RemboursementService {
    List<RemboursementDTO> getAllRemboursements();

    RemboursementDTO getRemboursementById(Long remboursementId);

    List<RemboursementDTO> getRemboursementsByCreditId(Long creditId);

    RemboursementDTO createRemboursement(RemboursementDTO remboursementDTO);

    RemboursementDTO updateRemboursement(Long remboursementId, RemboursementDTO remboursementDTO);

    void deleteRemboursement(Long remboursementId);
}