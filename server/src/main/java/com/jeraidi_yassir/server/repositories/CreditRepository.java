package com.jeraidi_yassir.server.repositories;

import com.jeraidi_yassir.server.entities.Credit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditRepository extends JpaRepository<Credit, Long> {
}