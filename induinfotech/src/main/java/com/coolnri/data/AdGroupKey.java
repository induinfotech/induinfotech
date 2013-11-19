package com.coolnri.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class AdGroupKey implements Serializable {
	@JoinColumn (name = "campaign_id")
	@ManyToOne Campaign campaign;
	
	@Column (name = "adgroup_id")
	private int id;

	public AdGroupKey() {
		
	}
	
	public AdGroupKey(Campaign campaign) {
		this.campaign = campaign;
	}

	public Campaign getCampaign() {
		return this.campaign;
	}

	public void setCampaign(Campaign campaign) {
		this.campaign = campaign;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
