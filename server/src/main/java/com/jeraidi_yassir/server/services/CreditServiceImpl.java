package com.jeraidi_yassir.server.services;

import com.jeraidi_yassir.server.dtos.ClientDTO;
import com.jeraidi_yassir.server.dtos.CreditDTO;
import com.jeraidi_yassir.server.entities.Client;
import com.jeraidi_yassir.server.entities.Credit;
import com.jeraidi_yassir.server.mappers.CreditMapper;
import com.jeraidi_yassir.server.repositories.ClientRepository;
import com.jeraidi_yassir.server.repositories.CreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CreditServiceImpl implements CreditService {

    private final CreditRepository creditRepository;
    private final ClientRepository clientRepository;
    private final CreditMapper creditMapper;

    @Autowired
    public CreditServiceImpl(CreditRepository creditRepository, ClientRepository clientRepository,
            CreditMapper creditMapper) {
        this.creditRepository = creditRepository;
        this.clientRepository = clientRepository;
        this.creditMapper = creditMapper;
    }

    @Override
    public List<CreditDTO> getAllCredits() {
        return creditRepository.findAll().stream()
                .map(creditMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CreditDTO getCreditById(Long creditId) {
        Credit credit = creditRepository.findById(creditId)
                .orElseThrow(() -> new RuntimeException("Credit not found"));
        return creditMapper.toDto(credit);
    }

    @Override
    public List<CreditDTO> getCreditsByClientId(Long clientId) {
        return creditRepository.findByClientId(clientId).stream()
                .map(creditMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CreditDTO createCredit(CreditDTO creditDTO) {
        Client client = clientRepository.findById(creditDTO.getClientId())
                .orElseThrow(() -> new RuntimeException("Client not found"));

        Credit credit = creditMapper.toEntity(creditDTO);
        credit.setClient(client);

        Credit savedCredit = creditRepository.save(credit);
        return creditMapper.toDto(savedCredit);
    }

    @Override
    public CreditDTO updateCredit(Long creditId, CreditDTO creditDTO) {
        Credit existingCredit = creditRepository.findById(creditId)
                .orElseThrow(() -> new RuntimeException("Credit not found"));

        Client client = clientRepository.findById(creditDTO.getClientId())
                .orElseThrow(() -> new RuntimeException("Client not found"));

        Credit credit = creditMapper.toEntity(creditDTO);
        credit.setId(creditId);
        credit.setClient(client);

        Credit updatedCredit = creditRepository.save(credit);
        return creditMapper.toDto(updatedCredit);
    }

    @Override
    public void deleteCredit(Long creditId) {
        creditRepository.deleteById(creditId);
    }

    @Override
    public ClientDTO createClient(ClientDTO clientDTO) {
        return null;
    }
}
