#!/bin/sh
cd ..
cd docker-sii
docker-compose down
docker-compose up -d
cd ..
cd Interneteros
mvn clean
mvn package -Dskip.ejb.tests
