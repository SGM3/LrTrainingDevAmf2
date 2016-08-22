@echo off

rmdir /S /Q data
rmdir /S /Q tomcat-7.0.62\temp
rmdir /S /Q tomcat-7.0.62\work

mkdir data
mkdir tomcat-7.0.62\temp
mkdir tomcat-7.0.62\work