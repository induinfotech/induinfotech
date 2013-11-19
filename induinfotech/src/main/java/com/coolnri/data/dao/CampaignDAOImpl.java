package com.coolnri.data.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.coolnri.data.Campaign;

@Repository("campaignDAO")
@Transactional
public class CampaignDAOImpl implements CampaignDAO {
	
	private HibernateTemplate hibernateTemplate;
	
	@Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        hibernateTemplate = new HibernateTemplate(sessionFactory);
    }
 
    @Transactional(readOnly = false)
    public void saveCampaign(Campaign campaign) {
        hibernateTemplate.saveOrUpdate(campaign);
    }

	public Campaign selectCampaignById(int campaignId, String advertiserId) {
		Campaign campaign = (Campaign) hibernateTemplate.get(Campaign.class, campaignId);
		
		hibernateTemplate.initialize(campaign);
		
		return campaign;
	}

	public List<Campaign> selectCampaigns(String advertieserId) {	
		return (List<Campaign>) hibernateTemplate.find(" from " + Campaign.class.getName());
	}
}
