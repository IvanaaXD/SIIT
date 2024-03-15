"""
Modul sadrži implementaciju deka.
"""


class EmptyDequeException(Exception):
    """
    Klasa modeluje izuzetke vezane za klasu Deque.
    """
    pass


class FullDequeException(Exception):
    """
    Klasa modeluje izuzetke vezane za klasu Deque.
    """
    pass


class Deque(object):
    """
    Implementacija deka na osnovu liste.
    """

    def __init__(self, max_size):
        """
        Konstruktor.
        """
        self._max_size = max_size
        self._data = [0] * max_size
        self._front = -1
        self._rear = 0
        self._size = 0

    def __len__(self):
        return self._size

    def is_empty(self):
        """
        Metoda proverava da li je dek prazan.
        """
        if self.__len__() == 0:
            return self._size == 0

    def is_full(self):

        if self._size == self._max_size:
            raise FullDequeException("Dek je pun.")
        """return len(self) == self._max_size - 1"""

    def first(self):
        """
        Metoda omogućava pristup prvom elementu deka.
        """
        if self.is_empty():
            raise EmptyDequeException('Dek je prazan.')
        return self._data[self._front]

    def last(self):
        """
        Metoda omogućava pristup poslednjem elementu deka.
        """
        if self.is_empty():
            raise EmptyDequeException('Dek je prazan.')

        return self._data[self._rear]

    def add_first(self, e):
        """
        Metoda dodaje element na početak deka.

        Argument:
        - `e`: novi element
        """
        if self.is_full():
            raise FullDequeException("Dek je pun.")

        if self._front == 0:
            self._front = self._max_size - 1

        elif self._front == -1:
            self._rear = 0
            self._front = 0

        else:
            self._front = self._front - 1

        self._data[self._front] = e
        self._size += 1

        """self._data.insert(0, e)"""

    def add_last(self, e):
        """
        Metoda dodaje element na kraj deka.

        Argument:
        - `e`: novi element
        """
        if self.is_full():
            raise FullDequeException("Dek je pun.")

        if self._rear == self._max_size - 1:
            self._rear = 0

        elif self._front == -1:
            self._rear = 0
            self._front = 0

        else:
            self._rear = self._rear + 1

        self._data[self._rear] = e
        self._size += 1

        """self._data.append(e)"""

    def delete_first(self):
        """
        Metoda izbacuje prvi element iz deka.
        """
        if self.is_empty():
            raise EmptyDequeException('Dek je prazan.')

        el = self._data[self._front]

        if self._front == self._rear:
            self._rear -= 1
            self._front -= 1

        else:
            if self._front == self._max_size - 1:
                self._front = 0
            else:
                self._front = self._front + 1

        self._size -= 1
        return el

        """return self._data.pop(0)"""

    def delete_last(self):
        """
        Metoda izbacuje poslednji element iz deka.
        """
        if self.is_empty():
            raise EmptyDequeException('Dek je prazan.')

        el = self._data[self._rear]

        if self._front == self._rear:
            self._rear = -1
            self._front = -1

        elif self._rear == 0:
            self._rear = self._max_size - 1
        else:
            self._rear = self._rear - 1

        self._size -= 1
        return el

        """return self._data.pop()"""


if __name__ == '__main__':
    d = Deque(10)
    d.add_last(5)
    d.add_first(7)
    d.add_first(3)
    print(d.first())

    d.delete_last()
    print(len(d))

    d.delete_last()
    d.delete_last()
    d.add_first(6)
    print(d.last())

    d.add_first(8)
    print(d.is_empty())
    print(d.last())
