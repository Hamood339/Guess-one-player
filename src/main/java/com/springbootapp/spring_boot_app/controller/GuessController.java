package com.springbootapp.spring_boot_app.controller;

import com.springbootapp.spring_boot_app.service.DTO.GuessRequest;
import com.springbootapp.spring_boot_app.service.GuessService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/plouf")
public class GuessController {

    private final GuessService guessService;

    public GuessController(GuessService guessService) {
        this.guessService = guessService;
    }

    // 🎲 Tirer un nom au hasard
    @PostMapping("/draw")
    public ResponseEntity<String> draw(@RequestBody GuessRequest request) {
        String chosen = guessService.getRandomName(request);
        return ResponseEntity.ok(chosen);
    }

    // ✅ Voir la liste des noms déjà choisis
    @GetMapping("/chosen")
    public ResponseEntity<Set<String>> getChosen() {
        return ResponseEntity.ok(guessService.getChosen());
    }

    // ✅ Voir la liste des noms restants
    @GetMapping("/remaining")
    public ResponseEntity<List<String>> getRemaining() {
        return ResponseEntity.ok(guessService.getRemaining());
    }

    // 🔄 Réinitialiser le jeu
    @DeleteMapping("/reset")
    public ResponseEntity<String> reset() {
        guessService.reset();
        return ResponseEntity.ok("✅ Le jeu a été réinitialisé !");
    }

    // Remettre un nom dans les participants
    @PostMapping("/remove")
    public ResponseEntity<Map<String, Object>> remove(@RequestBody Map<String, String> request) {
        String name = request.get("name");
        guessService.removeName(name);

        Map<String, Object> response = new HashMap<>();
        response.put("remaining", guessService.getRemaining());
        response.put("chosen", guessService.getChosen());

        return ResponseEntity.ok(response);
    }
}