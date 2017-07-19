# JavaEE7-Archetype
Archetype for JavaEE7 projects

##### Package overview

##### config
startup for the application. Here the rootpath for the REST servive is set ( Currently "rest" ) and the REST stateless beans should be added here

##### dao
This package contains the DAO layer with a GenericDao that is an abstract class with basic CRUD operations. 
The GenericDao also holds the PersistanceUnit injection. So all DAOs should extend the GenericDao.

The AccountDao is an example of how a DAO should be used together with the GenericDao

##### entity
This package contains all entities needed in your application. The GenericEntity has the basic need any table should have.
The GenericEntity is a MappedSuperclass and entities should inherit from that class. See the Account entity for an example.

##### exception
All your own exceptions here

##### service
All your REST services here

#### Configure to fit your project
+ Update persistance.xml to enable it for your datasource
+ Generate och create entities
+ Create new services


