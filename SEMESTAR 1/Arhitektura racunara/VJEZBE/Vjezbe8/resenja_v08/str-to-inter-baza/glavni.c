#include <stdio.h>

int str_to_inter(char* str, unsigned int* greska, unsigned int baza);

int test(char* b, unsigned int bz) {
    char s[20];
    unsigned int g,r;
    printf("Ulaz  : str_to_inter(\"%s\",&g,%d)\n",b, bz);
    r = str_to_inter(b,&g,bz);
    printf("Greška: %u\n",g);
    if (g == 0)
        printf("Izlaz : %d\n",r);
    printf("\n");
}

int main() {
    test("-12aAbBcC",16);      //ok
    test("123 4567",8);       //greška
    test("12345678",8);       //greška
    test("12345671234567",8); //greška
    test("12345671",8);       //ok
    test("12345671",10);      //ok
    test("12=45", 16);
}
