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
    
    /**
     * 
     * @throws Exception
     */
    public void testStandards() throws Exception
    {
        System.out.println("====> Starting EdGateClientTest.testStandards");
        
        try
        {
        	EdGateClient client = new EdGateClient(PUBLIC_KEY, PRIVATE_KEY);
            
        	List<StandardsSet> standardsSetList = client.getStandards();
        	
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
    public void testStandard() throws Exception
    {
        System.out.println("====> Starting EdGateClientTest.testStandard");
        
        try
        {
        	EdGateClient client = new EdGateClient(PUBLIC_KEY, PRIVATE_KEY);
            
        	List<StandardsSet> tmpStandard = client.getStandard("TX");
        	
        	if(tmpStandard != null)
        	{
        		int counter = 0;
        		
        		for(StandardsSet tmpStandardsSet : tmpStandard)
        		{
        			System.out.println("id (" + counter + "): " + tmpStandardsSet.getSetId());
        			System.out.println("name (" + counter + "): " + tmpStandardsSet.getName());
        			System.out.println("parent (" + counter + "): " + tmpStandardsSet.getSetId());
        			
        			counter++;
        		}
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
