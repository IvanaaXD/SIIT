#!/bin/bash
file='cars.txt'
touch $file
if [ -f $file ];
then
rm cars.txt
echo "$file deleted"
fi
