package com.nfbsoftware.edgate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nfbsoftware.edgate.model.Concept;
import com.nfbsoftware.edgate.model.ConceptStandards;
import com.nfbsoftware.edgate.model.Profile;
import com.nfbsoftware.edgate.model.Standard;
import com.nfbsoftware.edgate.model.StandardsSet;

/**
 * This is a Java utility class that is used to communicate with the Academic Benchmarks' RESTful API.
 * 
 * @author brendanclemenzi
 */
public class EdGateClient
{
    private static final Log logger = LogFactory.getLog(EdGateClient.class);
    
    private static final String HASH_ALGORITHM = "HmacSHA256";
    
    private static final String HOST_DOMAIN = "api.edgate.com"; 
    private static final int HOST_PORT = 443; 
    private static final String HOST_SCHEME = "https";

    private String m_privateKey;
    private String m_publicKey;
    
    /**
     * 
     * @param publicKey - EdGate Public Key
     * @param privateKey - EdGate Private Key
     */
    public EdGateClient(String publicKey, String privateKey)
    {
        m_publicKey = publicKey;
        m_privateKey = privateKey;
    }
    
    /**
     * <p>Get all options and parameters available in client profile</p>
     * 
     * @return Profile - Account profile
     * @throws Exception - catch all for exceptions
     */
    public Profile getProfile() throws Exception
    {
    	Profile profileValue = null;
    	
    	HashMap<String, String> parameterMap = new HashMap<String, String>();
    	//parameterMap.put("param1", "value1");
    	
    	String parameterString = createParameterString(parameterMap);
    	String parameterHash = getSignatureHash(parameterString);
    	
        HttpClient httpClient = HttpClientBuilder.create().build();
        
        // specify the host, protocol, and port
        HttpHost target = new HttpHost(HOST_DOMAIN, HOST_PORT, HOST_SCHEME);
        
        // specify the get request
        HttpGet getRequest = new HttpGet("/profile?" + parameterString);
        
        // Add the hash header
        getRequest.setHeader("X-Hash", parameterHash);
        
        // Get our response from the EdGate server
        HttpResponse apiResponse = httpClient.execute(target, getRequest);
        HttpEntity entity = apiResponse.getEntity();
        
        // If we have an entity, convert it to Java objects
        if(entity != null) 
        {
            String responseString = EntityUtils.toString(entity); 
            System.out.println(responseString);
            
            ObjectMapper mapper = new ObjectMapper();
            profileValue = mapper.readValue(responseString,  Profile.class);
        }
        
        return profileValue;
    }
    
    /**
     * <p>Returns a list of the standards available to the account profile.</p>
     * 
     * @return List - A set of Standard objects
     * @throws Exception - catch all for exceptions
     */
    public List<Standard> getAvailableStandards() throws Exception
    {
    	List<Standard> standardsList = new ArrayList<Standard>();
    	
    	Profile tmpProfile = getProfile();
    	
    	for(StandardsSet tmpStandardsSet : tmpProfile.getStandardsSets())
    	{
    		List<Standard> rootStandardsList = getStandardsRoot(tmpStandardsSet.getSetId());
    		
    		for(Standard rootStandard : rootStandardsList)
    		{
    			standardsList.add(rootStandard);
    		}
    	}
    	
    	return standardsList;
    }
    
