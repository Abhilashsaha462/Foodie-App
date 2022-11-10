FROM openjdk
WORKDIR usr/lib
ENV MONGO_DATABASE=Restaurant
ENV MONGO_URL=mongodb://localhost:27017/Restaurant
ADD ./target/UserService-0.0.1-SNAPSHOT.jar /usr/lib/UserService-0.0.1-SNAPSHOT.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar", "UserService-0.0.1-SNAPSHOT.jar"]