#!/bin/bash
#
# Napisati bash skript koji omogucava korisniku da pozove jedan od cetiri signala
# nad procesom zadatim sa pid-om. Signal koji se salje procesu i pid procesa se 
# prosledjuju kao argumenti komandne linije. Signali koje treba podrzati su
# SIGSTOP (kod 19), SIGKILL (kod 9), SIGTERM (kod 15) i SIGCONT (kod 18).
# 
# Argumenti:
#   $1 - signal
#   $2 - PID procesa kojem se salje signal
#
# Primer poziva:
#   ./04_kill_process.sh STOP <pid-procesa>


# TODO implementirati

pid=$2
signal=$1

case $signal in
	STOP)
		echo "Stoping the process"
		kill -19 $pid
		;;
	KILL)
		echo "Killing the process"
		kill -9 $pid
		;;
	TERM)
		echo "Terminating the process"
		kill -15 $pid
		;;
	CONT)
		echo "Cont the process"
		kill -18 $pid
		;;
	*) echo "Unknown operation $signal"
esac
