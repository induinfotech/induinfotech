package com.coolnri.data.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.coolnri.data.Keyword;

@Repository("keywordDAO")
@Transactional
public class KeywordDAOImpl implements KeywordDAO {

	private HibernateTemplate hibernateTemplate;
	
	@Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        hibernateTemplate = new HibernateTemplate(sessionFactory);
    }
	
	public Keyword selectByKeyword(String keyword) {
		List<Keyword> kwList = hibernateTemplate.findByNamedQueryAndNamedParam("findByKeyword", "keyword", keyword);
		if ((kwList == null) || kwList.isEmpty()) return null;
		return kwList.get(0);
	}

	public void saveKeyword(Keyword keyword) {
		hibernateTemplate.save(keyword);
	}
}
