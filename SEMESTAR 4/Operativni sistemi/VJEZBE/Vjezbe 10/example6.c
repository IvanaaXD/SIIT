#include <stdio.h>
#include <signal.h>
#include <unistd.h>

int main() {
	signal(SIGINT, SIG_IGN);
	
	for (int i = 1; ; i++) {
		printf("%d: Ignoring the signal..........\n", i);
		sleep(5);
	}
	
	return 0;
}
