FROM openjdk:alpine

ADD /maven/release-tracker.jar /appl/release-tracker.jar

EXPOSE 8183

ENTRYPOINT ["java","-jar","/appl/release-tracker.jar"]