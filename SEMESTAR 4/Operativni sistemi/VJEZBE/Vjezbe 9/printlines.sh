#!/bin/bash
file=`cat cars.txt`
#echo $file
for line in "${file[@]}";
do
echo "$line";
done
