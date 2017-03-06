package com.chikara.strategist.dao;

import com.chikara.strategist.dao.Dao;
import com.chikara.strategist.entity.User;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserDao extends Dao<User, Long>
{
    User loadUserByUsername(String username) throws UsernameNotFoundException;

    User findByName(String name);
}