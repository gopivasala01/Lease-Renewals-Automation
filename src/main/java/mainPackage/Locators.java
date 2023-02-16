package mainPackage;

import org.openqa.selenium.By;

public class Locators 
{
	public static By username= By.id("loginEmail");
	public static By password= By.name("password");
	public static By signInButton= By.xpath("//*[@class=\"button login-button\"]");
	public static By searchBar= By.id("eqsSearchText");
	public static By noItemsFound= By.xpath("//*[text()='No Items Found']");
	public static By Lease= By.xpath("//*[@class='tabbedSection']/a[4]");
	public static By Edit= By.xpath("//*[@value='Edit']");
	public static By AutoCharges=By.xpath("//*[text()=\"Auto Charges\"]");
	
	public static By searchbox = By.name("eqsSearchText");
	public static By dashboardsTab = By.linkText("Dashboards");
	public static By searchingLoader = By.xpath("//*[@id='eqsResult']/h1");
	public static By searchedLeaseCompanyHeadings = By.xpath("//*[@id='eqsResult']/div/div/h1");
	//public static By selectSearchedLease = By.partialLinkText(RunnerClass.buildingAbbreviation);
	
	public static By leasesTab = By.xpath("//*[@class='tabbedSection']/a[4]");	
    public static By RCDetails = By.xpath("//*[contains(text(),'Resident Coordinator [Name/Phone/Email]')]/following::td[1]/div");
    public static By leaseStartDate_PW = By.xpath("//*[@id='infoTable']/tbody/tr[3]/td[1]");
    public static By leaseEndDate_PW = By.xpath("//*[@id='infoTable']/tbody/tr[3]/td[2]");
    public static By popUpAfterClickingLeaseName = By.id("viewStickyNoteForm");
    public static By popupClose = By.xpath("//*[@id='editStickyBtnDiv']/input[2]");
    public static By notesAndDocs = By.id("notesAndDocuments");
    public static By documentsList = By.xpath("//*[@id='documentHolderBody']/tr/td[1]/a"); 
    
    public static By summaryEditButton = By.xpath("//*[@value='Edit']");
    public static By newAutoCharge = By.xpath("//*[@value='New Auto Charge']");
    
    public static By autoChargeCodesList = By.xpath("//*[@id='autoChargesTable']/tbody/tr/td[1]");
    public static By checkPortfolioType = By.xpath("//*[@title='Click to jump to portfolio']");
    
    public static By published = By.xpath("//*[contains(text(),'Published Rental')]/following::td[1]");
    public static By listingAgent = By.xpath("//*[contains(text(),'Listing Agent [Name/Phone/Email]')]/following::td[1]");
    
    public static By renewalPopup = By.id("viewStickyNoteForm");
    public static By renewalPoupCloseButton = By.xpath("//*[@id='viewStickyNoteForm']/div/div[1]/input[2]");
    
    public static By autoCharge_List = By.xpath("//*[@id='autoChargesTable']/tbody/tr/td[1]");
    public static By autoCharge_List_Amounts = By.xpath("//*[@id='autoChargesTable']/tbody/tr/td[3]");
    public static By autoCharge_List_EndDates = By.xpath("//*[@id='autoChargesTable']/tbody/tr/td[6]");
    public static By autoCharge_List_startDates = By.xpath("//*[@id='autoChargesTable']/tbody/tr/td[5]");
    public static By autoCharge_StartDate = By.name("charge.startDateAsString");
    public static By autoCharge_EndDate = By.name("charge.endDateAsString");
    public static By autoCharge_Amount = By.name("charge.amountAsString");
    public static By autoCharge_CancelButton = By.xpath("//*[@id='editAutoChargeForm']/descendant::div[4]/input[2]");
    public static By autoCharge_SaveButton = By.xpath("(//*[@class='primaryButtons'])[3]/input[1]");
    public static By autoCharge_MonthlyRentEditButton = By.xpath("//*[@id='autoChargesTable']/tbody/tr/td[9]/a[1]");
    public static By autoCharge_petRentList = By.xpath("//*[@id='autoChargesTable']/tbody/tr/td[3]");
    
    public static By accountDropdown = By.name("charge.GLAccountID");
    public static By autoCharge_refField = By.name("charge.refNo");
    public static By autoCharge_Description = By.name("charge.description");
    
    //Other information
    public static By renewalStatusDropdown = By.xpath("//*[text()='Renewal Status']/following::select[1]");
    public static By renewalFollowUpNotes = By.xpath("//*[text()='Renewal Follow-Up Notes']/following::input[1]");
    public static By renewalExecutionDate = By.xpath("//*[text()='Renewal Execution Date']/following::input[1]");
    public static By currentMonthlyRent = By.xpath("//*[text()='Current Monthly Rent']/following::input[1]");
    public static By priorMonthlyRent = By.xpath("//*[text()='Prior Monthly Rent']/following::input[1]");
    public static By renewalCoordinatorName = By.xpath("//*[text()='Renewal Coordinator Name']/following::input[1]");
    public static By saveLease = By.xpath("(//*[@class='primaryButtons'])[2]/input[1]");
    public static By cancelLease = By.xpath("(//*[@class='primaryButtons'])[2]/input[2]");
    
    //Related Activities
    public static By relatedActivities_LeaseRenewal = By.xpath("//*[text()=' Lease Renewal ']");
    public static By relatedActivities_newStartDate = By.xpath("//*[text()=' New Start Date']");
    public static By relatedActivities_newEndDate = By.xpath("//*[text()=' New End Date']/following::input[1]");
    public static By relatedActivities_renewalOnDate = By.xpath("//*[text()=' Renewed On Date']/following::input[1]");
    public static By relatedActivities_save = By.xpath("(//*[@class='primaryButtons'])[4]/input[1]");
    public static By relatedActivities_cancel = By.xpath("(//*[@class='primaryButtons'])[4]/input[2]");
    
    public static By relatedActivities_newLeaseRenewalPopUpHeading = By.xpath("//*[text()='New Lease Renewal']");
    
    
    
}
