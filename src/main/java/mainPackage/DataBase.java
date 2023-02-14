package mainPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DataBase 
{

	 public static void insertData(String buildingName, String status, int statusID) throws Exception
	  {

		  String currentTime = RunnerClass.getCurrentDateTime();
		  String connectionUrl = "jdbc:sqlserver://azrsrv001.database.windows.net;databaseName=HomeRiverDB;user=service_sql02;password=xzqcoK7T;encrypt=true;trustServerCertificate=true;";
		  String sql;
		  if(statusID==1)
		   sql = "Update Automation.LeaseRenewalAutomation Set Status ='"+status+"', StatusID="+statusID+",NotAutomatedFields=NULL,StartTime= "+currentTime+" where BuildingName like '%"+buildingName+"%'";
		  else 
			sql = "Update Automation.LeaseRenewalAutomation Set Status ='"+status+"', StatusID="+statusID+",StartTime= '"+currentTime+"' where BuildingName like '%"+buildingName+"%'";
           //String sql = "Update [Automation].[LeaseInfo] Set Status = 'Completed', StatusID =4 where OwnerName='Duff, V.'";
		  
		    try (Connection conn = DriverManager.getConnection(connectionUrl);
		        Statement stmt = conn.createStatement();) 
		    {
		      stmt.executeUpdate(sql);
		      System.out.println("Database updated successfully ");
		      stmt.close();
	            conn.close();
		    } catch (SQLException e) {
		      e.printStackTrace();
		    }
           
	  }
	
	public static boolean getBuildingsList()
	{
		try
		{
		        Connection con = null;
		        Statement stmt = null;
		        ResultSet rs = null;
		            //Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		            con = DriverManager.getConnection(AppConfig.connectionUrl);
		            String SQL = AppConfig.pendingRenewalLeases;
		            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		           // stmt = con.createStatement();
		            rs = stmt.executeQuery(SQL);
		            int rows =0;
		            if (rs.last()) {
		            	rows = rs.getRow();
		            	// Move to beginning
		            	rs.beforeFirst();
		            }
		            System.out.println("No of Rows = "+rows);
		            RunnerClass.pendingRenewalLeases = new String[rows][3];
		           int  i=0;
		            while(rs.next())
		            {
		            	
		            	String 	company =  (String) rs.getObject(1);
		                String  buildingAbbreviation = (String) rs.getObject(2);
		                String  ownerName = (String) rs.getObject(3);
		                System.out.println(company +" ----  "+buildingAbbreviation+" ---- "+ownerName);
		    				//Company
		    				RunnerClass.pendingRenewalLeases[i][0] = company;
		    				//Building Abbreviation
		    				RunnerClass.pendingRenewalLeases[i][1] = buildingAbbreviation;
		    				//Owner Name
		    				RunnerClass.pendingRenewalLeases[i][2] = ownerName;
		    				i++;
		            }	
		            System.out.println("Total Pending Buildings  = " +RunnerClass.pendingRenewalLeases.length);
		            //for(int j=0;j<RunnerClass.pendingBuildingList.length;j++)
		            //{
		            //	System.out.println(RunnerClass.pendingBuildingList[j][j]);
		           // }
		            rs.close();
		            stmt.close();
		            con.close();
		 return true;
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		 return false;
		}
	}
	
	public static void updateTable(String query)
	 {
		    try (Connection conn = DriverManager.getConnection(AppConfig.connectionUrl);
		        Statement stmt = conn.createStatement();) 
		    {
		      stmt.executeUpdate(query);
		      System.out.println("Record Updated");
		      stmt.close();
	            conn.close();
		    } catch (SQLException e) 
		    {
		      e.printStackTrace();
		    }
	 }
	
	public static boolean getCompletedBuildingsList()
	{
		try
		{
		        Connection con = null;
		        Statement stmt = null;
		        ResultSet rs = null;
		            //Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		            con = DriverManager.getConnection(AppConfig.connectionUrl);
		            String SQL = AppConfig.getBuildingsWithStatusforCurrentDay;
		            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		           // stmt = con.createStatement();
		            rs = stmt.executeQuery(SQL);
		            int rows =0;
		            if (rs.last()) {
		            	rows = rs.getRow();
		            	// Move to beginning
		            	rs.beforeFirst();
		            }
		            System.out.println("No of buildings with status = "+rows);
		            RunnerClass.completedBuildingList = new String[rows][8];
		           int  i=0;
		            while(rs.next())
		            {
		            	
		            	String 	company =  (String) rs.getObject(1);
		                String  buildingAbbreviation = (String) rs.getObject(2);
		                String  MonthlyRentFromLeaseAgreement = (String) rs.getObject(3);
		                String  MonthlyRentInPW = (String) rs.getObject(4);
		                String  StartDateFromLeaseAgreement = (String) rs.getObject(5);
		                String  StartDateInPW = (String) rs.getObject(6);
		                String  Status = (String) rs.getObject(7);
		                String  Notes = (String) rs.getObject(8);
		                
		                System.out.println(company +" ----  "+buildingAbbreviation+" ---- "+MonthlyRentFromLeaseAgreement+" ---- "+MonthlyRentInPW+" ---- "+StartDateFromLeaseAgreement+" ---- "+StartDateInPW+" ---- "+Status+" ---- "+Notes );
		    				//Company
		    				RunnerClass.completedBuildingList[i][0] = company;
		    				//Building Abbreviation
		    				RunnerClass.completedBuildingList[i][1] = buildingAbbreviation;
		    				//Monthly Rent From Lease Agreement
		    				RunnerClass.completedBuildingList[i][2] = MonthlyRentFromLeaseAgreement;
		    				//Monthly Rent In PW
		    				RunnerClass.completedBuildingList[i][3] = MonthlyRentInPW;
		    				//Start Date From Lease Agreement
		    				RunnerClass.completedBuildingList[i][4] = StartDateFromLeaseAgreement;
		    				//Start Date In PW
		    				RunnerClass.completedBuildingList[i][5] = StartDateInPW;
		    				//Status
		    				RunnerClass.completedBuildingList[i][6] = Status;
		    				//Notes
		    				RunnerClass.completedBuildingList[i][7] = Notes;
		    				i++;
		            }	
		           // System.out.println("Total Pending Buildings  = " +RunnerClass.pendingBuildingList.length);
		            //for(int j=0;j<RunnerClass.pendingBuildingList.length;j++)
		            //{
		            //	System.out.println(RunnerClass.pendingBuildingList[j][j]);
		           // }
		            rs.close();
		            stmt.close();
		            con.close();
		 return true;
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		 return false;
		}
	}

}
