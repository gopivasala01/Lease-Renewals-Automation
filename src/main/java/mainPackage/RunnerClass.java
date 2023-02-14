package mainPackage;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RunnerClass 
{
	public static String[][] pendingRenewalLeases; 
    public static String company;
    public static String buildingAbbreviation;
    public static String ownerName;
    public static WebDriver driver;
    
	public static ChromeOptions options;
	public static Actions actions;
	public static JavascriptExecutor js;
	public static WebDriverWait wait;
	public static String[][] pendingBuildingList;
	public static int updateStatus;
	public static String failedReason ="";
	public static ArrayList<String> successBuildings = new ArrayList<String>();
	public static ArrayList<String> failedBuildings = new ArrayList<String>();
	public static String[][] completedBuildingList;
	public static String [] statusList;
	public static String currentDate = "";
	public static HashMap<String,String> failedReaonsList= new HashMap<String,String>();
	public static String leaseStatuses[][];
	public static String UWStatuses[][];
	public static String downloadFilePath;
	public static String monthlyRent;
	public static String startDate;
	public static String monthlyRentInPW;
	public static String startDateInPW;
	public static String portfolioType;
	public static boolean published;
	public static boolean listingAgent;
	public static String currentTime;
	
	
	public static void main(String[] args) throws Exception 
	{
		//Get Pending Renewal Leases
		//Company,BuildingAbbreviation, LeaseNae
		DataBase.getBuildingsList();
		for(int i=0;i<pendingRenewalLeases.length;i++)
		{
		  company = pendingRenewalLeases[i][0];
		  buildingAbbreviation = pendingRenewalLeases[i][1];
		  ownerName = pendingRenewalLeases[i][2];
		  
		  
		  //Change the Status of the Lease to Started so that it won't run again in the Jenkins scheduling Process
		  DataBase.insertData(buildingAbbreviation,"Started",6);
		  
		  if(buildingAbbreviation.split("-")[0].trim().contains(" "))
			  buildingAbbreviation = buildingAbbreviation;
				else 
					buildingAbbreviation = buildingAbbreviation.split("-")[0].trim();
          // Login to the PropertyWare		  
		  PropertyWare.login();
		  try
		  {
		  //Search building in property Ware
		   if(PropertyWare.searchBuilding(company, buildingAbbreviation)==true)
			{
				if(PropertyWare.downloadLeaseAgreement(buildingAbbreviation, ownerName)==true)
				{
					if(PDFReader.readPDFPerMarket(company)==true)
					{
						PropertyWare_InsertData.clearExistingAutoCharges();
						PropertyWare_InsertData.addingNewAutoCharges();
					}
					else 
					{
						String updateSuccessStatus = "update [Automation].[leaseAuditAutomation] Set Status ='Failed', completedDate = getdate() ,Notes = 'Unable to Read Lease Agreement' where [BuildingAbbreviation] = '"+buildingAbbreviation+"'";
				    	DataBase.updateTable(updateSuccessStatus);
					}
				}
				else 
				{
					String updateSuccessStatus = "update [Automation].[leaseAuditAutomation] Set Status ='Failed', completedDate = getdate() ,Notes = 'Unable to download Lease Agreement' where [BuildingAbbreviation] = '"+buildingAbbreviation+"'";
			    	DataBase.updateTable(updateSuccessStatus);
				}
			}
		    else 
		    {
 		    	String updateSuccessStatus = "Update [Automation].leaseRenewalAutomation Set Status ='Failed', StatusID=3,NotAutomatedFields='"+failedReason+"',LeaseCompletedDate= getDate() where BuildingName like '%"+buildingAbbreviation+"%'";
		    	DataBase.updateTable(updateSuccessStatus);
		    }
		   RunnerClass.driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
	        RunnerClass.wait = new WebDriverWait(RunnerClass.driver, Duration.ofSeconds(5));
		   try
			{
				if(RunnerClass.driver.findElement(Locators.renewalPopup).isDisplayed())
				{
					RunnerClass.driver.findElement(Locators.renewalPoupCloseButton).click();
				}

			}
			catch(Exception e) {}
		   RunnerClass.driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
	        RunnerClass.wait = new WebDriverWait(RunnerClass.driver, Duration.ofSeconds(15));
		   driver.navigate().refresh();
		   RunnerClass.js.executeScript("window.scrollBy(document.body.scrollHeight,0)");
		   RunnerClass.driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
	        RunnerClass.wait = new WebDriverWait(RunnerClass.driver, Duration.ofSeconds(5));
		   try
			{
				if(RunnerClass.driver.findElement(Locators.renewalPopup).isDisplayed())
				{
					RunnerClass.driver.findElement(Locators.renewalPoupCloseButton).click();
				}

			}
			catch(Exception e) {}
		   RunnerClass.driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
	        RunnerClass.wait = new WebDriverWait(RunnerClass.driver, Duration.ofSeconds(15));
		  }
		  catch(Exception e)
		  {
			  e.printStackTrace();
			  driver.navigate().refresh();
			  RunnerClass.js.executeScript("window.scrollBy(document.body.scrollHeight,0)");
		  }
		}
		
		
		

	}   

	public static File getLastModified() throws Exception
	{
		
	    File directory = new File(AppConfig.downloadFilePath);
	    File[] files = directory.listFiles(File::isFile);
	    long lastModifiedTime = Long.MIN_VALUE;
	    File chosenFile = null;

	    if (files != null)
	    { 
	        for (File file : files)
	        {
	            if (file.lastModified() > lastModifiedTime)
	            {
	                chosenFile = file;
	                lastModifiedTime = file.lastModified();
	            }
	        }
	    }

	    return chosenFile;
	}
	
	public static String convertDate(String dateRaw) throws Exception
	{
		try
		{
		SimpleDateFormat format1 = new SimpleDateFormat("MMMM dd, yyyy");
	    SimpleDateFormat format2 = new SimpleDateFormat("MM/dd/yyyy");
	    Date date = format1.parse(dateRaw.trim().replaceAll(" +", " "));
	    System.out.println(format2.format(date));
		return format2.format(date).toString();
		}
		catch(Exception e)
		{
		return "Error";
		}
	}
	
	    public static String firstDayOfMonth(String date) throws Exception 
	    {
	    	//String string = "02/05/2014"; //assuming input
	        DateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	        Date dt = sdf .parse(date);
	        Calendar c = Calendar.getInstance();
	        c.setTime(dt);
	        if(portfolioType=="MCH")
	        c.add(Calendar.MONTH, 1);  //adding a month directly - gives the start of next month.
	        else c.add(Calendar.MONTH, 2);
	        c.set(Calendar.DAY_OF_MONTH, 01);
	        String firstDate = sdf.format(c.getTime());
	        System.out.println(firstDate);
	        return firstDate;
	    }
	    public static String getCurrentDateTime()
	    {
	    	currentTime ="";
	    	 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
			 LocalDateTime now = LocalDateTime.now();  
			// System.out.println(dtf.format(now));
			 currentTime = dtf.format(now);
			 return currentTime;
	    }
	    public static String lastDateOfTheMonth(String date) throws Exception
	    {
	    	//String date =RunnerClass.convertDate("January 1, 2023");
	    	LocalDate lastDayOfMonth = LocalDate.parse(date, DateTimeFormatter.ofPattern("M/dd/yyyy"))
	    	       .with(TemporalAdjusters.lastDayOfMonth());
	    	String newDate = new SimpleDateFormat("MM/dd/yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(lastDayOfMonth.toString()));
	    	return newDate;
	    }
}

/*
String success = String.join(",",successBuildings);
String failed = String.join(",",failedBuildings);
try
{
	if(successBuildings.size()>0)
	{
	String updateSuccessStatus = "update [Automation].[leaseAuditAutomation] Set Status ='Completed', completedDate = getdate() where [BuildingAbbreviation] in ("+success+")";
	DataBase.updateTable(updateSuccessStatus);
	}
	if(failedBuildings.size()>0)
	{
	String failedReasons = String.join(",",failedReaonsList.values());
	String failedBuildings = String.join(",",failedReaonsList.keySet());
	String failedBuildingsUpdateQuery = "";
	for(int i=0;i<failedReaonsList.size();i++)
	{
		String buildingAbbr = failedBuildings.split(",")[i].trim();
		String failedReason = failedReasons.split(",")[i].trim();
		failedBuildingsUpdateQuery =failedBuildingsUpdateQuery+"\nupdate [Automation].[leaseAuditAutomation] Set Status ='Failed', completedDate = getdate(),Notes='"+failedReason+"' where [BuildingAbbreviation] ='"+buildingAbbr+"'";
		
	}
	//String updateFailedStatus = "update automation.TargetRent Set Status ='Failed', completedOn = getdate(),Notes='"+failedReason+"' where [Building/Unit Abbreviation] in ("+failed+")";
	DataBase.updateTable(failedBuildingsUpdateQuery);
	}
	*/

