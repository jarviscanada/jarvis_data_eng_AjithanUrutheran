FROM openjdk:8-alpine
COPY target/stockquote.dao-1.0-SNAPSHOT.jar /usr/local/app/jdbc/lib/jdbc.jar
ENTRYPOINT ["java","-jar","/usr/local/app/jdbc/lib/jdbc.jar"]
