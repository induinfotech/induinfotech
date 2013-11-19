package com.coolnri.data.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.coolnri.data.AdGroup;

@Repository("adGroupDAO")
public class AdGroupDAOImpl implements AdGroupDAO {

	private HibernateTemplate hibernateTemplate;
	
	public AdGroup selectAdGroupById(int campaignId, int adGroupId, String advertiserId) {
		List<AdGroup> results = (List<AdGroup>)
			hibernateTemplate.findByNamedQueryAndNamedParam(
				"findAdGroupById", 
				new String[]{"campaignId", "adGroupId"}, 
				new Integer[]{campaignId, adGroupId}
			);
				
		
		if ((results == null) || results.isEmpty()) {
			return null;
		}
		
		return results.get(0);
	}

	public void saveAdGroup(AdGroup adGroup) {	
		int maxAdGroupId = getMaxAdGroupId(adGroup.getAdGroupKey().getCampaign().getId());
		maxAdGroupId++;
		adGroup.getAdGroupKey().setId(maxAdGroupId);
		
		Session session = hibernateTemplate.getSessionFactory().openSession();
		session.saveOrUpdate(adGroup);
	}
	
	public void updateAdGroup(AdGroup adGroup) {
		Session session = hibernateTemplate.getSessionFactory().openSession();
		session.saveOrUpdate(adGroup);
	}

	private int getMaxAdGroupId(int campaignId) {
		List<Integer> results = hibernateTemplate.findByNamedQueryAndNamedParam("findMaxAdGroupId", "campaignId", campaignId);
		
		if ((results == null) || results.isEmpty()) {
			return 0;
		}
		
		Integer result = results.get(0);
		
		return result != null ? result : 0;
	}
	
	@Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        hibernateTemplate = new HibernateTemplate(sessionFactory);
    }
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
}
