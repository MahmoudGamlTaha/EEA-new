# #####################################################################################################
# 
# 
# 
# @author Ahmad Adly
# @since 20-Sep-2022
# References:
# >> https://www.educative.io/edpresso/what-is-the-workdir-command-in-docker
# 
# #####################################################################################################


FROM 87.119.195.27:8086/openjdk:8-jdk-alpine

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} accounting.jar

ENTRYPOINT ["java","-jar", "-Dserver.port=8080", "accounting.jar"]

EXPOSE 8080