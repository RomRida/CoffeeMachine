# Coffee Machine App

### About
This is a simple REST application for managing and monitoring various coffee machine states.

### How to 
To explore app first you will need to launch it _(guid for that is bellow)_, 
then navigate to `http://localhost:some port/swagger-ui/index.html`. 
App will have some data from start, defined in `resources/db/changelog/inserts.sql`,
but in short, almost every request, except `GET:see-all-coffee-types`, 
includes a query parameter id which will always be 1 _(unless of course you add more coffee machines to the list)_.

In order to use the coffee machine, you will need to turn it on and add water (no more than 5 liters), 
coffee (no more than 5 kg) and milk (no more than 3 liters) inside. To do this, use the following endpoints:
* add 500 ml of water - `PUT:api/v1/coffee-machine/add-water?id=1&water=0.5`
* add 500 ml of milk - `PUT:api/v1/coffee-machine/add-milk?id=1&milk=0.5`
* add 500 gr of beans - `PUT:api/v1/coffee-machine/add-beans?id=1&beans=0.5`
* turn machine on or off - `PUT:api/v1/coffee-machine/turn-on-or-off?id=1` 

After that you can use it:
* to see all coffee types - `GET:api/v1/coffee-machine/see-all-coffee-types` 
* to make coffee - `GET:api/v1/coffee-machine/make-coffee?id=1&type=Espresso` 

To check on machine status use following (it will work even if machine turned off):
* `GET:api/v1/coffee-machine/status?id=1`


### Local launch

For local app launch from your IDE of choice you would need Docker and JDK 17 or above.

You have two main ways to launch this app:

1) First navigate to `\docker\local\ ` directory located inside root of the project.
   From terminal launch `docker-compose up` command;
   Postgres Database will launch after that;
   Now, from your IDE lunch app with local spring profile, it will lunch on port 8083, 
   but you can change that in `application-local.properties`

2) Second is to run application with local database (not from docker) 
   I highly recommend you use default profile and define all properties ether through 
   environmental variables (listed below) or changing them right in default properties file. 
    ```table
   | Environmental variable |            Definition           |
   |:----------------------:|:-------------------------------:|
   | POSTGRES_HOST          | define postgresql host          |
   | POSTGRES_PORT          | define postgresql port          |
   | POSTGRES_DATABASE      | define postgresql database name |
   | POSTGRES_USER          | define postgresql user          |
   | POSTGRES_PASSWORD      | define postgresql password      |
    ```