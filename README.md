# WAES Assignment
The Waes Assignment.

This implementation use in memory H2 database, if you restar the application you will lose all data.
The service use port 8000.

Requirements
======
* Maven
* Java 8 or later

Running
======
* Run server `mvn spring-boot:run`
* Run tests `mvn clean test`
* Genaretting version `mvn clean install package`

Using
======
* Adding base64 data in left, call url `http:\\localhost:8000\v1\diff\{id}\left`
* Adding base64 data in right, call url `http:\\localhost:8000\v1\diff\{id}\right`
* Calculating base64 differences, call url `http:\\localhost:8000\v1\diff\{id}`

API Documentation
======
After start server go to `http:\\localhost:8000\swagger-ui.html`

