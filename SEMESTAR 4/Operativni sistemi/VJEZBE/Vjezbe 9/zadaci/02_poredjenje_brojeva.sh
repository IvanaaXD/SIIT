#!/bin/bash
#
# Napisati bash skript koji za dva zadata cela broja a i b poredi 
# njihove vrednosti i ispisuje na standardni izlaz u kakvoj su relaciji.
# a i b zadati kao prizvoljne promenljive skripta sa predefinisanim
# vrednostima (npr. a=100 i b=10).
#
# Primer koriscenja:
#   ./02_poredjenje_brojeva.sh


# TODO implementirati

echo "Enter first number:"
read a
echo "Enter second number:"
read b
if [ $a -gt $b ];
then
echo "$a is greater than $b"
elif [ $a -lt $b ];
then
echo "$a is lower than $b"
else
echo "$a and $b are equal"
fi
