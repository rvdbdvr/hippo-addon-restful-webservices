# Hippo RESTful web services add-on

This project is a clone of Jeroen Reijns project (see [original project](https://github.com/jreijn/hippo-addon-restful-webservices)). 

This project has a slightly different purpose and therefore will probably continue as a separate project.
This purpose is primarily to provide a way to extract (old) Hippo CMS repository content through a REST API.

To support this it differs in the following ways from the original:

+ it is meant to work with older versions of Hippo/JCR. As a result some features of Jeroens solution had to be disabled.
+ it is meant to be deployed standalone: in a .war separate from the CMS, possibly running in a separate container.
+ it is meant to understand the way Hippo uses the JCR (without knowing about document types). For this an additional API is provided (/content).

The project is in an proof-of-concept state. This means that all sorts of things (like the Maven parameters) still need to be fugured out.
It works somewhat... 

## Current Available APIs

Version 0.0.1-SNAPSHOT
+ Info ```/```
+ Nodes API ```/nodes```
+ Properties API ```/properties```
+ Query API ```/_query```
+ System API ```/_system```
+ Statistics API ```/_stats```
+ Users API ```/users```
+ Groups API ```/groups```
+ Content API ```/content```

These are almost the same as the original project except for the last one: content. 
The content API understands Hippo's content model and allows you to retrieve the content as folders and documents (including images and assets). 

If you would like to know more about how to use the other REST endpoints go ahead and read the [API Reference section](https://github.com/jreijn/hippo-addon-restful-webservices/wiki/API-Reference).
Some functionality (like paging query results) had to be disabled to be able to support older Hippo repositories.

## <a name="source"></a>Building from source

Build project yourself using [Maven](http://maven.apache.org): 

``` console
$ mvn install
```

Then build a war from the result using the [war project](https://github.com/rvdbdvr/hippo-addon-restful-webservices-war).

For now it is necessary to name and deploy your war "rest" as there is a hard reference to it in the code.
This should be changed in the future, of course.


## Issues, Questions or improvements

If you find any problems, have a question or see a possibility to improve the add-on please browse the [project issues](https://github.com/rvdbdvr/hippo-addon-restful-webservices/issues).

## Contributions

Pull requests are, of course, very welcome! Head over to the [open issues](https://github.com/rvdbdvr/hippo-addon-restful-webservices/issues) to see what we need help with. Make sure you let us know if you intend to work on something. Also, check out the [milestones](https://github.com/rvdbdvr/hippo-addon-restful-webservices/issues/milestones) to see what is planned for future releases.
