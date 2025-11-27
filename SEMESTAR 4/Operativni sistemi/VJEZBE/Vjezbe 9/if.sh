#!/bin/bash
a=10
b=10
if [ $a -gt $b ];
then
	echo "a is greater than b"
elif [ $a -lt $b ];
then
	echo "a is lower than b"
else
	echo "a is equal to b"
fi
