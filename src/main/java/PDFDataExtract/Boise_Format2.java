package PDFDataExtract;

import java.io.File;
import java.io.FileInputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import mainPackage.PDFReader;
import mainPackage.RunnerClass;

public class Boise_Format2 
{
	public static String text ;
	//public static void main(String[] args) 
	public static boolean boise() throws Exception
	{
		try
		{
			File file = RunnerClass.getLastModified();
			RunnerClass.logger.info(file);
			FileInputStream fis = new FileInputStream(file);
			PDDocument document = PDDocument.load(fis);
			PDFTextStripper stripper = new PDFTextStripper();
			 text = stripper.getText(document);
			stripper.setStartPage(1);
			stripper.setEndPage(1);
			String firstPageText = stripper.getText(document);
			text = text.replaceAll(System.lineSeparator(), " ");
			text = text.replaceAll(" +", " ");
			firstPageText = firstPageText.replaceAll(System.lineSeparator(), " ");
			firstPageText = firstPageText.replaceAll(" +", " ");
			RunnerClass.logger.info("First page text:\n" + firstPageText);
			RunnerClass.logger.info("All pages text:\n" + text);
			document.close();
	    
	    RunnerClass.logger.info("------------------------------------------------------------------");
	    
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


	    RunnerClass.logger.info("Last date mentioned on the page: " + PDFReader.renewalExecutionDate);
		    try
		    {
		    	PDFReader.commencementDate = text.substring(text.indexOf(PDFAppConfig.Boise_Format1.commencementDate_Prior)+PDFAppConfig.Boise_Format1.commencementDate_Prior.length(),text.indexOf(PDFAppConfig.Boise_Format1.commencementDate_After));
		    }
		    catch(Exception e)
		    {
		    	PDFReader.commencementDate = "Error";
		    	e.printStackTrace();
		    }
		    RunnerClass.logger.info("Commensement Date = "+PDFReader.commencementDate);
		   try
		    {
			   PDFReader.expirationDate = text.substring(text.indexOf(PDFAppConfig.Boise_Format1.expirationDate_Prior)+PDFAppConfig.Boise_Format1.expirationDate_Prior.length(),text.indexOf(PDFAppConfig.Boise_Format1.expirationDate_After));
		    	//PDFReader.expirationDate = PDFReader.expirationDate.substring(0,PDFReader.expirationDate.indexOf("(the")).trim();
		    }
		    catch(Exception e)
		    {
		    	 PDFReader.expirationDate = "Error";
		    	 e.printStackTrace();
		    }
		   RunnerClass.logger.info("Expiration Date = "+PDFReader.expirationDate);
		   
		   
		   
		   // Monthly Rent
		   //PDFReader.monthlyRent =  Boise_Format1.getValues(PDFAppConfig.Boise_Format2.monthlyRentFromPDF);
		   
		   
		   try {
			    String[] monthlyRentCandidates = {
			        PDFAppConfig.Boise_Format1.monthlyRent_Prior,
			        PDFAppConfig.Boise_Format1.monthlyRent_Prior1,
			        PDFAppConfig.Boise_Format1.monthlyRent_Prior2
			    };

			    for (String candidate : monthlyRentCandidates) {
			        int startIndex = text.indexOf(candidate);
			        if (startIndex != -1) {
			            String extractedValue = text.substring(startIndex + candidate.length()).trim().split(" ")[0].replaceAll("[^.0-9]", "");

			            if (!extractedValue.matches(".*[a-zA-Z]+.*")) {
			                PDFReader.monthlyRent = extractedValue;

			                if (PDFReader.monthlyRent.contains("$")) {
			                    PDFReader.monthlyRent = PDFReader.monthlyRent.replace("$", "");
			                }

			                break; // Break the loop if we successfully extract the value
			            }
			        }
			    }

			    if (PDFReader.monthlyRent == null || PDFReader.monthlyRent.equals("Error")) {
			        PDFReader.monthlyRent = "Error";
			    }
			} catch (Exception e) {
			    PDFReader.monthlyRent = "Error";
			    e.printStackTrace();
			}

			RunnerClass.logger.info("MonthlyRent = " + PDFReader.monthlyRent);

		   
		    
		    //HVAC Air Filter Fee (OR) Resident Benefits Package
		    if(text.contains(PDFAppConfig.Boise_Format1.HVACFilterAddendumTextAvailabilityCheck))
		    {
		    	PDFReader.HVACFilterFlag = true;
		    	//HVAC Air Filter Fee
		    	 try
				    {
				    	PDFReader.HVACAirFilterFee = text.substring(text.indexOf(PDFAppConfig.Boise_Format1.HVACAirFilterFee)+PDFAppConfig.Boise_Format1.HVACAirFilterFee.length()).trim().split(" ")[0];
				    	if(PDFReader.HVACAirFilterFee.matches(".*[a-zA-Z]+.*"))
				    		PDFReader.HVACAirFilterFee = "Error";
				    }
				    catch(Exception e)
				    {
				    	PDFReader.HVACAirFilterFee = "Error";
				    	e.printStackTrace();
				    }
				    RunnerClass.logger.info("HVAC Air Filter Fee = "+PDFReader.HVACAirFilterFee);
		    }
		    
		    if(text.contains(PDFAppConfig.Boise_Format1.residentBenefitsPackageCheck)&&(!text.contains("Resident Benefits Package Opt-Out Addendum")||(!text.contains("Resident Benefits Package Opt-Out Addendum")||!text.contains("RESIDENT BENEFITS PACKAGE OPT-OUT ADDENDUM"))))
		    {
		    	PDFReader.residentBenefitsPackageAvailabilityCheck = true;
		    	//HVAC Air Filter Fee
		    	 try
				    {
				    	PDFReader.residentBenefitsPackage = text.substring(text.indexOf(PDFAppConfig.Boise_Format1.RBP_Prior)+PDFAppConfig.Boise_Format1.RBP_Prior.length()).trim().split(" ")[0].replaceAll("[^0-9a-zA-Z.]", "");
				    	if(PDFReader.residentBenefitsPackage.matches(".*[a-zA-Z]+.*"))
				    		PDFReader.residentBenefitsPackage = "Error";
				    }
				    catch(Exception e)
				    {
				    	PDFReader.residentBenefitsPackage = "Error";
				    	e.printStackTrace();
				    }
				    RunnerClass.logger.info("Resident Benefits Package = "+PDFReader.residentBenefitsPackage);
		    }
		    
		    
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
		                
		                if(specialProvisions.contains("D. n/a") || specialProvisions.contains("D. N/A")) {
		                    PDFReader.RUBS = "Error";
		                }
		                else {

		                PDFReader.RUBS = lastDollarValue.trim().split(" ")[0].replaceAll("[^0-9a-zA-Z.]", "");
		                }
		            }
		            RunnerClass.logger.info("RUBS = " + PDFReader.RUBS);
		        } catch (Exception e) {
		            PDFReader.RUBS = "Error";
		        }
		    }

		    
		    //Prorate Rent
		    try
		    {
 
		    	if(PDFReader.proratedRent.matches(".*[a-zA-Z]+.*"))
		    		PDFReader.proratedRent = "Error";
		    }
		    catch(Exception e)
		    {
		    	PDFReader.proratedRent = "Error";
		    	e.printStackTrace();
		    }
		    RunnerClass.logger.info("Prorate Rent = "+PDFReader.proratedRent);
		    
			//Pet Rent
		    if(text.contains(PDFAppConfig.OKC_Format2.petAgreementAvailabilityCheck)||text.contains(PDFAppConfig.OKC_Format2.petAgreementAvailabilityCheck2)||text.contains(PDFAppConfig.OKC_Format2.petAgreementAvailabilityCheck3))
		    {
		    	PDFReader.petFlag = true;
		    	RunnerClass.logger.info("Pet Addendum Available = "+PDFReader.petFlag);
		    	
		    	try
		    	{
		    		PDFReader.petRent = text.substring(text.indexOf(PDFAppConfig.Boise_Format1.petRent_Prior)+PDFAppConfig.Boise_Format1.petRent_Prior.length()).trim().split(" ")[0].trim();
		    		if(PDFReader.petRent.matches(".*[a-zA-Z]+.*"))
			    		PDFReader.petRent = "Error";
		    	}
		    	catch(Exception e)
		    	{
		    		PDFReader.petRent = "Error";
		    	}
		    	RunnerClass.logger.info("Pet Rent = "+PDFReader.petRent);
		    }
		    
		    //Lease Renewal Admin Fee
		    try
	    	{
	    		PDFReader.leaseRenewalFee = text.substring(text.indexOf(PDFAppConfig.Boise_Format1.leaseRenewalFee_Prior)+PDFAppConfig.Boise_Format1.leaseRenewalFee_Prior.length()).trim().split(" ")[0].trim();
	    		if(PDFReader.leaseRenewalFee.matches(".*[a-zA-Z]+.*"))
		    		PDFReader.leaseRenewalFee = "Error";
	    	}
	    	catch(Exception e)
	    	{
	    		PDFReader.leaseRenewalFee = "Error";
	    	}
	    	RunnerClass.logger.info("Lease Renewal Fee = "+PDFReader.leaseRenewalFee);
	    	
	    	
	    	 
			return true;
		}
		catch(Exception e)
		{
			RunnerClass.logger.info("Issue in fetching values from PDF");
			RunnerClass.failedReason =  RunnerClass.failedReason+","+"Issue in fetching values from PDF";
			return false;
		}

	}
	public static String extractMonthlyRent(String text, String format) {
	    try {
	        String rent = text.substring(text.indexOf(format) + format.length()).trim().split(" ")[0];
	        return rent.matches(".*[a-zA-Z]+.*") ? null : rent;
	    } catch (Exception e) {
	        System.err.println("An error occurred while extracting monthly rent: " + e.getMessage());
	        e.printStackTrace();
	        return null;
	    }
	}
	
	public static String extractSpecialProvisions(String text) {
	    int startIndex = text.indexOf("SPECIAL PROVISIONS:");
	    if (startIndex == -1) {
	        return ""; // "D." section not found
	    }

	    int endIndex = text.indexOf("27. DEFAULT", startIndex);

	    if (endIndex == -1) {
	        // Extract to the end of the document
	        String specialProvisionsText = text.substring(startIndex);
	        return specialProvisionsText;
	    } else {
	        // Extract text between startIndex and endIndex
	        String specialProvisionsText = text.substring(startIndex, endIndex);
	        return specialProvisionsText;
	    }
	}
	

}
