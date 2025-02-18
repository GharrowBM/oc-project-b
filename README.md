# OpenClassRoom - Projet #3 => ChaTôp Rentals Application 

## Database (MySQL)

The database will store several tables: 

- **chatop_users**: This tables will keep track of every application user, storing their passwords using the BCrypt algorithm for hashing and encoding their passwords. 
- **chatop_messages**: This table will like a user and a rental through a message (featuring its date of publication on the website)
- **chatop_rentals**: This table will store rental informations, linking them to their picture urls (the picture's location on the server for future access) and a owner (a ChaTop user)

To launch an instance of the database, you'll need docker and a terminal command. This command needs to be done from withing the resources folder. It's location is `backend\oc-chatop-backend\src\main\resources`: 

```bash
docker compose up -d 
```

## Backend - Spring Boot MVC (Java)

The backend side is a monolithic API which allows the access to the database. It is done with Spring MVC, thus features a MVC architecture. Repository pattern has been implemented as well and a service layer will handles the mapping for the controllers. Through the use of JDBC, the application is able to connect to a MySQL database for storing its data. The security is made with Spring Security and will work with a JWT token, sent by the user during each of their requests.

Several routes are available for the user, some of them being protected through the use of a JWT bearer token:

Public routes:
- **POST: /auth/register**: This route allows the user to send an email, a password and a name. It will handle the request and create a user, responding with a token in case everything went right.
- **POST: /auth/login**: This route allows the user to send an email and a password. It will handle the request and respond with a token in case everything went right.

Secured routes:
- **GET: /auth/me**: This route will extract the user infos from the JWT and respond with their profile as a JSON object 
- **GET: /users/{id}**: This route will respond with a JSON featuring information about the requested user (through the use of their id)
- **GET: /rentals**: This route will respond with a JSON featuring all rentals infos (as an embedded array under the `rentals` key)
- **GET: /rentals/{id}**: This route will respond with a JSON featuring information about a specific rental (the one with the corresponding ID)
- **POST: /rentals**: This route will process the rental request, extract infos from the body and stores eveything on the database. During this process, the picture will be sent to a service which will stores the file on the server and return the corresponding file location for the database.
- **PUT: /rentals/{id}**: This route is for editing rental information (the one from the ID in the route path), allowing users to change the title, description, etc... for the corresponding rental.
- **POST: /messages**: This route will allow app users to post messages for a specific rental. Its purpose is to allow ChaTôp users to ask questions to owners about a specific rental.

In order to launch the application, you need Java installed on your machine and to use a terminal command. This command needs to be made from within the `backend\oc-chatop-backend\out\artifacts\oc_chatop_backend_jar` folder: 

```bash
# Launch the application
java -jar oc-chatop-backend.jar
```

## Frontend - Angular (Typescript)

The frontend side will allow users to manage their rentals through a dedicated UI featuring several pages: 

- **/login**: Login form
- **/register**: Register form
- **/me**: Informations about the currect connected user
- **/rentals**: List of all rentals featuring their pictures
- **/rentals/create**: Rental creation form
- **/rentals/update/{id}**: Rental editiong form
- **/rentals/details/{id}**: Rental informations with details

In order to launch the application, you need Node.js installed on your machine and to use terminal commands. These commands needs to be made from within the `frontend` folder: 

```bash
# Install dependencies
npm i

# Launch the application
npm start
```