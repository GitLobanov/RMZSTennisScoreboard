package com.service;

import com.model.Player;

public class PlayerService extends CrudService{

    public Player findPlayerById(long id, Player entity) {
        return (Player) findById(id, entity);
    }
}
