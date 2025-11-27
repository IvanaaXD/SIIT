#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>
#include <stdlib.h>

int main()
{
	// make two processes which run the same
	// program after this instruction
	int p = fork();
	if (p<0)
	{
		perror("fork fail");
		return 1;
	}
	printf("Hello world!, process_id(pid) = %d \n", getpid());
	return 0;
}
