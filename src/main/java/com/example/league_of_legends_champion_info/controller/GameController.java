package com.example.league_of_legends_champion_info.controller;

import com.example.league_of_legends_champion_info.model.Champion;
import com.example.league_of_legends_champion_info.service.ChampionService;
import com.example.league_of_legends_champion_info.webclient.champion.dto.ChampionDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
@Slf4j
@RestController
@RequestMapping("/game")
public class GameController {
    private final ChampionService championService;

    public GameController(ChampionService championService) {
        this.championService = championService;
    }

    @GetMapping("/fight")
    public String fight(@RequestParam String ch1, @RequestParam String ch2) throws JsonProcessingException {
        List<String> championNames = new ArrayList<>();
        championNames.add(ch1);
        championNames.add(ch2);
        List<ChampionDTO> championDTOs = championService.getChampions();
        // tworzenie obiektów Champion na podstawie championdto
        List<Champion> champions = new ArrayList<>();
        for (String championName : championNames) {
            log.info(championName);
            for (ChampionDTO j : championDTOs) {
                if (Objects.equals(championName, j.getName())) {
                    champions.add(new Champion(j.getName(),j.getTitle(), j.getAttack(), j.getDefense(), j.getHealth(), j.getDifficulty()));
                }
            }
        }
        // walka między bohaterami
        Champion bohater1 = champions.get(0);
        Champion bohater2 = champions.get(1);
        while (bohater1.isAlive() && bohater2.isAlive()) {
            bohater1.attack(bohater1, bohater2);
            if (bohater2.isAlive()) {
                bohater2.attack(bohater2, bohater1);
            }
        }
        StringBuilder sB = new StringBuilder();
        if (bohater1.isAlive()) {
            sB.append(bohater1.getName() + " wygrał walkę!");
        } else {
            sB.append(bohater2.getName() + " wygrał walkę!");
        }
        return sB.toString();
    }
}
