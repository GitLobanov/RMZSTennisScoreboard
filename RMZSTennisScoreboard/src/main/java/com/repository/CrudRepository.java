package com.repository;

import com.model.Match;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T,ID> {

    public void save(T entity);
    public void delete (T entity);
    public void update (T entity);
    public List<T> findAll();
    public Optional<T> findById(long id);

}
