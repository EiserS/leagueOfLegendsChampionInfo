package com.example.league_of_legends_champion_info;

import com.example.league_of_legends_champion_info.controller.GameController;
import com.example.league_of_legends_champion_info.service.ChampionService;
import com.example.league_of_legends_champion_info.webclient.champion.ChampionClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
class LeagueInfoApplicationTests {

    private final ChampionClient championClient = new ChampionClient();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final ChampionService championService = new ChampionService(championClient, objectMapper);
    private final GameController gameController = new GameController(championService);

    @Test
    void testGetChampionReturnsValidJson() {
        String championName = "Annie";
        String championJson = championClient.getChampion(championName);
        assertTrue(isValidJson(championJson), "Returned JSON is not valid");
    }

    private boolean isValidJson(String json) {
        try {
            new JSONObject(json);
            return true;
        } catch (JSONException e) {
            try {
                new JSONArray(json);
                return true;
            } catch (JSONException e2) {
                return false;
            }
        }
    }

    @Test
    void testGetChampionsReturnsValidJson() {
        String championsJson = championClient.getChampions();
        assertTrue(isValidJson(championsJson), "Returned JSON is not valid");
    }

    @Test
    void testFightReturnsWinner() throws Exception {
        String ch1 = "Annie";
        String ch2 = "Ahri";

        String result = gameController.fight(ch1, ch2);

        assertNotNull(result);
        assertTrue(result.contains("wygrał walkę!"));
    }
}
