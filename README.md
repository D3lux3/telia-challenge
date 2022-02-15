# Simple REST api Assignment for Telia 2022

## Description
An REST API that allows users to do create, read, save and update Person JSON objects to database.


##  Getting started


### Using docker:

```docker
docker-compose up
```

### Using maven:

Running: 


```shell

mvn spring-boot:run

```

Testing:

```shell

mvn test

```


## Example Request

All the request should be made to the root of the server.

#### Create Person, Update (POST, UPDATE request)
To add an person the request should me made to root path:


```
yoururl/
```


Update request should me made to persons id path:
Example to update person with id 1:

```
yoururl/1
```

Example: 
```json
{
"name": "Testi",
"address": "Testikatu",
"phoneNumber": "112",
}
```

#### Find a persons by name (Get request)
Example to find all persons named "Testi": 

```
yoururl/testi
```

#### Find all persons (Get request)
Example: 

```
your_url/
```

##### Field details

| Field             | Type  | Description                                                           | 
|:---               |:---   |:---                                                                   |
|name               |String |Name of the person                                                     |
|address            |String |Persons address                                                        |
|phoneNumber        |String |Persons phonenumber                                                    |
