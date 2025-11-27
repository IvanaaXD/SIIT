#include <stdio.h>
#include <sys/mman.h>
#include <stdlib.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>

int main(int argc, char *argv[])
{
	if(argc < 2) { 
		printf("File path not mentioned.\n");
		return 0;
	}
	
	const char *filepath = argv[1];
	int fd = open(filepath, O_RDONLY);
	if (fd < 0) {
		printf("\n \"%s \" could not open\n", filepath);
		return 1;
	}
	
	struct stat statbuf;
	int err = fstat(fd, &statbuf);
	if (err < 0) {
		printf("\n \"%s \" could not open\n", filepath);
		return 2;	
	}
	
	char *ptr = mmap(NULL, statbuf.st_size, PROT_READ | PROT_WRITE, MAP_SHARED, fd, 0);
	if (ptr == MAP_FAILED) {
		printf("Mapping failed");
		close(fd);
		return 1;
	}
	
	ssize_t n = write(1, ptr, statbuf.st_size);
	if (n != statbuf.st_size) {
		printf("Write failed");
	}

	err = munmap(ptr, statbuf.st_size);
	if (err != 0) {
		printf("Unmapping failed");
		return 1;
	}
	
	return 0;
}


