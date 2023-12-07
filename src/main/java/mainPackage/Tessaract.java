 package mainPackage;


	

 import org.apache.pdfbox.pdmodel.PDDocument;
 import org.apache.pdfbox.rendering.ImageType;
 import org.apache.pdfbox.rendering.PDFRenderer;
 import net.sourceforge.tess4j.ITesseract;
 import net.sourceforge.tess4j.Tesseract;
 import javax.imageio.ImageIO;
 import java.awt.image.BufferedImage;
 import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



	 public class Tessaract{

		    private static final String TARGET_TEXT_1 = "Monthly Rent due in the amount of $";
		    private static final String IMAGE_OUTPUT_PATH = "C:\\SantoshMurthyP\\Tessaract Images\\Image.jpeg";
		    private static final String TESSDATA_PATH = "C:\\Users\\beerim\\git\\Lease-Renewals-Automation3\\tessdata";

		    /*public static void main(String[] args) {
		        try {
		            File file = new File("C:\\SantoshMurthyP\\Lease Audit Automation\\LEase\\RT_-_RENEWAL_-[91-1319_Puamaeole_St_Apt_34B]_[HI]_[Hairston,_D.]_[01.01.24-12.31.24] (2).pdf");
		            pdfScreenShot(file);
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    }*/

		    public static void pdfScreenShot(File pdfFile) {
		        try (PDDocument pdfDocument = PDDocument.load(pdfFile)) {
		            PDFRenderer pdfRenderer = new PDFRenderer(pdfDocument);

		            for (int page = 0; page < pdfDocument.getNumberOfPages(); ++page) {
		                BufferedImage image = pdfRenderer.renderImageWithDPI(page, 300, ImageType.RGB);
		                File outputFile = new File(IMAGE_OUTPUT_PATH);
		                ImageIO.write(image, "jpeg", outputFile);

		                Tesseract tesseract = new Tesseract();
		                tesseract.setDatapath(TESSDATA_PATH);

		                try {
		                    String text = tesseract.doOCR(outputFile);
		                    text = text.replaceAll(System.lineSeparator(), " ");
		                    text = text.trim().replaceAll(" +", " ");
		    			    text = text.toLowerCase();
		                    System.out.print(text);

		                    if (text.contains(TARGET_TEXT_1.toLowerCase())) {
		                        System.out.println("Option 1 is selected");

		                        String monthlyRent = extractDollarValueBetween(text, "monthly rent due in the amount", "lease administrative fee(s):").toLowerCase();
		                        processMonthlyRent(monthlyRent);
		                        break;
		                    }
		                } catch (Exception e) {
		                    e.printStackTrace();
		                }
		            }
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    }

		    private static String extractDollarValueBetween(String text, String startMarker, String endMarker) {
		        Pattern pattern = Pattern.compile("\\$" + "\\d+,(\\.\\d{2})");
		        String amount = "";
		        int startIndex = text.indexOf(startMarker);
		        int endIndex = text.indexOf(endMarker, startIndex);

		        String extractedValue = "Error"; // Default if no value is found

		        if (startIndex != -1 && endIndex != -1) {
		            String substringBetweenMarkers = text.substring(startIndex, endIndex);
		             amount = substringBetweenMarkers.substring(substringBetweenMarkers.indexOf("$")+1).split("month")[0].trim();
		           /* substringBetweenMarkers = substringBetweenMarkers.replaceAll(System.lineSeparator(), " ");
		            substringBetweenMarkers = substringBetweenMarkers.trim().replaceAll(" +", " ");
		            Matcher valueMatcher = pattern.matcher(substringBetweenMarkers);

		            if (valueMatcher.find()) {
		                extractedValue = valueMatcher.group();
		            }*/
		        }
		        return amount;
		        
		    }



		    private static void processMonthlyRent(String text) {
		        try {
		            PDFReader.monthlyRent = (text.equals("Error")) ? "Error" : text;
		            
		        } catch (Exception e) {
		            PDFReader.monthlyRent = "Error";
		            e.printStackTrace();
		        }
		        System.out.println("MonthlyRent = " + PDFReader.monthlyRent);
		    }
		}
 

		
		/*public static String floridaLiquidizedAddendumOptionCheck(File newFile) throws Exception 
		{
			try
			{
			//File newFile = new File ("C:\\SantoshMurthyP\\Lease Audit Automation\\Lease_923_924_619_W_Sabine_ATX_Cloteaux.pdf");
			 //File newFile = RunnerClass.getLastModified();
			 PDDocument pdfDocument = PDDocument.load(newFile);
			 PDFRenderer pdfRenderer = new PDFRenderer(pdfDocument);
			 String targetText1 = "[ X]1/We agree"; //Tenant will pay Landlord monthly rent in the amount of";
			 String targetText2 = "[X ]1/We agree";
			 String targetText3 = "[ x]11/We agree";
			 String targetText4 = "[x ]1/We agree";
			 //String targetText2 = "x Option 2: Purchase a Renters Insurance Policy";
			// String targetText2 = "(X)) monthly installments,"; //on or before the 1° day of each month, in the amount";
			 //Rectangle textCoordinates = textStripper.getTextBounds("monthly installments, Tenant will pay Landlord monthly rent in the amount of");
			
			 for (int page = 15; page < pdfDocument.getNumberOfPages(); ++page) {
					 BufferedImage bim = pdfRenderer.renderImageWithDPI(page, 300, ImageType.RGB);
			         // Crop the image based on the specified coordinates
			        // BufferedImage croppedImage = bim.getSubimage(x, y, width, height);
			         File outputFile = new File("C:\\SantoshMurthyP\\Tessaract Images\\Image.jpeg");
			         ImageIO.write(bim, "jpeg", outputFile);
			        // System.out.println( "Image has been extracted successfully");
					  
				     Tesseract tesseract = new Tesseract();

					 tesseract.setDatapath("F:\\Eclipse Workspace\\Gopi\\Lease-Close-Outs-2.0\\tessdata");

					 //image.setLanguage(“eng”);
					 try 
					 {
					   String text= tesseract.doOCR(new File(AppConfig.pdfImage+"Image.jpeg"));
					   System.out.print(text);
					   if(text.contains("Liquidated Damages Addendum"))
					   {
						   String x = text.substring(text.indexOf("[")+1,text.indexOf("]")).trim();
						   if(x.equalsIgnoreCase("x"))
						   {
						   System.out.println("Option 1 is selected");
						   return "Option 1";
						   }
					   }
					   //else
						  // return "Error";
					  }
					 catch(Exception e) 
					 {
						 return "Error";
					    //System.out.println("Exception "+e);
					   }
					      
		        }
			 // Closing the PDF document
		        pdfDocument.close();
			}
			catch(Exception e)
			{
				return "Error";
			}
			return "Error";
			
			       
		 }*/

	