    /**
     * <p>Returns information about all standards sets from the system that the user has access to</p>
     * 
     * @return List - A set of StandardsSet objects
     * @throws Exception - catch all for exceptions
     */
    public List<StandardsSet> getStandardSets() throws Exception
    {
    	List<StandardsSet> standardsSetList = new ArrayList<StandardsSet>();
    	
    	HashMap<String, String> parameterMap = new HashMap<String, String>();
    	//parameterMap.put("param1", "value1");
    	
    	String parameterString = createParameterString(parameterMap);
    	String parameterHash = getSignatureHash(parameterString);
    	
        HttpClient httpClient = HttpClientBuilder.create().build();
        
        // specify the host, protocol, and port
        HttpHost target = new HttpHost(HOST_DOMAIN, HOST_PORT, HOST_SCHEME);
        
        // specify the get request
        HttpGet getRequest = new HttpGet("/standards?" + parameterString);
        
        // Add the hash header
        getRequest.setHeader("X-Hash", parameterHash);
        
        // Get our response from the EdGate server
        HttpResponse apiResponse = httpClient.execute(target, getRequest);
        HttpEntity entity = apiResponse.getEntity();
        
        // If we have an entity, convert it to Java objects
        if(entity != null) 
        {
            String responseString = EntityUtils.toString(entity);  
            System.out.println(responseString);
            
            ObjectMapper mapper = new ObjectMapper();
            standardsSetList = mapper.readValue(responseString, new TypeReference<List<StandardsSet>>(){});
        }
        
        return standardsSetList;
    }
    
    /**
     * <p>Returns the StandardSet object for the given ID</p>
     * 
     * @param setId - The ID of the given StandardsSet
     * @return StandardsSet - A StandardsSet object
     * @throws Exception - catch all for exceptions
     */
    public StandardsSet getStandardSet(String setId) throws Exception
    {
    	StandardsSet standardsSet = null;
    	
    	List<StandardsSet> tmpStandardsSetList = getStandardSets();
    	
    	for(StandardsSet tmpStandardsSet : tmpStandardsSetList)
    	{
    		if(tmpStandardsSet.getSetId().equalsIgnoreCase(setId))
    		{
    			standardsSet = tmpStandardsSet;
    			break;
    		}
    	}
    	
    	return standardsSet;
    }
    
    /**
     * <p>Returns a list of top level standards for a given standards set</p>
     * 
     * @param setId - abbreviation of standards set
     * @return List of Standard objects for a parent standard
     * @throws Exception - catch all for exceptions
     */
    public List<Standard> getStandardsRoot(String setId) throws Exception
    {
    	List<Standard> standardsList = new ArrayList<Standard>();
    	
    	HashMap<String, String> parameterMap = new HashMap<String, String>();
    	//parameterMap.put("param1", "value1");
    	
    	String parameterString = createParameterString(parameterMap);
    	String parameterHash = getSignatureHash(parameterString);
    	
        HttpClient httpClient = HttpClientBuilder.create().build();
        
        // specify the host, protocol, and port
        HttpHost target = new HttpHost(HOST_DOMAIN, HOST_PORT, HOST_SCHEME);
        
        // specify the get request
        HttpGet getRequest = new HttpGet("/standards/root/" + setId + "?" + parameterString);
        
        // Add the hash header
        getRequest.setHeader("X-Hash", parameterHash);
        
        // Get our response from the EdGate server
        HttpResponse apiResponse = httpClient.execute(target, getRequest);
        HttpEntity entity = apiResponse.getEntity();
        
        // If we have an entity, convert it to Java objects
        if(entity != null) 
        {
            String responseString = EntityUtils.toString(entity);  
            System.out.println(responseString);
            
            ObjectMapper mapper = new ObjectMapper();
            standardsList = mapper.readValue(responseString, new TypeReference<List<Standard>>(){});
        }
        
        return standardsList;
    }
    
