"# internet-shop"
#Project Description 
This project represents small online internet-shop which has functions like: 
Registration, Login, Add Product To Cart, Complete Order, etc. 
Users can also read a description of their fulfilled orders. 

#Build Pattern 
This project was built using MVC pattern, it is structured using Dao and Service 
layers which make it easier to read for other developers.
**_DAO_** layer contains basic CRUD operations with the persistence layer  
**_Service_** layer contains business logic for the application 
**_Servlets_**, implement client-server communicating logic 

#DBMS 
The project closely works with SQL queries and provides options to store data 
in comfortable tables, each table has its own purpose. 

###LAUNCH GUIDE 
In order to launch the program you need to download Tomcat and configure it the next way:

You need to add a "war exploded artifact" and add build configuration. 
Together with this, you need to check URL, in my case, it is: http://localhost:8080/ 
but it can vary 
HTTP port: 8080 
JMX port: 1099 
Click apply and then press the start button.  

By default, all registered users have the role of a regular user
if you would like to log in as an admin please enter the following data:
Login: admin 
Password: 1