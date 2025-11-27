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

#include <unistd.h>
#include <errno.h>
#include <sys/types.h>
#include <sys/wait.h>

#define MAX_LENGTH 100

int main(void)
{
	int pipefd[2];
	int p = fork();

	if (p<0) {
		perror("fork failed");
		return 1;
	}
	
	if (pipe(pipefd) == -1) {
		perror("pipe failed");
		return 1;
	}
	
	// child
	if (p==0) {
		
		char buffer[100];
		read(pipefd[0], buffer, MAX_LENGTH);
		write(STDOUT_FILENO, buffer, MAX_LENGTH);
		
	} else if (p>0) {
		
		char buffer[100];
		read(STDIN_FILENO, buffer, MAX_LENGTH);
		write(pipefd[1], buffer, MAX_LENGTH);
	}

    return 0;
}
