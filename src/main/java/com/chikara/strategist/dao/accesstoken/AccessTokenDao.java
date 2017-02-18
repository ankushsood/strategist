package com.chikara.strategist.dao.accesstoken;

import com.chikara.strategist.dao.Dao;
import com.chikara.strategist.entity.AccessToken;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
public interface AccessTokenDao extends Dao<AccessToken, Long>
{
    AccessToken findByToken(String accessTokenString);
}
