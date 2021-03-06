FROM openjdk:10.0.1-jre
WORKDIR /
ADD target/demo-0.0.1-SNAPSHOT.jar demo.jar
ENV PORT=8080
ENV SPRING_PROFILES_ACTIVE=mysql
ENV MYSQL_HOST=mysql
ENV MYSQL_DBNAME=devops_demo
ENV MYSQL_USERNAME=root
ENV MYSQL_PASSWORD=root
EXPOSE ${PORT}
CMD java -jar demo.jar
