package com.factory;

import com.model.Player;

public class PlayerFactory {

    public static Player createPlayer(String name) {
        Player player = new Player();
        player.setName(name);
        return player;
    }

}
