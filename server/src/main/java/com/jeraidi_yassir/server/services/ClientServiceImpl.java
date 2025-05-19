package com.jeraidi_yassir.server.services;

import com.jeraidi_yassir.server.dtos.ClientDTO;
import com.jeraidi_yassir.server.entities.Client;
import com.jeraidi_yassir.server.mappers.ClientMapper;
import com.jeraidi_yassir.server.repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Override
    public List<ClientDTO> getAllClients() {
        return clientRepository.findAll().stream()
                .map(clientMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ClientDTO getClientById(Long clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));
        return clientMapper.toDto(client);
    }

    @Override
    public ClientDTO createClient(ClientDTO clientDTO) {
        Client client = clientMapper.toEntity(clientDTO);
        Client savedClient = clientRepository.save(client);
        return clientMapper.toDto(savedClient);
    }

    @Override
    public ClientDTO updateClient(Long clientId, ClientDTO clientDTO) {
        Client existingClient = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        Client client = clientMapper.toEntity(clientDTO);
        client.setId(clientId);

        Client updatedClient = clientRepository.save(client);
        return clientMapper.toDto(updatedClient);
    }

    @Override
    public void deleteClient(Long clientId) {
        clientRepository.deleteById(clientId);
    }
}