    /**
     * <p>Returns a standard based on a GUID</p>
     * 
     * @param guid The GUID that identifies the standard that is to be read from the service provider.
     * @return Standard - A single Standard
     * @throws Exception - catch all for exceptions
     */
    public Standard getStandard(String guid) throws Exception
    {
    	Standard standardObject = null;
    	
    	HashMap<String, String> parameterMap = new HashMap<String, String>();
    	//parameterMap.put("param1", "value1");
    	
    	String parameterString = createParameterString(parameterMap);
    	String parameterHash = getSignatureHash(parameterString);
    	
        HttpClient httpClient = HttpClientBuilder.create().build();
        
        // specify the host, protocol, and port
        HttpHost target = new HttpHost(HOST_DOMAIN, HOST_PORT, HOST_SCHEME);
        
        // specify the get request
        HttpGet getRequest = new HttpGet("/standards/" + guid + "?" + parameterString);
        
        // Add the hash header
        getRequest.setHeader("X-Hash", parameterHash);
        
        // Get our response from the EdGate server
        HttpResponse apiResponse = httpClient.execute(target, getRequest);
        HttpEntity entity = apiResponse.getEntity();
        
        // If we have an entity, convert it to Java objects
        if(entity != null) 
        {
            String responseString = EntityUtils.toString(entity);  
            System.out.println(responseString);
            
            ObjectMapper mapper = new ObjectMapper();
            standardObject = mapper.readValue(responseString, new TypeReference<Standard>(){});
        }
        
        return standardObject;
    }
    
    /**
     * <p>Returns a list of child standard objects for a given standard GUID</p>
     * 
     * @param guid GUID of standard to fetch
     * @return List of Standard objects for a parent standard
     * @throws Exception - catch all for exceptions
     */
    public List<Standard> getStandardsChildren(String guid) throws Exception
    {
    	List<Standard> standardsList = new ArrayList<Standard>();
    	
    	HashMap<String, String> parameterMap = new HashMap<String, String>();
    	//parameterMap.put("param1", "value1");
    	
    	String parameterString = createParameterString(parameterMap);
    	String parameterHash = getSignatureHash(parameterString);
    	
        HttpClient httpClient = HttpClientBuilder.create().build();
        
        // specify the host, protocol, and port
        HttpHost target = new HttpHost(HOST_DOMAIN, HOST_PORT, HOST_SCHEME);
        
        // specify the get request
        HttpGet getRequest = new HttpGet("/standards/" + guid + "/children?" + parameterString);
        
        // Add the hash header
        getRequest.setHeader("X-Hash", parameterHash);
        
        // Get our response from the EdGate server
        HttpResponse apiResponse = httpClient.execute(target, getRequest);
        HttpEntity entity = apiResponse.getEntity();
        
        // If we have an entity, convert it to Java objects
        if(entity != null) 
        {
            String responseString = EntityUtils.toString(entity);  
            System.out.println(responseString);
            
            ObjectMapper mapper = new ObjectMapper();
            standardsList = mapper.readValue(responseString, new TypeReference<List<Standard>>(){});
        }
        
        return standardsList;
    }
    
    /**
     * <p>Returns a list of standards related to a given standard GUID</p>
     * 
     * @param guid GUID of standard to fetch
     * @param setId GUID of standards set to compare
     * @return List of Standard objects for a parent standard
     * @throws Exception - catch all for exceptions
     */
    public List<Standard> getRelatedStandards(String guid, String setId) throws Exception
    {
    	List<Standard> standardsList = new ArrayList<Standard>();
    	
    	HashMap<String, String> parameterMap = new HashMap<String, String>();
    	parameterMap.put("set", setId);
    	
    	String parameterString = createParameterString(parameterMap);
    	String parameterHash = getSignatureHash(parameterString);
    	
        HttpClient httpClient = HttpClientBuilder.create().build();
        
        // specify the host, protocol, and port
        HttpHost target = new HttpHost(HOST_DOMAIN, HOST_PORT, HOST_SCHEME);
        
        // specify the get request
        HttpGet getRequest = new HttpGet("/standards/related/" + guid + "?" + parameterString);
        
        // Add the hash header
        getRequest.setHeader("X-Hash", parameterHash);
        
        // Get our response from the EdGate server
        HttpResponse apiResponse = httpClient.execute(target, getRequest);
        HttpEntity entity = apiResponse.getEntity();
        
        // If we have an entity, convert it to Java objects
        if(entity != null) 
        {
            String responseString = EntityUtils.toString(entity);  
            System.out.println(responseString);
            
            ObjectMapper mapper = new ObjectMapper();
            standardsList = mapper.readValue(responseString, new TypeReference<List<Standard>>(){});
        }
        
        return standardsList;
    }
    
