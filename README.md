PlantUMLServlet description 
===========================

PlantUMLServlet is a PlantUML extension to generate images on-the-fly.
 
To know more about PlantUML, please visit http://plantuml.sourceforge.net/.

How to build the project
========================

To build the project you need to install the following components:

 * java jdk 1.6.0 or above
 * apache maven 3.0.2 or above

To build the war, just run "mvn package" at the root directory of the project to produce 
plantuml.war in the target/ directory.

How to testrun the project
==========================

To run the application deployed on an embedded jetty server run "mvn jetty:run-war" 
and go to http://localhost:8080/plantuml with your favorite web browser (after it finishes
to start up).
