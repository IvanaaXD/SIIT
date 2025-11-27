#!/bin/bash
first="I drive Volvo and BMW"
second="Audi"
echo "${first/Volvo/"$second"}"
