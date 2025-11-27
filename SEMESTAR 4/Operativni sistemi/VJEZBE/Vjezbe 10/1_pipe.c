#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main(void)
{
	int pipefds[2];
	
	if(pipe(pipefds) == -1)
	{
		perror("pipe");
		return 1;
	}
	
	printf("Read file descriptor value: %d \n", pipefds[0]);
	printf("Write file descriptor value: %d \n", pipefds[1]);
		
	return 0;
}
