package com.coolnri.data.dao;

import com.coolnri.data.AdGroup;

public interface AdGroupDAO {
	
	public AdGroup selectAdGroupById(int campaignId, int adgroupId, String advertiserId);
	
	public void saveAdGroup(AdGroup adGroup);
	
	public void updateAdGroup(AdGroup adGroup);
}