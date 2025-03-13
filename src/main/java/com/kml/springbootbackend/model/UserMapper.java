package com.kml.springbootbackend.model;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    
    @Deprecated
    public User getById(Long id); // TODO: Migrating to parent method defined in BaseMapper

    // @Override
    // public User findById(Long id);

    // @Override
    // public int updateBy(User entity);

    @Deprecated
    public int updateByUser(User entity); // TODO: Migrating to parent method defined in BaseMapper

    @Deprecated
    public int insertByUser(User entity); // TODO: Migrating to parent method defined in BaseMapper

    public User findByUsername(String username); // TODO: Migrating to parent method defined in BaseMapper

    public User getByCredential(@Param("username") String username, @Param("password") String password);
}
