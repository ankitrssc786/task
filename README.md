# task
Restaurant Selection mini project (within existing users)

-------------------Create tables first in Myql-----------------

----------Create SQL Table------------

CREATE TABLE task_db.user ( id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255) -- Adjust the length as needed );

INSERT INTO task_db.user (id, name) VALUES ('1', 'Ankit'); INSERT INTO task_db.user (id, name) VALUES ('2', 'Ankita'); INSERT INTO task_db.user (id, name) VALUES ('3', 'Vanessa'); INSERT INTO task_db.user (id, name) VALUES ('4', 'Eugene'); INSERT INTO task_db.user (id, name) VALUES ('5', 'Rahul');

CREATE TABLE task_db.restaurants ( id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255), vote INT -- Adjust the length as needed );

INSERT INTO task_db.restaurants (id, name, vote) VALUES ('1', 'Subway', '0'); INSERT INTO task_db.restaurants (id, name, vote) VALUES ('2', 'MacDonlads', '0'); INSERT INTO task_db.restaurants (id, name, vote) VALUES ('3', 'KFC', '0'); INSERT INTO task_db.restaurants (id, name, vote) VALUES ('4', 'Breadtalk', '0'); INSERT INTO task_db.restaurants (id, name, vote) VALUES ('5', 'Komalas', '0');

---------------Run the Spring Boot Application---------

mvn spring-boot:run

--------API Details-----------

Create Session API
POST Method http://localhost:8080/api/session
Body : { "intitatorUserId": 1 }

Join Session API
POST http://localhost:8080/api/join
Param : sessionId = 1

Body : (User id below) { "id": 2 }

Get all session
GET Method http://localhost:8080/api/session
Get all restaurats
GET Method http://localhost:8080/api/restaurants
Submit restaurant
POST Method http://localhost:8080/api/submitRestaurant
Param : sessionId = 1

Body : (restaurant id below) { "id": 3 }

Get Active session submit restaurant
GET Method http://localhost:8080/api/getRestaurants/{sessionId}
End Session
POST Method http://localhost:8080/api/endSession
Param : sessionId = 1

Body : (User id below) { "id": 3 }
