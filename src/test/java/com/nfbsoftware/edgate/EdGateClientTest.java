package com.nfbsoftware.edgate;

import java.util.List;

import com.nfbsoftware.edgate.model.Concept;
import com.nfbsoftware.edgate.model.ConceptStandards;
import com.nfbsoftware.edgate.model.Profile;
import com.nfbsoftware.edgate.model.Standard;
import com.nfbsoftware.edgate.model.StandardsSet;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * 
 * @author Brendan Clemenzi
 * @email brendan@clemenzi.com
 */
public class EdGateClientTest extends TestCase
{
    private String PUBLIC_KEY = "client-public-key"; 
    private String PRIVATE_KEY = "client-private-key"; 
    
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public EdGateClientTest( String testName )
    {
        super( testName );
    }
    
    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( EdGateClientTest.class );
    }
    
    /**
     * 
     * @throws Exception
     */
    public void testProfile() throws Exception
    {
        System.out.println("====> Starting EdGateClientTest.testProfile");
        
        try
        {
        	EdGateClient client = new EdGateClient(PUBLIC_KEY, PRIVATE_KEY);
            
        	Profile profileValue = client.getProfile();
        	
        	if(profileValue != null)
        	{
        		int counter = 0;
        		
        		for(StandardsSet tmpStandardsSet : profileValue.getStandardsSets())
        		{
        			System.out.println("id (" + counter + "): " + tmpStandardsSet.getSetId());
        			System.out.println("name (" + counter + "): " + tmpStandardsSet.getName());
        			System.out.println("parent (" + counter + "): " + tmpStandardsSet.getParent());
        			
        			counter++;
        		}
        	}
            
            assertTrue(profileValue != null);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            
            assertTrue(true);
        }
        
        System.out.println("====> Finished EdGateClientTest.testProfile");
    }
    
    /* 
    * @throws Exception
    */
   public void testAvailableStandards() throws Exception
   {
       System.out.println("====> Starting EdGateClientTest.testAvailableStandards");
       
       try
       {
       		EdGateClient client = new EdGateClient(PUBLIC_KEY, PRIVATE_KEY);
           
       		List<Standard> standardsList = client.getAvailableStandards();
       	
	       	if(standardsList != null)
	       	{
	       		int counter = 0;
	       		
	       		for(Standard tmpStandard : standardsList)
	       		{
	       			System.out.println("guid (" + counter + "): " + tmpStandard.getGuid());
	       			System.out.println("number (" + counter + "): " + tmpStandard.getStandardNumber());
	       			System.out.println("text (" + counter + "): " + tmpStandard.getStandardText());
	       			System.out.println("parent (" + counter + "): " + tmpStandard.getParent());
	       			
	       			counter++;
	       		}
	       	}
           
           assertTrue(standardsList != null);
       }
       catch (Exception e)
       {
           e.printStackTrace();
           
           assertTrue(true);
       }
       
       System.out.println("====> Finished EdGateClientTest.testAvailableStandards");
   }
    
    /**
     * 
     * @throws Exception
     */
    public void testStandardSets() throws Exception
    {
        System.out.println("====> Starting EdGateClientTest.testStandards");
        
        try
        {
        	EdGateClient client = new EdGateClient(PUBLIC_KEY, PRIVATE_KEY);
            
        	List<StandardsSet> standardsSetList = client.getStandardSets();
        	
        	if(standardsSetList != null)
        	{
        		int counter = 0;
        		
        		for(StandardsSet tmpStandardsSet : standardsSetList)
        		{
        			System.out.println("id (" + counter + "): " + tmpStandardsSet.getSetId());
        			System.out.println("name (" + counter + "): " + tmpStandardsSet.getName());
        			System.out.println("parent (" + counter + "): " + tmpStandardsSet.getSetId());
        			
        			counter++;
        		}
        	}
            
            assertTrue(standardsSetList != null);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            
            assertTrue(true);
        }
        
        System.out.println("====> Finished EdGateClientTest.testStandards");
    }
    
    /**
     * 
     * @throws Exception
     */
    public void testStandardSet() throws Exception
    {
        System.out.println("====> Starting EdGateClientTest.testStandardSet");
        
        try
        {
        	EdGateClient client = new EdGateClient(PUBLIC_KEY, PRIVATE_KEY);
            
        	StandardsSet standardsSet = client.getStandardSet("FL");
        	
        	if(standardsSet != null)
        	{
        		System.out.println("id: " + standardsSet.getSetId());
    			System.out.println("name: " + standardsSet.getName());
    			System.out.println("parent: " + standardsSet.getSetId());
        	}
            
            assertTrue(standardsSet != null);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            
            assertTrue(true);
        }
        
        System.out.println("====> Finished EdGateClientTest.testStandardSet");
    }
    
    /**
     * 
     * @throws Exception
     */
    public void testStandardsRoot() throws Exception
    {
        System.out.println("====> Starting EdGateClientTest.testStandardsRoot");
        
        try
        {
        	EdGateClient client = new EdGateClient(PUBLIC_KEY, PRIVATE_KEY);
            
        	List<Standard> standardList = client.getStandardsRoot("FL");
        	
        	if(standardList != null)
        	{
        		int counter = 0;
        		
        		for(Standard tmpStandard : standardList)
        		{
        			System.out.println("GUID (" + counter + "): " + tmpStandard.getGuid());
        			System.out.println("Set (" + counter + "): " + tmpStandard.getSet());
        			System.out.println("StandardText (" + counter + "): " + tmpStandard.getStandardText());
        			System.out.println("StandardNumber (" + counter + "): " + tmpStandard.getStandardNumber());
        			System.out.println("Parent (" + counter + "): " + tmpStandard.getParent());
        			System.out.println("");
        			
        			counter++;
        		}
        	}
            
            assertTrue(standardList != null);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            
            assertTrue(true);
        }
        
        System.out.println("====> Finished EdGateClientTest.testStandardsRoot");
    }
    
    /**
     * 
     * @throws Exception
     */
    public void testStandard() throws Exception
    {
        System.out.println("====> Starting EdGateClientTest.testStandard");
        
        try
        {
        	EdGateClient client = new EdGateClient(PUBLIC_KEY, PRIVATE_KEY);
            
        	// FL - American History - FL.SS.4.A
        	Standard tmpStandard = client.getStandard("ff719539-3b0b-47e9-8a10-31a03cb066f1");
        	
        	if(tmpStandard != null)
        	{
    			System.out.println("GUID: " + tmpStandard.getGuid());
    			System.out.println("Set: " + tmpStandard.getSet());
    			System.out.println("StandardText: " + tmpStandard.getStandardText());
    			System.out.println("StandardNumber: " + tmpStandard.getStandardNumber());
    			System.out.println("Parent: " + tmpStandard.getParent());
    			System.out.println("");
        	}
            
            assertTrue(tmpStandard != null);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            
            assertTrue(true);
        }
        
        System.out.println("====> Finished EdGateClientTest.testStandard");
    }
    
    /**
     * 
     * @throws Exception
     */
    public void testStandardsChildren() throws Exception
    {
        System.out.println("====> Starting EdGateClientTest.testStandardsChildren");
        
        try
        {
        	EdGateClient client = new EdGateClient(PUBLIC_KEY, PRIVATE_KEY);
            
        	// FL - American History - FL.SS.4.A
        	List<Standard> standardList = client.getStandardsChildren("ff719539-3b0b-47e9-8a10-31a03cb066f1");
        	
        	if(standardList != null)
        	{
        		int counter = 0;
        		
        		for(Standard tmpStandard : standardList)
        		{
        			System.out.println("GUID (" + counter + "): " + tmpStandard.getGuid());
        			System.out.println("Set (" + counter + "): " + tmpStandard.getSet());
        			System.out.println("StandardText (" + counter + "): " + tmpStandard.getStandardText());
        			System.out.println("StandardNumber (" + counter + "): " + tmpStandard.getStandardNumber());
        			System.out.println("Parent (" + counter + "): " + tmpStandard.getParent());
        			System.out.println("");
        			
        			counter++;
        		}
        	}
            
            assertTrue(standardList != null);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            
            assertTrue(true);
        }
        
        System.out.println("====> Finished EdGateClientTest.testStandardsChildren");
    }
    
    /**
     * 
     * @throws Exception
     */
    public void testStandardsConcepts() throws Exception
    {
        System.out.println("====> Starting EdGateClientTest.testStandardsConcepts");
        
        try
        {
        	EdGateClient client = new EdGateClient(PUBLIC_KEY, PRIVATE_KEY);
            
        	// FL - American History - FL.SS.4.A
        	List<Concept> conceptList = client.getStandardsConcepts("ff719539-3b0b-47e9-8a10-31a03cb066f1");
        	
        	if(conceptList != null)
        	{
        		int counter = 0;
        		
        		for(Concept tmpConcept : conceptList)
        		{
        			System.out.println("GUID (" + counter + "): " + tmpConcept.getGuid());
        			System.out.println("Name (" + counter + "): " + tmpConcept.getName());
        			System.out.println("");
        			
        			counter++;
        		}
        	}
            
            assertTrue(conceptList != null);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            
            assertTrue(true);
        }
        
        System.out.println("====> Finished EdGateClientTest.testStandardsConcepts");
    }
    
    /**
     * 
     * @throws Exception
     */
    public void testConcepts() throws Exception
    {
        System.out.println("====> Starting EdGateClientTest.testConcepts");
        
        try
        {
        	EdGateClient client = new EdGateClient(PUBLIC_KEY, PRIVATE_KEY);
            
        	List<Concept> conceptList = client.getConcepts();
        	
        	if(conceptList != null)
        	{
        		int counter = 0;
        		
        		for(Concept tmpConcept : conceptList)
        		{
        			System.out.println("guid (" + counter + "): " + tmpConcept.getGuid());
        			System.out.println("name (" + counter + "): " + tmpConcept.getName());
        			
        			if(tmpConcept.getChildren() != null)
        			{
	        			int childCount = 0;
	        			for(String childGuid : tmpConcept.getChildren())
	        			{
	        				System.out.println("children (" + counter + ") child " + childCount + ": " + childGuid);
	        				
	        				childCount++;
	        			}
        			}
        			
        			counter++;
        		}
        	}
            
            assertTrue(conceptList != null);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            
            assertTrue(true);
        }
        
        System.out.println("====> Finished EdGateClientTest.testConcepts");
    }
    
    /**
     * 
     * @throws Exception
     */
    public void testConcept() throws Exception
    {
        System.out.println("====> Starting EdGateClientTest.testConcept");
        
        try
        {
        	EdGateClient client = new EdGateClient(PUBLIC_KEY, PRIVATE_KEY);
            
        	Concept conceptObj = client.getConcept("f06fdee2-b6c3-4174-acf0-03b58393b68c");
        	
        	if(conceptObj != null)
        	{
    			System.out.println("guid: " + conceptObj.getGuid());
    			System.out.println("name: " + conceptObj.getName());
    			
    			if(conceptObj.getChildren() != null)
    			{
	    			int childCount = 0;
	    			for(String childGuid : conceptObj.getChildren())
	    			{
	    				System.out.println("child " + childCount + ": " + childGuid);
	    				
	    				childCount++;
	    			}
    			}
        	}
            
            assertTrue(conceptObj != null);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            
            assertTrue(true);
        }
        
        System.out.println("====> Finished EdGateClientTest.testConcept");
    }
    
    /**
     * 
     * @throws Exception
     */
    public void testConceptStandards() throws Exception
    {
        System.out.println("====> Starting EdGateClientTest.testConceptStandards");
        
        try
        {
        	EdGateClient client = new EdGateClient(PUBLIC_KEY, PRIVATE_KEY);
            
        	ConceptStandards conceptStandardsObj = client.getConceptStandards("f06fdee2-b6c3-4174-acf0-03b58393b68c", "TX", null);
        	
        	if(conceptStandardsObj != null)
        	{
    			if(conceptStandardsObj.getStandards() != null)
    			{
	    			int childCount = 0;
	    			for(Standard tmpStandard : conceptStandardsObj.getStandards())
	    			{
	    				System.out.println("child " + childCount + ": " + tmpStandard.getGuid());
	    				
	    				childCount++;
	    			}
    			}
        	}
            
            assertTrue(conceptStandardsObj != null);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            
            assertTrue(true);
        }
        
        System.out.println("====> Finished EdGateClientTest.testConceptStandards");
    }
}
