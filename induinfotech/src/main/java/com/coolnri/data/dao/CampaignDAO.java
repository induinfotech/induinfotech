package com.coolnri.data.dao;

import java.util.List;

import com.coolnri.data.Campaign;

public interface CampaignDAO {
	
	public Campaign selectCampaignById(int campaignId, String advertiserId);
	
	public List<Campaign> selectCampaigns(String advertieserId);
	
	public void saveCampaign(Campaign campaign);
}
