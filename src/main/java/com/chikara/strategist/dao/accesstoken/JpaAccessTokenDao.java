package com.chikara.strategist.dao.accesstoken;

import com.chikara.strategist.dao.JpaDao;
import com.chikara.strategist.entity.AccessToken;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
public class JpaAccessTokenDao extends JpaDao<AccessToken, Long> implements AccessTokenDao
{
    public JpaAccessTokenDao()
    {
        super(AccessToken.class);
    }
    
    
    public Map<String, AccessToken> accessTokenMap = new HashMap<String, AccessToken>();
    
    
    @Override
    @Transactional(readOnly = true, noRollbackFor = NoResultException.class)
    public AccessToken findByToken(String accessTokenString)
    {
    	
    	String accTokenString = (String)this.accessTokenMap.keySet().toArray()[0];
    	
    	return this.accessTokenMap.get(accTokenString);
    }
    
    
    @Override
    @Transactional
    public AccessToken save(AccessToken entity)
    {
    	
    	accessTokenMap.put(entity.getToken(), entity);
        return entity;
    }

}


