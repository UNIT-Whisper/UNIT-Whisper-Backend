FROM amazoncorretto:17
ARG JAR_FILE=build/libs/whisper.jar
COPY ${JAR_FILE} whisper.jar
ENTRYPOINT ["java","-jar","/whisper.jar"]