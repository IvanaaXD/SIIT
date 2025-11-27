#!/bin/bash
file=cars.txt
if [ -f "$file" ];
then
echo "$file exists"
else
echo "$file does not exist"
fi
