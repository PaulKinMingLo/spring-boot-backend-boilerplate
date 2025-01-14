package com.kml.springbootbackend.model;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    
    public User getById(Long id);

    public int updateByUser(User entity);

    public int insertByUser(User entity);
}
