package com.example.league_of_legends_champion_info.controller;

import com.example.league_of_legends_champion_info.model.Champion;
import com.example.league_of_legends_champion_info.service.ChampionService;
import com.example.league_of_legends_champion_info.webclient.champion.dto.ChampionDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ChampionController {
    private final ChampionService championService;

    public ChampionController(ChampionService championService) {
        this.championService = championService;
    }

    @GetMapping("/champions")
    public String getChampions() throws JsonProcessingException {
            List<ChampionDTO> champions = championService.getChampions();
        for (ChampionDTO i:champions) {
               i.showChampion();
            }
        for (ChampionDTO i:champions) {
            i.showChampion();
        }
            return null;
    }
}