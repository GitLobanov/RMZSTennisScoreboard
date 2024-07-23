package com.service;

import com.model.Match;

import java.util.List;

public class MatchService extends CrudService {

    public MatchService() {
    }

    public void saveMatches (Match match){
        save(match);
    }

    public List<Match> getMatches(){
        return getAll(new Match());
    }

}
