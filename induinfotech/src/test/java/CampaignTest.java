import java.util.Collections;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.coolnri.data.AdGroup;
import com.coolnri.data.Campaign;
import com.coolnri.data.Keyword;
import com.coolnri.data.dao.AdGroupDAO;
import com.coolnri.data.dao.CampaignDAO;
import com.coolnri.data.dao.KeywordDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring/app.test.xml" })
@Transactional
@TransactionConfiguration(defaultRollback=false)
public class CampaignTest {

	@Autowired
	CampaignDAO campaignDao;
	
	@Autowired
	AdGroupDAO adGroupDAO;
	
	@Autowired
	KeywordDAO keywordDAO;
	
	@Test
	public void testCreateCampaign() {
		Campaign cmpn = getTestCampaign();
		campaignDao.saveCampaign(cmpn);
		Assert.assertTrue(cmpn.getId() > 0);
		
		// Try searching
		Campaign dbCmpn = campaignDao.selectCampaignById(cmpn.getId(), "test");
		Assert.assertTrue(cmpn.getId() == dbCmpn.getId());
		float budget = cmpn.getDailyBudget();
		
		// Add an adgroup
		AdGroup adg = getTestAdGroup(cmpn);
		
		Keyword kw = new Keyword("test");
		keywordDAO.saveKeyword(kw);
		
		adg.setKeywords(Collections.singleton(kw));
		
		adGroupDAO.saveAdGroup(adg);
		
		// Try searching for AdGroup
		AdGroup dbAdGroup = adGroupDAO.selectAdGroupById(cmpn.getId(), adg.getAdGroupKey().getId(), "test");
		Assert.assertTrue(adg.getAdGroupKey().getId() == dbAdGroup.getAdGroupKey().getId());
		
		// Edit
		dbCmpn.setDailyBudget(dbCmpn.getDailyBudget() + 1);
		campaignDao.saveCampaign(dbCmpn);
		Assert.assertTrue(budget + 1 == dbCmpn.getDailyBudget());

	}

	public void testSelectCampaign() {
		// Try searching
		Campaign dbCmpn = campaignDao.selectCampaignById(11, "test");
		Assert.assertTrue(dbCmpn.getId() == 11);
		System.out.println("Adgroup count = " + dbCmpn.getAdGroups().size());
		for (AdGroup adGroup:dbCmpn.getAdGroups()) {
			System.out.println("Adgroup = " + adGroup);
		}
		
		AdGroup adg = adGroupDAO.selectAdGroupById(11, 1, "test");
		System.out.println("Near end of test");
	}

	private Campaign getTestCampaign() {
		Campaign cmpn = new Campaign();
		cmpn.setName("test");
		
		Date today = new Date();
		cmpn.setStartDt(today);
		
		cmpn.setEndDt(today);
		
		cmpn.setDailyBudget(100f);
		cmpn.setDailyBudgetCurrency("USD");
		
		return cmpn;
	}
	
	private AdGroup getTestAdGroup(Campaign cmpn) {
		return new AdGroup(cmpn, "test adgroup", 2.0f, "USD");
	}
}
