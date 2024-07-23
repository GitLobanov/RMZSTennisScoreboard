package com.model;

import lombok.Data;

import java.util.Optional;

@Data
public class Game {

    private int score1;
    private int score2;
    private Player winner;

    public int getMax (){
        return Math.max(score1, score2);
    }
}