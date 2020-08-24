#!/bin/bash

amountDays=365
amountEvents=100000
amountCustomers=1000
pause=$1
if [ -z "$pause" ];
then
    pause="pause"
fi
    

java -jar ./target/banksim-1.0-SNAPSHOT.jar $amountDays $amountEvents $amountCustomers $pause
