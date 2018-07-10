#!/bin/bash

  CURRENT_LOCATION=`pwd`

  rm bin_test/jax-y-thorntail.jar

  cd $CURRENT_LOCATION/source/dep/01_G-Jax-Swar-Api && mvn -Dmaven.test.skip=true clean install

  cd $CURRENT_LOCATION/source/dep/02_G-Jax-Security-Provider && mvn clean install

  cd $CURRENT_LOCATION/source/dep/03_G-Jax-Service-Discovery && mvn clean install

  cd $CURRENT_LOCATION/source/Jax-Y/ && mvn clean package

