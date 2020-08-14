#!/bin/bash

amountDays=60
amountEvents=3500000
amountCustomers=50000
pause=$1
if [ -z "$pause" ];
then
    pause="pause"
fi
    

java -jar ./target/banksim-1.0-SNAPSHOT.jar $amountDays $amountEvents $amountCustomers $pause
