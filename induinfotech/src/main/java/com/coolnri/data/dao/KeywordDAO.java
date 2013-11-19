package com.coolnri.data.dao;

import com.coolnri.data.Keyword;

public interface KeywordDAO {
	
	public Keyword selectByKeyword(String keyword);
	
	public void saveKeyword(Keyword keyword);
	
}
