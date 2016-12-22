# Microbenchmark mit JMH

Das OpenJDK-Tool [JMH](http://openjdk.java.net/projects/code-tools/jmh/) ist 
ein Werkzeug zum testen von Kleinst-Algorithmen.

Im folgenden zeige ich das mal bei Vergleich von 2 Primzahl-Algorithmen. Die
Algorithmen sind keine grundsätzlich unterschiedlichen Lösungsstrategien, 
sondern unterscheiden sich nur in Implementierungsdetails. Genau dazu ist
JMH gedacht.

Das Tool wird einfach mittels Maven gestartet. Um ein passenden Maven-Projekt
zu erzeugen, kann der Archetype jmh-java-benchmark-archtype genutzt werden.
Die entsprechende Zeile lautet:

````
mvn archetype:generate 
    -DinteractiveMode=false 
    -DarchetypeGroupId=org.openjdk.jmh 
    -DarchetypeArtifactId=jmh-java-benchmark-archetype 
    -DgroupId=de.jdufner.microbenchmark 
    -DartifactId=primes 
    -Dversion=1.0
````
