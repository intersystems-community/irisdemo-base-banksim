#!/bin/bash

amountDays=90
amountEvents=3150000
amountCustomers=100000
pause=$1
if [ -z "$pause" ];
then
    pause="pause"
fi
    

java -jar ./target/banksim-1.0-SNAPSHOT.jar $amountDays $amountEvents $amountCustomers $pause
