package com.coolnri.data;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity 
@Table (name = "adgroup")
@NamedQueries ({
	@NamedQuery (name="findMaxAdGroupId", query=" select max(adg.adGroupKey.id) from AdGroup adg where adg.adGroupKey.campaign.id = :campaignId "),
	@NamedQuery (name="findAdGroupById", query=" from AdGroup adg where adg.adGroupKey.campaign.id = :campaignId and adg.adGroupKey.id = :adGroupId ")
})
public class AdGroup {
	
	@Id
	private AdGroupKey adGroupKey;
	
	@Column (name = "name")
	private String name;

	@Column (name = "daily_budget")
	private float dailyBudget;

	@Column (name = "daily_budget_currency")
	private String dailyBudgetCurrency;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "adg_keyword", 
		joinColumns = {@JoinColumn(name = "campaign_id"), @JoinColumn(name = "adgroup_id")}, 
		inverseJoinColumns = { @JoinColumn(name = "keyword_id") })
	@LazyCollection(LazyCollectionOption.FALSE)	
	private Set<Keyword> keywords = new HashSet<Keyword>();

	public AdGroup() {
		
	}
	
	public AdGroup(Campaign campaign, String name, float dailyBudget, String dailyBudgetCurrency) {
		this.adGroupKey = new AdGroupKey(campaign);
		this.name = name;
		this.dailyBudget = dailyBudget;
		this.dailyBudgetCurrency = dailyBudgetCurrency;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getDailyBudget() {
		return dailyBudget;
	}

	public void setDailyBudget(float dailyBudget) {
		this.dailyBudget = dailyBudget;
	}

	public String getDailyBudgetCurrency() {
		return dailyBudgetCurrency;
	}

	public void setDailyBudgetCurrency(String dailyBudgetCurrency) {
		this.dailyBudgetCurrency = dailyBudgetCurrency;
	}

	public int getId() {
		return this.adGroupKey.getId();
	}
	
	public Campaign getCampaign() {
		return this.adGroupKey.getCampaign();
	}
	
	public AdGroupKey getAdGroupKey() {
		return adGroupKey;
	}

	public void setAdGroupKey(AdGroupKey adGroupKey) {
		this.adGroupKey = adGroupKey;
	}
	
	public Set<Keyword> getKeywords() {
		return keywords;
	}

	public void setKeywords(Set<Keyword> keywords) {
		this.keywords = keywords;
	}

	@Override
	public String toString() {
		return "AdGroup [campaignId=" + adGroupKey.getCampaign().getId() + ", id=" + adGroupKey.getId() + ", name="
				+ name + ", dailyBudget=" + dailyBudget
				+ ", dailyBudgetCurrency=" + dailyBudgetCurrency + "]";
	}
}
