How to run the program:

*need eclipse/IntelliJ (any editor)
1) Clone repo from GitHub/GitLab https://github.com/Hazmizan/Library-System-Project.git

OR

1) Unzip file

*java 17 required
*SETUP PATH and JAVA_HOME in Environment Variables
*maven 3.9.X required, BACKWARDS COMPATIBLE
*SETUP Eclipse properties and preferences

--------------------------------------------------

2) Import files into Eclipse
3) Clean and Build Eclipse packages - ensure Eclipse files sync with local
4) Run command in CMD
 	mvnw clean
	mvnw package
	mvnw test
	mvnw spring-boot:run
5) Ensure local container is running

*need postman to test
6) Drag and Drop postman JSON Collection to pre-load requests. LIBRARY SYSTEM.postman_collection.json
7) Access http://localhost:8080/h2-console/ to see in-memory database
	UserName: mizan
	Password: 1234
8) Trigger requests

