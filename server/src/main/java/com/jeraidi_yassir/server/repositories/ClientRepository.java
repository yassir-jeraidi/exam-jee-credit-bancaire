package com.jeraidi_yassir.server.repositories;

import com.jeraidi_yassir.server.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}