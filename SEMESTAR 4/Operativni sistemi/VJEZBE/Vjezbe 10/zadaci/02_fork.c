/*
 * Napisati program koji klonira svoj proces, i zatim:
 *
 * Originalni proces treba da ispisuje slovo A odredjeni broj puta sa
 * pauzom od 2 sekunde izmedju ispisa.
 * Kopija treba da ispisuje slovo B odredjeni broj puta sa pauzom od 2
 * sekunde izmedju ispisa.
 *
 * Ukoliko je PID kopije paran broj, kopija treba da ceka 1 sekund pre
 * nego sto pocne sa ispisima, a u suprotnom, originalni proces treba
 * da ceka 1 sekund pre nego sto pocne sa ispisima.
 */

#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>

#define BROJ_PUTA 5

void child() {
	for (int i = 0; i < BROJ_PUTA; i++) {
		write(STDOUT_FILENO, "B", 1);
		sleep(2);
	}
}

void parent() {
	for (int i = 0; i < BROJ_PUTA; i++) {
		write(STDOUT_FILENO, "A", 1);
		sleep(2);
	}
}

int main(int argc, char *argv[])
{
	/* Implementirati... */
    
    	int p = fork();
    	
    	if (p < 0) {
    		perror("fork failed");
    		return 1;
    	}
    	
    	// child
    	if (p==0) {
    		if (getpid() % 2 == 0) 
    			sleep(1);
    		
    		child();
    	}
    	
    	else if (p>0) {
    		if (p % 2 != 0) 
    			sleep(1);
    		
    		parent();
    	}

	return 0;
}
