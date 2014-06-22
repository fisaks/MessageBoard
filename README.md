MessageBoard
============

This is simple REST based message board web application using Jersey.
It also utilizes the Jersey test framework to validate the functionality of the service.

It supports two services

* listMessages/${version}
* createMessage

where ${version} is either v1 or v2.

  v1 only returns limited meta data in the response and supports only json response format.

  v2 returns full meta data in the response and supports both json and xml response format. 
     The response format is chosen based on the Accept header of the client.

If no version is specified version 1 is used.


Install and test instructions:
--------------

  mvn clean install

import the war file target/MessageBoardWebApp.war into your favorite web container

or use the maven plugin part of the pom.xml for testing.

  mvn jetty:run

Test request using curl:

  curl -v -X POST -H 'Content-Type: application/json' http://localhost:8080/webapi/messageboard/createMessage --data '{"title":"sample title", "content": "sample content", "sender": "sample sender", "url": "http://google.com" }'


Version 1 can optionally be called without specifying the version
  curl -v -X GET -H 'Accept: application/json'  http://localhost:8080/webapi/messageboard/listMessages

  curl -v -X GET -H 'Accept: application/json'  http://localhost:8080/webapi/messageboard/listMessages/v1

  curl -v -X GET -H 'Accept: application/json'  http://localhost:8080/webapi/messageboard/listMessages/v2

  curl -v -X GET -H 'Accept: application/xml'  http://localhost:8080/webapi/messageboard/listMessages/v2

The project comes with some basic Jersey test cases to validate the functionality of the messages board application. These tests are run automatically part of the mvn install or can be individually run using mvn test.


