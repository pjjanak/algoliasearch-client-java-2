<!--NO_HTML-->

# Algolia Search API Client for Java2







[Algolia Search](https://www.algolia.com) is a hosted full-text, numerical, and faceted search engine capable of delivering realtime results from the first keystroke.


Our Java2 client lets you easily use the [Algolia Search API](https://www.algolia.com/doc/rest) from your backend. It wraps the [Algolia Search REST API](https://www.algolia.com/doc/rest).



[![Build Status](https://travis-ci.org/algolia/algoliasearch-client-java-2.png?branch=master)](https://travis-ci.org/algolia/algoliasearch-client-java-2) [![GitHub version](https://badge.fury.io/gh/algolia%2Falgoliasearch-client-java-2-2.png)](http://badge.fury.io/gh/algolia%2Falgoliasearch-client-java-2) [![Coverage Status](https://coveralls.io/repos/algolia/algoliasearch-client-java-2/badge.svg)](https://coveralls.io/r/algolia/algoliasearch-client-java-2)[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.algolia/algoliasearch/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.algolia/algoliasearch/)



**WARNING:**
The JVM has an infinite cache on successful DNS resolution. As our hostnames points to multiple IPs, the load could be not evenly spread among our machines, and you might also target a dead machine.

You should change this TTL by setting the property `networkaddress.cache.ttl`. For example to set the cache to 60 seconds:
```java
java.security.Security.setProperty("networkaddress.cache.ttl", "60");
```




## Table of Contents

**Getting Started**

1. [Getting started](#getting-started)
1. [Quick Start](#quick-start)
1. [Philosophy of the java client](#philosophy)
1. [Guides & Tutorials](#guides--tutorials)


**Commands Reference**

Getting started

1. [Install](#install)
1. [Init index](#init-index---initindex)

Search

1. [Search in an index](#search-in-an-index---search)
1. [Find by IDs](#find-by-ids---getobjects)

Indexing

1. [Add objects](#add-objects---addobjects)
1. [Update objects](#update-objects---saveobjects)
1. [Partial update](#partial-update---partialupdateobjects)
1. [Delete objects](#delete-objects---deleteobjects)

Settings

1. [Get settings](#get-settings---getsettings)
1. [Set settings](#set-settings)

Manage Indices

1. [List indices](#list-indices---listindices)
1. [Delete index](#delete-index---delete)
1. [Clear index](#clear-index---clear)
1. [Copy index](#copy-index---clear)
1. [Move index](#move-index---moveto)

Api Keys

1. [Generate key](#generate-key---listindices)


Synonyms

1. [Save synonym](#save-synonym---savesynonym)
1. [Batch synonyms](#batch-synonyms---batchsynonyms)
1. [Editing Synonyms](#editing-synonyms)
1. [Delete Synonyms](#delete-synonyms---delete_synonyms)
1. [Clear all synonyms](#clear-all-synonyms---clearsynonyms)
1. [Get synonym](#get-synonym---getsynonym)
1. [Search synonyms](#search-synonyms---searchsynonyms)


Advanced

1. [Custom batch](#custom-batch---batch)
1. [Wait for operations](#wait-for-operations---waittask)
1. [Multiple queries](#multiple-queries---multiplequeries)
1. [Delete by query](#delete-by-query---deletebyquery)
1. [Backup / Export an index](#backup--export-an-index---browse)
1. [List api keys](#list-api-keys---listapikeys)
1. [Add user key](#add-user-key---adduserkey)
1. [Update user key](#update-user-key---updateuserkey)
1. [Delete user key](#delete-user-key---listindices)
1. [Get key permissions](#get-key-permissions---getuserkeyacl)
1. [Get Logs](#get-logs---getlogs)



## Guides & Tutorials

Check our [online guides](https://www.algolia.com/doc):
 * [Data Formatting](https://www.algolia.com/doc/indexing/formatting-your-data)
 * [Import and Synchronize data](https://www.algolia.com/doc/indexing/import-synchronize-data/java2)
 * [Autocomplete](https://www.algolia.com/doc/search/auto-complete)
 * [Instant search page](https://www.algolia.com/doc/search/instant-search)
 * [Filtering and Faceting](https://www.algolia.com/doc/search/filtering-faceting)
 * [Sorting](https://www.algolia.com/doc/relevance/sorting)
 * [Ranking Formula](https://www.algolia.com/doc/relevance/ranking)
 * [Typo-Tolerance](https://www.algolia.com/doc/relevance/typo-tolerance)
 * [Geo-Search](https://www.algolia.com/doc/geo-search/geo-search-overview)
 * [Security](https://www.algolia.com/doc/security/best-security-practices)
 * [API-Keys](https://www.algolia.com/doc/security/api-keys)
 * [REST API](https://www.algolia.com/doc/rest)









<!--/NO_HTML-->



## Getting Started

### Install


If you're using Maven, add the following dependency to your `pom.xml` file:

```xml
<dependency>
  <groupId>com.algolia</groupId>
  <artifactId>algoliasearch</artifactId>
  <version>[2,]</version>
</dependency>
```

For the Async version use this:

```xml
<dependency>
  <groupId>com.algolia</groupId>
  <artifactId>algoliasearch-async</artifactId>
  <version>[2,]</version>
</dependency>
```

On Google AppEngine use this:

```xml
<dependency>
  <groupId>com.algolia</groupId>
  <artifactId>algoliasearch-appengine</artifactId>
  <version>[2,]</version>
</dependency>
```






### Init index - `InitIndex`

To initialize the client you need your ApplicationID and API-Key. You can find all of them on [your Algolia account](http://www.algolia.com/users/edit)

```java
APIClient client = new ApacheAPIClientBuilder("YourApplicationID", "YourAPIKey").build();
```

For the Async version

```java
AsyncAPIClient client = new AsyncHttpAPIClientBuilder("YourApplicationID", "YourAPIKey").build();
```

For Google AppEngine

```java
APIClient client = new AppEngineAPIClientBuilder("YourApplicationID", "YourAPIKey").build();
```



### Quick Start



In 30 seconds, this quick start tutorial will show you how to index and search objects.

Without any prior configuration, you can start indexing contacts in the ```contacts``` index using the following code:

```java
class Contact {

	private String firstname;
	private String lastname;
	private int followers;
	private String company;

	//Getters/Setters ommitted
}


Index<Contact> index = client.initIndex("contacts", Contact.class);
index.addObject(new Contact()
      .setFirstname("Jimmie")
      .setLastname("Barninger")
      .setFollowers(93)
      .setCompany("California Paint"));
index.addObject(new JSONObject()
      .setFirstname("Warren")
      .setLastname("Speach")
      .setFollowers(42)
      .setCompany("Norwalk Crmc"));
```

If you prefer the async version:

```java
AsyncIndex<Contact> index = client.initIndex("contacts", Contact.class);
index.addObject(new Contact()
      .setFirstname("Jimmie")
      .setLastname("Barninger")
      .setFollowers(93)
      .setCompany("California Paint"));
index.addObject(new JSONObject()
      .setFirstname("Warren")
      .setLastname("Speach")
      .setFollowers(42)
      .setCompany("Norwalk Crmc"));
```

You can now search for contacts using firstname, lastname, company, etc. (even with typos):
```java
//Sync version

// search by firstname
System.out.println(index.search(new Query("jimmie")));
// search a firstname with typo
System.out.println(index.search(new Query("jimie")));
// search for a company
System.out.println(index.search(new Query("california paint")));
// search for a firstname & company
System.out.println(index.search(new Query("jimmie paint")));
```

```java
//Async version

// search by firstname
System.out.println(index.search(new Query("jimmie")).get());
// search a firstname with typo
System.out.println(index.search(new Query("jimie")).get());
// search for a company
System.out.println(index.search(new Query("california paint")).get());
// search for a firstname & company
System.out.println(index.search(new Query("jimmie paint")).get());
```

Settings can be customized to tune the search behavior. For example, you can add a custom sort by number of followers to the already great built-in relevance:
```java
//Sync & Async version

index.setSettings(new IndexSettings().setCustomRanking(Arrays.asList("desc(followers)")));
```

You can also configure the list of attributes you want to index by order of importance (first = most important):
```java
//Sync & Async version

index.setSettings(new IndexSettings().setAttributesToIndex(
	Arrays.asList("lastname", "firstname", "company")
);
```

Since the engine is designed to suggest results as you type, you'll generally search by prefix. In this case the order of attributes is very important to decide which hit is the best:
```java
//Sync version

System.out.println(index.search(new Query("or")));
System.out.println(index.search(new Query("jim")));
```

```java
//Async version

System.out.println(index.search(new Query("or")).get());
System.out.println(index.search(new Query("jim")).get());
```


**Note:** If you are building a web application, you may be more interested in using our [JavaScript client](https://github.com/algolia/algoliasearch-client-js) to perform queries. It brings two benefits:
  * Your users get a better response time by not going through your servers
  * It will offload unnecessary tasks from your servers

```html
<script src="https://cdn.jsdelivr.net/algoliasearch/3/algoliasearch.min.js"></script>
<script>
var client = algoliasearch('ApplicationID', 'apiKey');
var index = client.initIndex('indexName');

// perform query "jim"
index.search('jim', searchCallback);

// the last optional argument can be used to add search parameters
index.search(
  'jim', {
    hitsPerPage: 5,
    facets: '*',
    maxValuesPerFacet: 10
  },
  searchCallback
);

function searchCallback(err, content) {
  if (err) {
    console.error(err);
    return;
  }

  console.log(content);
}
</script>
```





### Philosophy

#### Builder

The v2 of the api client, uses a builder to create the APIClient object. If you are on a regular JVM (not android, not Google App Engine), use the `ApacheAPIClientBuilder`, if you are on Google App Engine use the `AppEngineAPIClientBuilder`. If you fancy `Future`, use the `AsyncHttpAPIClientBuilder`.
As for Android, please use the [Android API Client](https://github.com/algolia/algoliasearch-client-android)


#### JSON & Jackson2

All the serialization/deserialization is done with Jackson2. You can add your custom ObjectMapper with the method `setObjectMapper` of the builder.
Changing it might result in unexpected result. You can find the one used in the interface `com.algolia.search.Defaults.DEFAULT_OBJECT_MAPPER`.


#### Async & Future

All methods of the `AsyncAPIClient` are exactly the same as the `APIClient` but returns `CompletableFuture<?>`. All other classes are prefixes with `Async`. You can also pass a optional `ExecutorService` to the `build` of the `AsyncHttpAPIClientBuilder`.


## Search

### Search in an index - `Search`


**Notes:** If you are building a web application, you may be more interested in using our [JavaScript client](https://github.com/algolia/algoliasearch-client-js) to perform queries. It brings two benefits:
  * Your users get a better response time by not going through your servers
  * It will offload unnecessary tasks from your servers.


To perform a search, you only need to initialize the index and perform a call to the search function.

The search query allows only to retrieve 1000 hits, if you need to retrieve more than 1000 hits for seo, you can use [Backup / Retrieve all index content](#backup--export-an-index)

```java
//Sync version

Index<Contact> index = client.initIndex("contacts", Contact.class);
SearchResult<Contact> search1 = index.search(new Query("query string"));
SearchResult<Contact> search2 = index.search(new Query("query string").
             setAttributesToRetrieve(Arrays.asList("firstname", "lastname")).
             setNbHitsPerPage(50));
```

```java
//Async version
AsyncIndex<Contact> index = client.initIndex("contacts", Contact.class);
CompletableFuture<SearchResult<Contact>> search1 = index.search(new Query("query string"));
CompletableFuture<SearchResult<Contact>> search2 = index.search(new Query("query string").
             setAttributesToRetrieve(Arrays.asList("firstname", "lastname")).
             setNbHitsPerPage(50));
```

The server response will look like:

```json
{
  "hits": [
    {
      "firstname": "Jimmie",
      "lastname": "Barninger",
      "objectID": "433",
      "_highlightResult": {
        "firstname": {
          "value": "<em>Jimmie</em>",
          "matchLevel": "partial"
        },
        "lastname": {
          "value": "Barninger",
          "matchLevel": "none"
        },
        "company": {
          "value": "California <em>Paint</em> & Wlpaper Str",
          "matchLevel": "partial"
        }
      }
    }
  ],
  "page": 0,
  "nbHits": 1,
  "nbPages": 1,
  "hitsPerPage": 20,
  "processingTimeMS": 1,
  "query": "jimmie paint",
  "params": "query=jimmie+paint&attributesToRetrieve=firstname,lastname&hitsPerPage=50"
}
```

You can use the following optional arguments:

### Search Parameters

<!--PARAMETERS_LINK-->
Here is the list of parameters you can use with the search method (`search` [scope](#scope)):
Parameters that can also be used in a setSettings also have the `indexing` [scope](#scope)

**Search**
- [query](#query) `search`

**Attributes**
- [attributesToRetrieve](#attributestoretrieve) `settings`, `search`

**Filtering / Faceting**
- [filters](#filters) `search`
- [facets](#facets) `search`
- [maxValuesPerFacet](#maxvaluesperfacet) `settings`, `search`

**Highlighting / Snippeting**
- [attributesToHighlight](#attributestohighlight) `settings`, `search`
- [attributesToSnippet](#attributestosnippet) `settings`, `search`
- [highlightPreTag](#highlightpretag) `settings`, `search`
- [highlightPostTag](#highlightposttag) `settings`, `search`
- [snippetEllipsisText](#snippetellipsistext) `settings`, `search`

**Pagination**
- [page](#page) `search`
- [hitsPerPage](#hitsperpage) `settings`, `search`

**Typos**
- [minWordSizefor1Typo](#minwordsizefor1typo) `settings`, `search`
- [minWordSizefor2Typos](#minwordsizefor2typos) `settings`, `search`
- [typoTolerance](#typotolerance) `settings`, `search`
- [allowTyposOnNumericTokens](#allowtyposonnumerictokens) `settings`, `search`
- [ignorePlurals](#ignoreplurals) `settings`, `search`
- [disableTypoToleranceOnAttributes](#disabletypotoleranceonattributes) `settings`, `search`

**Geo-Search**
- [aroundLatitudeLongitude(float, float)](#aroundlatitudelongitudefloat,-float) `search`
- [aroundLatitudeLongitude(float, float, int, int)](#aroundlatitudelongitudefloat,-float,-int,-int) `search`
- [aroundLatLngViaIP](#aroundlatlngviaip) `search`
- [insideBoundingBox](#insideboundingbox) `search`
- [insidePolygon](#insidepolygon) `search`


**Query Strategy**
- [queryType](#querytype) `settings`, `search`
- [removeWordsIfNoResults](#removewordsifnoresults) `settings`, `search`
- [advancedSyntax](#advancedsyntax) `settings`, `search`
- [optionalWords](#optionalwords) `settings`, `search`
- [removeStopWords](#removestopwords) `settings`, `search`
- [exactOnSingleWordQuery](#exactonsinglewordquery) `settings`, `search`
- [alternativesAsExact](#alternativesasexact) `settings`, `search`

**Advanced**
- [distinct](#distinct) `settings`, `search`
- [rankingInfo](#rankinginfo) `search`
- [numericFilters (deprecated)](#numericfilters-deprecated) `search`
- [tagFilters (deprecated)](#tagfilters-deprecated) `search`
- [facetFilters (deprecated)](#facetfilters-deprecated) `search`
- [analytics](#analytics) `settings`, `search`

<!--/PARAMETERS_LINK-->

### Find by IDs - `getObjects`

You can easily retrieve an object using its `objectID` and optionally specify a comma separated list of attributes you want:

```java
//Sync version
Contact contact = index.getObject("myID");
```

```java
//Async version
CompletableFuture<Contact> contact = index.getObject("myID");
```

You can also retrieve a set of objects:

```java
//Sync version
List<Contact> list = index.getObjects(Arrays.asList("myObj1", "myObj2"));
```

```java
//Async version
CompletableFuture<List<Contact>> list = index.getObjects(Arrays.asList("myObj1", "myObj2"));
```




## Indexing

### Add objects - `addObjects`

Each entry in an index has a unique identifier called `objectID`. There are two ways to add an entry to the index:

 1. Using automatic `objectID` assignment. You will be able to access it in the answer.
 2. Supplying your own `objectID`.

You don't need to explicitly create an index, it will be automatically created the first time you add an object.
Objects are schema less so you don't need any configuration to start indexing. If you wish to configure things, the settings section provides details about advanced settings.

Example with automatic `objectID` assignment:

```java
//Sync version
TaskIndexing task = index.addObject(new Contact()
      .setFirstName("Jimmie")
      .setLastName("Barninger"));

String objectID = task.getObjectID("objectID"));
```

```java
//Async version
CompletableFuture<AsyncTaskIndexing> task = index.addObject(new Contact()
      .setFirstName("Jimmie")
      .setLastName("Barninger"));

String objectID = task.get().getObjectID("objectID"));
```

Example with manual `objectID` assignment:

```java
//Sync version
TaskSingleIndex task = index.addObject(new Contact()
      .setFirstName("Jimmie"),
      .setLastname("Barninger")
      .setObjectID("objectID"));

String objectID = task.getObjectID("objectID"));
```

```java
//Async version
CompletableFuture<AsyncTaskSingleIndex> task = index.addObject(new Contact()
      .setFirstName("Jimmie"),
      .setLastname("Barninger")
      .setObjectID("objectID"));

String objectID = task.get().getObjectID("objectID"));
```


### Update objects - `saveObjects`

You have three options when updating an existing object:

 1. Replace all its attributes.
 2. Replace only some attributes.
 3. Apply an operation to some attributes.

Example on how to replace all attributes of an existing object:

```java
//Sync & async version

index.saveObject(new Contact()
      .setFirstname("Jimmie")
      .setLastname("Barninger")
      .setCity("New York")
      .setObjectID("myID"));
```

### Partial update - `PartialUpdateObjects`

You have many ways to update an object's attributes:

 1. Set the attribute value
 2. Add a string or number element to an array
 3. Remove an element from an array
 4. Add a string or number element to an array if it doesn't exist
 5. Increment an attribute
 6. Decrement an attribute

Example to update only the city attribute of an existing object:

```java
//Sync & async version

index.partialUpdateObject("myID", new Contact().setCity("San Francisco"));
```

Example to add a tag:

```java
//Sync & async version

index.partialUpdateObject(new AddValueOperation("myID", "_tags", "MyTags"));
```

Example to remove a tag:

```java
//Sync & async version

index.partialUpdateObject(new RemoveValueOperation("myID", "_tags", "MyTags"));
```

Example to add a tag if it doesn't exist:

```java
//Sync & async version

index.partialUpdateObject(new AddValueUniqueOperation("myID", "_tags", "MyTags"));
```

Example to increment a numeric value:

```java
//Sync & async version

index.partialUpdateObject(new IncrementValueOperation("myID", "price", 42));
```

Note: Here we are incrementing the value by `42`. To increment just by one, put
`value:1`.

Example to decrement a numeric value:

```java
//Sync & async version

index.partialUpdateObject(new DecrementValueOperation("myID", "price", 42));
```

Note: Here we are decrementing the value by `42`. To decrement just by one, put
`value:1`.


### Delete objects - `deleteObjects`

You can delete an object using its `objectID`:

```java
//Sync & Async version
index.deleteObject("myID");
```

### Delete by query - `deleteByQuery`

You can delete all objects matching a single query with the following code. Internally, the API client performs the query, deletes all matching hits, and waits until the deletions have been applied.


Take your precautions when using this method. Calling it with an empty query will result in cleaning the index of all its records.

This method does not exists in Async

```java
Query query = /* [ ... ] */;
index.deleteByQuery(query);
```

### Wait for operations - `waitTask`

All write operations in Algolia are asynchronous by design.

It means that when you add or update an object to your index, our servers will
reply to your request with a `taskID` as soon as they understood the write
operation.

The actual insert and indexing will be done after replying to your code.

You can wait for a task to complete using the `waitTask` method on the `taskID` returned by a write operation.

For example, to wait for indexing of a new object:
```java
//Sync version

//Every Task object, has a method waitForCompletion()
TaskIndexing task = index.addObject(new Contact().setFirstname("Jimmie").setLastname("Barninger"));
task.waitForCompletion();
```

```java
//Async version

CompletableFuture<AsyncTaskIndexing> task = index.addObject(new Contact().setFirstname("Jimmie").setLastname("Barninger"));
client.waitTask(task.get());
```

If you want to ensure multiple objects have been indexed, you only need to check
the biggest `taskID`.

## Settings

### Get settings - `getSettings`

You can retrieve settings:

```java
//Sync version
IndexSettings settings = index.getSettings());
```

```java
//Async version
CompletableFuture<IndexSettings> settings = index.getSettings());
```

### Set settings

```java
//Sync & Async version

index.setSettings(new IndexSettings().setCustomRanking(Arrays.asList("desc(followers)")));
```

#### Slave settings

You can forward all settings updates to the slaves of an index by using the `forwardToSlaves` option:

```java
//Sync & Async version

index.setSettings(new IndexSettings().setCustomRanking(Arrays.asList("desc(followers)")), true);
```


To add a custom setting for an index, simply extend the class `IndexSettings` and add your getter/setter. The new settings will be automatically serialized by jackson.


### Index settings parameters

<!--PARAMETERS_LINK-->

Here is the list of parameters you can use with the set settings method (`indexing` [scope](#scope))


Parameters that can be override at search time also have the `indexing` [scope](#scope)

**Attributes**
- [attributesToIndex](#attributestoindex) `settings`
- [attributesForFaceting](#attributesforfaceting) `settings`
- [attributesToRetrieve](#attributestoretrieve) `settings`, `search`
- [unretrievableAttributes](#unretrievableattributes) `settings`

**Ranking**
- [ranking](#ranking) `settings`
- [customRanking](#customranking) `settings`
- [slaves](#slaves) `settings`

**Filtering / Faceting**
- [maxValuesPerFacet](#maxvaluesperfacet) `settings`, `search`

**Highlighting / Snippeting**
- [attributesToHighlight](#attributestohighlight) `settings`, `search`
- [attributesToSnippet](#attributestosnippet) `settings`, `search`
- [highlightPreTag](#highlightpretag) `settings`, `search`
- [highlightPostTag](#highlightposttag) `settings`, `search`
- [snippetEllipsisText](#snippetellipsistext) `settings`, `search`

**Pagination**
- [hitsPerPage](#hitsperpage) `settings`, `search`

**Typos**
- [minWordSizefor1Typo](#minwordsizefor1typo) `settings`, `search`
- [minWordSizefor2Typos](#minwordsizefor2typos) `settings`, `search`
- [typoTolerance](#typotolerance) `settings`, `search`
- [allowTyposOnNumericTokens](#allowtyposonnumerictokens) `settings`, `search`
- [ignorePlurals](#ignoreplurals) `settings`, `search`
- [disableTypoToleranceOnAttributes](#disabletypotoleranceonattributes) `settings`, `search`
- [separatorsToIndex](#separatorstoindex) `settings`

**Query Strategy**
- [queryType](#querytype) `settings`, `search`
- [removeWordsIfNoResults](#removewordsifnoresults) `settings`, `search`
- [advancedSyntax](#advancedsyntax) `settings`, `search`
- [optionalWords](#optionalwords) `settings`, `search`
- [removeStopWords](#removestopwords) `settings`, `search`
- [disablePrefixOnAttributes](#disableprefixonattributes) `settings`
- [disableExactOnAttributes](#disableexactonattributes) `settings`
- [exactOnSingleWordQuery](#exactonsinglewordquery) `settings`, `search`
- [alternativesAsExact](#alternativesasexact) `settings`, `search`

**Advanced**
- [attributeForDistinct](#attributefordistinct) `settings`
- [distinct](#distinct) `settings`, `search`
- [numericAttributesToIndex](#numericattributestoindex) `settings`
- [allowCompressionOfIntegerArray](#allowcompressionofintegerarray) `settings`
- [altCorrections](#altcorrections) `settings`
- [placeholders](#placeholders) `settings`

<!--/PARAMETERS_LINK-->


## Parameters

### Overview

#### Scope

Each parameter in this page has a scope. Depending on the scope, you can use the parameter within the `setSettings`
and/or the `search` method

They are three scopes:
- `settings`: The setting can only be used in the `setSettings` method
- `search`: The setting can only be used in the `search` method
- `settings` `search`: The setting can be used in the `setSettings` method and be override in the`search` method


#### Parameters List

**Search**
- [query](#query) `search`

**Attributes**
- [attributesForFaceting](#attributesforfaceting) `settings`
- [attributesToIndex](#attributestoindex) `settings`
- [attributesToRetrieve](#attributestoretrieve) `settings`, `search`
- [unretrievableAttributes](#unretrievableattributes) `settings`


**Ranking**
- [ranking](#ranking) `settings`
- [customRanking](#customranking) `settings`
- [slaves](#slaves) `settings`

**Filtering / Faceting**
- [filters](#filters) `search`
- [facets](#facets) `search`
- [maxValuesPerFacet](#maxvaluesperfacet) `settings`, `search`

**Highlighting / Snippeting**
- [attributesToHighlight](#attributestohighlight) `settings`, `search`
- [attributesToSnippet](#attributestosnippet) `settings`, `search`
- [highlightPreTag](#highlightpretag) `settings`, `search`
- [highlightPostTag](#highlightposttag) `settings`, `search`
- [snippetEllipsisText](#snippetellipsistext) `settings`, `search`

**Pagination**
- [page](#page) `search`
- [hitsPerPage](#hitsperpage) `settings`, `search`

**Typos**
- [minWordSizefor1Typo](#minwordsizefor1typo) `settings`, `search`
- [minWordSizefor2Typos](#minwordsizefor2typos) `settings`, `search`
- [typoTolerance](#typotolerance) `settings`, `search`
- [allowTyposOnNumericTokens](#allowtyposonnumerictokens) `settings`, `search`
- [ignorePlurals](#ignoreplurals) `settings`, `search`
- [disableTypoToleranceOnAttributes](#disabletypotoleranceonattributes) `settings`, `search`
- [separatorsToIndex](#separatorstoindex) `settings`

**Geo-Search**

- [aroundLatitudeLongitude(float, float)](#aroundlatitudelongitudefloat,-float) `search`
- [aroundLatitudeLongitude(float, float, int, int)](#aroundlatitudelongitudefloat,-float,-int,-int) `search`
- [aroundLatLngViaIP](#aroundlatlngviaip) `search`
- [insideBoundingBox](#insideboundingbox) `search`
- [insidePolygon](#insidepolygon) `search`


**Query Strategy**
- [queryType](#querytype) `settings`, `search`
- [removeWordsIfNoResults](#removewordsifnoresults) `settings`, `search`
- [advancedSyntax](#advancedsyntax) `settings`, `search`
- [optionalWords](#optionalwords) `settings`, `search`
- [removeStopWords](#removestopwords) `settings`, `search`
- [disablePrefixOnAttributes](#disableprefixonattributes) `settings`
- [disableExactOnAttributes](#disableexactonattributes) `settings`
- [exactOnSingleWordQuery](#exactonsinglewordquery) `settings`, `search`
- [alternativesAsExact](#alternativesasexact) `settings`, `search`

**Advanced**
- [attributeForDistinct](#attributefordistinct) `settings`
- [distinct](#distinct) `settings`, `search`
- [rankingInfo](#rankinginfo) `search`
- [numericAttributesToIndex](#numericattributestoindex) `settings`
- [allowCompressionOfIntegerArray](#allowcompressionofintegerarray) `settings`
- [numericFilters (deprecated)](#numericfilters-deprecated) `search`
- [tagFilters (deprecated)](#tagfilters-deprecated) `search`
- [facetFilters (deprecated)](#facetfilters-deprecated) `search`
- [analytics](#analytics) `settings`, `search`
- [altCorrections](#altcorrections) `settings`
- [placeholders](#placeholders) `settings`

### Search

#### query

- scope: `search`
- type: `string`
- default: `""`


The instant search query string, used to set the string you want to search in your index. If no query parameter is set, the textual search will match with all the objects.

### Attributes

#### attributesToIndex

- scope: `settings`
- type: `array of strings`
- default: `*`


The list of attributes you want index (i.e. to make searchable).

If set to null, all textual and numerical attributes of your objects are indexed.
Make sure you updated this setting to get optimal results.

This parameter has two important uses:
* **Limit the attributes to index**.
<br/>For example, if you store the URL of a picture, you want to store it and be able to retrieve it,
but you probably don't want to search in the URL.
* **Control part of the ranking**.
<br/> Matches in attributes at the beginning of the list will be considered more important than matches in attributes
further down the list.
In one attribute, matching text at the beginning of the attribute will be considered more important than text after.
You can disable this behavior if you add your attribute inside `unordered(AttributeName)`.
For example, `attributesToIndex: ["title", "unordered(text)"]`.
You can decide to have the same priority for two attributes
by passing them in the same string using a comma as a separator.
For example `title` and `alternative_title` have the same priority in this example,
which is different than text priority: `attributesToIndex:["title,alternative_title", "text"]`.
To get a full description of how the Ranking works, you can have a look at our
[Ranking guide](https://www.algolia.com/doc/relevance/ranking).


#### attributesForFaceting

- scope: `settings`
- type: `array of strings`
- default: `null`


The list of fields you want to use for faceting.
All strings in the attribute selected for faceting are extracted and added as a facet.
If set to null, no attribute is used for faceting.


#### unretrievableAttributes

- scope: `settings`
- type: `array of strings`
- default: `null`


The list of attributes that cannot be retrieved at query time.
This feature allows you to have attributes that are used for indexing
and/or ranking but cannot be retrieved

**Warning**: for testing purposes, this setting is ignored when you're using the ADMIN API Key.

#### attributesToRetrieve

- scope: `settings`, `search`
- type: `array of strings`
- default: `*`


A string that contains the list of attributes you want to retrieve in order to minimize the size of the JSON answer.

Attributes are separated with a comma (for example `"name,address"`).
You can also use a string array encoding (for example `["name","address"]` ).
By default, all attributes are retrieved.
You can also use `*` to retrieve all values when an **attributesToRetrieve** setting is specified for your index.

`objectID` is always retrieved even when not specified.


#### restrictSearchableAttributes

- scope: `search`
- type: `array of strings`
- default: `attributesToIndex`


List of attributes you want to use for textual search (must be a subset of the `attributesToIndex` index setting).
Attributes are separated with a comma such as `"name,address"`.
You can also use JSON string array encoding such as `encodeURIComponent("[\"name\",\"address\"]")`.
By default, all attributes specified in the `attributesToIndex` settings are used to search.


### Ranking

#### ranking

- scope: `settings`
- type: `array of strings`
- default: `['typo', 'geo', 'words', 'filters', 'proximity', 'attribute', 'exact', 'custom']`


Controls the way results are sorted.

We have nine available criterion:

* `typo`: Sort according to number of typos.
* `geo`: Sort according to decreasing distance when performing a geo location based search.
* `words`: Sort according to the number of query words matched by decreasing order. This parameter is useful when you use the `optionalWords` query parameter to have results with the most matched words first.
* `proximity`: Sort according to the proximity of the query words in hits.
* `attribute`: Sort according to the order of attributes defined by attributesToIndex.
* `exact`:
  * If the user query contains one word: sort objects having an attribute that is exactly the query word before others. For example, if you search for the TV show "V", you want to find it with the "V" query and avoid getting all popular TV shows starting by the letter V before it.
  * If the user query contains multiple words: sort according to the number of words that matched exactly (not as a prefix).
* `custom`: Sort according to a user defined formula set in the `customRanking` attribute.
* `asc(attributeName)`: Sort according to a numeric attribute using ascending order. `attributeName` can be the name of any numeric attribute in your records (integer, double or boolean).
* `desc(attributeName)`: Sort according to a numeric attribute using descending order. `attributeName` can be the name of any numeric attribute in your records (integer, double or boolean).

<br/>To get a full description of how the Ranking works,
you can have a look at our [Ranking guide](https://www.algolia.com/doc/relevance/ranking).

#### customRanking

- scope: `settings`
- type: `array of strings`
- default: `[]`


Lets you specify part of the ranking.

The syntax of this condition is an array of strings containing attributes
prefixed by the asc (ascending order) or desc (descending order) operator.

For example, `"customRanking" => ["desc(population)", "asc(name)"]`.

To get a full description of how the Custom Ranking works,
you can have a look at our [Ranking guide](https://www.algolia.com/doc/relevance/ranking).

#### slaves

- scope: `settings`
- type: `array of strings`
- default: `[]`


The list of indices on which you want to replicate all write operations.

In order to get response times in milliseconds, we pre-compute part of the ranking during indexing.

If you want to use different ranking configurations depending of the use case,
you need to create one index per ranking configuration.

This option enables you to perform write operations only on this index and automatically
update slave indices with the same operations.

### Filtering / Faceting

#### filters

- scope: `search`
- type: `string`
- default: `""`


Filter the query with numeric, facet or/and tag filters.

The syntax is a SQL like syntax, you can use the OR and AND keywords.
The syntax for the underlying numeric, facet and tag filters is the same than in the other filters:

`available=1 AND (category:Book OR NOT category:Ebook) AND _tags:public`
`date: 1441745506 TO 1441755506 AND inStock > 0 AND author:"John Doe"`

If no attribute name is specified,
the filter applies to `_tags`.

For example: `public OR user_42` will translate to `_tags:public OR _tags:user_42`.

The list of keywords is:
* `OR`: create a disjunctive filter between two filters.
* `AND`: create a conjunctive filter between two filters.
* `TO`: used to specify a range for a numeric filter.
* `NOT`: used to negate a filter. The syntax with the `-` isn’t allowed.

*Note*: To specify a value with spaces or with a value equal to a keyword, it's possible to add quotes.

**Warning:**

* Like for the other filters (for performance reasons), it's not possible to have FILTER1 OR (FILTER2 AND FILTER3).
* It's not possible to mix different categories of filters inside an OR like: num=3 OR tag1 OR facet:value
* It's not possible to negate a group, it's only possible to negate a filter:  NOT(FILTER1 OR (FILTER2) is not allowed.


#### facets

- scope: `search`
- type: `string`
- default: `""`


List of object attributes that you want to use for faceting.

For each of the declared attributes, you'll be able to retrieve a list of the most relevant facet values,
and their associated count for the current query.

Attributes are separated by a comma.

For example, `"category,author"`.

You can also use JSON string array encoding.

For example, `["category","author"]`.

Only the attributes that have been added in **attributesForFaceting** index setting can be used in this parameter.
You can also use `*` to perform faceting on all attributes specified in `attributesForFaceting`.
If the number of results is important, the count can be approximate,
the attribute `exhaustiveFacetsCount` in the response is true when the count is exact.

#### maxValuesPerFacet

- scope: `settings`, `search`
- type: `integer`
- default: `""`


Limit the number of facet values returned for each facet.

For example, `maxValuesPerFacet=10` will retrieve a maximum of 10 values per facet.

### Highlighting / Snippeting

#### attributesToHighlight

- scope: `settings`, `search`
- type: `array of strings`
- default: `null`


Default list of attributes to highlight.
If set to null, all indexed attributes are highlighted.

A string that contains the list of attributes you want to highlight according to the query.
Attributes are separated by commas.
You can also use a string array encoding (for example `["name","address"]`).
If an attribute has no match for the query, the raw value is returned.
By default, all indexed attributes are highlighted (as long as they are strings).
You can use `*` if you want to highlight all attributes.

A matchLevel is returned for each highlighted attribute and can contain:
* `full`: If all the query terms were found in the attribute.
* `partial`: If only some of the query terms were found.
* `none`: If none of the query terms were found.

#### attributesToSnippet

- scope: `settings`, `search`
- type: `array of strings`
- default: `null`


Default list of attributes to snippet alongside the number of words to return (syntax is `attributeName:nbWords`).
If set to null, no snippet is computed.

#### highlightPreTag

- scope: `settings`, `search`
- type: `string`
- default: `<em>`


Specify the string that is inserted before the highlighted parts in the query result (defaults to `<em>`).



#### highlightPostTag

- scope: `settings`, `search`
- type: `string`
- default: `</em>`


Specify the string that is inserted after the highlighted parts in the query result (defaults to `</em>`).



#### snippetEllipsisText

- scope: `settings`, `search`
- type: `string`
- default: `…`


String used as an ellipsis indicator when a snippet is truncated.
Defaults to an empty string for all accounts created before 10/2/2016, and to … (UTF-8 U+2026) for accounts created after that date.

### Pagination

#### page

- scope: `search`
- type: `integer`
- default: `0`


Pagination parameter used to select the page to retrieve.
<br>
Page is zero based and defaults to 0. Thus, to retrieve the 10th page you need to set `page=9`.

#### hitsPerPage

- scope: `settings`, `search`
- type: `integer`
- default: `20`


Pagination parameter used to select the number of hits per page. Defaults to 20.

### Typos

#### minWordSizefor1Typo

- scope: `settings`, `search`
- type: `integer`
- default: `4`


The minimum number of characters needed to accept one typo.

#### minWordSizefor2Typos

- scope: `settings`, `search`
- type: `integer`
- default: `8`


The minimum number of characters needed to accept two typos.

#### typoTolerance

- scope: `settings`, `search`
- type: `boolean`
- default: `true`


This option allows you to control the number of typos allowed in the result set:

* `true`: The typo tolerance is enabled and all matching hits are retrieved (default behavior).
* `false`: The typo tolerance is disabled. All results with typos will be hidden.
* `min`: Only keep results with the minimum number of typos. For example, if one result matches without typos, then all results with typos will be hidden.
* `strict`: Hits matching with 2 typos are not retrieved if there are some matching without typos.


#### allowTyposOnNumericTokens

- scope: `settings`, `search`
- type: `boolean`
- default: `true`


If set to false, disables typo tolerance on numeric tokens (numbers).

#### ignorePlurals

- scope: `settings`, `search`
- type: `boolean`
- default: `false`


If set to true, plural won't be considered as a typo. For example, car and cars, or foot and feet will be considered as equivalent. Defaults to false.

#### disableTypoToleranceOnAttributes

- scope: `settings`, `search`
- type: `string`
- default: `""`


List of attributes on which you want to disable typo tolerance
(must be a subset of the `attributesToIndex` index setting).

Attributes are separated with a comma such as `"name,address"`.
You can also use JSON string array encoding such as `encodeURIComponent("[\"name\",\"address\"]")`.

#### separatorsToIndex

- scope: `settings`
- type: `string`
- default: `""`


Specify the separators (punctuation characters) to index.

By default, separators are not indexed.

Use `+#` to be able to search Google+ or C#.



### Geo-Search



#### aroundLatitudeLongitude(float, float)

- scope: `search`
- type: `float, float`
- default: ``


Search for entries around a given latitude/longitude.

The maximum distance is automatically guessed depending of the density of the area
but you also manually specify the maximum distance in meters
with the `radius` parameter.

At indexing, you should specify the geo location of an object with the `_geoloc` attribute
in the form ` {"_geoloc":{"lat":48.853409, "lng":2.348800}} `.

#### aroundLatitudeLongitude(float, float, int, int)

- scope: `search`
- type: `float, float, int, int`
- default: ``


Search for entries around a given latitude/longitude with a given precision for ranking.

For example:
- if you set aroundPrecision=100, the distances will be considered by ranges of 100m.
- all distances 0 and 100m will be considered as identical for the "geo" ranking parameter.





#### aroundLatLngViaIP

- scope: `search`
- type: `string`
- default: `false`


Search for entries around a given latitude/longitude automatically computed from user IP address.

To enable it, use `aroundLatLngViaIP=true`.

You can specify the maximum distance in meters with the `aroundRadius` parameter
and the precision for ranking with `aroundPrecision`.

For example:
- if you set aroundPrecision=100,
two objects that are in the range 0-99m
will be considered as identical in the ranking for the "geo" ranking parameter (same for 100-199, 200-299, ... ranges).

When indexing, you should specify the geo location of an object with the `_geoloc` attribute
in the form `{"_geoloc":{"lat":48.853409, "lng":2.348800}}`.



#### insideBoundingBox

- scope: `search`
- type: `boolean`
- default: `false`


Search entries inside a given area defined by the two extreme points of a rectangle
(defined by 4 floats: p1Lat,p1Lng,p2Lat,p2Lng).
For example:
- `insideBoundingBox=47.3165,4.9665,47.3424,5.0201`


At indexing, you should specify geoloc of an object with the _geoloc attribute
(in the form `"_geoloc":{"lat":48.853409, "lng":2.348800}`
or `"_geoloc":[{"lat":48.853409, "lng":2.348800},{"lat":48.547456, "lng":2.972075}]`
if you have several geo-locations in your record).


You can use several bounding boxes (OR) by passing more than 4 values.
For example: instead of having 4 values you can pass 8 to search inside the UNION of two bounding boxes.

#### insidePolygon

- scope: `search`
- type: `string`
- default: ``


Search entries inside a given area defined by a set of points
(defined by a minimum of 6 floats: p1Lat,p1Lng,p2Lat,p2Lng,p3Lat,p3Long).

For example:
`InsidePolygon=47.3165,4.9665,47.3424,5.0201,47.32,4.98`).


At indexing, you should specify geoloc of an object with the _geoloc attribute
(in the form `"_geoloc":{"lat":48.853409, "lng":2.348800}`
or `"_geoloc":[{"lat":48.853409, "lng":2.348800},{"lat":48.547456, "lng":2.972075}]`
if you have several geo-locations in your record).


### Query Strategy

#### queryType

- scope: `settings`, `search`
- type: `enum`
- default: `'prefixLast'`


Selects how the query words are interpreted. It can be one of the following values:
* `prefixAll`:
All query words are interpreted as prefixes. This option is not recommended.
* `prefixLast`:
Only the last word is interpreted as a prefix (default behavior).
* `prefixNone`:
No query word is interpreted as a prefix. This option is not recommended.

#### removeWordsIfNoResults

- scope: `settings`, `search`
- type: `string`
- default: `'none'`


This option is used to select a strategy in order to avoid having an empty result page.
There are four different options:
- `lastWords`:
When a query does not return any results, the last word will be added as optional.
The process is repeated with n-1 word, n-2 word, ... until there are results.
- `firstWords`:
When a query does not return any results, the first word will be added as optional.
The process is repeated with second word, third word, ... until there are results.
- `allOptional`:
When a query does not return any results, a second trial will be made with all words as optional.
This is equivalent to transforming the AND operand between query terms to an OR operand.
- `none`:
No specific processing is done when a query does not return any results (default behavior).


#### advancedSyntax

- scope: `settings`, `search`
- type: `boolean`
- default: `false`


Enables the advanced query syntax.

This syntax allow to do two things:
* **Phrase query**: A phrase query defines a particular sequence of terms. A phrase query is built by Algolia's query parser for words surrounded by `"`. For example, `"search engine"` will retrieve records having `search` next to `engine` only. Typo tolerance is _disabled_ on phrase queries.
* **Prohibit operator**: The prohibit operator excludes records that contain the term after the `-` symbol. For example, `search -engine` will retrieve records containing `search` but not `engine`.


#### optionalWords

- scope: `settings`, `search`
- type: `array of strings`
- default: `[]`


A string that contains the comma separated list of words that should be considered as optional when found in the query.

#### removeStopWords

- scope: `settings`, `search`
- type: `boolean`, `array of strings`
- default: `false`


Remove stop words from the query **before** executing it. It can be:

- a **boolean**: enable or disable stop words for all 41 supported languages; or
- a **list of language ISO codes** (as a comma-separated string) for which stop words should be enabled.

In most use-cases, **we don’t recommend enabling this option**.

List of 41 supported languages with their associated iso code: Arabic=`ar`, Armenian=`hy`, Basque=`eu`, Bengali=`bn`, Brazilian=`pt-br`, Bulgarian=`bg`, Catalan=`ca`, Chinese=`zh`, Czech=`cs`, Danish=`da`, Dutch=`nl`, English=`en`, Finnish=`fi`, French=`fr`, Galician=`gl`, German=`de`, Greek=`el`, Hindi=`hi`, Hungarian=`hu`, Indonesian=`id`, Irish=`ga`, Italian=`it`, Japanese=`ja`, Korean=`ko`, Kurdish=`ku`, Latvian=`lv`, Lithuanian=`lt`, Marathi=`mr`, Norwegian=`no`, Persian (Farsi)=`fa`, Polish=`pl`, Portugese=`pt`, Romanian=`ro`, Russian=`ru`, Slovak=`sk`, Spanish=`es`, Swedish=`sv`, Thai=`th`, Turkish=`tr`, Ukranian=`uk`, Urdu=`ur`.

Stop words removal is applied on query words that are not interpreted as a prefix. The behavior depends of the `queryType` parameter:

* `queryType=prefixLast` means the last query word is a prefix and it won’t be considered for stop words removal
* `queryType=prefixNone` means no query word are prefix, stop words removal will be applied on all query words
* `queryType=prefixAll` means all query terms are prefix, stop words won’t be removed

This parameter is useful when you have a query in natural language like “what is a record?”.
In this case, before executing the query, we will remove “what”, “is” and “a” in order to just search for “record”.
This removal will remove false positive because of stop words, especially when combined with optional words.
For most use cases, it is better to not use this feature as people search by keywords on search engines.




#### disablePrefixOnAttributes

- scope: `settings`
- type: `array of strings`
- default: `[]`


List of attributes on which you want to disable prefix matching
(must be a subset of the `attributesToIndex` index setting).

This setting is useful on attributes that contain string that should not be matched as a prefix
(for example a product SKU).


#### disableExactOnAttributes

- scope: `settings`
- type: `array of strings`
- default: `[]`


List of attributes on which you want to disable the computation of `exact` criteria
(must be a subset of the `attributesToIndex` index setting).

#### exactOnSingleWordQuery

- scope: `settings`, `search`
- type: `string`
- default: `attribute`


This parameter control how the `exact` ranking criterion is computed when the query contains one word. There is three different values:
* `none`: no exact on single word query
* `word`: exact set to 1 if the query word is found in the record. The query word needs to have at least 3 chars and not be part of our stop words dictionary
* `attribute` (default): exact set to 1 if there is an attribute containing a string equals to the query

#### alternativesAsExact

- scope: `settings`, `search`
- type: `string`
- default: `['ignorePlurals', 'singleWordSynonym']`


Specify the list of approximation that should be considered as an exact match in the ranking formula:

* `ignorePlurals`: alternative words added by the ignorePlurals feature
* `singleWordSynonym`: single-word synonym (For example "NY" = "NYC")
* `multiWordsSynonym`: multiple-words synonym (For example "NY" = "New York")

### Advanced

#### attributeForDistinct

- scope: `settings`
- type: `string`
- default: `null`


The name of the attribute used for the `Distinct` feature.

This feature is similar to the SQL "distinct" keyword.
When enabled in queries with the `distinct=1` parameter,
all hits containing a duplicate value for this attribute are removed from the results.

For example, if the chosen attribute is `show_name` and several hits have the same value for `show_name`,
then only the first one is kept and the others are removed from the results.

To get a full understanding of how `Distinct` works,
you can have a look at our [guide on distinct](https://www.algolia.com/doc/search/distinct).

#### distinct

- scope: `settings`, `search`
- type: `integer`
- default: `0`


If set to 1,
enables the distinct feature, disabled by default, if the `attributeForDistinct` index setting is set.

This feature is similar to the SQL "distinct" keyword.
When enabled in a query with the `distinct=1` parameter,
all hits containing a duplicate value for the attributeForDistinct attribute are removed from results.

For example, if the chosen attribute is `show_name` and several hits have the same value for `show_name`,
then only the best one is kept and the others are removed.

To get a full understanding of how `Distinct` works,
you can have a look at our [guide on distinct](https://www.algolia.com/doc/search/distinct).


#### rankingInfo

- scope: `search`
- type: `boolean`
- default: `false`


If set to true,
the result hits will contain ranking information in the **_rankingInfo** attribute.

#### numericAttributesToIndex

- scope: `settings`
- type: `array of strings`
- default: ``


All numerical attributes are automatically indexed as numerical filters
(allowing filtering operations like `<` and `<=`).
If you don't need filtering on some of your numerical attributes,
you can specify this list to speed up the indexing.
<br/> If you only need to filter on a numeric value with the operator '=',
you can speed up the indexing by specifying the attribute with `equalOnly(AttributeName)`.
The other operators will be disabled.

#### allowCompressionOfIntegerArray

- scope: `settings`
- type: `boolean`
- default: `false`


Allows compression of big integer arrays.

In data-intensive use-cases,
we recommended enabling this feature and then storing the list of user IDs or rights as an integer array.
When enabled, the integer array is reordered to reach a better compression ratio.

#### numericFilters (deprecated)

- scope: `search`
- type: `array of strings`
- default: `[]`


A string that contains the comma separated list of numeric filters you want to apply.
The filter syntax is `attributeName` followed by `operand` followed by `value`.
Supported operands are `<`, `<=`, `=`, `>` and `>=`.

You can easily perform range queries via the `:` operator.
This is equivalent to combining a `>=` and `<=` operand.

For example, `numericFilters=price:10 to 1000`.

You can also mix OR and AND operators.
The OR operator is defined with a parenthesis syntax.

For example, `(code=1 AND (price:[0-100] OR price:[1000-2000]))`
translates to `encodeURIComponent("code=1,(price:0 to 100,price:1000 to 2000)")`.

You can also use a string array encoding (for example `numericFilters: ["price>100","price<1000"]`).

#### tagFilters (deprecated)

- scope: `search`
- type: `string`
- default: `""`


Filter the query by a set of tags.

You can AND tags by separating them with commas.
To OR tags, you must add parentheses.

For example, `tagFilters=tag1,(tag2,tag3)` means *tag1 AND (tag2 OR tag3)*.

You can also use a string array encoding.

For example, `tagFilters: ["tag1",["tag2","tag3"]]` means *tag1 AND (tag2 OR tag3)*.

Negations are supported via the `-` operator, prefixing the value.

For example: `tagFilters=tag1,-tag2`.

At indexing, tags should be added in the **_tags** attribute of objects.

For example `{"_tags":["tag1","tag2"]}`.

#### facetFilters (deprecated)

- scope: `search`
- type: `string`
- default: `""`


Filter the query with a list of facets. Facets are separated by commas and is encoded as `attributeName:value`.
To OR facets, you must add parentheses.

For example: `facetFilters=(category:Book,category:Movie),author:John%20Doe`.

You can also use a string array encoding.

For example, `[["category:Book","category:Movie"],"author:John%20Doe"]`.

#### analytics

- scope: `settings`, `search`
- type: `string`
- default: `['ignorePlurals', 'singleWordSynonym']`


If set to false, this query will not be taken into account in the analytics feature.

#### placeholders

- scope: `settings`
- type: `hash of array of words`
- default: ``


This is an advanced use-case to define a token substitutable by a list of words
without having the original token searchable.

It is defined by a hash associating placeholders to lists of substitutable words.

For example, `"placeholders": { "<streetnumber>": ["1", "2", "3", ..., "9999"]}`
would allow it to be able to match all street numbers. We use the `< >` tag syntax
to define placeholders in an attribute.

For example:
* Push a record with the placeholder:
`{ "name" : "Apple Store", "address" : "&lt;streetnumber&gt; Opera street, Paris" }`.
* Configure the placeholder in your index settings:
`"placeholders": { "<streetnumber>" : ["1", "2", "3", "4", "5", ... ], ... }`.

#### altCorrections

- scope: `settings`
- type: `array of objects`
- default: `[]`


Specify alternative corrections that you want to consider.

Each alternative correction is described by an object containing three attributes:
* **word**: The word to correct.
* **correction**: The corrected word.
* **nbTypos** The number of typos (1 or 2) that will be considered for the ranking algorithm (1 typo is better than 2 typos).

For example:

`"altCorrections": [ { "word" : "foot", "correction": "feet", "nbTypos": 1 }, { "word": "feet", "correction": "foot", "nbTypos": 1 } ]`.


## Manage Indices

### Create an index

To create an index, you need to perform any indexing operation like:
- set settings
- add object

### List indices - `listIndices`

You can list all your indices along with their associated information (number of entries, disk size, etc.) with the `` method:

```java
//Sync & Async version

client.listIndices();
```




### Delete index - `delete`

You can delete an index using its name:

```java
//Sync version
Index contacts = client.initIndex("contacts");
contacts.delete();
```

```java
//Async version
AsyncIndex contacts = client.initIndex("contacts");
contacts.delete();
```


### Clear index - `clear`
You can delete the index contents without removing settings and index specific API keys by using the clearIndex command:

```java
//Sync & Async version
index.clear();
```


### Copy index - `clear`

You can easily copy or rename an existing index using the `copy` and `move` commands.
**Note**: Move and copy commands overwrite the destination index.

```java
//Sync version

Index myIndex = client.initIndex("MyIndex");

// Rename MyIndex in MyIndexNewName
myIndex.moveTo("MyIndexNewName");

// Copy MyIndex in MyIndexCopy
myIndex.copyTo("MyIndexCopy");
```

```java
//Async version
AsyncIndex myIndex = client.initIndex("MyIndex");

// Rename MyIndex in MyIndexNewName
myIndex.moveTo("MyIndexNewName");

// Copy MyIndex in MyIndexCopy
myIndex.copyTo("MyIndexCopy");
```


### Move index - `moveTo`

The move command is particularly useful if you want to update a big index atomically from one version to another. For example, if you recreate your index `MyIndex` each night from a database by batch, you only need to:
 1. Import your database into a new index using [batches](#batch-writes). Let's call this new index `MyNewIndex`.
 1. Rename `MyNewIndex` to `MyIndex` using the move command. This will automatically override the old index and new queries will be served on the new one.

```java
//For the sync version
Index myNewIndex = client.initIndex("MyNewIndex");

// Rename MyNewIndex in MyIndex (and overwrite it)
myNewIndex.moveTo("MyIndex");
```

```java
//For the async version
AsyncIndex myNewIndex = client.initIndex("MyNewIndex");

// Rename MyNewIndex in MyIndex (and overwrite it)
myNewIndex.moveTo("MyIndex");
```






## Api Keys

The **admin** API key provides full control of all your indices. *The admin API key should always be kept secure; do NOT use it from outside your back-end.*

You can also generate user API keys to control security.
These API keys can be restricted to a set of operations or/and restricted to a given index.

### Generate key - `listIndices`

You may have a single index containing **per user** data. In that case, all records should be tagged with their associated `user_id` in order to add a `tagFilters=user_42` filter at query time to retrieve only what a user has access to. If you're using the [JavaScript client](http://github.com/algolia/algoliasearch-client-js), it will result in a security breach since the user is able to modify the `tagFilters` you've set by modifying the code from the browser. To keep using the JavaScript client (recommended for optimal latency) and target secured records, you can generate a secured API key from your backend:

```java
//Sync & Async version

// generate a public API key for user 42. Here, records are tagged with:
//  - 'user_XXXX' if they are visible by user XXXX
String publicKey = client.generateSecuredApiKey("YourSearchOnlyApiKey", new Query().setFilters("_tags:user_42"));
```

This public API key can then be used in your JavaScript code as follow:

```js
var client = algoliasearch('YourApplicationID', '<%= public_api_key %>');

var index = client.initIndex('indexName')

index.search('something', function(err, content) {
  if (err) {
    console.error(err);
    return;
  }

  console.log(content);
});
```

You can mix rate limits and secured API keys by setting a `userToken` query parameter at API key generation time. When set, a unique user will be identified by her `IP + user_token` instead of only by her `IP`. This allows you to restrict a single user to performing a maximum of `N` API calls per hour, even if she shares her `IP` with another user.

```java
//Sync & Async version

// generate a public API key for user 42. Here, records are tagged with:
//  - 'user_XXXX' if they are visible by user XXXX
String publicKey = client.generateSecuredApiKey("YourSearchOnlyApiKey", new Query().setFilters("_tags:user_42").setUserToken("42"));
```

This public API key can then be used in your JavaScript code as follow:

```js
var client = algoliasearch('YourApplicationID', '<%= public_api_key %>');

var index = client.initIndex('indexName')

index.search('another query', function(err, content) {
  if (err) {
    console.error(err);
    return;
  }

  console.log(content);
});
```





## Synonyms

### Save synonym - `saveSynonym`

This method saves a single synonym record into the index.

In this example, we specify true to forward the creation to slave indices.
By default the behavior is to save only on the specified index.

```java
index.saveSynonym("a-unique-identifier", new Synonym()
           .setSynonyms(Arrays.asList("car", "vehicle", "auto")), true);
```

### Batch synonyms - `batchSynonyms`

Use the batch method to create a large number of synonyms at once,
forward them to slave indices if desired,
and optionally replace all existing synonyms
on the index with the content of the batch using the replaceExistingSynonyms parameter.

You should always use replaceExistingSynonyms to atomically replace all synonyms
on a production index. This is the only way to ensure the index always
has a full list of synonyms to use during the indexing of the new list.

```java
// Batch synonyms, with slave forwarding and atomic replacement of existing synonyms
index.batchSynonyms(Arrays.asList(
      new Synonym()
           .setObjectID("a-unique-identifier")
           .setSynonyms(Arrays.asList("car", "vehicle", "auto")),
      new Synonym()
           .setObjectID("another-unique-identifier")
           .setSynonyms(Arrays.asList("street", "st"))
), true);
```

### Editing Synonyms

Updating the value of a specific synonym record is the same as creating one.
Make sure you specify the same objectID used to create the record and the synonyms
will be updated.
When updating multiple synonyms in a batch call (but not all synonyms),
make sure you set replaceExistingSynonyms to false (or leave it out,
false is the default value).
Otherwise, the entire synonym list will be replaced only partially with the records
in the batch update.

### Delete Synonyms - `delete_synonyms`

Use the normal index delete method to delete synonyms,
specifying the objectID of the synonym record you want to delete.
Forward the deletion to slave indices by setting the forwardToSlaves parameter to true.

```java
// Delete and forward to slaves
index.deleteSynonym("a-unique-identifier", true);
```

### Clear all synonyms - `clearSynonyms`

This is a convenience method to delete all synonyms at once.
It should not be used on a production index to then push a new list of synonyms:
there would be a short period of time during which the index would have no synonyms
at all.

To atomically replace all synonyms of an index,
use the batch method with the replaceExistingSynonyms parameter set to true.

```java
// Clear synonyms and forward to slaves
index.clearSynonyms(true);
```

### Get synonym - `getSynonym`

Search for synonym records by their objectID or by the text they contain.
Both methods are covered here.

```java
Optional<AbstractSynonym> synonym = index.getSynonym("a-unique-identifier");
```

### Search synonyms - `searchSynonyms`

Search for synonym records similar to how you’d search normally.

Accepted search parameters:
- query: the actual search query to find synonyms. Use an empty query to browse all the synonyms of an index.
- type: restrict the search to a specific type of synonym. Use an empty string to search all types (default behavior). Multiple types can be specified using a comma-separated list or an array.
- page: the page to fetch when browsing through several pages of results. This value is zero-based.
hitsPerPage: the number of synonyms to return for each call. The default value is 100.

```java
// Searching for "street" in synonyms and one-way synonyms; fetch the second page with 10 hits per page
SearchSynonymResult results = index.searchSynonyms(new SynonymQuery("street").setTypes(Arrays.asList("synonym", "one_way")).setPage(1).setHitsPerPage(10));
```



## Advanced

### Custom batch - `batch`

You may want to perform multiple operations with one API call to reduce latency.
We expose four methods to perform batch operations:
 * Add objects - `addObjects`: Add an array of objects using automatic `objectID` assignment.
 * Update objects - `saveObjects`: Add or update an array of objects that contains an `objectID` attribute.
 * Delete objects - `deleteObjects`: Delete an array of objectIDs.
 * Partial update - `PartialUpdateObjects`: Partially update an array of objects that contain an `objectID` attribute (only specified attributes will be updated).

Example using automatic `objectID` assignment:
```java
//Sync & Async version

index.addObjects(Arrays.asList(
  new Contact().setFirstName("Jimmie").setLastName("Barninger"),
  new Contact().setFirstName("Warren").setLastName("Speach")
));
```

Example with user defined `objectID` (add or update):
```java
//Sync & Async version
index.saveObjects(Arrays.asList(
  new Contact().setFirstName("Jimmie").setLastName("Barninger").setObjectID("SFO"),
  new Contact().setFirstName("Warren").setLastName("Speach").setIbjectID("LA")
));
```

Example that deletes a set of records:
```java
//Sync & Async version
index.deleteObjects(Arrays.asList("myID1", "myID2"));
```

Example that updates only the `firstname` attribute:
```java
//Sync & async version

List<JSONObject> array = Arrays.asList(
	new Contact().setCity("San Francisco").setObjectID("MyID"),
	new Contact().setCity("Paris").setObjectID("MyID2")
);

index.partialUpdateObjects(array);
```



If you have one index per user, you may want to perform a batch operations across severals indexes.
We expose a method to perform this type of batch:
```java
//Sync version
Index<Contact> index1 = client.initIndex("index1", Contact.class);

//BatchOperations accepts an Index object or a String
client.batch(Arrays.asList(
	new BatchAddObjectOperation<>(index1, new Contact().setFirstName("Jimmie").setLastName("Barninger")),
	new BatchAddObjectOperation<>("index2", new Contact().setFirstName("Warren").setLastName("Speach")),
));
```

```java
//ASync version
AsyncIndex<Contact> index1 = client.initIndex("index1", Contact.class);

//BatchOperations accepts an Index object or a String
client.batch(Arrays.asList(
	new BatchAddObjectOperation<>(index1, new Contact().setFirstName("Jimmie").setLastName("Barninger")),
	new BatchAddObjectOperation<>("index2", new Contact().setFirstName("Warren").setLastName("Speach")),
));
```

The attribute **action** can have these values:
- addObject
- updateObject
- partialUpdateObject
- partialUpdateObjectNoCreate
- deleteObject

### Backup / Export an index - `browse`

The `search` method cannot return more than 1,000 results. If you need to
retrieve all the content of your index (for backup, SEO purposes or for running
a script on it), you should use the `browse` method instead. This method lets
you retrieve objects beyond the 1,000 limit.

This method is optimized for speed. To make it fast, distinct, typo-tolerance,
word proximity, geo distance and number of matched words are disabled. Results
are still returned ranked by attributes and custom ranking.


It will return a `cursor` alongside your data, that you can then use to retrieve
the next chunk of your records.

You can specify custom parameters (like `page` or `hitsPerPage`) on your first
`browse` call, and these parameters will then be included in the `cursor`. Note
that it is not possible to access records beyond the 1,000th on the first call.

Example:

This method does not exists in the Async version

```java
// Iterate with a filter over the index
IndexIterable<Contact> it = index.browse(new Query("text").setFilters("i<42"));

// Iterate with a stream
it.stream.map(o -> {});

// Retrieve the next cursor from the browse method
IndexIterable<Contact> it  = index.browseFrom(new Query("text").setFilters("i<42"), null);
System.out.println(it.getCursor());
```




### List api keys - `listApiKeys`

To list existing keys, you can use:

```java
//Sync version

// Lists global API Keys
List<ApiKey> global = client.listKeys();

// Lists API Keys that can access only to this index
List<ApiKey> indexKeys = index.listKeys();
```

```java
//Async version

// Lists global API Keys
CompletableFuture<List<ApiKey>> global = client.listKeys();

// Lists API Keys that can access only to this index
CompletableFuture<List<ApiKey>> indexKeys = index.listKeys();
```

Each key is defined by a set of permissions that specify the authorized actions. The different permissions are:
 * **search**: Allowed to search.
 * **browse**: Allowed to retrieve all index contents via the browse API.
 * **addObject**: Allowed to add/update an object in the index.
 * **deleteObject**: Allowed to delete an existing object.
 * **deleteIndex**: Allowed to delete index content.
 * **settings**: allows to get index settings.
 * **editSettings**: Allowed to change index settings.
 * **analytics**: Allowed to retrieve analytics through the analytics API.
 * **listIndexes**: Allowed to list all accessible indexes.

### Add user key - `addUserKey`

To create API keys:

```java
//Sync version

// Creates a new global API key that can only perform search actions
ApiKey apiKey = new ApiKey().setAcl(Arrays.asList("search")));
CreateUpdateKey res = client.addKey(apiKey);
System.out.println("Key: " + apiKey.getKey());

// Creates a new API key that can only perform search action on this index
ApiKey apiKey = new ApiKey().setAcl(Arrays.asList("search")));
CreateUpdateKey res = index.addKey(apiKey);
System.out.println("Key: " + apiKey.getKey());
```

```java
//Async version

// Creates a new global API key that can only perform search actions
ApiKey apiKey = new ApiKey().setAcl(Arrays.asList("search")));
CompletableFuture<CreateUpdateKey> res = client.addKey(apiKey);
System.out.println("Key: " + apiKey.getKey().get());

// Creates a new API key that can only perform search action on this index
ApiKey apiKey = new ApiKey().setAcl(Arrays.asList("search")));
CompletableFuture<CreateUpdateKey> res = index.addKey(apiKey);
System.out.println("Key: " + apiKey.getKey().get());
```

You can also create an API Key with advanced settings:

<table><tbody>
  
    <tr>
      <td valign='top'>
        <div class='client-readme-param-container'>
          <div class='client-readme-param-container-inner'>
            <div class='client-readme-param-name'><code>validity</code></div>
            
          </div>
        </div>
      </td>
      <td class='client-readme-param-content'>
        <p>Add a validity period. The key will be valid for a specific period of time (in seconds).</p>

      </td>
    </tr>
    
  
    <tr>
      <td valign='top'>
        <div class='client-readme-param-container'>
          <div class='client-readme-param-container-inner'>
            <div class='client-readme-param-name'><code>maxQueriesPerIPPerHour</code></div>
            
          </div>
        </div>
      </td>
      <td class='client-readme-param-content'>
        <p>Specify the maximum number of API calls allowed from an IP address per hour. Each time an API call is performed with this key, a check is performed. If the IP at the source of the call did more than this number of calls in the last hour, a 403 code is returned. Defaults to 0 (no rate limit). This parameter can be used to protect you from attempts at retrieving your entire index contents by massively querying the index.</p>

<p>Note: If you are sending the query through your servers, you must use the <code>enableRateLimitForward(&quot;TheAdminAPIKey&quot;, &quot;EndUserIP&quot;, &quot;APIKeyWithRateLimit&quot;)</code> function to enable rate-limit.</p>

      </td>
    </tr>
    
  
    <tr>
      <td valign='top'>
        <div class='client-readme-param-container'>
          <div class='client-readme-param-container-inner'>
            <div class='client-readme-param-name'><code>maxHitsPerQuery</code></div>
            
          </div>
        </div>
      </td>
      <td class='client-readme-param-content'>
        <p>Specify the maximum number of hits this API key can retrieve in one call. Defaults to 0 (unlimited). This parameter can be used to protect you from attempts at retrieving your entire index contents by massively querying the index.</p>

      </td>
    </tr>
    
  
    <tr>
      <td valign='top'>
        <div class='client-readme-param-container'>
          <div class='client-readme-param-container-inner'>
            <div class='client-readme-param-name'><code>indexes</code></div>
            
          </div>
        </div>
      </td>
      <td class='client-readme-param-content'>
        <p>Specify the list of targeted indices. You can target all indices starting with a prefix or ending with a suffix using the &#39;*&#39; character. For example, &quot;dev_*&quot; matches all indices starting with &quot;dev_&quot; and &quot;*_dev&quot; matches all indices ending with &quot;_dev&quot;. Defaults to all indices if empty or blank.</p>

      </td>
    </tr>
    
  
    <tr>
      <td valign='top'>
        <div class='client-readme-param-container'>
          <div class='client-readme-param-container-inner'>
            <div class='client-readme-param-name'><code>referers</code></div>
            
          </div>
        </div>
      </td>
      <td class='client-readme-param-content'>
        <p>Specify the list of referers. You can target all referers starting with a prefix or ending with a suffix using the &#39;*&#39; character. For example, &quot;algolia.com/*&quot; matches all referers starting with &quot;algolia.com/&quot; and &quot;*.algolia.com&quot; matches all referers ending with &quot;.algolia.com&quot;. Defaults to all referers if empty or blank.</p>

      </td>
    </tr>
    
  
    <tr>
      <td valign='top'>
        <div class='client-readme-param-container'>
          <div class='client-readme-param-container-inner'>
            <div class='client-readme-param-name'><code>queryParameters</code></div>
            
          </div>
        </div>
      </td>
      <td class='client-readme-param-content'>
        <p>Specify the list of query parameters. You can force the query parameters for a query using the url string format (param1=X&amp;param2=Y...).</p>

      </td>
    </tr>
    
  
    <tr>
      <td valign='top'>
        <div class='client-readme-param-container'>
          <div class='client-readme-param-container-inner'>
            <div class='client-readme-param-name'><code>description</code></div>
            
          </div>
        </div>
      </td>
      <td class='client-readme-param-content'>
        <p>Specify a description to describe where the key is used.</p>

      </td>
    </tr>
    

</tbody></table>

```java
//Sync version

// Creates a new global API key that is valid for 300 seconds
ApiKey apiKey = new ApiKey();
	.setAcl(Arrays.asList("search")).
	.setMaxHitsPerQuery(20).
	.setMaxQueriesPerIPPerHour(100).
	.setValidity(300).
	.setIndexes(Arrays.asList("myIndex")).
	.setReferers(Arrays.asList("algolia.com/*")).
	.setQueryParameters("typoTolerance=strict&ignorePlurals=false").
	.setDescription("Limited search only API key for algolia.com");

CreateUpdateKey res = client.addKey(apiKey);
System.out.println("Key: " + res.getKey());
```

```java
//Async version

// Creates a new global API key that is valid for 300 seconds
ApiKey apiKey = new ApiKey();
	.setAcl(Arrays.asList("search")).
	.setMaxHitsPerQuery(20).
	.setMaxQueriesPerIPPerHour(100).
	.setValidity(300).
	.setIndexes(Arrays.asList("myIndex")).
	.setReferers(Arrays.asList("algolia.com/*")).
	.setQueryParameters("typoTolerance=strict&ignorePlurals=false").
	.setDescription("Limited search only API key for algolia.com");

CompletableFuture<CreateUpdateKey> res = client.addKey(apiKey);
System.out.println("Key: " + res.get().getKey());
```

### Update user key - `updateUserKey`

To update the permissions of an existing key:
```java
//Sync version

// Creates a new global API key that is valid for 300 seconds
CreateUpdateKey res = client.updateUserKey("myAPIKey", new ApiKey().setAcl(Arrays.asList("search")).setValidity(300));

// Update a index specific API key valid for 300 seconds, with a rate limit of 100 calls per hour per IP and a maximum of 20 hits
CreateUpdateKey res = index.updateUserKey("myAPIKey", new ApiKey()
	.setAcl(Arrays.asList("search"))
	.setValidity(300)
	.setMaxQueriesPerIPPerHour(200)
	.setMaxHitsPerQuery(20)
);
```

```java
//Async version

// Creates a new global API key that is valid for 300 seconds
CompletableFuture<CreateUpdateKey> res = client.updateUserKey("myAPIKey", new ApiKey().setAcl(Arrays.asList("search")).setValidity(300));

// Update a index specific API key valid for 300 seconds, with a rate limit of 100 calls per hour per IP and a maximum of 20 hits
CompletableFuture<CreateUpdateKey> res = index.updateUserKey("myAPIKey", new ApiKey()
	.setAcl(Arrays.asList("search"))
	.setValidity(300)
	.setMaxQueriesPerIPPerHour(200)
	.setMaxHitsPerQuery(20)
);
```
To get the permissions of a given key:
```java
//Sync version

// Gets the rights of a global key
Optional<ApiKey> apiKey1 = client.getKey("f420238212c54dcfad07ea0aa6d5c45f");

// Gets the rights of an index specific key
Optional<ApiKey> apiKey2 = index.getKey("71671c38001bf3ac857bc82052485107");
```

```java
//Async version

// Gets the rights of a global key
Optional<ApiKey> apiKey1 = client.getKey("f420238212c54dcfad07ea0aa6d5c45f").get();

// Gets the rights of an index specific key
Optional<ApiKey> apiKey2 = index.getKey("71671c38001bf3ac857bc82052485107").get();
```

### Delete user key - `listIndices`
To delete an existing key:
```java
//Sync & Async version

// Deletes a global key
client.deleteKey("f420238212c54dcfad07ea0aa6d5c45f");

// Deletes an index specific key
index.deleteKey("71671c38001bf3ac857bc82052485107");
```

### Get key permissions - `getUserKeyACL`



To get the permissions of a given key:
```java
//Sync version

// Gets the rights of a global key
Optional<ApiKey> apiKey1 = client.getKey("f420238212c54dcfad07ea0aa6d5c45f");

// Gets the rights of an index specific key
Optional<ApiKey> apiKey2 = index.getKey("71671c38001bf3ac857bc82052485107");
```

```java
//Async version

// Gets the rights of a global key
Optional<ApiKey> apiKey1 = client.getKey("f420238212c54dcfad07ea0aa6d5c45f").get();

// Gets the rights of an index specific key
Optional<ApiKey> apiKey2 = index.getKey("71671c38001bf3ac857bc82052485107").get();
```

### Multiple queries - `multipleQueries`

You can send multiple queries with a single API call using a batch of queries:

```java
//Sync version

// perform 3 queries in a single API call:
//  - 1st query targets index `categories`
//  - 2nd and 3rd queries target index `products`

List<IndexQuery> queries = Arrays.asList(
	new IndexQuery("categories", new Query(myQueryString).setHitsPerPage(3)),
	new IndexQuery("products", new Query(myQueryString).setHitsPerPage(3).setFilters("_tags:promotion")),
	new IndexQuery("products", new Query(myQueryString).setHitsPerPage(10))
);

MultiQueriesResult search = client.multipleQueries(queries);
```

```java
//Async version

// perform 3 queries in a single API call:
//  - 1st query targets index `categories`
//  - 2nd and 3rd queries target index `products`

CompletableFuture<List<IndexQuery>> queries = Arrays.asList(
	new IndexQuery("categories", new Query(myQueryString).setHitsPerPage(3)),
	new IndexQuery("products", new Query(myQueryString).setHitsPerPage(3).setFilters("_tags:promotion")),
	new IndexQuery("products", new Query(myQueryString).setHitsPerPage(10))
);

CompletableFuture<MultiQueriesResult> search = client.multipleQueries(queries);
```

The resulting JSON answer contains a ```results``` array storing the underlying queries answers. The answers order is the same than the requests order.

You can specify a `strategy` parameter to optimize your multiple queries:
- `none`: Execute the sequence of queries until the end.
- `stopIfEnoughMatches`: Execute the sequence of queries until the number of hits is reached by the sum of hits.



### Get Logs - `getLogs`

You can retrieve the latest logs via this API. Each log entry contains:
 * Timestamp in ISO-8601 format
 * Client IP
 * Request Headers (API Key is obfuscated)
 * Request URL
 * Request method
 * Request body
 * Answer HTTP code
 * Answer body
 * SHA1 ID of entry

You can retrieve the logs of your last 1,000 API calls and browse them using the offset/length parameters:

<table><tbody>
  
    <tr>
      <td valign='top'>
        <div class='client-readme-param-container'>
          <div class='client-readme-param-container-inner'>
            <div class='client-readme-param-name'><code>offset</code></div>
            
          </div>
        </div>
      </td>
      <td class='client-readme-param-content'>
        <p>Specify the first entry to retrieve (0-based, 0 is the most recent log entry). Defaults to 0.</p>

      </td>
    </tr>
    
  
    <tr>
      <td valign='top'>
        <div class='client-readme-param-container'>
          <div class='client-readme-param-container-inner'>
            <div class='client-readme-param-name'><code>length</code></div>
            
          </div>
        </div>
      </td>
      <td class='client-readme-param-content'>
        <p>Specify the maximum number of entries to retrieve starting at the offset. Defaults to 10. Maximum allowed value: 1,000.</p>

      </td>
    </tr>
    
  
    <tr>
      <td valign='top'>
        <div class='client-readme-param-container'>
          <div class='client-readme-param-container-inner'>
            <div class='client-readme-param-name'><code>onlyErrors</code></div>
            
          </div>
        </div>
      </td>
      <td class='client-readme-param-content'>
        <p>Retrieve only logs with an HTTP code different than 200 or 201. (deprecated)</p>

      </td>
    </tr>
    
  
    <tr>
      <td valign='top'>
        <div class='client-readme-param-container'>
          <div class='client-readme-param-container-inner'>
            <div class='client-readme-param-name'><code>type</code></div>
            
          </div>
        </div>
      </td>
      <td class='client-readme-param-content'>
        <p>Specify the type of logs to retrieve:</p>

<ul>
<li><code>query</code>: Retrieve only the queries.</li>
<li><code>build</code>: Retrieve only the build operations.</li>
<li><code>error</code>: Retrieve only the errors (same as <code>onlyErrors</code> parameters).</li>
</ul>

      </td>
    </tr>
    
</tbody></table>

```java
//Sync & Async version

// Get last 10 log entries
client.getLogs();

// Get last 100 log entries, of type build
client.getLogs(0, 100, LogType.LOG_BUILD);
```


### REST API

We've developed API clients for the most common programming languages and platforms.
These clients are advanced wrappers on top of our REST API itself and have been made
in order to help you integrating the service within your apps:
for both indexing and search.

Everything that can be done using the REST API can be done using those clients.

The REST API lets your interact directly with Algolia platforms from anything that can send an HTTP request
[Go to the REST API doc](https://algolia.com/doc/rest)



