# edgate-java
A simplified Java library for use with the EdGate Correlation API

# EdGate Java Library

## What is EdGate?

The [EdGate Data Service](http://correlation.edgate.com/api/docs) is a REST based API platform that allows clients direct, secure access to the EdGate data repository. Using the API gives clients and their developers complete flexibility and control in creating tools to display standards and correlation data.

Features
--------

  * Follows [EdGate Correlation API](http://correlation.edgate.com/api/docs)
  * Fully constructed and inflated document trees
  * Published on Maven Central Repository

Getting started
---------------
Including the Java library in your project

The easiest way to incorporate the library into your Java project is to use Maven. Simply add a new dependency to your `pom.xml`:

```xml
<dependency>
   <groupId>com.nfbsoftware</groupId>
   <artifactId>edgate-java</artifactId>
   <version>1.0.8</version>
</dependency>
```

Get some credentials
-----

First you need some credentials (a "Public Key" and a "Private Key").  Either get some by paying EdGate Correlation Services some money, or by [signing up for a sandbox account](http://correlation.edgate.com/products_services/samples.html).


Usage
-----
Below you will find a number of basic examples to guide you through the use of the Java library.

**Basic Client Option**

```java
// Set the license keys for the EdGate API
String myPublicKey = "example";
String myPrivateKey = "6a3c8fb1062285ee69d111ca77e6f72957e55dfa0e3ac277a5a5ef82c7ba6208";

// Init our client object with the account keys
EdGateClient client = new EdGateClient(myPublicKey, myPrivateKey);
```

Library Functions
-----
The current set of function were designed to allow a developer to mimic the functionality of the search system found online: [EdGate Correlation API](http://api.edgate.com/navigate/)

**Get all options and parameters available in client profile**

```java	
// Get the account profile data
Profile profileValue = client.getProfile();

// Loop through the returned StandardsSets of the Profile
for(StandardsSet tmpStandardsSet : profileValue.getStandardsSets())
{
	System.out.println("id: " + tmpStandardsSet.getSetId());
	System.out.println("name: " + tmpStandardsSet.getName());
	System.out.println("parent: " + tmpStandardsSet.getParent());
}
```

**Return information about all standards sets the user has access to**

```java	
// Get the list of root standards for a StandardsSet
List<Standard> standardList = client.getStandardsRoot("FL");

if(standardList != null)
{
	int counter = 0;
	
	// Loop through the returned Authorities
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
```

**Return a standard based on a GUID**

```java	
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
```

**Return a list of ROOT standards of a Standard based on a GUID**

```java	
// FLorida Standard Set
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
```

**Return a list of child standard objects for a given standard GUID**

```java	
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
```