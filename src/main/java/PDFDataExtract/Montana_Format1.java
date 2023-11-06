package PDFDataExtract;

import java.io.File;
import java.io.FileInputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import mainPackage.PDFReader;
import mainPackage.RunnerClass;

public class Montana_Format1 
{
	public static boolean montana() throws Exception
	{
		try
		{
			File file = RunnerClass.getLastModified();
			System.out.println(file);
			FileInputStream fis = new FileInputStream(file);
			PDDocument document = PDDocument.load(fis);
			PDFTextStripper stripper = new PDFTextStripper();
			String text = stripper.getText(document);
			stripper.setStartPage(1);
			stripper.setEndPage(1);
			String firstPageText = stripper.getText(document);
			text = text.replaceAll(System.lineSeparator(), " ");
			text = text.replaceAll(" +", " ");
			firstPageText = firstPageText.replaceAll(System.lineSeparator(), " ");
			firstPageText = firstPageText.replaceAll(" +", " ");
			System.out.println("First page text:\n" + firstPageText);
			System.out.println("All pages text:\n" + text);
			document.close();
	    
	    System.out.println("------------------------------------------------------------------");
	    
	    String pattern = "\\d{1,2}/\\d{1,2}/\\d{4}"; 
	    Pattern datePattern = Pattern.compile(pattern);

	    Matcher matcher = datePattern.matcher(firstPageText);
	    String renewalExecutionDate = "";

	    
	    while (matcher.find()) {
	    	PDFReader.renewalExecutionDate = matcher.group();
	    }
	    if(PDFReader.renewalExecutionDate.isEmpty())
	    {
	    	 matcher = datePattern.matcher(text);
	    	 while (matcher.find()) {
	 	    	PDFReader.renewalExecutionDate = matcher.group();
	 	    	
	 	    }
	    }

	    String[] SplitDate = PDFReader.renewalExecutionDate.split("/");

   	 for (int i = 0; i < 2; i++) {
   	     if (SplitDate[i].length() == 1) {
   	         // Add a leading zero for single-digit values in the first two components
   	         SplitDate[i] = "0" + SplitDate[i];
   	     }
   	 }
   	 
   	 PDFReader.renewalExecutionDate= SplitDate[0]+"/"+ SplitDate[1]+"/"+SplitDate[2];

	    System.out.println("Last date mentioned on the page: " + PDFReader.renewalExecutionDate);
		    try
		    {
		    	PDFReader.commencementDate = text.substring(text.indexOf(PDFAppConfig.IdahoFalls_Format1.commencementDate_Prior)+PDFAppConfig.IdahoFalls_Format1.commencementDate_Prior.length(),text.indexOf(PDFAppConfig.IdahoFalls_Format1.commencementDate_After));
		    }
		    catch(Exception e)
		    {
		    	PDFReader.commencementDate = "Error";
		    	e.printStackTrace();
		    }
		    System.out.println("Commensement Date = "+PDFReader.commencementDate);
		   try
		    {
			   PDFReader.expirationDate = text.substring(text.indexOf(PDFAppConfig.IdahoFalls_Format1.expirationDate_Prior)+PDFAppConfig.IdahoFalls_Format1.expirationDate_Prior.length(),text.indexOf(PDFAppConfig.IdahoFalls_Format1.expirationDate_After));
		    	//PDFReader.expirationDate = PDFReader.expirationDate.substring(0,PDFReader.expirationDate.indexOf("(the")).trim();
		    }
		    catch(Exception e)
		    {
		    	 PDFReader.expirationDate = "Error";
		    	 e.printStackTrace();
		    }
		   System.out.println("Expiration Date = "+PDFReader.expirationDate);
		    
			
			//Monthly Rent
		    try
		    {
		    	PDFReader.monthlyRent = text.substring(text.indexOf(PDFAppConfig.IdahoFalls_Format1.monthlyRent_Prior)+PDFAppConfig.IdahoFalls_Format1.monthlyRent_Prior.length()).trim().split(" ")[0];
		    	if(PDFReader.monthlyRent.matches(".*[a-zA-Z]+.*"))
		    		PDFReader.monthlyRent = "Error";
		    	if(PDFReader.monthlyRent.contains("$"))
		    		PDFReader.monthlyRent = PDFReader.monthlyRent.replace("$", "");
		    }
		    catch(Exception e)
		    {
		    	PDFReader.monthlyRent = "Error";
		    	e.printStackTrace();
		    }
		    System.out.println("Monthly Rent = "+PDFReader.monthlyRent);
		    
		    //HVAC Air Filter Fee (OR) Resident Benefits Package
		    if(text.contains(PDFAppConfig.IdahoFalls_Format1.HVACFilterAddendumTextAvailabilityCheck))
		    {
		    	PDFReader.HVACFilterFlag = true;
		    	//HVAC Air Filter Fee
		    	 try
				    {
				    	PDFReader.HVACAirFilterFee = text.substring(text.indexOf(PDFAppConfig.IdahoFalls_Format1.HVACAirFilterFee)+PDFAppConfig.IdahoFalls_Format1.HVACAirFilterFee.length()).trim().split(" ")[0];
				    	if(PDFReader.HVACAirFilterFee.matches(".*[a-zA-Z]+.*"))
				    		PDFReader.HVACAirFilterFee = "Error";
				    }
				    catch(Exception e)
				    {
				    	PDFReader.HVACAirFilterFee = "Error";
				    	e.printStackTrace();
				    }
				    System.out.println("HVAC Air Filter Fee = "+PDFReader.HVACAirFilterFee);
		    }
		    
		    if(text.contains(PDFAppConfig.IdahoFalls_Format1.residentBenefitsPackageCheck)&&(!text.contains("Resident Benefits Package Opt-Out Addendum")||!text.contains("RESIDENT BENEFITS PACKAGE OPT-OUT ADDENDUM")))
		    {
		    	PDFReader.residentBenefitsPackageAvailabilityCheck = true;
		    	//HVAC Air Filter Fee
		    	 try
				    {
				    	PDFReader.residentBenefitsPackage = text.substring(text.indexOf(PDFAppConfig.IdahoFalls_Format1.RBP_Prior)+PDFAppConfig.IdahoFalls_Format1.RBP_Prior.length()).trim().split(" ")[0].replaceAll("[^0-9a-zA-Z.]", "");
				    	if(PDFReader.residentBenefitsPackage.matches(".*[a-zA-Z]+.*"))
				    		PDFReader.residentBenefitsPackage = "Error";
				    }
				    catch(Exception e)
				    {
				    	PDFReader.residentBenefitsPackage = "Error";
				    	e.printStackTrace();
				    }
				    System.out.println("Resident Benefits Package = "+PDFReader.residentBenefitsPackage);
		    }
		    
		    
		    //Prorate Rent
		    try
		    {
		    	PDFReader.proratedRent = text.substring(text.indexOf(PDFAppConfig.IdahoFalls_Format1.prorateRent_Prior)+PDFAppConfig.IdahoFalls_Format1.prorateRent_Prior.length()).trim().split(" ")[0];
		    	if(PDFReader.proratedRent.matches(".*[a-zA-Z]+.*"))
		    		PDFReader.proratedRent = "Error";
		    }
		    catch(Exception e)
		    {
		    	PDFReader.proratedRent = "Error";
		    	e.printStackTrace();
		    }
		    System.out.println("Prorate Rent = "+PDFReader.proratedRent);
		    
			//Pet Rent
		    if(text.contains(PDFAppConfig.IdahoFalls_Format1.petAgreementAvailabilityCheck))
		    {
		    	PDFReader.petFlag = true;
		    	System.out.println("Pet Addendum Available = "+PDFReader.petFlag);
		    	
		    	try
		    	{
		    		PDFReader.petRent = text.substring(text.indexOf(PDFAppConfig.IdahoFalls_Format1.petRent_Prior)+PDFAppConfig.IdahoFalls_Format1.petRent_Prior.length()).trim().split(" ")[0].trim();
		    		if(PDFReader.petRent.matches(".*[a-zA-Z]+.*"))
			    		PDFReader.petRent = "Error";
		    	}
		    	catch(Exception e)
		    	{
		    		PDFReader.petRent = "Error";
		    	}
		    	System.out.println("Pet Rent = "+PDFReader.petRent);
		    }
		    
		    //Lease Renewal Admin Fee
		    try
	    	{
	    		PDFReader.leaseRenewalFee = text.substring(text.indexOf(PDFAppConfig.IdahoFalls_Format1.leaseRenewalFee_Prior)+PDFAppConfig.IdahoFalls_Format1.leaseRenewalFee_Prior.length()).trim().split(" ")[0].trim();
	    		if(PDFReader.leaseRenewalFee.matches(".*[a-zA-Z]+.*"))
		    		PDFReader.leaseRenewalFee = "Error";
	    	}
	    	catch(Exception e)
	    	{
	    		PDFReader.leaseRenewalFee = "Error";
	    	}
	    	System.out.println("Lease Renewal Fee = "+PDFReader.leaseRenewalFee);
	    	
	    	if (text.contains(PDFAppConfig.Boise_Format1.residentUtilityBillTextCheck)) {
		        PDFReader.residentUtilityBillFlag = true;
		        try {
		            // Extract the "D." section under "Special Provisions"
		            String specialProvisions = extractSpecialProvisions(text);

		            if (specialProvisions.isEmpty()) {
		                PDFReader.RUBS = "Error"; // Handle the case when the "D." section is not found
		            } else {
		                // Define a regular expression pattern to match dollar values
		                Pattern pattern1 = Pattern.compile("\\$\\d+(\\.\\d{2})");
		                Matcher matcher1 = pattern1.matcher(specialProvisions);
		                
		                String lastDollarValue = "Error"; // Default if no value is found

		                // Find and store the last dollar value
		                while (matcher1.find()) {
		                    lastDollarValue = matcher1.group();
		                }
		                
		                PDFReader.RUBS = lastDollarValue.trim().split(" ")[0].replaceAll("[^0-9a-zA-Z.]", "");
		            }
		            System.out.println("RUBS = " + PDFReader.RUBS);
		        } catch (Exception e) {
		            PDFReader.RUBS = "Error";
		        }
		    }
				return true;
			
		}
		catch(Exception e)
		{
			System.out.println("Issue in fetching values from PDF");
			RunnerClass.failedReason =  RunnerClass.failedReason+","+"Issue in fetching values from PDF";
			return false;
		}

	}
	public static String extractSpecialProvisions(String text) {
	    int startIndex = text.indexOf("SPECIAL PROVISIONS:");
	    if (startIndex == -1) {
	        return ""; // "D." section not found
	    }

	    int endIndex = text.indexOf("27. DEFAULT", startIndex);

	    if (endIndex == -1) {
	        return text.substring(startIndex); // End of document, extract to the end
	    } else {
	        return text.substring(startIndex, endIndex);
	    }
	}

}
