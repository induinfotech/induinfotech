package com.coolnri.web.ads;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.coolnri.data.AdGroup;
import com.coolnri.data.Campaign;
import com.coolnri.data.Keyword;
import com.coolnri.data.dao.AdGroupDAO;
import com.coolnri.data.dao.CampaignDAO;
import com.coolnri.data.dao.KeywordDAO;

@Controller
public class AdGroupController {
	@Autowired
	CampaignDAO campaignDAO;
	
	@Autowired
	AdGroupDAO adGroupDAO;
	
	@Autowired
	KeywordDAO keywordDAO;
	
	@RequestMapping(value="/adgroup_list.do", method = RequestMethod.GET)
	public String listAdGroups(HttpServletRequest request, ModelMap model) {
		String cid = request.getParameter("cid");
		
		Campaign campaign = campaignDAO.selectCampaignById(Integer.parseInt(cid), "Adv");

		model.addAttribute("campaign", campaign);

		return "ads/adGroupList";
 	}
	
	@RequestMapping(value="/adgroup_add.do", method = RequestMethod.GET)
	public String addAdGroup(HttpServletRequest request, ModelMap model) {
		String cid = request.getParameter("cid");
		model.addAttribute("cid", cid);
		return "ads/adGroupAdd";
	}
	
	@RequestMapping(value="/adgroup_add_process.do", method = RequestMethod.GET)
	public String addAdGroupProcess(HttpServletRequest request, ModelMap model) {
		
		String aName = request.getParameter("aName");
		String aDlyBudget = request.getParameter("aDlyBudget");
		String currency = request.getParameter("currency");		
		String kws = request.getParameter("keywords");
		String cid = request.getParameter("cid");
		
		int numCID;
		Campaign campaign;
		
		// TODO: Validation layer
		campaign = campaignDAO.selectCampaignById(Integer.parseInt(cid), "Adv");
		
		AdGroup adg = new AdGroup(campaign, aName, Float.parseFloat(aDlyBudget), "USD");
		
		Set<Keyword> kwList = getKeywords(kws);
		if ((kwList != null) && ! kwList.isEmpty()) {
			adg.getKeywords().addAll(kwList);
		}
		
		adGroupDAO.saveAdGroup(adg);

		model.addAttribute("message", "AdGroup " + adg.getAdGroupKey().getId() + " created successfully");
		
		return listAdGroups(request, model);
 	}		
	
	private Set<Keyword> getKeywords(String kwText) {
		if ((kwText == null) || kwText.isEmpty()) return null;
		
		Set<Keyword> kwList = new HashSet<Keyword>();
		
		String[] kwArray = kwText.split("\n");
		
		for (int i = 0; i < kwArray.length; i++) {
			// Check if present in Keywords
			Keyword kw = keywordDAO.selectByKeyword(kwArray[i]);
			if (kw == null) {
				kw = new Keyword(kwArray[i]);
				keywordDAO.saveKeyword(kw);
			}
			
			kwList.add(kw);
		}
		
		return null;
	}
	
	@RequestMapping(value="/adgroup_edit.do", method = RequestMethod.GET)
	public String editAdGroup(HttpServletRequest request, ModelMap model) {
		String cid = request.getParameter("cid");
		String aid = request.getParameter("aid");
		
		AdGroup adg = adGroupDAO.selectAdGroupById(Integer.parseInt(cid), Integer.parseInt(aid), "Adv");

		model.addAttribute("cid", cid);
		model.addAttribute("aid", aid);
		model.addAttribute("adGroup", adg);
		
		return "ads/adGroupEdit";
	}
	
	@RequestMapping(value="/adgroup_edit_process.do", method = RequestMethod.GET)
	public String editAdGroupProcess(HttpServletRequest request, ModelMap model) {
		
		String aName = request.getParameter("aName");
		String aDlyBudget = request.getParameter("aDlyBudget");
		String currency = request.getParameter("currency");		
		String kws = request.getParameter("keywords");
		String cid = request.getParameter("cid");
		String aid = request.getParameter("aid");
		
		int numCID;
		Campaign campaign;
		
		// TODO: Validation layer
		AdGroup adg = adGroupDAO.selectAdGroupById(Integer.parseInt(cid), Integer.parseInt(aid), "Adv");

		if ((aName != null) && ! aName.trim().isEmpty())
			adg.setName(aName);
		
		if ((aDlyBudget != null) && ! aDlyBudget.trim().isEmpty())
			adg.setDailyBudget(Float.valueOf(aDlyBudget));

		Set<Keyword> kwList = getKeywords(kws);
		if ((kwList != null) && ! kwList.isEmpty()) {
			adg.setKeywords(kwList);
		}
		
		adGroupDAO.updateAdGroup(adg);

		model.addAttribute("message", "AdGroup " + adg.getAdGroupKey().getId() + " updated successfully");
		
		return listAdGroups(request, model);
 	}		
	
}
