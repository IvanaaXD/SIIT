#include <stdio.h>

unsigned int RUNPP_REG_ERR = 0;

int poredjenjeBrojeva(unsigned int v1, unsigned int v2);

void printbin(unsigned int x) {
    unsigned int m=0x80000000, s=0;
    while(m) {
        printf("%s%s",m&x ? "1" : "0",++s%8 ? "" : " ");
        m >>= 1;
    }
}

int main() {
    unsigned int v1, v2, g=0;
    int r;

    printf("Prva vrednost (hex): ");
    scanf("%x",&v1);
    printf("Binarno : ");
    printbin(v1);
    printf("\n");
    printf("Druga vrednost (hex): ");
    scanf("%x",&v2);
    printf("Binarno : ");
    printbin(v2);
    r = poredjenjeBrojeva(v1,v2);
    printf("\nRezultat: ");
    printbin(r);
    printf("\n");

    #ifdef LEVEL42
    printf("\nRUNPP_REG_ERR:%d\n",RUNPP_REG_ERR);
    #endif
    return g;
}    
