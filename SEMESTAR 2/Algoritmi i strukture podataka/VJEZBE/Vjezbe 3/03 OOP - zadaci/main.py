class ComplexNumber(object):
    def __init__(self, real=0, imag=0):
        self._real = real
        self._imag = imag

    @property
    def real(self):
        return self._real

    @real.setter
    def real(self, real):
        self._real = real

    @property
    def imag(self):
        return self._imag

    @imag.setter
    def imag(self, imag):
        self._imag = imag

    def __str__(self):
        return str(self._real) + "+" + str(self._imag) + "i"

    def __add__(self, other):
        return ComplexNumber(self._real+other.real, self._imag+other.imag)


if __name__ == "__main__":
    c1 = ComplexNumber(1, 20)
    c2 = ComplexNumber(-4, 2)
    print(c1)
    print(c2)
    print(c1+c2)
