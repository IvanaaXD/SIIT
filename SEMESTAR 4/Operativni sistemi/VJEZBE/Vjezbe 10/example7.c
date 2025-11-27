#include <stdio.h>
#include <signal.h>
#include <unistd.h>

void Handle(int s) {
	printf("\nRaise.......\n");
	sleep(5);
}

int main() {
	signal(SIGUSR1, Handle);
	
	for (int i = 1; ; i++) {
		printf("%d: Main................\n", i);
		sleep(10);
		raise(SIGUSR1);
	}
	
	return 0;
}
