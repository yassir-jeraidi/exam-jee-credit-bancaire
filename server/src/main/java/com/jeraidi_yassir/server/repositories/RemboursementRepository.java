package com.jeraidi_yassir.server.repositories;

import com.jeraidi_yassir.server.entities.Remboursement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RemboursementRepository extends JpaRepository<Remboursement, Long> {
}