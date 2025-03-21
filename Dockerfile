#de que imagen partimos
FROM amazoncorretto:17-alpine-jdk 

#quien es el dueño
MAINTAINER MartinaBerns 

#va a copiar el empaquetado a github
COPY target/martina-b-0.0.1-SNAPSHOT.jar portfolio-mb-app.jar

#la instruccion que se va a ejecutar primero
ENTRYPOINT ["java", "-jar", "/portfolio-mb-app.jar"]
