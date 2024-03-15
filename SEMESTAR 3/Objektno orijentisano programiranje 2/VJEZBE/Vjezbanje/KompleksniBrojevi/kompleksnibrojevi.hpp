#ifndef KOMPLEKSNIBROJEVI_HPP
#define KOMPLEKSNIBROJEVI_HPP

#include <string>

class KompleksniBroj {
private:
    double x;
    double y;

public:
    KompleksniBroj();
    KompleksniBroj(double real, double img);
    KompleksniBroj(const KompleksniBroj& other);

    void setReal(double real);
    void setImg(double img);
    double getReal();
    double getImg();
    std :: string to_string();
};

KompleksniBroj konjugovano(KompleksniBroj broj);

KompleksniBroj saberi(KompleksniBroj broj1, KompleksniBroj broj2);

KompleksniBroj oduzmi(KompleksniBroj broj1, KompleksniBroj broj2);

KompleksniBroj pomnozi(KompleksniBroj broj1, KompleksniBroj broj2);

KompleksniBroj podijeli(KompleksniBroj broj1, KompleksniBroj broj2);

class Broj {

};

class Broj1 {
private:
    int i;
};

class Slovo {
private:
    char k;
};

class Tekst {
private:
    std::string s;
};

#endif