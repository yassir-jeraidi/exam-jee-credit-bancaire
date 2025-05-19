package com.jeraidi_yassir.server.controllers;

import com.jeraidi_yassir.server.dtos.RemboursementDTO;
import com.jeraidi_yassir.server.services.RemboursementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/remboursements")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class RemboursementController {

    private final RemboursementService remboursementService;

    @GetMapping
    public ResponseEntity<List<RemboursementDTO>> getAllRemboursements() {
        return ResponseEntity.ok(remboursementService.getAllRemboursements());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RemboursementDTO> getRemboursementById(@PathVariable Long id) {
        return ResponseEntity.ok(remboursementService.getRemboursementById(id));
    }

    @GetMapping("/credit/{creditId}")
    public ResponseEntity<List<RemboursementDTO>> getRemboursementsByCreditId(@PathVariable Long creditId) {
        return ResponseEntity.ok(remboursementService.getRemboursementsByCreditId(creditId));
    }

    @PostMapping
    public ResponseEntity<RemboursementDTO> createRemboursement(@RequestBody RemboursementDTO remboursementDTO) {
        return ResponseEntity.ok(remboursementService.createRemboursement(remboursementDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RemboursementDTO> updateRemboursement(@PathVariable Long id, @RequestBody RemboursementDTO remboursementDTO) {
        return ResponseEntity.ok(remboursementService.updateRemboursement(id, remboursementDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRemboursement(@PathVariable Long id) {
        remboursementService.deleteRemboursement(id);
        return ResponseEntity.noContent().build();
    }
}