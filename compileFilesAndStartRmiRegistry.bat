@echo off
path C:\Program Files\Java\jdk1.8.0_161\bin;%path%
javac src\Client\*.java src\Interface\*.java src\Server\*.java
echo Arquivos compilados
cd src
start rmiregistry
echo Registrado RMI
