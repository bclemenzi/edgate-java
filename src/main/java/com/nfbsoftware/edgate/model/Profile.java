
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
	"standards_sets", 
	"content_sets", 
	"subjects", 
	"grades"
})
public class Profile
{
	@JsonProperty("standards_sets")
	private List<StandardsSet> standardsSets = null;
	
	@JsonProperty("content_sets")
	private List<ContentSet> contentSets = null;
	
	@JsonProperty("subjects")
	private List<String> subjects = null;
	
	@JsonProperty("grades")
	private List<String> grades = null;
	
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("standards_sets")
	public List<StandardsSet> getStandardsSets()
	{
		return standardsSets;
	}

	@JsonProperty("standards_sets")
	public void setStandardsSets(List<StandardsSet> standardsSets)
	{
		this.standardsSets = standardsSets;
	}

	@JsonProperty("content_sets")
	public List<ContentSet> getContentSets()
	{
		return contentSets;
	}

	@JsonProperty("content_sets")
	public void setContentSets(List<ContentSet> contentSets)
	{
		this.contentSets = contentSets;
	}

	@JsonProperty("subjects")
	public List<String> getSubjects()
	{
		return subjects;
	}

	@JsonProperty("subjects")
	public void setSubjects(List<String> subjects)
	{
		this.subjects = subjects;
	}

	@JsonProperty("grades")
	public List<String> getGrades()
	{
		return grades;
	}

	@JsonProperty("grades")
	public void setGrades(List<String> grades)
	{
		this.grades = grades;
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
