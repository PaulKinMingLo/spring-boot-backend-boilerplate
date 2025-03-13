package com.kml.springbootbackend.model;

import java.util.List;

public interface BaseMapper<T> {

    public List<T> getAll();

    public T findById(Long id);

    public int updateBy(T entity);

    public int insertBy(T entity);

}