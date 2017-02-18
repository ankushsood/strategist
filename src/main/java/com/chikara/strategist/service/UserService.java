package com.chikara.strategist.service;

import com.chikara.strategist.entity.AccessToken;
import com.chikara.strategist.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
public interface UserService extends UserDetailsService
{
    User findUserByAccessToken(String accessToken);

    AccessToken createAccessToken(User user);
}
