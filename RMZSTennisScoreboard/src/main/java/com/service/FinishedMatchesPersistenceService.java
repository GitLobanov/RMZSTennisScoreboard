package com.service;

import com.model.Match;
import com.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class FinishedMatchesPersistenceService extends CrudService {

    public FinishedMatchesPersistenceService() {
    }

    public void saveMatches (Match match){
        save(match);
    }

    public List<Match> getMatches(){
        return getAll(new Match());
    }

}
