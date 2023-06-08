package com.example.league_of_legends_champion_info.webclient.champion;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ChampionClient {
    private static final String CHAMPION_URL = "http://ddragon.leagueoflegends.com/cdn/13.8.1/data/pl_PL/";
    private final RestTemplate restTemplate = new RestTemplate();

    public String getChampion(String championName) {
        return callGetMethod("champion/{championName}.json",String.class, championName);
    }
    public String getChampions() {
        return callGetMethod("champion.json", String.class);

    }
    private <T> T callGetMethod(String url,Class<T> responseType, Object... objects) {
        return restTemplate.getForObject(CHAMPION_URL + url, responseType, objects);
    }
}
