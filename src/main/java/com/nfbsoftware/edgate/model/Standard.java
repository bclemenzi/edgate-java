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
	"set", 
	"standard_number", 
	"standard_text", 
	"label", 
	"depth", 
	"subject", 
	"grade", 
	"adopted", 
	"modified", 
	"parent", 
	"children", 
	"metadata", 
	"correlated"
})
public class Standard
{
	@JsonProperty("guid")
	private String guid;
	
	@JsonProperty("set")
	private String set;
	
	@JsonProperty("standard_number")
	private String standardNumber;
	
	@JsonProperty("standard_text")
	private String standardText;
	
	@JsonProperty("label")
	private String label;
	
	@JsonProperty("depth")
	private Integer depth;
	
	@JsonProperty("subject")
	private List<String> subject = null;
	
	@JsonProperty("grade")
	private List<String> grade = null;
	
	@JsonProperty("adopted")
	private String adopted;
	
	@JsonProperty("modified")
	private String modified;
	
	@JsonProperty("parent")
	private String parent;
	
	@JsonProperty("children")
	private List<String> children = null;
	
	@JsonProperty("metadata")
	private List<Metadatum> metadata = null;
	
	@JsonProperty("correlated")
	private Boolean correlated;
	
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

	@JsonProperty("set")
	public String getSet()
	{
		return set;
	}

	@JsonProperty("set")
	public void setSet(String set)
	{
		this.set = set;
	}

	@JsonProperty("standard_number")
	public String getStandardNumber()
	{
		return standardNumber;
	}

	@JsonProperty("standard_number")
	public void setStandardNumber(String standardNumber)
	{
		this.standardNumber = standardNumber;
	}

	@JsonProperty("standard_text")
	public String getStandardText()
	{
		return standardText;
	}

	@JsonProperty("standard_text")
	public void setStandardText(String standardText)
	{
		this.standardText = standardText;
	}

	@JsonProperty("label")
	public String getLabel()
	{
		return label;
	}

	@JsonProperty("label")
	public void setLabel(String label)
	{
		this.label = label;
	}

	@JsonProperty("depth")
	public Integer getDepth()
	{
		return depth;
	}

	@JsonProperty("depth")
	public void setDepth(Integer depth)
	{
		this.depth = depth;
	}

	@JsonProperty("subject")
	public List<String> getSubject()
	{
		return subject;
	}

	@JsonProperty("subject")
	public void setSubject(List<String> subject)
	{
		this.subject = subject;
	}

	@JsonProperty("grade")
	public List<String> getGrade()
	{
		return grade;
	}

	@JsonProperty("grade")
	public void setGrade(List<String> grade)
	{
		this.grade = grade;
	}

	@JsonProperty("adopted")
	public String getAdopted()
	{
		return adopted;
	}

	@JsonProperty("adopted")
	public void setAdopted(String adopted)
	{
		this.adopted = adopted;
	}

	@JsonProperty("modified")
	public String getModified()
	{
		return modified;
	}

	@JsonProperty("modified")
	public void setModified(String modified)
	{
		this.modified = modified;
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

	@JsonProperty("metadata")
	public List<Metadatum> getMetadata()
	{
		return metadata;
	}

	@JsonProperty("metadata")
	public void setMetadata(List<Metadatum> metadata)
	{
		this.metadata = metadata;
	}

	@JsonProperty("correlated")
	public Boolean getCorrelated()
	{
		return correlated;
	}

	@JsonProperty("correlated")
	public void setCorrelated(Boolean correlated)
	{
		this.correlated = correlated;
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
