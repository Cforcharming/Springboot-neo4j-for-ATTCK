FROM adoptopenjdk/openjdk11
ARG JAR_FILE=out/artifacts/ATTCK_jar/*.jar
COPY ${JAR_FILE} ATTCK/
ENTRYPOINT ["java","-jar","/ATTCK/ATTCK.jar"]