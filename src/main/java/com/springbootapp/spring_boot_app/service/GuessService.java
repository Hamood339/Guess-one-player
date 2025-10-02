package com.springbootapp.spring_boot_app.service;

import com.springbootapp.spring_boot_app.service.DTO.GuessRequest;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GuessService {

    private final Random random = new Random();

    private Set<String> dejaChoisis = new HashSet<>();

    private List<String> lastNames = new ArrayList<>();


    public String getRandomName(GuessRequest guess) {
        List<String> noms = guess.getName();

        if (noms == null || noms.isEmpty()) {
            throw new IllegalArgumentException("⚠️ Vous devez saisir au moins un nom !");
        }
        lastNames = noms;

        if (dejaChoisis.containsAll(noms)) {
            return "🎉 Tout le monde est déjà passé !";
        }
        List<String> restants = new ArrayList<>(noms);
        restants.removeAll(dejaChoisis);

        int index = random.nextInt(restants.size());
        String choisi = restants.get(index);
        dejaChoisis.add(choisi);
        return choisi;
    }

    public Set<String> getChosen() {
        return new HashSet<>(dejaChoisis);
    }

    public List<String> getRemaining() {
        if (lastNames.isEmpty()) {
            return Collections.emptyList();
        }
        List<String> restants = new ArrayList<>(lastNames);
        restants.removeAll(dejaChoisis);
        return restants;
    }

    public void reset() {
        dejaChoisis.clear();
        lastNames.clear();
    }
    public void removeName(String name) {
        if (name != null) {
            dejaChoisis.remove(name);
            if (!lastNames.contains(name)) {
                lastNames.add(name);
            }
        }
    }
}
