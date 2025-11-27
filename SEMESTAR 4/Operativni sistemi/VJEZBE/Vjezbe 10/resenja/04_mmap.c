/*
 * Napisati program koji sifruje datoteku ROT13 algoritmom pomocu
 * sistemskih poziva.
 *
 * ROT13 algoritam pretvara zamenjuje slova A-M sa slovima N-Z, i
 * obrnuto. Drugacije gledano, na redni broj slova (izmedju 0 i 25) se
 * dodaje broj 13, a zatim se uzima ostatak pri deljenju sa 26 kako bi
 * se dobio novi redni broj. Karakteri koji nisu velika ili mala slova
 * ostaju nepromenjeni.
 *
 * Program treba da otvori datoteku ciji je naziv dat u parametru
 * komandne linije, i da mapira celokupan sadrzaj te datoteke u
 * memoriju pomocu sistemskog poziva mmap(). Zatim se ROT13 algoritam
 * primenjuje nad nizom koji predstavlja sadrzaj cele datoteke.
 */

#include <unistd.h>
#include <fcntl.h>
#include <sys/types.h>
#include <sys/mman.h>
#include <sys/stat.h>

int main(int argc, char *argv[])
{
	if (argc < 2) {
		printf("File not mentioned\n");
		return 1;
	}
	
	static char *filepath = argv[1];
	int f = open(filepath, O_RDWR);
	if (f < 0) {
		printf("Error opening the file");
		return 1;
	}
	
	struct stat info;
	int o = fstat(f, &info);
	if (o < 0) {
		printf("Error opening the file");
		return 1;
	} 
	
 	char *buffer = mmap(NULL, info.st_size, PROT_WRITE | PROT_READ, MAP_SHARED, f, 0);
 	if (buffer == MAP_FAILED) {
 		printf("Mapping failed");
 		return 1;
 	}
 	
 	for (int i = 0; i < info.st_size; i++) {
 		if (buffer[i] >= 'A' && buffer[i] <= 'M') 
 			buffer[i] = buffer[i] + 13;

 	}
}
