PlantUML Server 
===============
[![Build Status](https://travis-ci.org/plantuml/plantuml-server.png?branch=master)](https://travis-ci.org/plantuml/plantuml-server)

PlantUML Server is a web application to generate UML diagrams on-the-fly.

![](https://raw.githubusercontent.com/ftomassetti/plantuml-server/readme/screenshots/screenshot.png)
 
To know more about PlantUML, please visit http://plantuml.sourceforge.net/.

Requirements
============

 * jre/jdk 1.6.0 or above
 * apache maven 3.0.2 or above

How to run the server
=====================

Just run:

```
mvn jetty:run
```

The server is now listing to [http://localhost:8080/plantuml](http://localhost:8080/plantuml).
In this way the server is run on an embedded jetty server. 

You can specify the port at which it runs:

```
mvn jetty:run -Djetty.port=9999"
```

How to run the server with Docker
=================================

```
docker build -t plantuml-server .
docker run -d -p 8080:8080 plantuml-server
```

The server is now listing to [http://localhost:8080/plantuml](http://localhost:8080/plantuml).

You may specity the port in `-p` Docker command line argument.

Alternate: How to run the server with Tomcat + Docker?
======================================================

Above method uses maven to run the application. That requires internet connectivity. That is undesireable in a corporate environment with firewalls. So, you can use following command to create a self-contained docker image that will "just-work". 

```
docker build -t platuml-server:tomcat8 -f Dockerfile.tomcat8 . 
docker run -d -p 8080:8080 plantuml-server:tomcat8
```
The server is now listing to [http://localhost:8080/plantuml](http://localhost:8080/plantuml).

You may specity the port in `-p` Docker command line argument.
 

How to generate the war
=======================

To build the war, just run:

```
mvn package
```

at the root directory of the project to produce plantuml.war in the target/ directory.
