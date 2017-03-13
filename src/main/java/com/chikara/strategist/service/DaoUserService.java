package com.chikara.strategist.service;

import com.chikara.strategist.dao.accesstoken.AccessTokenDao;
import com.chikara.strategist.entity.AccessToken;
import com.chikara.strategist.entity.Role;
import com.chikara.strategist.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
public class DaoUserService implements UserService
{

    private AccessTokenDao accessTokenDao;
    private PasswordEncoder passwordEncoder;

    protected DaoUserService()
    {
        /* Reflection instantiation */
    }

    public DaoUserService( AccessTokenDao accessTokenDao)
    {
        this.accessTokenDao = accessTokenDao;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User adminUser = new User(username, this.passwordEncoder.encode("admin"));
        Set<Role> roles = new HashSet<Role>();
        roles.add(Role.USER);
        roles.add(Role.ADMIN);
        adminUser.setRoles(roles);
        
        
        return adminUser;
    }

    @Override
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

    @Override
    @Transactional
    public AccessToken createAccessToken(User user)
    {
        AccessToken accessToken = new AccessToken(user, UUID.randomUUID().toString());
        return this.accessTokenDao.save(accessToken);
    }
}