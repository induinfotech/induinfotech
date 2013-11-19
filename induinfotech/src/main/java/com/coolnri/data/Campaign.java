package com.coolnri.data;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity 
@Table (name = "campaign")
public class Campaign {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "campaign_id")
	private int id;
	
	private String name;
	
	@Column (name = "start_dt")
	private Date startDt;
	
	@Column (name = "end_dt")
	private Date endDt;
	
	@Column (name = "daily_budget")
	private float dailyBudget;
	
	@Column (name = "daily_budget_currency")
	private String dailyBudgetCurrency;
	
	@OneToMany
	@JoinColumn(name = "campaign_id")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<AdGroup> adGroups;
	
	/* Getters & Settes */
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getStartDt() {
		return startDt;
	}
	public void setStartDt(Date startDt) {
		this.startDt = startDt;
	}
	public Date getEndDt() {
		return endDt;
	}
	public void setEndDt(Date endDt) {
		this.endDt = endDt;
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
	
	public List<AdGroup> getAdGroups() {
		return adGroups;
	}
	public void setAdGroups(List<AdGroup> adGroups) {
		this.adGroups = adGroups;
	}
	@Override
	public String toString() {
		return "Campaign [id=" + id + ", name=" + name + ", startDt=" + startDt
				+ ", endDt=" + endDt + ", dailyBudget=" + dailyBudget
				+ ", dailyBudgetCurrency=" + dailyBudgetCurrency + "]";
	}
}
