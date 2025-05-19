package com.jeraidi_yassir.server.services;

import com.jeraidi_yassir.server.dtos.RemboursementDTO;
import com.jeraidi_yassir.server.entities.Credit;
import com.jeraidi_yassir.server.entities.Remboursement;
import com.jeraidi_yassir.server.mappers.RemboursementMapper;
import com.jeraidi_yassir.server.repositories.CreditRepository;
import com.jeraidi_yassir.server.repositories.RemboursementRepository;
import jakarta.persistence.OptimisticLockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RemboursementServiceImpl implements RemboursementService {

    private final RemboursementRepository remboursementRepository;
    private final CreditRepository creditRepository;
    private final RemboursementMapper remboursementMapper;

    @Autowired
    public RemboursementServiceImpl(RemboursementRepository remboursementRepository,
            CreditRepository creditRepository,
            RemboursementMapper remboursementMapper) {
        this.remboursementRepository = remboursementRepository;
        this.creditRepository = creditRepository;
        this.remboursementMapper = remboursementMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<RemboursementDTO> getAllRemboursements() {
        return remboursementRepository.findAll().stream()
                .map(remboursementMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public RemboursementDTO getRemboursementById(Long remboursementId) {
        Remboursement remboursement = remboursementRepository.findById(remboursementId)
                .orElseThrow(() -> new RuntimeException("Remboursement not found"));
        return remboursementMapper.toDto(remboursement);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RemboursementDTO> getRemboursementsByCreditId(Long creditId) {
        return remboursementRepository.findByCreditId(creditId).stream()
                .map(remboursementMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public RemboursementDTO createRemboursement(RemboursementDTO remboursementDTO) {
        try {
            Credit credit = creditRepository.findById(remboursementDTO.getCreditId())
                    .orElseThrow(() -> new RuntimeException("Credit not found"));

            Remboursement remboursement = remboursementMapper.toEntity(remboursementDTO);
            remboursement.setCredit(credit);
            remboursement.setVersion(0L); // Initialize version

            Remboursement savedRemboursement = remboursementRepository.save(remboursement);
            return remboursementMapper.toDto(savedRemboursement);
        } catch (OptimisticLockException e) {
            throw new RuntimeException("Concurrent modification detected. Please try again.");
        }
    }

    @Override
    public RemboursementDTO updateRemboursement(Long remboursementId, RemboursementDTO remboursementDTO) {
        try {
            Remboursement existingRemboursement = remboursementRepository.findById(remboursementId)
                    .orElseThrow(() -> new RuntimeException("Remboursement not found"));

            Credit credit = creditRepository.findById(remboursementDTO.getCreditId())
                    .orElseThrow(() -> new RuntimeException("Credit not found"));

            Remboursement remboursement = remboursementMapper.toEntity(remboursementDTO);
            remboursement.setId(remboursementId);
            remboursement.setCredit(credit);
            remboursement.setVersion(existingRemboursement.getVersion());

            Remboursement updatedRemboursement = remboursementRepository.save(remboursement);
            return remboursementMapper.toDto(updatedRemboursement);
        } catch (OptimisticLockException e) {
            throw new RuntimeException("Concurrent modification detected. Please try again.");
        }
    }

    @Override
    public void deleteRemboursement(Long remboursementId) {
        remboursementRepository.deleteById(remboursementId);
    }
}