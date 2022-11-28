FROM openjdk:11

ADD shop-0.0.1-SNAPSHOT.jar /opt
ADD agent/whatap.agent-2.2.0.jar /opt
ADD agent/whatap.conf /opt
ADD agent/paramkey.txt /opt

WORKDIR /opt

CMD ["java", "-javaagent:/opt/whatap.agent-2.2.0.jar", "-jar", "shop-0.0.1-SNAPSHOT.jar"]

