package com.example.league_of_legends_champion_info.model;

import lombok.Getter;

@Getter
public class Champion {
    private String name;
    private String title;
    private int attack;
    private int defense;
    private int health;
    private int difficulty;

    public Champion(String name, String title, int attack, int defense, int health, int difficulty) {
        this.name = name;
        this.title = title;
        this.attack = attack;
        this.defense = defense;
        this.health = health;
        this.difficulty = difficulty;
    }

    public void attack(Champion attacker, Champion defender) {
        int damage = attacker.getAttack() - defender.getDefense();
        if (damage > 0) {
            int healthAfterAttack = defender.getHealth() - damage;
            defender.setHealth(healthAfterAttack);
            System.out.println(attacker.getName() + " zaatakował " + defender.getName() + " za " + damage + " punktów obrażeń!");
            System.out.println(defender.getName() + " ma teraz " + defender.getHealth() + " punktów życia.");
        } else {
            System.out.println(attacker.getName() + " zaatakował " + defender.getName() + ", ale zrobił 0 obrażeń.");
        }
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
    private void setHealth(int healthAfterAttack) {
        this.health=healthAfterAttack;
    }

    public boolean isAlive() {
        return this.health>0;
    }
}
