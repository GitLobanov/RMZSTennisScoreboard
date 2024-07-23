package com.util;

import javax.persistence.Entity;
import java.util.List;
import java.util.Optional;

public interface CrudInterface {

    public void save(Object entity);
    public void update(Object entity);
    public void delete(Object entity);
    public Optional findById(long id);
    public List<Object> getAll(Object entity);

}
