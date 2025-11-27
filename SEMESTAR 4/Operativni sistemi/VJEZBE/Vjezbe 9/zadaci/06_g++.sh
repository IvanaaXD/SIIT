#!/bin/bash
#
# Kompajlirati sve cpp datoteke u okviru zadatog direktorijuma. Direktorijum se zadaje kao
# argument komandne linije. Naziv iskompajlirane izvrsne datoteke treba da odgovara nazivu
# izvorne datoteke bez ekstenzije (npr. za izvornu datoteku helloworld.cpp izvrsna datoteka
# treba da se zove helloworld). Za svaku kompajliranu datoteku na standardni izlaz ispisati
# da li je uspesno iskompalirana ili nije. Izlaz g++ kompajlera preusmeriti u datoteku 
# compile.log. Pre pokusaja kompajliranja proveriti da li je zadat postojeci direktorijum
# kao i da li korisnik koji pokrece skript ima potrebna prava (citanje i pisanje).
#
# Argumenti:
#   $1 - putanja do direktorijuma u kojem se nalaze datoteke za kompajliranje
# 
# Primer poziva:
#   ./06_g++.sh 06_izvorni_kodovi/


# TODO implementirati

dir=$1

if [ ! -d $dir ]; then
	echo "$dir is not directory"
elif [ ! -r $dir ]; then
	echo "User does not have rights to read $dir"
elif [ ! -w $dir ]; then
	echo "User does not have rights to write to $dir"
else
	cd $dir;
	for d in $(ls *.cpp); 
	do
		g++ $d -o $(basename $d .cpp) -pthread 1>&2 2>compile.log;
		
		success=$?
		if [[ $success -eq 0 ]]; then
			echo "File $d successfully compiled"
		else
			echo "Error while comiling the file $d"
		fi
	done
fi
