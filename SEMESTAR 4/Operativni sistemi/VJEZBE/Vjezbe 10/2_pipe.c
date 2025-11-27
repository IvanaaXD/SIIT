#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>

int main(void)
{
	int pipefds[2];
	char buffer[5];
	
	if(pipe(pipefds) == -1)
	{
		perror("pipe");
		return 1;
	}
	
	char *pin = "4128\0";
	
	printf("Writing PIN to pipe...\n");
	write(pipefds[1], pin, 5);
	printf("Done.\n\n");
	
	printf("Reading PIN to pipe...\n");
	read(pipefds[0], buffer, 5);
	printf("Done.\n\n");	
	
	printf("PIN from pipe: %s\n", buffer);
	
	return 0;
}
