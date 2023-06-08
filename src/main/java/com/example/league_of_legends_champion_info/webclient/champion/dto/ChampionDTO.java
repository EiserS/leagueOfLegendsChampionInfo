package com.example.league_of_legends_champion_info.webclient.champion.dto;

import lombok.Getter;

@Getter
public class ChampionDTO {
    private String name;
    private String title;
    private int attack;
    private int defense;
    private int health;
    private int difficulty;


    public ChampionDTO(String name, String title, int attack, int defense, int health, int difficulty) {
        this.name = name;
        this.title = title;
        this.attack = attack;
        this.defense = defense;
        this.health = health;
        this.difficulty = difficulty;
    }
    public void showChampion()
    {
        System.out.println("----------------------");
        System.out.println("Bohater: " + this.getName());
        System.out.println("Tytuł: " + this.getTitle());
        System.out.println("Hp: " + this.getHealth());
        System.out.println("Attack: " + this.getAttack());
        System.out.println("Defense: " + this.getDefense());
        System.out.println("difficulty: " + this.getDifficulty());
    }
    public String addChampionToString()
    {
        return "Bohater: " + this.getName() +
        "Tytuł: " + this.getTitle() +
        "Hp: " + this.getHealth() +
        "Attack: " + this.getAttack() +
        "Defense: " + this.getDefense() +
         "difficulty: " + this.getDifficulty();
    }
}