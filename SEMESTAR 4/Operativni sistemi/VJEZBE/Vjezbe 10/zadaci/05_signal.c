/*
 * Napisati program koji klonira trenutni proces, i zatim:
 *
 * Originalni proces treba svaki sekund da ispise poruku "Prosao je
 * jedan sekund." pomocu sistemskog poziva alarm() i signala SIGALRM.
 *
 * Pomocu signala SIGCHLD, treba detektovati kraj izvrsavanja kopije
 * procesa i ispisati poruku "Kopija procesa je zavrsila sa radom.".
 *
 * Kopija procesa samo treba da ceka 5 sekundi.
 *
 * Kada korisnik pritisne kombinaciju tastera Ctrl+C (signal SIGINT),
 * treba ispisati poruku "Pritisnuli ste Ctrl+C, program zavrsava sa
 * radom.", i zatim prekinuti originalni proces.
 */

#include <unistd.h>
#include <signal.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <stdlib.h>
#include <stdio.h>

static const char message1[] = "One second passed...\n";
static const char message2[] = "Child finished with work\n";
static const char message3[] = "You entered Ctrl+C, program ends\n";

void Handler(int s) {
	
	switch (s) {
	case SIGALRM:
		write(STDOUT_FILENO, message1, sizeof(message1)-1);
		alarm(1);
		break;
	case SIGCHLD:
		write(STDOUT_FILENO, message2, sizeof(message2)-1);
		break;	
	case SIGINT:
		write(STDOUT_FILENO, message3, sizeof(message3)-1);
		exit(0);
		break;
	}
}

int main(void)
{
	/* Implementirati... */

	int p = fork();
	
	// child
	if (p==0) {
		sleep(5);
	}
	
	else if (p>0) {
		signal(SIGALRM, Handler);
		signal(SIGCHLD, Handler);
		signal(SIGINT, Handler);
		alarm(1);
		
		for (;;) select(0, NULL, NULL, NULL, NULL);
	}
	
	return 0;	
}

