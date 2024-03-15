class RectangleException(Exception):
    pass


class SpecRecEx(RectangleException):
    pass


class Rectangle(object):
    def __init__(self, a, b):
        self._a = a
        self._b = b

    @property
    def a(self):
        return self._a

    @a.setter
    def a(self, a):
        self._a = a

    @property
    def b(self):
        return self._b

    @b.setter
    def b(self, b):
        self._b = b

    def obim(self):
        return 2 * (self._a + self._b)

    def povrsina(self):
        return self._b * self._a

    def nova_funk(self):
        raise RectangleException("porukica")


class Square(Rectangle):
    def __init__(self, a):
        super().__init__(a, a)


if __name__ == "__main__":
    d = Rectangle(4, 5)
    print("Obim je", d.obim())
    print("Površina je", d.povrsina())
    c = Square(6)
    print("Obim je", c.obim())
    print("Površina je", c.povrsina())
