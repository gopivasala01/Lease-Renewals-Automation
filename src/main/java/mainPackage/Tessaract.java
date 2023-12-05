 package mainPackage;


	

 import org.apache.pdfbox.pdmodel.PDDocument;
 import org.apache.pdfbox.rendering.ImageType;
 import org.apache.pdfbox.rendering.PDFRenderer;
 import net.sourceforge.tess4j.ITesseract;
 import net.sourceforge.tess4j.Tesseract;
 import javax.imageio.ImageIO;
 import java.awt.image.BufferedImage;
 import java.io.File;

 public class Tessaract {

	 private static final String TARGET_TEXT_OPTION_1 = "Monthly Rent due in the amount of\r\n" + "$";

	 public static void main(String[] args) {
	     File pdfFile = new File("C:\\SantoshMurthyP\\Lease Audit Automation\\RT_-_RENEWAL_-[1517_Waterford_Dr]_[DFW-TX]_[Cooper,_S._1]_[1.01.24-12.31.24].pdf");
	     try {
	         String selectedOption = extractOptionFromPDF(pdfFile);
	         System.out.println("Selected Option: " + selectedOption);
	     } catch (Exception e) {
	         System.err.println("Error: " + e.getMessage());
	     }
	 }

	 private static String extractOptionFromPDF(File pdfFile) throws Exception {
	     try (PDDocument pdfDocument = PDDocument.load(pdfFile)) {
	         PDFRenderer pdfRenderer = new PDFRenderer(pdfDocument);

	         for (int page = 0; page < pdfDocument.getNumberOfPages(); ++page) {
	             BufferedImage image = pdfRenderer.renderImageWithDPI(page, 300, ImageType.RGB);
	             File imageFile = new File("C:\\SantoshMurthyP\\Lease Audit Automation\\image\\Image_" + page + ".jpeg");
	             ImageIO.write(image, "jpeg", imageFile);

	             String extractedText = performOCR(imageFile);

	             if (extractedText.contains(TARGET_TEXT_OPTION_1)) {
	                 return "Option 1";
	             }
	         }
	     }
	     return "Option not found";
	 }

	 private static String performOCR(File imageFile) throws Exception {
	     ITesseract tesseract = new Tesseract();
	     tesseract.setDatapath("C:\\SantoshMurthyP\\Lease Audit Automation\\Log Files");

	     try {
	         return tesseract.doOCR(imageFile);
	     } catch (Exception e) {
	         throw new Exception("Error performing OCR on image: " + e.getMessage());
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

	}



