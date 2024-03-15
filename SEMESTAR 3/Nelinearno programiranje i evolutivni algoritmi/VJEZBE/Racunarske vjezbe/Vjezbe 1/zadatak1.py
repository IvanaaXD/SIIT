
def faktorijel_for(n):

    p = 1

    for i in range(n, 1, -1):
        p *= i

    return p


def faktorijel_while(n):

    p = 1

    while n > 0:

        p *= n
        n -= 1

    return p


if __name__ == '__main__':

    br = int(input("Unesite broj ciji faktorijel zelite naci: "))

    p_for = faktorijel_for(br)
    print("Faktorijel preko for petlje: ", p_for)

    p_while = faktorijel_while(br)
    print("Faktorijel preko while petlje: ", p_while)
