#!/bin/bash
echo "Insert the number you want factorial for: "
read number
k=1
for (( i=1; i<=number; i++))
do
k=$(($k*$i))
done
echo "Factorial of $number is $k"
