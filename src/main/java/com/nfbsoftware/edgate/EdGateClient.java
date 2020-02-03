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
import com.nfbsoftware.edgate.model.Profile;
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
        
        // Get our response from the SALT server
        HttpResponse saltyResponse = httpClient.execute(target, getRequest);
        HttpEntity entity = saltyResponse.getEntity();
        
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
     * <p>Returns information about all standards sets from the system that the user has access to</p>
     * 
     * @return List - A set of StandardsSet objects
     * @throws Exception - catch all for exceptions
     */
    public List<StandardsSet> getStandards() throws Exception
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
        
        // Get our response from the SALT server
        HttpResponse saltyResponse = httpClient.execute(target, getRequest);
        HttpEntity entity = saltyResponse.getEntity();
        
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
     * <p>Returns a standard based on a GUID</p>
     * 
     * @param id The GUID that identifies the standard that is to be read from the service provider.
     * @return List - A set of StandardsSet objects
     * @throws Exception - catch all for exceptions
     */
    public List<StandardsSet> getStandard(String id) throws Exception
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
        HttpGet getRequest = new HttpGet("/standards/" + id + "/children?" + parameterString);
        
        // Add the hash header
        getRequest.setHeader("X-Hash", parameterHash);
        
        // Get our response from the SALT server
        HttpResponse saltyResponse = httpClient.execute(target, getRequest);
        HttpEntity entity = saltyResponse.getEntity();
        
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
