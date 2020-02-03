
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
	"set", 
	"standards"
})
public class ConceptStandards
{
	@JsonProperty("set")
	private Set set;
	
	@JsonProperty("standards")
	private List<Standard> standards = null;
	
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("set")
	public Set getSet()
	{
		return set;
	}

	@JsonProperty("set")
	public void setSet(Set set)
	{
		this.set = set;
	}

	@JsonProperty("standards")
	public List<Standard> getStandards()
	{
		return standards;
	}

	@JsonProperty("standards")
	public void setStandards(List<Standard> standards)
	{
		this.standards = standards;
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
