package com.example.league_of_legends_champion_info.service;

import com.example.league_of_legends_champion_info.webclient.champion.ChampionClient;
import com.example.league_of_legends_champion_info.webclient.champion.dto.ChampionDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@Service
@Slf4j
public class ChampionService {
    private final ChampionClient championClient;
    private final ObjectMapper objectMapper;

    public ChampionService(ChampionClient championClient, ObjectMapper objectMapper) {
        this.championClient = championClient;
        this.objectMapper = objectMapper;
    }

    public List<ChampionDTO> getChampions() throws JsonProcessingException {
        String response = championClient.getChampions();
        List<ChampionDTO> champions = new ArrayList<>();
        try {
            JsonNode rootNode = objectMapper.readTree(response);
            JsonNode dataNode = rootNode.get("data");

            Iterator<String> fieldNames = dataNode.fieldNames();
            while (fieldNames.hasNext()) {
                String championName = fieldNames.next();
                JsonNode championNode = dataNode.get(championName);

                String name = championNode.get("name").textValue();
                String title = championNode.get("title").textValue();
                int attack = championNode.get("stats").get("attackdamage").intValue();
                int defense = championNode.get("stats").get("armor").intValue();
                int health = championNode.get("stats").get("hp").intValue();
                int difficulty = championNode.get("info").get("difficulty").intValue();
                if(difficulty==0)
                    difficulty=2;


                ChampionDTO champion = new ChampionDTO(name, title, attack, defense, health, difficulty);
                champions.add(champion);
            }
        } catch (Exception e) {
            log.error("Nie udało się przetworzyć JSON-a z API League of Legends.", e);
        }
        return champions;
    }
}
