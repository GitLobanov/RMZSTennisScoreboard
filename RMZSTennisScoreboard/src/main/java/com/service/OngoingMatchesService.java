package com.service;

import com.model.Game;
import com.model.GameSet;
import com.model.Player;

import java.util.ArrayList;
import java.util.List;

public class OngoingMatchesService {

    private MatchScoreCalculationService matchScoreCalculationService = new MatchScoreCalculationService();
    private Player player1;
    private Player player2;
    private List<GameSet> gameSets;
    private GameSet currentGameSet;
    private List<Game> games;
    private Game currentGame;
    private Player currentPlayer;

    public int[] countGamesWin;
    public int[] countSetsWin;


    public void startMatch(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        countSetsWin = new int[2];
        gameSets = new ArrayList<>();
    }

    public void startSet() {
        currentGameSet = new GameSet(player1, player2);
        games = new ArrayList<Game>();
        gameSets.add(currentGameSet);
        currentGameSet.setGames(games);
        countGamesWin = new int[2];
    }

    public void startGame() {
        currentGame = new Game();
        games.add(currentGame);
    }

    public void appendGamePoint(Player player) {

        if (player1.equals(player)) {
            currentGame.setScore1(currentGame.getScore1() + 15);
        } else if (player2.equals(player)) {
            currentGame.setScore2(currentGame.getScore2() + 15);
        }

        if (Math.abs(currentGame.getScore1() - currentGame.getScore2()) > 15 & currentGame.getMax() > 40) {
            currentGame.setWinner(player);
        }
    }

    public boolean isHaveGameWinner(Player player) {
        boolean returnTo = currentGame.getWinner() != null;
        if (returnTo) {
            if (player1.equals(player)) {
                countGamesWin[0]++;
            }

            if (player2.equals(player)) {
                countGamesWin[1]++;
            }
        }

        return returnTo;
    }

    public boolean isHaveSetWinner(Player player) {
        currentGameSet.setGames(games);
        if (Math.abs(countGamesWin[0] - countGamesWin[1]) > 2 & (countGamesWin[0] + countGamesWin[1] > 6)) {
            if (player1.equals(player)) {
                countSetsWin[0]++;
            }

            if (player2.equals(player)) {
                countSetsWin[1]++;
            }
            return true;
        }
        return false;
    }

    public String getResultSet() {
        return countSetsWin[0] + " / " + countSetsWin[1];
    }

    public String getResultGames() {
        return countGamesWin[0] + " / " + countGamesWin[1];
    }

    public String getResultGame() {
        return currentGame.getScore1() + " / " + currentGame.getScore2();
    }

    public void appendSetWin(int p) {
        if (p == 0) countSetsWin[p]++;
        if (p == 1) countSetsWin[p]--;
    }

    public void appendGamesWin(int p) {
        if (p == 0) countGamesWin[p]++;
        if (p == 1) countGamesWin[p]--;
    }


    public boolean isEndMatch() {
        if (Math.abs(countSetsWin[0] - countSetsWin[1]) > 2 & (countSetsWin[0] + countSetsWin[1] > 2)) {
            return true;
        }
        return false;
    }

    public Player getWinner() {
        if (countSetsWin[0] > countSetsWin[1]) {
            return player1;
        } else {
            return player2;
        }

    }

}
