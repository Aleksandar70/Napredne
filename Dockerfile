FROM java:8
EXPOSE 8080
ADD build/libs/appraisalsheet.war appraisalsheet.war
ENTRYPOINT ["java","-jar","appraisalsheet.war"]