package com.coolnri.web.ads;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.coolnri.data.Campaign;
import com.coolnri.data.dao.CampaignDAO;

@Controller
public class CampaignController {
	@Autowired
	CampaignDAO campaignDAO;
	
	@RequestMapping(value="/adv_portal.do", method = RequestMethod.GET)
	public String advertiserPortal(ModelMap model) {
		return "ads/advPortal";
 	}
	
	@RequestMapping(value="/campaign_list.do", method = RequestMethod.GET)
	public String listCampaigns(ModelMap model) {
		List<Campaign> campaigns = campaignDAO.selectCampaigns("adv");
		
		System.out.println("Campaigns = " + campaigns.size());
		model.addAttribute("campaigns", campaigns);

		return "ads/campaignList";
 	}
	
	@RequestMapping(value="/campaign_expire.do", method = RequestMethod.GET)
	public String expireCampaign(HttpServletRequest request, ModelMap model) {
		String cid = request.getParameter("cid");
		
		System.out.println("Trying to expire CID = " + cid);
		int numCID;
		
		try {
			numCID = Integer.parseInt(cid);
		} catch (Exception ex) {
			model.addAttribute("message", "Invalid Campaign ID ");
			return "ads/campaignList";
		}
		
		Campaign campaign = campaignDAO.selectCampaignById(numCID, "adv");
		campaign.setEndDt(new Date());
		campaignDAO.saveCampaign(campaign);

		model.addAttribute("message", "Campaign " + cid + " expired successfully");
		return listCampaigns(model);
 	}	
	
	@RequestMapping(value="/campaign_add.do", method = RequestMethod.GET)
	public String addCampaign(ModelMap model) {
		return "ads/campaignAdd";
	}
	
	@RequestMapping(value="/campaign_add_process.do", method = RequestMethod.GET)
	public String addCampaignProcess(HttpServletRequest request, ModelMap model) {
		String cName = request.getParameter("cName");
		String cStartDt = request.getParameter("cStartDt");
		String cEndDt = request.getParameter("cEndDt");	
		String cDlyBudget = request.getParameter("cDlyBudget");
		String currency = request.getParameter("currency");		
		
		// Validation layer
		
		// Validate name is not empty
		// Name is not already present for same advertiser
		
		// Start Date is not empty, in the future
		
		// End Date is null / greater than start date / greater than today
		
		// Daily budget is not null, > 1 $
		DateFormat df = new SimpleDateFormat("mm/dd/yyyy");
		
		Campaign campaign = new Campaign();
		
		try {
			campaign.setName(cName);
			campaign.setStartDt(df.parse(cStartDt));
			campaign.setEndDt(df.parse(cEndDt));
			campaign.setDailyBudget(Float.parseFloat(cDlyBudget));
			campaign.setDailyBudgetCurrency(currency);
		} catch (Exception ex) {
			model.addAttribute("message", "Please correct campaign data and resubmit");
			return addCampaign(model);
		}

		campaignDAO.saveCampaign(campaign);

		model.addAttribute("message", "Campaign " + campaign.getId() + " created successfully");
		return listCampaigns(model);
 	}		
	
	@RequestMapping(value="/campaign_edit.do", method = RequestMethod.GET)
	public String editCampaign(HttpServletRequest request, ModelMap model) {
		String cid = request.getParameter("cid");
		
		System.out.println("Trying to edit CID = " + cid);
		int numCID;
		
		try {
			numCID = Integer.parseInt(cid);
			model.addAttribute("campaign", campaignDAO.selectCampaignById(numCID, "adv"));
		} catch (Exception ex) {
			model.addAttribute("message", "Invalid Campaign ID ");
			return listCampaigns(model);
		}
		
		
		return "ads/campaignEdit";
	}
	
	@RequestMapping(value="/campaign_edit_process.do", method = RequestMethod.GET)
	public String editCampaignProcess(HttpServletRequest request, ModelMap model) {
		String cid = request.getParameter("cid");
		String cDlyBudget = request.getParameter("cDlyBudget");
		
		System.out.println("Trying to edit CID = " + cid);
		int numCID;
		Campaign campaign = null;
		
		try {
			numCID = Integer.parseInt(cid);
			campaign = campaignDAO.selectCampaignById(numCID, "adv");
			campaign.setDailyBudget(Float.valueOf(cDlyBudget));
		} catch (Exception ex) {
			model.addAttribute("message", "Invalid Values in edit screen. Please correct and retry ");
			return editCampaign(request, model);
		}

		
		try {
			campaignDAO.saveCampaign(campaign);
		} catch (Exception ex) {
			model.addAttribute("message", "Issue in saving campaign. Please correct campaign data and resubmit");
			return editCampaign(request, model);
		}

		model.addAttribute("message", "Campaign " + campaign.getId() + " edit successfully");
		return listCampaigns(model);
 	}		
	
}
