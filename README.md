# Getting Started

### Summary
Demo project for BrightTALK.

#### Work done
* sql schema for `Realm` - [schema.sql](../blob/master/schema.sql)
* *CrudRepository* and *Entity* added for `Realm`
* `EncryptionService` dummy implementation to return a fixed length (32) key
* `RealmService` with `get` and `create` features
* `RealmServiceException` as app specific exception created with `ExceptionCode` to handle the different known error codes
* `RealmController` to handle `get` and `create` requests
* *ExceptionHandler*s added to `RealmController` to handle exceptions generally
* `RealmRequest` and `RealmResponse` added for `RealmController`
* Unit tests added for `RealmService` and `EncryptionService` using *Mockito*
* Unit tests added for `RealmController` using *MockMvc* for JSON content type
* Integration tests for happy paths and an exceptional case for JSON content type

#### Future improvements

* unit tests for `RealmRestController` using XML content type
* integration tests for more exceptional cases, eg:
    * mandatory realm name is not supplied
    * duplicated realm name provided
    * realm id not proper type when querying by it
    

### Deployment