    /**
     * <p>Get a list concepts tagged to a standard</p>
     * 
     * @param guid GUID of standard to lookup concepts for
     * @return List of Concept objects for a standard
     * @throws Exception - catch all for exceptions
     */
    public List<Concept> getStandardsConcepts(String guid) throws Exception
    {
    	List<Concept> conceptList = new ArrayList<Concept>();
    	
    	HashMap<String, String> parameterMap = new HashMap<String, String>();
    	//parameterMap.put("param1", "value1");
    	
    	String parameterString = createParameterString(parameterMap);
    	String parameterHash = getSignatureHash(parameterString);
    	
        HttpClient httpClient = HttpClientBuilder.create().build();
        
        // specify the host, protocol, and port
        HttpHost target = new HttpHost(HOST_DOMAIN, HOST_PORT, HOST_SCHEME);
        
        // specify the get request
        HttpGet getRequest = new HttpGet("/standards/concepts/" + guid + "?" + parameterString);
        
        // Add the hash header
        getRequest.setHeader("X-Hash", parameterHash);
        
        // Get our response from the EdGate server
        HttpResponse apiResponse = httpClient.execute(target, getRequest);
        HttpEntity entity = apiResponse.getEntity();
        
        // If we have an entity, convert it to Java objects
        if(entity != null) 
        {
            String responseString = EntityUtils.toString(entity);  
            System.out.println(responseString);
            
            ObjectMapper mapper = new ObjectMapper();
            conceptList = mapper.readValue(responseString, new TypeReference<List<Concept>>(){});
        }
        
        return conceptList;
    }
    
    /**
     * <p>Returns the top level of concept taxonomy</p>
     * 
     * @return List - A set of Concept objects
     * @throws Exception - catch all for exceptions
     */
    public List<Concept> getConcepts() throws Exception
    {
    	List<Concept> conceptList = new ArrayList<Concept>();
    	
    	HashMap<String, String> parameterMap = new HashMap<String, String>();
    	//parameterMap.put("q", "");
    	
    	String parameterString = createParameterString(parameterMap);
    	String parameterHash = getSignatureHash(parameterString);
    	
        HttpClient httpClient = HttpClientBuilder.create().build();
        
        // specify the host, protocol, and port
        HttpHost target = new HttpHost(HOST_DOMAIN, HOST_PORT, HOST_SCHEME);
        
        // specify the get request
        HttpGet getRequest = new HttpGet("/concepts/browse?" + parameterString);
        
        // Add the hash header
        getRequest.setHeader("X-Hash", parameterHash);
        
        // Get our response from the EdGate server
        HttpResponse apiResponse = httpClient.execute(target, getRequest);
        HttpEntity entity = apiResponse.getEntity();
        
        // If we have an entity, convert it to Java objects
        if(entity != null) 
        {
            String responseString = EntityUtils.toString(entity);  
            System.out.println(responseString);
            
            ObjectMapper mapper = new ObjectMapper();
            conceptList = mapper.readValue(responseString, new TypeReference<List<Concept>>(){});
        }
        
        return conceptList;
    }
    
