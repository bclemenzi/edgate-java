
package com.nfbsoftware.edgate.model;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * 
 * @author brendanclemenzi
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ 
	"set_id", 
	"name", 
	"parent"
})
public class StandardsSet
{
	@JsonProperty("set_id")
	private String setId;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("parent")
	private String parent;
	
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("set_id")
	public String getSetId()
	{
		return setId;
	}

	@JsonProperty("set_id")
	public void setSetId(String setId)
	{
		this.setId = setId;
	}

	@JsonProperty("name")
	public String getName()
	{
		return name;
	}

	@JsonProperty("name")
	public void setName(String name)
	{
		this.name = name;
	}

	@JsonProperty("parent")
	public String getParent()
	{
		return parent;
	}

	@JsonProperty("parent")
	public void setParent(String parent)
	{
		this.parent = parent;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties()
	{
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value)
	{
		this.additionalProperties.put(name, value);
	}
}
