from abc import *
from board import Board


class State(object):
    """
    Apstraktna klasa koja opisuje stanje pretrage.
    """

    @abstractmethod
    def __init__(self, board: Board, parent=None, position=None, goal_position=None):
        """
        :param board: Board - tabla
        :param parent: State - roditeljsko stanje
        :param position: (int x, int y) - pozicija stanja
        :param goal_position: (int x, int y) - pozicija krajnjeg stanja
        :return:
        """
        self.board = board  # Reference na stanje table koje se vidi na ekranu. Ovo se ne menja
        self.parent = parent  # roditeljsko stanje


        if self.parent is None:  # ako nema roditeljsko stanje, onda je ovo inicijalno stanje
            # pronaladji elemente sa table
            self.position = board.find_position(self.get_agent_code())  # pronadji pocetnu poziciju
            self.goal_position = board.find_position(self.get_agent_goal_code())  # pronadji krajnju poziciju
            self.checkpoints = tuple(board.find_all_positions(self.get_checkpoint_code()))
            self.teleports = tuple(board.find_all_positions(self.get_teleport_code()))
            self.teleport = False
        else:  # ako ima roditeljsko stanje, samo sacuvaj vrednosti parametara
            self.position = position
            self.goal_position = goal_position
            self.checkpoints = self.parent.checkpoints
            self.teleports = self.parent.teleports
            self.teleport = self.parent.teleport


        self.depth = parent.depth + 1 if parent is not None else 1  # povecaj dubinu/nivo pretrage


    def get_next_states(self):
        new_positions = self.get_legal_positions()  # dobavi moguce (legalne) sledece pozicije iz trenutne pozicije
        next_states = []
        # napravi listu mogucih sledecih stanja na osnovu mogucih sledecih pozicija
        for new_position in new_positions:
            next_state = self.__class__(self.board, self, new_position, self.goal_position)
            next_states.append(next_state)
        return next_states


    def get_agent_code(self):
        return 'r'

    def get_agent_goal_code(self):
        return 'g'
    
    def get_checkpoint_code(self):
        return 'b'
    
    def get_teleport_code(self):
        return 'y'

    @abstractmethod
    def get_legal_positions(self):
        """
        Apstraktna metoda koja treba da vrati moguce (legalne) sledece pozicije na osnovu trenutne pozicije.
        :return: list
        """
        pass

    @abstractmethod
    def is_final_state(self):
        """
        Apstraktna metoda koja treba da vrati da li je treuntno stanje zapravo zavrsno stanje.
        :return: bool
        """
        pass

    @abstractmethod
    def unique_hash(self):
        """
        Apstraktna metoda koja treba da vrati string koji je JEDINSTVEN za ovo stanje
        (u odnosu na ostala stanja).
        :return: str
        """
        pass
    
    @abstractmethod
    def get_cost_estimate(self):
        """
        Apstraktna metoda koja treba da vrati procenu cene
        (vrednost heuristicke funkcije - h(n)) za ovo stanje.
        Koristi se za vodjene pretrage.
        :return: float
        """
        pass
    
    @abstractmethod
    def get_current_cost(self):
        """
        Apstraktna metoda koja treba da vrati stvarnu dosadašnju trenutnu cenu za ovo stanje, odnosno g(n)
        Koristi se za vodjene pretrage.
        :return: float
        """
        pass


class RobotState(State):

    def __init__(self, board: Board, parent: State=None, position: tuple=None, goal_position: tuple=None):
        super().__init__(board, parent, position, goal_position)
        # posle pozivanja super konstruktora, mogu se dodavati "custom" stvari vezani za stanje
        # TODO 1: prosiriti stanje sa informacijom da li je robot pokupio kutiju

        self.new_checkpoints = []
        for cp in self.checkpoints:
            if position == cp:
                continue
            self.new_checkpoints.append(cp)
        
        self.checkpoints = self.new_checkpoints

        # if position in self.checkpoints:
        #     self.has_box = True
        # else:
        #     self.has_box = parent.has_box if parent is not None else False

        
    def get_legal_positions(self):
        # moguci smerovi kretanja robota (desno, levo, dole, gore)
        actions = [(0, 1), (0, -1), (1, 0), (-1, 0)]

        row, col = self.position  # trenutno pozicija
        new_positions = []
        for d_row, d_col in actions:  # za sve moguce smerove
            new_row = row + d_row  # nova pozicija po redu
            new_col = col + d_col  # nova pozicija po koloni
            # ako nova pozicija nije van table i ako nije zid ('w'), ubaci u listu legalnih pozicija
            if not self.board.is_out_of_bounds(new_row, new_col) and not self.board.hits_wall(new_row, new_col):
                new_positions.append((new_row, new_col))
        return new_positions

    def is_final_state(self):
        # TODO 1: proširiti sa informacijom da li je robot pokupio kutiju
        return self.position == self.goal_position and len(self.checkpoints) == 0

    def unique_hash(self):
        # TODO 1: proširiti sa informacijom da li je robot pokupio kutiju
        return str(self.position) + str(self.checkpoints)
    
    def get_cost_estimate(self):
        pass
    
    def get_current_cost(self):
        pass
