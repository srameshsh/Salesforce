

import java.io.FileInputStream;
import java.util.Properties;

import com.sforce.soap.partner.Connector;
import com.sforce.soap.partner.PartnerConnection;
import com.sforce.soap.partner.QueryResult;
import com.sforce.soap.partner.SaveResult;
import com.sforce.soap.partner.UpsertResult;
import com.sforce.soap.partner.sobject.SObject;
import com.sforce.ws.ConnectorConfig;

public class SOAPJAVATEST {
	private static final String USERNAME = "XXXXXXXXXXXXX"; //User Name
	private static final String PASSWORD = "XXXXXXXXXXXXXXXXXX"; //Password with security token
	
   public static void main(String[] args) {

       ConnectorConfig config = new ConnectorConfig();

       PartnerConnection connection = null;
   
       try {
    		
    	    config.setUsername(USERNAME);
    	    config.setPassword(PASSWORD);
           SObject sObject = new SObject();
          sObject.setType("Account");
          sObject.setField("Name", "Installation5");
          sObject.setField("AccountNumber", "654321");
          sObject.setField("Site", "www.google.com");


           connection = Connector.newConnection(config);
   
           SaveResult[] results = connection.create(new SObject[] { sObject });
          
          
           if (results[0].isSuccess())
        	   System.out.println(sObject.getType()+" Object Created : " + results[0].getId());
           else
               System.out.println("Error: " + results[0].getErrors()[0].getStatusCode() + 
                                  ":" + results[0].getErrors()[0].getMessage());

          
         // connection.update(new SObject[] { _sObject });
          
 /*          UpsertResult[] results  = connection.upsert("AccountNumber", new SObject[] { sObject });
           for (UpsertResult result : results) {
        	   if (result.isSuccess()) {
        		   if(result.isCreated()){
        			   System.out.println("ID :: "+result.getId()+" :: POI ID :: "+sObject.getField("id")+" :: "+sObject.getType()+" Object CREATED successfully!");
        		   }
        		   else{
        			   System.out.println("ID :: "+result.getId()+" :: POI ID :: "+sObject.getField("id")+" :: "+sObject.getType()+" Object UPDATED successfully!");
        		   }
        	   }
        	   else{
        		 System.out.println("UPSET action is failed");  
        	   }
           }*/
           
       /*   if(queryResults.getSize())
        	  QueryResult queryResults = connection.query("SELECT Id from "+_sObject.getType()+ " WHERE ID = 'a007F000003QvEEQA0' ");
          
          System.out.println(queryResults.getSize());
          if (queryResults.getSize() > 0) {
               for (SObject s : queryResults.getRecords()) {
            	 //  System.out.println(s);
                   System.out.println("Id: " + s.getField("Id"));
               }
           }*/
       } catch (Exception e) {
           System.out.println("ERROR :: main "+e);
       }
   }
	
}