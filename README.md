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
   <version>1.0.0</version>
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
// Set the location of our CASE server
String myPublicKey = "example";
String myPrivateKey = "6a3c8fb1062285ee69d111ca77e6f72957e55dfa0e3ac277a5a5ef82c7ba6208";

// Init our client object with the host information
EdGateClient client = new EdGateClient(myPublicKey, myPrivateKey);

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

**Get all options and parameters available in client profile**

```java	
// Get the list of standard sets for the account
List<StandardsSet> standardsSetList = client.getStandards();

// Loop through the returned Authorities
for(StandardsSet tmpStandardsSet : standardsSetList)
{
	System.out.println("id: " + tmpStandardsSet.getSetId());
    System.out.println("name: " + tmpStandardsSet.getName());
    System.out.println("parent: " + tmpStandardsSet.getParent());
}
```