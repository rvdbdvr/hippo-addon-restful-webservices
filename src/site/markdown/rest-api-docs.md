#Hippo CMS RESTful API reference
This is the Hippo CMS REST API reference

License: [Apache 2.0](http://www.apache.org/licenses/LICENSE-2.0.html)



BasePath: http://host:port/cms/rest/api

Api Version: 0.3

## APIs
### /nodes
#### Overview
JCR node API

#### **GET** `/nodes/{path:.*}`
##### getNodeByPath 

Get a node
Returns a node by UUID or from the specified path

###### URL
http://host:port/cms/rest/api/nodes/{path:.*}
###### Parameters
- path

<table border="1">
    <tr>
        <th>Parameter</th>
        <th>Required</th>
        <th>Description</th>
        <th>Data Type</th>
    </tr>
    <tr>
        <th>path</th>
        <td>true</td>
        <td>Path of the node to retrieve</td>
        <td>string</td>
    </tr>
</table>
- query

<table border="1">
    <tr>
        <th>Parameter</th>
        <th>Required</th>
        <th>Description</th>
        <th>Data Type</th>
    </tr>
    <tr>
        <th>uuid</th>
        <td>false</td>
        <td>UUID of node to retrieve (overrides path)</td>
        <td>string</td>
    </tr>
    <tr>
        <th>depth</th>
        <td>false</td>
        <td>Depth of the retrieval</td>
        <td>int</td>
    </tr>
</table>

###### Response
[node](#node)


###### Errors
| Status Code | Reason      | Response Model |
|-------------|-------------|----------------|
| 200    | OK | [node](#node) |
| 401    | Unauthorized | - |
| 404    | Node not found | - |
| 500    | Error occurred | - |


- - -
#### **POST** `/nodes/{path:.*}`
##### createNodeByPath 

Creates a new sub node
Adds a node and it's properties as a child of the provided location

###### URL
http://host:port/cms/rest/api/nodes/{path:.*}
###### Parameters
- path

<table border="1">
    <tr>
        <th>Parameter</th>
        <th>Required</th>
        <th>Description</th>
        <th>Data Type</th>
    </tr>
    <tr>
        <th>path</th>
        <td>true</td>
        <td>Path of the node to which to add the new node e.g. &#39;/content/documents/&#39;</td>
        <td>string</td>
    </tr>
</table>
- body

<table border="1">
    <tr>
        <th>Parameter</th>
        <th>Required</th>
        <th>Description</th>
        <th>Data Type</th>
    </tr>
    <tr>
        <th>body</th>
        <td>false</td>
        <td></td>
        <td><a href="#node">node</a></td>
    </tr>
</table>

###### Response
[](#)


###### Errors
| Status Code | Reason      | Response Model |
|-------------|-------------|----------------|
| 200    | OK | - |
| 201    | Created | - |
| 400    | Request not understood due to errors or malformed syntax | - |
| 401    | Unauthorized | - |
| 404    | Node not found | - |
| 403    | Access denied | - |
| 500    | Error occurred | - |


- - -
#### **PUT** `/nodes/{path:.*}`
##### updateNodeByPath 

Update a node
To update a node you need to provide the entire entity. The entity will be replaced with the  provided data. Be careful in case you want to update entire node structures, because the UUIDs will be  regenerated

###### URL
http://host:port/cms/rest/api/nodes/{path:.*}
###### Parameters
- path

<table border="1">
    <tr>
        <th>Parameter</th>
        <th>Required</th>
        <th>Description</th>
        <th>Data Type</th>
    </tr>
    <tr>
        <th>path</th>
        <td>true</td>
        <td>Path of the node to update. &#39;/content/documents/&#39;</td>
        <td>string</td>
    </tr>
</table>
- body

<table border="1">
    <tr>
        <th>Parameter</th>
        <th>Required</th>
        <th>Description</th>
        <th>Data Type</th>
    </tr>
    <tr>
        <th>body</th>
        <td>false</td>
        <td></td>
        <td><a href="#node">node</a></td>
    </tr>
</table>

###### Response
[](#)


###### Errors
| Status Code | Reason      | Response Model |
|-------------|-------------|----------------|
| 200    | OK | - |
| 204    | Updated | - |
| 400    | Request not understood due to errors or malformed syntax | - |
| 401    | Unauthorized | - |
| 404    | Node not found | - |
| 403    | Access denied | - |
| 500    | Error occurred | - |


- - -
#### **DELETE** `/nodes/{path:.*}`
##### deleteNodeByPath 

Delete a node
Deletes a node (and child-nodes)

###### URL
http://host:port/cms/rest/api/nodes/{path:.*}
###### Parameters
- path

<table border="1">
    <tr>
        <th>Parameter</th>
        <th>Required</th>
        <th>Description</th>
        <th>Data Type</th>
    </tr>
    <tr>
        <th>path</th>
        <td>true</td>
        <td>Path of the node to delete e.g. &#39;/content/documents/&#39;</td>
        <td>string</td>
    </tr>
</table>

###### Response
[](#)


###### Errors
| Status Code | Reason      | Response Model |
|-------------|-------------|----------------|
| 204    | Deleted | - |
| 404    | Node not found | - |
| 500    | Error occurred | - |


- - -
### /properties
#### Overview
JCR property API

#### **PUT** `/properties/{path:.*}`
##### updatePropertyByPath 

Updates a property of a node
Updates a property of a node

###### URL
http://host:port/cms/rest/api/properties/{path:.*}
###### Parameters
- path

<table border="1">
    <tr>
        <th>Parameter</th>
        <th>Required</th>
        <th>Description</th>
        <th>Data Type</th>
    </tr>
    <tr>
        <th>path</th>
        <td>true</td>
        <td>Path of the property to update e.g. &#39;/content/documents/hippostd:foldertypes&#39;</td>
        <td>string</td>
    </tr>
</table>
- body

<table border="1">
    <tr>
        <th>Parameter</th>
        <th>Required</th>
        <th>Description</th>
        <th>Data Type</th>
    </tr>
    <tr>
        <th>body</th>
        <td>false</td>
        <td></td>
        <td><a href="#property">property</a></td>
    </tr>
</table>

###### Response
[](#)


###### Errors
| Status Code | Reason      | Response Model |
|-------------|-------------|----------------|
| 204    | Updated | - |
| 401    | Unauthorized | - |
| 404    | Property not found | - |
| 403    | Access denied | - |
| 500    | Error occurred | - |


- - -
#### **DELETE** `/properties/{path:.*}`
##### deletePropertyByPath 

Delete a property
Deletes a property

###### URL
http://host:port/cms/rest/api/properties/{path:.*}
###### Parameters
- path

<table border="1">
    <tr>
        <th>Parameter</th>
        <th>Required</th>
        <th>Description</th>
        <th>Data Type</th>
    </tr>
    <tr>
        <th>path</th>
        <td>true</td>
        <td>Path of the property to delete e.g. &#39;/content/hippostd:foldertype&#39;.</td>
        <td>string</td>
    </tr>
</table>

###### Response
[](#)


###### Errors
| Status Code | Reason      | Response Model |
|-------------|-------------|----------------|
| 204    | Deleted | - |
| 404    | Node not found | - |
| 500    | Error occurred | - |


- - -
#### **GET** `/properties/{path:.*}`
##### getPropertyByPath 

Get a property
Returns a property from the specified path

###### URL
http://host:port/cms/rest/api/properties/{path:.*}
###### Parameters
- path

<table border="1">
    <tr>
        <th>Parameter</th>
        <th>Required</th>
        <th>Description</th>
        <th>Data Type</th>
    </tr>
    <tr>
        <th>path</th>
        <td>true</td>
        <td>Path of the node to retrieve e.g &#39;/content/hippostd:foldertype&#39;.</td>
        <td>string</td>
    </tr>
</table>

###### Response
[property](#property)


###### Errors
| Status Code | Reason      | Response Model |
|-------------|-------------|----------------|
| 200    | OK | [property](#property) |
| 401    | Unauthorized | - |
| 404    | Node not found | - |
| 500    | Error occurred | - |


- - -
#### **POST** `/properties/{path:.*}`
##### createPropertyByPath 

Add a property to a node
Adds a property to a node

###### URL
http://host:port/cms/rest/api/properties/{path:.*}
###### Parameters
- path

<table border="1">
    <tr>
        <th>Parameter</th>
        <th>Required</th>
        <th>Description</th>
        <th>Data Type</th>
    </tr>
    <tr>
        <th>path</th>
        <td>true</td>
        <td>Path of the node to which to add the property to e.g. &#39;/content/documents/&#39;</td>
        <td>string</td>
    </tr>
</table>
- body

<table border="1">
    <tr>
        <th>Parameter</th>
        <th>Required</th>
        <th>Description</th>
        <th>Data Type</th>
    </tr>
    <tr>
        <th>body</th>
        <td>false</td>
        <td></td>
        <td><a href="#property">property</a></td>
    </tr>
</table>

###### Response
[](#)


###### Errors
| Status Code | Reason      | Response Model |
|-------------|-------------|----------------|
| 201    | Created | [property](#property) |
| 401    | Unauthorized | - |
| 404    | Node not found | - |
| 403    | Access denied | - |
| 500    | Error occurred | - |


- - -
### /_query
#### Overview
JCR QUERY API

#### **GET** `/_query/`
##### getResults 

Query for nodes
Returns a list of nodes

###### URL
http://host:port/cms/rest/api/_query/
###### Parameters
- query

<table border="1">
    <tr>
        <th>Parameter</th>
        <th>Required</th>
        <th>Description</th>
        <th>Data Type</th>
    </tr>
    <tr>
        <th>statement</th>
        <td>true</td>
        <td>JCR valid query syntax. &#39;//element(*,hippo:document) order by @jcr:score descending&#39;</td>
        <td>string</td>
    </tr>
    <tr>
        <th>language</th>
        <td>true</td>
        <td>JCR query language e.g &#39;xpath, sql&#39; or &#39;JCR-SQL2&#39;</td>
        <td>string</td>
    </tr>
    <tr>
        <th>limit</th>
        <td>false</td>
        <td>Sets the maximum size of the result set.</td>
        <td>int</td>
    </tr>
    <tr>
        <th>offset</th>
        <td>false</td>
        <td>Sets the start offset of the result set.</td>
        <td>int</td>
    </tr>
</table>

###### Response
[](#)


###### Errors
| Status Code | Reason      | Response Model |
|-------------|-------------|----------------|
| 200    | OK | [results](#results) |
| 401    | Unauthorized | - |
| 404    | Node not found | - |
| 500    | Error occurred | - |


- - -
#### **POST** `/_query/`
##### getResults 

Query for nodes
Returns a list of nodes. This method is especially useful when the query exceeds the maximum length of the URL.

###### URL
http://host:port/cms/rest/api/_query/
###### Parameters
- body

<table border="1">
    <tr>
        <th>Parameter</th>
        <th>Required</th>
        <th>Description</th>
        <th>Data Type</th>
    </tr>
    <tr>
        <th>body</th>
        <td>false</td>
        <td></td>
        <td><a href="#search">search</a></td>
    </tr>
</table>

###### Response
[](#)


###### Errors
| Status Code | Reason      | Response Model |
|-------------|-------------|----------------|
| 200    | OK | [results](#results) |
| 401    | Unauthorized | - |
| 404    | Node not found | - |
| 500    | Error occurred | - |


- - -
### /content
#### Overview
Hippo content API

#### **GET** `/content/{path:.*}`
##### getNodeByPath 

Get content
Returns a Hippo folder/document from the specified path

###### URL
http://host:port/cms/rest/api/content/{path:.*}
###### Parameters
- path

<table border="1">
    <tr>
        <th>Parameter</th>
        <th>Required</th>
        <th>Description</th>
        <th>Data Type</th>
    </tr>
    <tr>
        <th>path</th>
        <td>true</td>
        <td>Path of the node to retrieve</td>
        <td>string</td>
    </tr>
</table>
- query

<table border="1">
    <tr>
        <th>Parameter</th>
        <th>Required</th>
        <th>Description</th>
        <th>Data Type</th>
    </tr>
    <tr>
        <th>preview</th>
        <td>false</td>
        <td>Preview</td>
        <td>boolean</td>
    </tr>
</table>

###### Response
[HippoContent](#HippoContent)


###### Errors
| Status Code | Reason      | Response Model |
|-------------|-------------|----------------|
| 200    | OK | [HippoContent](#HippoContent) |
| 401    | Unauthorized | - |
| 404    | Node not found | - |
| 500    | Error occurred | - |


- - -
### /users
#### Overview
Users API

#### **POST** `/users`
##### createUser 

Creates a new user
Adds a user

###### URL
http://host:port/cms/rest/api/users
###### Parameters
- body

<table border="1">
    <tr>
        <th>Parameter</th>
        <th>Required</th>
        <th>Description</th>
        <th>Data Type</th>
    </tr>
    <tr>
        <th>body</th>
        <td>true</td>
        <td>Username of the new user</td>
        <td><a href="#User">User</a></td>
    </tr>
</table>

###### Response
[](#)


###### Errors
| Status Code | Reason      | Response Model |
|-------------|-------------|----------------|
| 200    | OK | - |
| 201    | Created | - |
| 400    | Request not understood due to errors or malformed syntax | - |
| 401    | Unauthorized | - |
| 404    | Node not found | - |
| 403    | Access denied | - |
| 500    | Error occurred | - |


- - -
#### **GET** `/users/`
##### getUsers 

Get all users
Returns a list of users

###### URL
http://host:port/cms/rest/api/users/
###### Parameters
- query

<table border="1">
    <tr>
        <th>Parameter</th>
        <th>Required</th>
        <th>Description</th>
        <th>Data Type</th>
    </tr>
    <tr>
        <th>limit</th>
        <td>false</td>
        <td></td>
        <td>long</td>
    </tr>
    <tr>
        <th>offset</th>
        <td>false</td>
        <td></td>
        <td>long</td>
    </tr>
</table>

###### Response
[](#)


###### Errors
| Status Code | Reason      | Response Model |
|-------------|-------------|----------------|
| 200    | OK | [User](#User) |
| 401    | Unauthorized | - |
| 404    | Node not found | - |
| 500    | Error occurred | - |


- - -
#### **GET** `/users/me`
##### getCurrentUser 

Get the current user
Returns the current user

###### URL
http://host:port/cms/rest/api/users/me
###### Parameters

###### Response
[](#)


###### Errors
| Status Code | Reason      | Response Model |
|-------------|-------------|----------------|
| 200    | OK | [User](#User) |
| 401    | Unauthorized | - |
| 404    | Node not found | - |
| 500    | Error occurred | - |


- - -
#### **GET** `/users/{username}`
##### getUserByName 

Get a user by username
Returns a user by username

###### URL
http://host:port/cms/rest/api/users/{username}
###### Parameters
- path

<table border="1">
    <tr>
        <th>Parameter</th>
        <th>Required</th>
        <th>Description</th>
        <th>Data Type</th>
    </tr>
    <tr>
        <th>username</th>
        <td>true</td>
        <td>Name of the user to retrieve</td>
        <td>string</td>
    </tr>
</table>

###### Response
[](#)


###### Errors
| Status Code | Reason      | Response Model |
|-------------|-------------|----------------|
| 200    | OK | [User](#User) |
| 401    | Unauthorized | - |
| 404    | Node not found | - |
| 500    | Error occurred | - |


- - -
#### **DELETE** `/users/{username}`
##### deleteUserByName 

Deletes a user by username
Deletes a user by username

###### URL
http://host:port/cms/rest/api/users/{username}
###### Parameters
- path

<table border="1">
    <tr>
        <th>Parameter</th>
        <th>Required</th>
        <th>Description</th>
        <th>Data Type</th>
    </tr>
    <tr>
        <th>username</th>
        <td>true</td>
        <td>Username of the user to delete</td>
        <td>string</td>
    </tr>
</table>

###### Response
[](#)


###### Errors
| Status Code | Reason      | Response Model |
|-------------|-------------|----------------|
| 204    | Deleted | - |
| 401    | Unauthorized | - |
| 404    | Node not found | - |
| 500    | Error occurred | - |


- - -
#### **PUT** `/users/{username}`
##### updateUserByName 

Updates a user
Updates a user by username

###### URL
http://host:port/cms/rest/api/users/{username}
###### Parameters
- path

<table border="1">
    <tr>
        <th>Parameter</th>
        <th>Required</th>
        <th>Description</th>
        <th>Data Type</th>
    </tr>
    <tr>
        <th>username</th>
        <td>true</td>
        <td>Name of the user to update</td>
        <td>string</td>
    </tr>
</table>
- body

<table border="1">
    <tr>
        <th>Parameter</th>
        <th>Required</th>
        <th>Description</th>
        <th>Data Type</th>
    </tr>
    <tr>
        <th>body</th>
        <td>false</td>
        <td></td>
        <td><a href="#User">User</a></td>
    </tr>
</table>

###### Response
[](#)


###### Errors
| Status Code | Reason      | Response Model |
|-------------|-------------|----------------|
| 204    | Updated | - |
| 401    | Unauthorized | - |
| 404    | Node not found | - |
| 500    | Error occurred | - |


- - -
#### **GET** `/users/{username}/groups`
##### getGroupsByUserName 

Get the groups of a user
Returns list of groups to which a user belongs

###### URL
http://host:port/cms/rest/api/users/{username}/groups
###### Parameters
- path

<table border="1">
    <tr>
        <th>Parameter</th>
        <th>Required</th>
        <th>Description</th>
        <th>Data Type</th>
    </tr>
    <tr>
        <th>username</th>
        <td>true</td>
        <td>Name of the user for which to retrieve the groups</td>
        <td>string</td>
    </tr>
</table>

###### Response
[](#)


###### Errors
| Status Code | Reason      | Response Model |
|-------------|-------------|----------------|
| 200    | OK | - |
| 401    | Unauthorized | - |
| 404    | Node not found | - |
| 500    | Error occurred | - |


- - -
### /_system
#### Overview
System API

#### **GET** `/_system/hardware`
##### getHardwareInfo 

Display the hardware information


###### URL
http://host:port/cms/rest/api/_system/hardware
###### Parameters

###### Response
[](#)


###### Errors
| Status Code | Reason      | Response Model |
|-------------|-------------|----------------|


- - -
#### **GET** `/_system/jvm`
##### getMemoryInfo 

Display the memory information from the JVM


###### URL
http://host:port/cms/rest/api/_system/jvm
###### Parameters

###### Response
[](#)


###### Errors
| Status Code | Reason      | Response Model |
|-------------|-------------|----------------|


- - -
#### **GET** `/_system/properties`
##### getSystemInfo 

Display the system properties


###### URL
http://host:port/cms/rest/api/_system/properties
###### Parameters

###### Response
[](#)


###### Errors
| Status Code | Reason      | Response Model |
|-------------|-------------|----------------|


- - -
#### **GET** `/_system/versions`
##### getVersionInfo 

Display the version information


###### URL
http://host:port/cms/rest/api/_system/versions
###### Parameters

###### Response
[](#)


###### Errors
| Status Code | Reason      | Response Model |
|-------------|-------------|----------------|


- - -
### /groups
#### Overview
Groups API

#### **POST** `/groups`
##### createGroup 

Creates a new group
Adds a new group

###### URL
http://host:port/cms/rest/api/groups
###### Parameters
- body

<table border="1">
    <tr>
        <th>Parameter</th>
        <th>Required</th>
        <th>Description</th>
        <th>Data Type</th>
    </tr>
    <tr>
        <th>body</th>
        <td>true</td>
        <td>Name of the new group</td>
        <td><a href="#Group">Group</a></td>
    </tr>
</table>

###### Response
[](#)


###### Errors
| Status Code | Reason      | Response Model |
|-------------|-------------|----------------|
| 200    | OK | - |
| 201    | Created | - |
| 400    | Request not understood due to errors or malformed syntax | - |
| 401    | Unauthorized | - |
| 404    | Node not found | - |
| 403    | Access denied | - |
| 500    | Error occurred | - |


- - -
#### **GET** `/groups/`
##### getGroups 

Get all groups
Returns a list of groups

###### URL
http://host:port/cms/rest/api/groups/
###### Parameters
- query

<table border="1">
    <tr>
        <th>Parameter</th>
        <th>Required</th>
        <th>Description</th>
        <th>Data Type</th>
    </tr>
    <tr>
        <th>limit</th>
        <td>false</td>
        <td></td>
        <td>long</td>
    </tr>
    <tr>
        <th>offset</th>
        <td>false</td>
        <td></td>
        <td>long</td>
    </tr>
</table>

###### Response
[](#)


###### Errors
| Status Code | Reason      | Response Model |
|-------------|-------------|----------------|
| 200    | OK | [GroupCollection](#GroupCollection) |
| 401    | Unauthorized | - |
| 404    | Node not found | - |
| 500    | Error occurred | - |


- - -
#### **DELETE** `/groups/{groupName}`
##### deleteGroupByName 

Deletes a group by it&#39;s name
Deletes a group by name

###### URL
http://host:port/cms/rest/api/groups/{groupName}
###### Parameters
- path

<table border="1">
    <tr>
        <th>Parameter</th>
        <th>Required</th>
        <th>Description</th>
        <th>Data Type</th>
    </tr>
    <tr>
        <th>groupName</th>
        <td>true</td>
        <td>Name of the group to delete</td>
        <td>string</td>
    </tr>
</table>

###### Response
[](#)


###### Errors
| Status Code | Reason      | Response Model |
|-------------|-------------|----------------|
| 204    | Deleted | - |
| 401    | Unauthorized | - |
| 404    | Node not found | - |
| 500    | Error occurred | - |


- - -
#### **GET** `/groups/{groupName}`
##### getGroupByName 

Get a group by it&#39;s name
Returns a group by it's name

###### URL
http://host:port/cms/rest/api/groups/{groupName}
###### Parameters
- path

<table border="1">
    <tr>
        <th>Parameter</th>
        <th>Required</th>
        <th>Description</th>
        <th>Data Type</th>
    </tr>
    <tr>
        <th>groupName</th>
        <td>true</td>
        <td>Name of the group to retrieve</td>
        <td>string</td>
    </tr>
</table>

###### Response
[](#)


###### Errors
| Status Code | Reason      | Response Model |
|-------------|-------------|----------------|
| 200    | OK | [Group](#Group) |
| 401    | Unauthorized | - |
| 404    | Node not found | - |
| 500    | Error occurred | - |


- - -
#### **PUT** `/groups/{groupName}`
##### updateGroup 

Updates a new group
Updates a new group

###### URL
http://host:port/cms/rest/api/groups/{groupName}
###### Parameters
- body

<table border="1">
    <tr>
        <th>Parameter</th>
        <th>Required</th>
        <th>Description</th>
        <th>Data Type</th>
    </tr>
    <tr>
        <th>body</th>
        <td>true</td>
        <td>Name of the group</td>
        <td><a href="#Group">Group</a></td>
    </tr>
</table>

###### Response
[](#)


###### Errors
| Status Code | Reason      | Response Model |
|-------------|-------------|----------------|
| 200    | OK | - |
| 204    | Updated | - |
| 400    | Request not understood due to errors or malformed syntax | - |
| 401    | Unauthorized | - |
| 404    | Node not found | - |
| 403    | Access denied | - |
| 500    | Error occurred | - |


- - -

## Data Types


## <a name="Calendar">Calendar</a>

<table border="1">
    <tr>
        <th>type</th>
        <th>required</th>
        <th>access</th>
        <th>description</th>
        <th>notes</th>
    </tr>
    <tr>
        <td>int</td>
        <td>optional</td>
        <td>-</td>
        <td>-</td>
        <td>-</td>
    </tr>
    <tr>
        <td>int</td>
        <td>optional</td>
        <td>-</td>
        <td>-</td>
        <td>-</td>
    </tr>
    <tr>
        <td><a href="#TimeZone">TimeZone</a></td>
        <td>optional</td>
        <td>-</td>
        <td>-</td>
        <td>-</td>
    </tr>
    <tr>
        <td>boolean</td>
        <td>optional</td>
        <td>-</td>
        <td>-</td>
        <td>-</td>
    </tr>
    <tr>
        <td>long</td>
        <td>optional</td>
        <td>-</td>
        <td>-</td>
        <td>-</td>
    </tr>
    <tr>
        <td>Date</td>
        <td>optional</td>
        <td>-</td>
        <td>-</td>
        <td>-</td>
    </tr>
    <tr>
        <td>int</td>
        <td>optional</td>
        <td>-</td>
        <td>-</td>
        <td>-</td>
    </tr>
    <tr>
        <td>boolean</td>
        <td>optional</td>
        <td>-</td>
        <td>-</td>
        <td>-</td>
    </tr>
    <tr>
        <td>int</td>
        <td>optional</td>
        <td>-</td>
        <td>-</td>
        <td>-</td>
    </tr>
</table>



## <a name="Collection">Collection</a>

<table border="1">
    <tr>
        <th>type</th>
        <th>required</th>
        <th>access</th>
        <th>description</th>
        <th>notes</th>
    </tr>
    <tr>
        <td>boolean</td>
        <td>optional</td>
        <td>-</td>
        <td>-</td>
        <td>-</td>
    </tr>
</table>



## <a name="Group">Group</a>

<table border="1">
    <tr>
        <th>type</th>
        <th>required</th>
        <th>access</th>
        <th>description</th>
        <th>notes</th>
    </tr>
    <tr>
        <td>boolean</td>
        <td>optional</td>
        <td>-</td>
        <td>-</td>
        <td>-</td>
    </tr>
    <tr>
        <td>string</td>
        <td>optional</td>
        <td>-</td>
        <td>-</td>
        <td>-</td>
    </tr>
    <tr>
        <td>string</td>
        <td>optional</td>
        <td>-</td>
        <td>Group description</td>
        <td>Group description</td>
    </tr>
    <tr>
        <td>Array[string]</td>
        <td>optional</td>
        <td>-</td>
        <td>Group members</td>
        <td>Group members</td>
    </tr>
    <tr>
        <td>string</td>
        <td>required</td>
        <td>-</td>
        <td>Group name</td>
        <td>Group name</td>
    </tr>
</table>



## <a name="GroupCollection">GroupCollection</a>

<table border="1">
    <tr>
        <th>type</th>
        <th>required</th>
        <th>access</th>
        <th>description</th>
        <th>notes</th>
    </tr>
    <tr>
        <td><a href="#Link">Array[Link]</a></td>
        <td>optional</td>
        <td>-</td>
        <td>-</td>
        <td>-</td>
    </tr>
    <tr>
        <td><a href="#Collection">Collection</a></td>
        <td>optional</td>
        <td>-</td>
        <td>-</td>
        <td>-</td>
    </tr>
</table>



## <a name="HippoContent">HippoContent</a>

<table border="1">
    <tr>
        <th>type</th>
        <th>required</th>
        <th>access</th>
        <th>description</th>
        <th>notes</th>
    </tr>
    <tr>
        <td><a href="#property">Array[property]</a></td>
        <td>optional</td>
        <td>-</td>
        <td>-</td>
        <td>-</td>
    </tr>
    <tr>
        <td>string</td>
        <td>optional</td>
        <td>-</td>
        <td>-</td>
        <td>-</td>
    </tr>
    <tr>
        <td>string</td>
        <td>required</td>
        <td>-</td>
        <td>-</td>
        <td>-</td>
    </tr>
    <tr>
        <td>string</td>
        <td>optional</td>
        <td>-</td>
        <td>-</td>
        <td>-</td>
    </tr>
</table>



## <a name="Link">Link</a>

<table border="1">
    <tr>
        <th>type</th>
        <th>required</th>
        <th>access</th>
        <th>description</th>
        <th>notes</th>
    </tr>
    <tr>
        <td>string</td>
        <td>optional</td>
        <td>-</td>
        <td>-</td>
        <td>-</td>
    </tr>
    <tr>
        <td>string</td>
        <td>optional</td>
        <td>-</td>
        <td>-</td>
        <td>-</td>
    </tr>
</table>



## <a name="TimeZone">TimeZone</a>

<table border="1">
    <tr>
        <th>type</th>
        <th>required</th>
        <th>access</th>
        <th>description</th>
        <th>notes</th>
    </tr>
    <tr>
        <td>string</td>
        <td>optional</td>
        <td>-</td>
        <td>-</td>
        <td>-</td>
    </tr>
    <tr>
        <td>int</td>
        <td>optional</td>
        <td>-</td>
        <td>-</td>
        <td>-</td>
    </tr>
    <tr>
        <td>int</td>
        <td>optional</td>
        <td>-</td>
        <td>-</td>
        <td>-</td>
    </tr>
    <tr>
        <td>string</td>
        <td>optional</td>
        <td>-</td>
        <td>-</td>
        <td>-</td>
    </tr>
</table>



## <a name="URI">URI</a>

<table border="1">
    <tr>
        <th>type</th>
        <th>required</th>
        <th>access</th>
        <th>description</th>
        <th>notes</th>
    </tr>
    <tr>
        <td>string</td>
        <td>optional</td>
        <td>-</td>
        <td>-</td>
        <td>-</td>
    </tr>
    <tr>
        <td>string</td>
        <td>optional</td>
        <td>-</td>
        <td>-</td>
        <td>-</td>
    </tr>
    <tr>
        <td>string</td>
        <td>optional</td>
        <td>-</td>
        <td>-</td>
        <td>-</td>
    </tr>
    <tr>
        <td>boolean</td>
        <td>optional</td>
        <td>-</td>
        <td>-</td>
        <td>-</td>
    </tr>
    <tr>
        <td>string</td>
        <td>optional</td>
        <td>-</td>
        <td>-</td>
        <td>-</td>
    </tr>
    <tr>
        <td>string</td>
        <td>optional</td>
        <td>-</td>
        <td>-</td>
        <td>-</td>
    </tr>
    <tr>
        <td>string</td>
        <td>optional</td>
        <td>-</td>
        <td>-</td>
        <td>-</td>
    </tr>
    <tr>
        <td>string</td>
        <td>optional</td>
        <td>-</td>
        <td>-</td>
        <td>-</td>
    </tr>
    <tr>
        <td>string</td>
        <td>optional</td>
        <td>-</td>
        <td>-</td>
        <td>-</td>
    </tr>
    <tr>
        <td>string</td>
        <td>optional</td>
        <td>-</td>
        <td>-</td>
        <td>-</td>
    </tr>
    <tr>
        <td>string</td>
        <td>optional</td>
        <td>-</td>
        <td>-</td>
        <td>-</td>
    </tr>
    <tr>
        <td>boolean</td>
        <td>optional</td>
        <td>-</td>
        <td>-</td>
        <td>-</td>
    </tr>
    <tr>
        <td>string</td>
        <td>optional</td>
        <td>-</td>
        <td>-</td>
        <td>-</td>
    </tr>
    <tr>
        <td>int</td>
        <td>optional</td>
        <td>-</td>
        <td>-</td>
        <td>-</td>
    </tr>
    <tr>
        <td>string</td>
        <td>optional</td>
        <td>-</td>
        <td>-</td>
        <td>-</td>
    </tr>
    <tr>
        <td>string</td>
        <td>optional</td>
        <td>-</td>
        <td>-</td>
        <td>-</td>
    </tr>
    <tr>
        <td>string</td>
        <td>optional</td>
        <td>-</td>
        <td>-</td>
        <td>-</td>
    </tr>
</table>



## <a name="User">User</a>

<table border="1">
    <tr>
        <th>type</th>
        <th>required</th>
        <th>access</th>
        <th>description</th>
        <th>notes</th>
    </tr>
    <tr>
        <td>string</td>
        <td>optional</td>
        <td>-</td>
        <td>The users last name</td>
        <td>The users last name</td>
    </tr>
    <tr>
        <td>string</td>
        <td>required</td>
        <td>-</td>
        <td>The unique username</td>
        <td>The unique username</td>
    </tr>
    <tr>
        <td><a href="#Calendar">Calendar</a></td>
        <td>optional</td>
        <td>-</td>
        <td>-</td>
        <td>-</td>
    </tr>
    <tr>
        <td>boolean</td>
        <td>optional</td>
        <td>-</td>
        <td>Indicates if the user originates from an external system</td>
        <td>Indicates if the user originates from an external system</td>
    </tr>
    <tr>
        <td>string</td>
        <td>optional</td>
        <td>-</td>
        <td>-</td>
        <td>-</td>
    </tr>
    <tr>
        <td><a href="#Group">Array[Group]</a></td>
        <td>optional</td>
        <td>-</td>
        <td>The groups of which the user is a member</td>
        <td>The groups of which the user is a member</td>
    </tr>
    <tr>
        <td>boolean</td>
        <td>required</td>
        <td>-</td>
        <td>Indicates if the user account is active</td>
        <td>Indicates if the user account is active</td>
    </tr>
    <tr>
        <td>string</td>
        <td>optional</td>
        <td>-</td>
        <td>The users password</td>
        <td>The users password</td>
    </tr>
    <tr>
        <td>string</td>
        <td>optional</td>
        <td>-</td>
        <td>The users e-mail</td>
        <td>The users e-mail</td>
    </tr>
    <tr>
        <td>string</td>
        <td>optional</td>
        <td>-</td>
        <td>-</td>
        <td>-</td>
    </tr>
    <tr>
        <td>string</td>
        <td>optional</td>
        <td>-</td>
        <td>The users first name</td>
        <td>The users first name</td>
    </tr>
    <tr>
        <td>boolean</td>
        <td>required</td>
        <td>-</td>
        <td>Indicates if the user account is a system user account</td>
        <td>Indicates if the user account is a system user account</td>
    </tr>
</table>



## <a name="node">node</a>

<table border="1">
    <tr>
        <th>type</th>
        <th>required</th>
        <th>access</th>
        <th>description</th>
        <th>notes</th>
    </tr>
    <tr>
        <td>Array[string]</td>
        <td>optional</td>
        <td>-</td>
        <td>-</td>
        <td>-</td>
    </tr>
    <tr>
        <td><a href="#node">Array[node]</a></td>
        <td>optional</td>
        <td>-</td>
        <td>-</td>
        <td>-</td>
    </tr>
    <tr>
        <td>string</td>
        <td>required</td>
        <td>-</td>
        <td>-</td>
        <td>-</td>
    </tr>
    <tr>
        <td><a href="#property">Array[property]</a></td>
        <td>optional</td>
        <td>-</td>
        <td>-</td>
        <td>-</td>
    </tr>
    <tr>
        <td>string</td>
        <td>optional</td>
        <td>-</td>
        <td>-</td>
        <td>-</td>
    </tr>
    <tr>
        <td>string</td>
        <td>required</td>
        <td>-</td>
        <td>-</td>
        <td>-</td>
    </tr>
    <tr>
        <td>string</td>
        <td>optional</td>
        <td>-</td>
        <td>-</td>
        <td>-</td>
    </tr>
</table>



## <a name="property">property</a>

<table border="1">
    <tr>
        <th>type</th>
        <th>required</th>
        <th>access</th>
        <th>description</th>
        <th>notes</th>
    </tr>
    <tr>
        <td>boolean</td>
        <td>required</td>
        <td>-</td>
        <td>-</td>
        <td>-</td>
    </tr>
    <tr>
        <td>Array[string]</td>
        <td>required</td>
        <td>-</td>
        <td>-</td>
        <td>-</td>
    </tr>
    <tr>
        <td>string</td>
        <td>required</td>
        <td>-</td>
        <td>-</td>
        <td>-</td>
    </tr>
    <tr>
        <td>string</td>
        <td>required</td>
        <td>-</td>
        <td>-</td>
        <td>-</td>
    </tr>
</table>



## <a name="result">result</a>

<table border="1">
    <tr>
        <th>type</th>
        <th>required</th>
        <th>access</th>
        <th>description</th>
        <th>notes</th>
    </tr>
    <tr>
        <td><a href="#URI">URI</a></td>
        <td>optional</td>
        <td>-</td>
        <td>-</td>
        <td>-</td>
    </tr>
    <tr>
        <td><a href="#Map[string,string]">Map[string,string]</a></td>
        <td>optional</td>
        <td>-</td>
        <td>-</td>
        <td>-</td>
    </tr>
    <tr>
        <td>double</td>
        <td>optional</td>
        <td>-</td>
        <td>-</td>
        <td>-</td>
    </tr>
</table>



## <a name="results">results</a>

<table border="1">
    <tr>
        <th>type</th>
        <th>required</th>
        <th>access</th>
        <th>description</th>
        <th>notes</th>
    </tr>
    <tr>
        <td>long</td>
        <td>optional</td>
        <td>-</td>
        <td>-</td>
        <td>-</td>
    </tr>
    <tr>
        <td><a href="#result">Array[result]</a></td>
        <td>optional</td>
        <td>-</td>
        <td>-</td>
        <td>-</td>
    </tr>
    <tr>
        <td>long</td>
        <td>optional</td>
        <td>-</td>
        <td>-</td>
        <td>-</td>
    </tr>
</table>



## <a name="search">search</a>

<table border="1">
    <tr>
        <th>type</th>
        <th>required</th>
        <th>access</th>
        <th>description</th>
        <th>notes</th>
    </tr>
    <tr>
        <td>int</td>
        <td>optional</td>
        <td>-</td>
        <td>-</td>
        <td>-</td>
    </tr>
    <tr>
        <td>string</td>
        <td>required</td>
        <td>-</td>
        <td>-</td>
        <td>-</td>
    </tr>
    <tr>
        <td>int</td>
        <td>optional</td>
        <td>-</td>
        <td>-</td>
        <td>-</td>
    </tr>
    <tr>
        <td>string</td>
        <td>required</td>
        <td>-</td>
        <td>-</td>
        <td>-</td>
    </tr>
</table>


