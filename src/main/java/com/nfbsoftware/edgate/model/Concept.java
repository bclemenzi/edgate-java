
package com.nfbsoftware.edgate.model;

import java.util.HashMap;
import java.util.List;
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
	"guid", 
	"name", 
	"children"
})
public class Concept
{

	@JsonProperty("guid")
	private String guid;

	@JsonProperty("name")
	private String name;

	@JsonProperty("children")
	private List<String> children = null;

	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("guid")
	public String getGuid()
	{
		return guid;
	}

	@JsonProperty("guid")
	public void setGuid(String guid)
	{
		this.guid = guid;
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

	@JsonProperty("children")
	public List<String> getChildren()
	{
		return children;
	}

	@JsonProperty("children")
	public void setChildren(List<String> children)
	{
		this.children = children;
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