    /**
     * <p>Returns the top level of concept taxonomy</p>
     * 
     * @param guid - GUID of concept
     * @return List - A set of Concept objects
     * @throws Exception - catch all for exceptions
     */
    public Concept getConcept(String guid) throws Exception
    {
    	Concept conceptObj = null;
    	
    	HashMap<String, String> parameterMap = new HashMap<String, String>();
    	//parameterMap.put("q", "");
    	
    	String parameterString = createParameterString(parameterMap);
    	String parameterHash = getSignatureHash(parameterString);
    	
        HttpClient httpClient = HttpClientBuilder.create().build();
        
        // specify the host, protocol, and port
        HttpHost target = new HttpHost(HOST_DOMAIN, HOST_PORT, HOST_SCHEME);
        
        // specify the get request
        HttpGet getRequest = new HttpGet("/concepts/" + guid + "?" + parameterString);
        
        // Add the hash header
        getRequest.setHeader("X-Hash", parameterHash);
        
        // Get our response from the EdGate server
        HttpResponse apiResponse = httpClient.execute(target, getRequest);
        HttpEntity entity = apiResponse.getEntity();
        
        // If we have an entity, convert it to Java objects
        if(entity != null) 
        {
            String responseString = EntityUtils.toString(entity);  
            System.out.println(responseString);
            
            ObjectMapper mapper = new ObjectMapper();
            conceptObj = mapper.readValue(responseString,  Concept.class);
        }
        
        return conceptObj;
    }
    
    /**
     * <p>get standards tagged with concept GUID</p>
     * 
     * @param guid - GUID of concept to get standards for
     * @param setId - ID of standards set to fetch
     * @param gradeLevel - Grade level of standards to retrieve
     * @return List - A set of Concept objects
     * @throws Exception - catch all for exceptions
     */
    public ConceptStandards getConceptStandards(String guid, String setId, String gradeLevel) throws Exception
    {
    	ConceptStandards conceptStandardsObj = null;
    	
    	HashMap<String, String> parameterMap = new HashMap<String, String>();
    	parameterMap.put("set", setId);
    	
    	if(StringUtils.isNotBlank(gradeLevel))
    	{
    		parameterMap.put("grade", gradeLevel);
    	}
    	
    	String parameterString = createParameterString(parameterMap);
    	String parameterHash = getSignatureHash(parameterString);
    	
        HttpClient httpClient = HttpClientBuilder.create().build();
        
        // specify the host, protocol, and port
        HttpHost target = new HttpHost(HOST_DOMAIN, HOST_PORT, HOST_SCHEME);
        
        // specify the get request
        HttpGet getRequest = new HttpGet("/concepts/standards/" + guid + "?" + parameterString);
        
        // Add the hash header
        getRequest.setHeader("X-Hash", parameterHash);
        
        // Get our response from the EdGate server
        HttpResponse apiResponse = httpClient.execute(target, getRequest);
        HttpEntity entity = apiResponse.getEntity();
        
        // If we have an entity, convert it to Java objects
        if(entity != null) 
        {
            String responseString = EntityUtils.toString(entity);  
            System.out.println(responseString);
            
            if(responseString.length() > 2)
            {
	            ObjectMapper mapper = new ObjectMapper();
	            conceptStandardsObj = mapper.readValue(responseString,  ConceptStandards.class);
            }
        }
        
        return conceptStandardsObj;
    }
    
    /**
     * 
     * @return
     */
    private String createParameterString(HashMap<String, String> parameterMap) throws Exception
    {
        long unixTimestamp = Instant.now().getEpochSecond();
        
        StringBuffer parameterBuffer = new StringBuffer();
        parameterBuffer.append("publicKey=" + m_publicKey);
        parameterBuffer.append("&timestamp=" + unixTimestamp);
        
        for(String parameterKey : parameterMap.keySet())
        {
        	parameterBuffer.append("&" + parameterKey + "=" + URLEncoder.encode(parameterMap.get(parameterKey), StandardCharsets.UTF_8));
        }

        return parameterBuffer.toString();
    }
    
    /**
     * 
     * @param expiration
     * @return
     */
    private String getSignatureHash(String parameterString)
    {
        String hash = null;

        try
        {
            Mac sha256HMAC = Mac.getInstance(HASH_ALGORITHM);
            
            SecretKeySpec secretKey = new SecretKeySpec(m_privateKey.getBytes(), HASH_ALGORITHM);
            sha256HMAC.init(secretKey);
            
            byte[] result = sha256HMAC.doFinal(parameterString.getBytes());
            hash = (DatatypeConverter.printHexBinary(result)).toLowerCase();
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage());
        }

        return hash;
    }
}
