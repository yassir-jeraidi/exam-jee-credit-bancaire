package com.jeraidi_yassir.server.seeders;


import com.jeraidi_yassir.server.entities.Client;
import com.jeraidi_yassir.server.entities.CreditPersonnel;
import com.jeraidi_yassir.server.entities.Remboursement;
import com.jeraidi_yassir.server.entities.User;
import com.jeraidi_yassir.server.enums.Role;
import com.jeraidi_yassir.server.enums.StatutCredit;
import com.jeraidi_yassir.server.enums.TypeRemboursement;
import com.jeraidi_yassir.server.repositories.ClientRepository;
import com.jeraidi_yassir.server.repositories.CreditRepository;
import com.jeraidi_yassir.server.repositories.RemboursementRepository;
import com.jeraidi_yassir.server.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class DatabaseSeeder implements CommandLineRunner {

    private final ClientRepository clientRepository;
    private final CreditRepository creditRepository;
    private final RemboursementRepository remboursementRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        if (userRepository.count() == 0) {
            userRepository.save(User.builder()
                    .firstName("Admin")
                    .lastName("User")
                    .email("admin@example.com")
                    .password(passwordEncoder.encode("admin123"))
                    .role(Role.ADMIN)
                    .build());
            userRepository.save(User.builder()
                    .firstName("Client")
                    .lastName("User")
                    .email("client@example.com")
                    .password(passwordEncoder.encode("client124"))
                    .role(Role.CLIENT)
                    .build());
            userRepository.save(User.builder()
                    .firstName("Employee")
                    .lastName("User")
                    .email("employee@example.com")
                    .password(passwordEncoder.encode("employee123"))
                    .role(Role.EMPLOYE)
                    .build());
        }

        // Create a client
        Client client = new Client();
        client.setNom("Jean Dupont");
        client.setEmail("jean.dupont@example.com");
        clientRepository.save(client);

        // Create a personal credit
        CreditPersonnel creditPersonnel = new CreditPersonnel();
        creditPersonnel.setClient(client);
        creditPersonnel.setDateDemande(LocalDate.now());
        creditPersonnel.setStatut(StatutCredit.EN_COURS);
        creditPersonnel.setMontant(5000.0);
        creditPersonnel.setDureeRemboursement(24);
        creditPersonnel.setTauxInteret(3.5);
        creditPersonnel.setMotif("Achat voiture");
        creditRepository.save(creditPersonnel);

        // Create a reimbursement
        Remboursement remboursement = new Remboursement();
        remboursement.setCredit(creditPersonnel);
        remboursement.setDateRemboursement(LocalDate.now());
        remboursement.setMontant(200.0);
        remboursement.setType(TypeRemboursement.MENSUALITE);
        remboursementRepository.save(remboursement);

        // Print all clients
        System.out.println("Clients: " + clientRepository.findAll());
    }
}