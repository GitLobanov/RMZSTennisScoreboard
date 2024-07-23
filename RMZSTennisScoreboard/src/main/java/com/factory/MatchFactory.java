package com.factory;

import com.model.Match;
import com.model.Player;

public class MatchFactory {

    public static Match createMatch(Player player1, Player player2, Player winner) {
        Match match = new Match();
        match.setPlayer1(player1);
        match.setPlayer2(player2);
        match.setWinner(winner);
        return match;
    }

}
