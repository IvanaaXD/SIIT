from zadatak1 import Deque


class BrowserHistory(object):

    def __init__(self, pages):
        self._brojac_b = 0
        self._brojac_f = 0
        self._pages = pages
        self._lista = Deque(pages)

    def visit(self, url):
        if self._brojac_b + self._brojac_f == self._pages:
            self._lista.delete_first()
        else:
            self._brojac_b += 1

        self._lista.add_last(url)

        for j in range(self._brojac_f):
            self._lista.delete_first()

        self._brojac_f = 0

    def back(self, steps):

        if steps >= self._brojac_b:
            steps = self._brojac_b - 1

        for j in range(steps):
            page = self._lista.delete_last()
            self._lista.add_first(page)
            self._brojac_b -= 1
            self._brojac_f += 1

        return self._lista.last()

    def forward(self, steps):

        if steps >= self._brojac_f:
            steps = self._brojac_f

        for j in range(steps):
            page = self._lista.delete_first()
            self._lista.add_last(page)
            self._brojac_b += 1
            self._brojac_f -= 1

        return self._lista.last()


if __name__ == '__main__':

    MAX_PAGES = 20
    d = BrowserHistory(MAX_PAGES)

    stranica = input("Unesite pocetnu stranicu: ")
    d.visit(stranica)

    unos = input(">> ")

    drama = True
    while drama:

        if " " in unos:
            wuhu = unos.split(" ")

            if wuhu[0] == "back":
                broj = int(wuhu[1])
                print(d.back(broj))
            elif wuhu[0] == "forward":
                broj = int(wuhu[1])
                print(d.forward(broj))
            """elif wuhu[0] == "exit":
                drama = False
                exit()
            else:
                d.visit(unos, sada, pages)
                sada += 1"""

        else:

            if unos == "back":
                print(d.back(1))
            elif unos == "forward":
                print(d.forward(1))
            elif unos == "exit":
                drama = False
                exit()
            else:
                d.visit(unos)

        unos = input(">> ")
