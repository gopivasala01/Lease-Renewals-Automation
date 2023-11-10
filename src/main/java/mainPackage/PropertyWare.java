  																																																																				package mainPackage;

import java.io.File;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PropertyWare 
{
	public static boolean login()
	{
		try
		{
		RunnerClass.downloadFilePath = AppConfig.downloadFilePath;
		Map<String, Object> prefs = new HashMap<String, Object>();
	    // Use File.separator as it will work on any OS
	    prefs.put("download.default_directory",
	    		RunnerClass.downloadFilePath);
        // Adding cpabilities to ChromeOptions
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--remote-allow-origins=*");
        WebDriverManager.chromedriver().clearDriverCache().setup();
        RunnerClass.driver= new ChromeDriver(options);
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL); // Or PageLoadStrategy.EAGER if needed
        options.setPageLoadTimeout(Duration.ofSeconds(500));
        RunnerClass.driver.manage().window().maximize();
        RunnerClass.driver.get(AppConfig.URL);
        RunnerClass.driver.findElement(Locators.username).sendKeys(AppConfig.username); 
        RunnerClass.driver.findElement(Locators.password).sendKeys(AppConfig.password);
        RunnerClass.driver.findElement(Locators.signInButton).click();
        RunnerClass.actions = new Actions(RunnerClass.driver);
        RunnerClass.js = (JavascriptExecutor)RunnerClass.driver;
        RunnerClass.driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
        RunnerClass.wait = new WebDriverWait(RunnerClass.driver, Duration.ofSeconds(2));
        try {
            if (RunnerClass.driver.findElement(Locators.loginError).isDisplayed()) 
            {
                System.out.println("Login failed");
                String failedReason = ", Login failed";
                MailActivities.sendEmail(failedReason);
                return false;
            }
        }
        catch(Exception e) {}
        RunnerClass.driver.manage().timeouts().implicitlyWait(100,TimeUnit.SECONDS);
        RunnerClass.wait = new WebDriverWait(RunnerClass.driver, Duration.ofSeconds(100));
        return true;
		}
		catch(Exception e)
		{
			System.out.println("Login failed");
		    RunnerClass.failedReason = RunnerClass.failedReason+","+ "Login failed";
			return false;
		}
	}
	
	

	public static boolean getToLeasePageWithLeaseEntityID()
	{
		
		try
		{
			RunnerClass.navigateToLeaseThroughLeaseEntityID = true;
			RunnerClass.driver.manage().timeouts().implicitlyWait(100,TimeUnit.SECONDS);
	        RunnerClass.wait = new WebDriverWait(RunnerClass.driver, Duration.ofSeconds(100));
	        RunnerClass.driver.navigate().refresh();
	        intermittentPopUp();
	        Thread.sleep(2000);
	        RunnerClass.driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
	        RunnerClass.driver.findElement(Locators.marketDropdown).click();
			/*
			 * if(RunnerClass.company.contains("Institutional Accounts")) {
			 * 
			 * RunnerClass.failedReason = "Institutional Accounts"; return false; }
			 */
	        RunnerClass.driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
	        String marketName = "HomeRiver Group - "+RunnerClass.company.trim();
	        Select marketDropdownList = new Select(RunnerClass.driver.findElement(Locators.marketDropdown));
	        marketDropdownList.selectByVisibleText(marketName);
	        Thread.sleep(2000);
	        RunnerClass.driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
	        //}
	        String buildingPageURL = AppConfig.buildingPageURL+RunnerClass.leaseEntityID;
	        if(RunnerClass.leaseEntityID== null || RunnerClass.leaseEntityID == "") {
	        	RunnerClass.failedReason = "Building Not Available";
	        	return false;
	        }
	        else {
	        	 System.out.println(buildingPageURL);
	 	        RunnerClass.driver.navigate().to(buildingPageURL);
	        }
	       
			/*
			 * if(PropertyWare.permissionDeniedPage()==true) {
			 * System.out.println("Wrong building Entity ID"); RunnerClass.failedReason =
			 * "Building Not Available"; return false; }
			 */
	        intermittentPopUp();
	       
	        //boolean portfolioCheck = false;
	        
	        return true;
		}
		catch(Exception e)
		{
			RunnerClass.failedReason= "Building Not Available";
			return false;
		}
	}
	
	public static boolean navigatetoLease(String company,String completeBuilding, String buildingAbbreviation,String leaseName) {
		RunnerClass.wait = new WebDriverWait(RunnerClass.driver, Duration.ofSeconds(10));
		RunnerClass.driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		//Thread.sleep(3000);
		
		if(DataBase.getLeaseEntityID(leaseName, completeBuilding) == true){
			try {
				if(getToLeasePageWithLeaseEntityID()==true) {
					return true;
				}
				else {
					return false;
				}
			}
			catch(Exception e) {
				
				System.out.println("Building Not Found");
			    RunnerClass.failedReason =  RunnerClass.failedReason+","+ "Building Not Found";
				return false;
			}
		
		}
		else {
			try {
				if(searchBuilding(company, buildingAbbreviation, leaseName)==true) {
					return true;
				}
				else {
					return false;
				}
			}
			catch(Exception e) {
				
				System.out.println("Building Not Found");
			    RunnerClass.failedReason =  RunnerClass.failedReason+","+ "Building Not Found";
				return false;
			}
			
			
		}
		
		
		//return true;
	}
	
	
	
	public static boolean searchBuilding(String company, String building, String ownerName) {
	    try {
	        //RunnerClass.driver.findElement(Locators.dashboardsTab).click();
	        RunnerClass.driver.findElement(Locators.searchbox).clear();
	        RunnerClass.driver.findElement(Locators.searchbox).sendKeys(building);
	        try {
	            RunnerClass.wait.until(ExpectedConditions.invisibilityOf(RunnerClass.driver.findElement(Locators.searchingLoader)));
	        } catch (Exception e) {
	            try {
	                RunnerClass.driver.manage().timeouts().implicitlyWait(200, TimeUnit.SECONDS);
	                RunnerClass.driver.navigate().refresh();
	                RunnerClass.driver.findElement(Locators.dashboardsTab).click();
	                RunnerClass.driver.findElement(Locators.searchbox).clear();
	                RunnerClass.driver.findElement(Locators.searchbox).sendKeys(building);
	                RunnerClass.wait.until(ExpectedConditions.invisibilityOf(RunnerClass.driver.findElement(Locators.searchingLoader)));
	            } catch (Exception e2) {
	            }
	        }
	        try {
	            RunnerClass.driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	            if (RunnerClass.driver.findElement(Locators.noItemsFoundMessagewhenLeaseNotFound).isDisplayed()) {
	                long count = building.chars().filter(ch -> ch == '.').count();
	                if (building.chars().filter(ch -> ch == '.').count() >= 2) {
	                    building = building.substring(building.indexOf(".") + 1, building.length());
	                    RunnerClass.driver.manage().timeouts().implicitlyWait(200, TimeUnit.SECONDS);
	                    RunnerClass.driver.navigate().refresh();
	                    RunnerClass.driver.findElement(Locators.dashboardsTab).click();
	                    RunnerClass.driver.findElement(Locators.searchbox).clear();
	                    RunnerClass.driver.findElement(Locators.searchbox).sendKeys(building);
	                    RunnerClass.wait.until(ExpectedConditions.invisibilityOf(RunnerClass.driver.findElement(Locators.searchingLoader)));
	                    try {
	                        RunnerClass.driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	                        if (RunnerClass.driver.findElement(Locators.noItemsFoundMessagewhenLeaseNotFound).isDisplayed()) {
	                            System.out.println("Building Not Found");
	                            RunnerClass.failedReason = RunnerClass.failedReason + "," + "Building Not Found";
	                            return false;
	                        }
	                    } catch (Exception e3) {
	                    }
	                } else {
	                    try {
	                        building = building.split("_")[1];
	                        RunnerClass.driver.manage().timeouts().implicitlyWait(200, TimeUnit.SECONDS);
	                        RunnerClass.driver.navigate().refresh();
	                        RunnerClass.driver.findElement(Locators.dashboardsTab).click();
	                        RunnerClass.driver.findElement(Locators.searchbox).clear();
	                        RunnerClass.driver.findElement(Locators.searchbox).sendKeys(building);
	                        RunnerClass.wait.until(ExpectedConditions.invisibilityOf(RunnerClass.driver.findElement(Locators.searchingLoader)));
	                        try {
	                            RunnerClass.driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	                            if (RunnerClass.driver.findElement(Locators.noItemsFoundMessagewhenLeaseNotFound).isDisplayed()) {
	                                System.out.println("Building Not Found");
	                                RunnerClass.failedReason = RunnerClass.failedReason + "," + "Building Not Found";
	                                return false;
	                            }
	                        } catch (Exception e3) {
	                        }
	                    } catch (Exception e) {
	                        System.out.println("Building Not Found");
	                        RunnerClass.failedReason = RunnerClass.failedReason + "," + "Building Not Found";
	                        return false;
	                    }
	                }
	            }
	        } catch (Exception e2) {
	        }
	        RunnerClass.driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
	        Thread.sleep(1000);
	        System.out.println(building);
	        // Select Lease from multiple leases
	        List<WebElement> displayedCompanies = null;
	        try {
	            displayedCompanies = RunnerClass.driver.findElements(Locators.searchedLeaseCompanyHeadings);
	        } catch (Exception e)
	        {
	        }
	        boolean leaseSelected = false;
	        for (int i = 0; i < displayedCompanies.size(); i++) 
	        {
	            String companyName = displayedCompanies.get(i).getText();
	            if (companyName.toLowerCase().contains(company.toLowerCase()) && !companyName.contains("Legacy")) 
	            {
	                List<WebElement> leaseList = RunnerClass.driver.findElements(By.xpath("(//*[@class='section'])[" + (i + 1) + "]/ul/li/a"));
	                //System.out.println(leaseList.size());
	                //Check if displayed leases list has the building name completely first
	                for (int j = 0; j < leaseList.size(); j++) {
	                    String lease = leaseList.get(j).getText();
	                    if (lease.toLowerCase().contains(RunnerClass.completeBuildingAbbreviation.toLowerCase())) {
	                        try {
	                            RunnerClass.portfolioType = RunnerClass.driver.findElement(By.xpath("(//*[@class='section'])[" + (i + 1) + "]/ul/li[" + (j + 1) + "]/a")).getText().trim().split(":")[0];
	                            RunnerClass.portfolioName = RunnerClass.portfolioType;
	                            System.out.println("Portfolio type = " + RunnerClass.portfolioType);
	                        } catch (Exception e) {
	                        }
	                        RunnerClass.driver.findElement(By.xpath("(//*[@class='section'])[" + (i + 1) + "]/ul/li[" + (j + 1) + "]/a")).click();
	                        leaseSelected = true;
	                        break;
	                    }
	                }
	            
	            if (leaseSelected == false) {
	                for (int j = 0; j < leaseList.size(); j++) {
	                    String lease = leaseList.get(j).getText();
	                    if (lease.toLowerCase().contains(building.toLowerCase()) && lease.contains(":")) {
	                        try {
	                            RunnerClass.portfolioType = RunnerClass.driver.findElement(By.xpath("(//*[@class='section'])[" + (i + 1) + "]/ul/li[" + (j + 1) + "]/a")).getText().trim().split(":")[0];
	                            RunnerClass.portfolioName = RunnerClass.portfolioType;
	                            System.out.println("Portfolio type = " + RunnerClass.portfolioType);
	                        } catch (Exception e) {
	                        }
	                        RunnerClass.driver.findElement(By.xpath("(//*[@class='section'])[" + (i + 1) + "]/ul/li[" + (j + 1) + "]/a")).click();
	                        leaseSelected = true;
	                        break;
	                    }
	                }
	            }
	            boolean checkBuildingIsClicked = false;
	            if (leaseSelected == false) {
	                String companyName1 = displayedCompanies.get(i).getText();
	                if (companyName1.toLowerCase().contains(company.toLowerCase()) && !companyName1.contains("Legacy") && !companyName1.contains("Sandbox")) 
	                {
	                    List<WebElement> advancesearch = RunnerClass.driver.findElements(Locators.advancedSearch);
	                    advancesearch.get(i).click();
	                    RunnerClass.actions.moveToElement(RunnerClass.driver.findElement(Locators.advancedSearch_buildingsSection)).build().perform();
	                    List<WebElement> buildingAddresses = RunnerClass.driver.findElements(Locators.advancedSearch_buildingAddresses);
	                    for (int k = 0; k < buildingAddresses.size(); k++)
	                    {
	                        String address = buildingAddresses.get(k).getText();
	                        if (address.toLowerCase().endsWith(building.toLowerCase())) 
	                        {
	                            buildingAddresses.get(k).click();
	                            checkBuildingIsClicked = true;
	                            leaseSelected = true;
	                            break;
	                        }
	                    }
	                  //  if (checkBuildingIsClicked == true)
	                      //  break;
	                    }
	            }
	             
	                }
	            
	            if (leaseSelected == true) {
	            	 try {
	     		        RunnerClass.js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	     		        Thread.sleep(2000);
	     		        if (RunnerClass.driver.findElement(Locators.leasesTab).getText().equals("Leases")) {
	     		            RunnerClass.driver.findElement(Locators.leasesTab).click();
	     		        } else {
	     		            RunnerClass.driver.findElement(Locators.leasesTab2).click();
	     		        }
	     		        RunnerClass.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	     		        RunnerClass.wait = new WebDriverWait(RunnerClass.driver, Duration.ofSeconds(5));
	     		        try {
	     		            RunnerClass.driver.findElement(By.partialLinkText(ownerName.trim())).click();
	     		        } catch (Exception e) {
	     		        }
	     		        intermittentPopUp();
	     		    } catch (Exception e) {
	     		        System.out.println("Unable to download Lease Agreement");
	     		        RunnerClass.failedReason = RunnerClass.failedReason + "," + "Unable to download Lease Agreement";
	     		        return false;
	     		    }
	                return true;
	            }
	        }
	        if (leaseSelected == false) {
	            RunnerClass.failedReason = RunnerClass.failedReason + ", Building Not Found";
	            return false;
	        }
	    
	    }catch (Exception e) {
	        RunnerClass.failedReason = RunnerClass.failedReason + ", Issue in selecting Building";
	        return false;
	    }
	    return true;
	}


					
	 public static void intermittentPopUp() {
	        try {
	            RunnerClass.driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
	            RunnerClass.wait = new WebDriverWait(RunnerClass.driver, Duration.ofSeconds(1));
	            
	            try {
	                if (RunnerClass.driver.findElement(Locators.popUpAfterClickingLeaseName).isDisplayed()) {
	                    RunnerClass.driver.findElement(Locators.popupClose).click();
	                }
	            } catch (Exception e) {}
	            
	            try {
	                if (RunnerClass.driver.findElement(Locators.scheduledMaintanancePopUp).isDisplayed()) {
	                    RunnerClass.driver.findElement(Locators.scheduledMaintanancePopUpOkButton).click();
	                }
	            } catch (Exception e) {}
	            
	            try {
	                if (RunnerClass.driver.findElement(Locators.scheduledMaintanancePopUpOkButton).isDisplayed()) {
	                    RunnerClass.driver.findElement(Locators.scheduledMaintanancePopUpOkButton).click();
	                }
	            } catch (Exception e) {}
	            
	            RunnerClass.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	            RunnerClass.wait = new WebDriverWait(RunnerClass.driver, Duration.ofSeconds(5));
	       
	        } catch (Exception e) {}
	 
	 }

	public static boolean downloadLeaseAgreement(String building, String ownerName) throws Exception
	{
		try {

		RunnerClass.driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
        RunnerClass.wait = new WebDriverWait(RunnerClass.driver, Duration.ofSeconds(15));
		RunnerClass.js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		
		RunnerClass.driver.findElement(Locators.notesAndDocs).click();
		
		List<WebElement> documents = RunnerClass.driver.findElements(Locators.documentsList);
		boolean checkLeaseAgreementAvailable = false;
		 
		for(int i =0;i<documents.size();i++)
		{
			if(documents.get(i).getText().startsWith("RT Renewal Signed"))//&&documents.get(i).getText().contains(leaseFirstName))
			{
				documents.get(i).click();
				checkLeaseAgreementAvailable = true;
				break;
			}
		}
		if(checkLeaseAgreementAvailable == false)
		for(int i =0;i<documents.size();i++)
		{
			if(documents.get(i).getText().startsWith("RT Renewal Signed"))//&&documents.get(i).getText().contains(leaseFirstName))
			{
				documents.get(i).click();
				checkLeaseAgreementAvailable = true;
				break;
			}
		}
		if(checkLeaseAgreementAvailable == false)
		for(int i =0;i<documents.size();i++)
		{
			if(documents.get(i).getText().startsWith("RT - RENEWAL"))//&&documents.get(i).getText().contains(leaseFirstName))
			{
				documents.get(i).click();
				checkLeaseAgreementAvailable = true;
				break;
			}
		}
		
		if(checkLeaseAgreementAvailable == false)
		for(int i =0;i<documents.size();i++)
		{
			if(documents.get(i).getText().startsWith("RT_Full_Lease"))//&&documents.get(i).getText().contains(leaseFirstName))
			{
				documents.get(i).click();
				checkLeaseAgreementAvailable = true;
				break;
			}
		}
		if(checkLeaseAgreementAvailable == false)
		
			for(int i =0;i<documents.size();i++)
			{
				if(documents.get(i).getText().startsWith("Full Lease -"))//&&documents.get(i).getText().contains(leaseFirstName))
				{
					documents.get(i).click();
					checkLeaseAgreementAvailable = true;
					break;
				}
			}
		
		if(checkLeaseAgreementAvailable == false)
		
		for(int i =0;i<documents.size();i++)
		{
			if(documents.get(i).getText().startsWith("RENEWAL"))//&&documents.get(i).getText().contains(leaseFirstName))
			{
				documents.get(i).click();
				checkLeaseAgreementAvailable = true;
				break;
			}
		}
		
		
		if(checkLeaseAgreementAvailable == false)
		{
		for(int i =0;i<documents.size();i++)
		{
			if(documents.get(i).getText().toLowerCase().startsWith("renewal_"))//&&documents.get(i).getText().contains(leaseFirstName))
			{
				documents.get(i).click();
				checkLeaseAgreementAvailable = true;
				break;
			}
		}
		
		}
		if(checkLeaseAgreementAvailable == false)
		{
		for(int i =0;i<documents.size();i++)
		{
			if(documents.get(i).getText().startsWith("Renewal"))//&&documents.get(i).getText().contains(leaseFirstName))
			{
				documents.get(i).click();
				checkLeaseAgreementAvailable = true;
				break;
			}
		}
		
		}
		if(checkLeaseAgreementAvailable == false)
		
		for(int i =0;i<documents.size();i++)
		{
			if(documents.get(i).getText().toLowerCase().startsWith("Full_Lease"))//&&documents.get(i).getText().contains(leaseFirstName))
			{
				documents.get(i).click();
				checkLeaseAgreementAvailable = true;
				break;
			}
		}
		if(checkLeaseAgreementAvailable == false)
		for(int i =0;i<documents.size();i++)
		{
			if(documents.get(i).getText().startsWith("RENEWAL"))//&&documents.get(i).getText().contains(leaseFirstName))
			{
				documents.get(i).click();
				checkLeaseAgreementAvailable = true;
				break;
			}
		}
		for(int i =0;i<documents.size();i++)
		{
			if(documents.get(i).getText().startsWith("Full"))//&&documents.get(i).getText().contains(leaseFirstName))
			{
				documents.get(i).click();
				checkLeaseAgreementAvailable = true;
				break;
			}
		}
		
		if(checkLeaseAgreementAvailable==false)
		{
			System.out.println("Unable to download Lease Agreement");
		    RunnerClass.failedReason =  RunnerClass.failedReason+","+ "Unable to download Lease Agreement";
			return false;
		}
		Thread.sleep(20000);
		File file = RunnerClass.getLastModified();
		
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(RunnerClass.driver).withTimeout(Duration.ofSeconds(25)).pollingEvery(Duration.ofMillis(100));
		wait.until( x -> file.exists());
		Thread.sleep(10000);
		return true;
		}
		catch(Exception e)
		{
			System.out.println("Unable to download Lease Agreement");
		    RunnerClass.failedReason =  RunnerClass.failedReason+","+"Unable to download Lease Agreement";
			return false;
		}
	}

}

