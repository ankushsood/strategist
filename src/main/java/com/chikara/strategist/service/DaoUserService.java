package com.chikara.strategist.service;

import java.util.UUID;

import org.springframework.transaction.annotation.Transactional;

import com.chikara.strategist.dao.UserDao;
import com.chikara.strategist.dao.accesstoken.AccessTokenDao;
import com.chikara.strategist.entity.AccessToken;
import com.chikara.strategist.entity.User;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
public class DaoUserService implements UserService 
{
    private UserDao userDao;

    private AccessTokenDao accessTokenDao;

    protected DaoUserService()
    {
    }

    public DaoUserService(AccessTokenDao accessTokenDao)
    {
        this.userDao = null;
        this.accessTokenDao = accessTokenDao;
    }

    @Transactional(readOnly = true)
    public User loadUserByUsername(String username)
    {
        return this.userDao.loadUserByUsername(username);
    }

    
    @Transactional
    public User findUserByAccessToken(String accessTokenString)
    {
        AccessToken accessToken = this.accessTokenDao.findByToken(accessTokenString);

        if (null == accessToken) {
            return null;
        }

        if (accessToken.isExpired()) {
            this.accessTokenDao.delete(accessToken);
            return null;
        }

        return accessToken.getUser();
    }

    @Transactional
    public AccessToken createAccessToken(User user)
    {
        AccessToken accessToken = new AccessToken(user, UUID.randomUUID().toString());
        return this.accessTokenDao.save(accessToken);
    } 
}
