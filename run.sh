#!/bin/bash


 mv source/Jax-Y/target/jax-y-thorntail.jar ./bin_test

 cd ./bin_test

 jobs &>/dev/null
 
   java -jar jax-y-thorntail.jar

  # java -Xdebug -Xrunjdwp:transport=dt_socket,address=11555,server=y,suspend=y -jar jax-y-thorntail.jar
