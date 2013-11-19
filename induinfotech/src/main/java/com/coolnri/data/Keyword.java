package com.coolnri.data;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity 
@Table (name = "keyword")
@NamedQueries ({
	@NamedQuery (name="findByKeyword", query=" from Keyword kw where kw.keyword = :keyword ")
})
public class Keyword {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "keyword_id")
	private int id;
	
	private String keyword;
	
	@Column (name = "active_flag")
	private boolean active;
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "keywords")
	private List<AdGroup> adGroups;

	public Keyword() {
		
	}
	
	public Keyword(String keyword) {
		this.keyword = keyword;
		this.active = true;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	public List<AdGroup> getAdGroups() {
		return adGroups;
	}

	public void setAdGroups(List<AdGroup> adGroups) {
		this.adGroups = adGroups;
	}

	@Override
	public String toString() {
		return "Keyword [id=" + id + ", keyword=" + keyword + ", active=" + active + "]";
	}
}
