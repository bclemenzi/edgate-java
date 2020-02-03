package com.nfbsoftware.edgate;

import java.util.List;

import com.nfbsoftware.edgate.model.Profile;
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
    private String PUBLIC_KEY = "public-key-here"; 
    private String PRIVATE_KEY = "private-key-here"; 
    
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
            
            assertTrue(false);
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
            
            assertTrue(false);
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
            
            assertTrue(false);
        }
        
        System.out.println("====> Finished EdGateClientTest.testStandard");
    }
}
