package com.model;

import lombok.Data;

import java.util.LinkedHashMap;
import java.util.List;

@Data
public class GameSet {

    private Player player1;
    private Player player2;
    private Player winner;
    private List<Game> games;

    public GameSet(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }
}
