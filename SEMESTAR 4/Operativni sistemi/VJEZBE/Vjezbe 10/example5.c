#include <stdio.h>
#include <signal.h>
#include <unistd.h>

void Handle(int s) {
	printf("\nStop.......\n");
}

int main() {
	signal(SIGINT, Handle);
	
	for (int i = 1; ; i++) {
		printf("%d: Main................\n", i);
		sleep(5);
	}
	
	return 0;
}
