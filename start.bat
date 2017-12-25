@echo off
cmd /k cd /d "d:\code\demo" mvn clean package cd "d:\code\demo\target" java -jar demo-0.0.1-SNAPSHOT.jar