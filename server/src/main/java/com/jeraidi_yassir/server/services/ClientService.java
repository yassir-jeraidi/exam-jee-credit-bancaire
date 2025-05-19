package com.jeraidi_yassir.server.services;

import com.jeraidi_yassir.server.dtos.ClientDTO;
import com.jeraidi_yassir.server.dtos.CreditDTO;

import java.util.List;

public interface ClientService {
    List<ClientDTO> getAllClients();
    ClientDTO getClientById(Long clientId);
    ClientDTO createClient(ClientDTO clientDTO);
    ClientDTO updateClient(Long clientId, ClientDTO clientDTO);
    void deleteClient(Long clientId);
}
