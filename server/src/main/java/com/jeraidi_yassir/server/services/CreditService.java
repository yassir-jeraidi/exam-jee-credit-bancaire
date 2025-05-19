package com.jeraidi_yassir.server.services;

import com.jeraidi_yassir.server.dtos.ClientDTO;
import com.jeraidi_yassir.server.dtos.CreditDTO;

import java.util.List;

public interface CreditService {
    List<CreditDTO> getAllCredits();

    CreditDTO getCreditById(Long creditId);

    List<CreditDTO> getCreditsByClientId(Long clientId);

    ClientDTO createClient(ClientDTO clientDTO);

    CreditDTO createCredit(CreditDTO creditDTO);

    CreditDTO updateCredit(Long creditId, CreditDTO creditDTO);

    void deleteCredit(Long creditId);
}
