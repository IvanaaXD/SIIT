/*
 * Napisati program koji kreira jednosmerni FIFO kanal za komunikaciju
 * (pipe) i zatim klonira svoj proces.
 *
 * Originalni proces treba da ucita string sa standardnog ulaza
 * (najvise 100 karaktera) i zatim da ga posalje kopiji procesa preko
 * pipe-a.
 *
 * Kopija procesa treba da ucita najvise 100 karatera iz pipe-a i da
 * ih ispise na standardni izlaz.
 *
 * Osim toga, originalni proces mora da saceka da se kopija zavrsi pre
 * nego sto zavrsi sa svojim radom.
 */

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <sys/types.h>
#include <sys/wait.h>

#define MAX_LENGTH 100

int main(void)
{
	/* Implementirati... */
    
	int pipefd[2];
	
	if (pipe(pipefd) == -1) {
		perror("pipe fail");
	    	return 1;
	}
	
	int p = fork();
	    
	if (p == 0) {
	
		close(pipefd[1]);
		
		char buffer[MAX_LENGTH];
	    	int length = read(pipefd[0], buffer, MAX_LENGTH); 
	    	
	    	if (length > 0)
	    		write(STDOUT_FILENO, buffer, length);
	    		
	    	close(pipefd[0]);
	} 
	else if (p > 0) {
	
		close(pipefd[0]);
		
		char buffer[MAX_LENGTH];
		int length = read(STDIN_FILENO, buffer, MAX_LENGTH);
		
	    	if (length >= 0)
	    		write(pipefd[1], buffer, length);
	    	
	    	close(pipefd[1]);
	    	
	    	wait(NULL);
	}
    
	return 0;
    
}
