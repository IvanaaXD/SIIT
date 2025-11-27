import tkinter as tk
from tkinter import filedialog
import tkinter.font as tkFont
import os
import sys
import time
from PIL import Image, ImageTk  # pip install --upgrade Pillow

from board import Board
from search import *
from state import *



search_class_map = {
    "BFS": BreadthFirstSearch,
    "DFS": DepthFirstSearch,
    "IDFS": IterativeDepthFirstSearch,
    "UCS": UniformCostSearch,
    "GS": GreedySearch,
    "A*": AStarSearch 
}


# mapiranje sadrzaja table na boju celije
board_to_colors = {
    '.': 'white',
    'w': 'gray',
    'g': 'orangered',
    'b': 'blue',
    'y': 'yellow'
    }
# mapiranje sadrzaja table na ikonicu
board_to_icons = {
    'r': 'robot.png',
    'f': 'fire.png'
}


class RobotGame:

    def __init__(self, rows=20, cols=20, board_file_path='boards/board.brd', default_search="BFS"):
        
        self.rows = rows  # broj redova table
        self.cols = cols  # broj kolona table
        self.cell_size = 40  # velicina celije
        self.board = Board(rows=self.rows, cols=self.cols)


        self.grid_elem_ids = [[[]] * self.cols for _ in range(self.rows)]
        self.grid_text_ids = [[[]] * self.cols for _ in range(self.rows)]


        self.root = tk.Tk()
        self.root.title('ORI - Pretrage')
        self.make_menu(self.root)  # make window menu
        self.ui = tk.Frame(self.root, bg='white')  # main UI
        self.ui2 = tk.Frame(self.root, bg='white')


        self.canvas = tk.Canvas(self.root, width=self.cols * self.cell_size + 1, height=self.rows * self.cell_size + 1,
                        highlightthickness=0, bd=0, bg='white')
        

        self.canvas.icons = dict()
        self.icons = dict()
        for f in os.listdir('icons'):
            icon = Image.open(os.path.join('icons', f))
            icon = icon.resize((self.cell_size - 2, self.cell_size - 2), Image.LANCZOS)  # resize icon to fit cell
            icon = ImageTk.PhotoImage(icon)
            self.icons[f] = icon

        self.search_class_text = tk.StringVar(self.ui)
        self.search_class_text.set(default_search)


        # create buttons
        search_option = tk.OptionMenu(self.ui, self.search_class_text, *search_class_map.keys())
        start_button = tk.Button(self.ui, text='SEARCH', width=10, command=self.do_search)
        restart_button = tk.Button(self.ui, text='RESET', width=10, command=self.reset)
        clear_button = tk.Button(self.ui, text='CLEAR ALL', width=10, command=self.clear)
        debug_button = tk.Button(self.ui, text='DEBUG', width=10, command=self.debug)
        stat_report = tk.Label(self.root, text='      ', bg='white', justify=tk.LEFT, relief=tk.GROOVE,
                            font=tkFont.Font(weight='bold'))
        

        search_option.grid(row=0, column=0, padx=10, pady=10)
        start_button.grid(row=1, column=0, padx=10, pady=10)
        clear_button.grid(row=2, column=0, padx=10, pady=10)
        clear_button.grid(row=3, column=0, padx=10, pady=10)
        restart_button.grid(row=4, column=0, padx=10, pady=10)
        debug_button.grid(row=5, column=0, padx=10, pady=10)


        self.display_board()
        self.canvas.bind('<Button-1>', self.switch_cell)  # bind left mouse click event to function switch_cell
        self.canvas.bind('<Button-2>', self.switch_cell_backwards)  # bind middle mouse click event to function switch_cell_backwards (right click on macOS)
        self.canvas.bind('<Button-3>', self.switch_cell_backwards)  # bind right mouse click event to function switch_cell_backwards
        self.root.bind('<Key>', self.key)  # bind keyboard event to function key
        self.ui.pack(side=tk.RIGHT, expand=tk.YES, fill=tk.BOTH)
        self.canvas.pack(side=tk.TOP, expand=tk.YES, fill=tk.BOTH)
        self.ui2.pack(side=tk.LEFT, expand=tk.YES, fill=tk.BOTH, anchor=tk.W)
        stat_report.pack(side=tk.RIGHT, expand=tk.NO, fill=tk.NONE)


        self.load_board(board_file_path)


        self.processed = None
        self.path = None

    def run(self):
        self.root.mainloop()


    def load_board_from_file(self, filename=None):
        if filename is None:
            filename = filedialog.askopenfilename(defaultextension='.brd',
                                                    filetypes=(('board files', '*.brd'), ('All files', '*.*')),
                                                    initialdir="./boards")
        self.board.load_from_file(filename)
        return filename


    def save_board_to_file(self):
        filename = filedialog.asksaveasfilename(defaultextension='.brd',
                                                filetypes=(('board files', '*.brd'), ('All files', '*.*')))
        self.board.save_to_file(filename)


    def load_board(self, from_file=None):      # filename passed when reopening (resetting) same file
        self.load_board_from_file(from_file)
        self.display_board()


    def clear(self):
        self.board.clear()
        self.display_board()


    def reset(self):
        for row in range(self.rows):
            for col in range(self.cols):
                self.delete_texts(row, col)
        self.display_board()


    def key(self, event):
        k = event.keysym.lower()
        row, col, new_row, new_col = self.board.move_player_keyboard(k)
        state = RobotState(self.board, action=(new_row - row, new_col - col))
        print(f'g: {state.get_current_cost():.2f} | h: {state.get_cost_estimate():.2f}')

        if self.board.find_position('r') != (None, None):
            self.update_board(row, col)
            self.update_board(new_row, new_col)


    def switch_cell(self, event, row=None, col=None):
        if row is None and col is None:
            cx = event.x
            cy = event.y
            col = cx // self.cell_size  # column
            row = cy // self.cell_size  # row
        self.board.switch_cell(row, col)
        self.update_board(row, col)

    def switch_cell_backwards(self, event, row=None, col=None):
        if row is None and col is None:
            cx = event.x
            cy = event.y
            col = cx // self.cell_size  # column
            row = cy // self.cell_size  # row
        self.board.switch_cell_backwards(row, col)
        self.update_board(row, col)


    def update_board(self, row, col):
        data = self.board.data
        text = self.board.text
        self.delete_elems(row, col)
        elem = data[row][col]
        d, i = elem, elem
        if ',' in elem:
            s = elem.split(',')
            d = s[0]
            i = s[1]

        if d in board_to_colors:
            self.draw_rectangle(row, col, board_to_colors[d])
        if i in board_to_icons:
            icon = self.icons[board_to_icons[i]]
            self.draw_icon(row, col, icon)

        if len(text[row][col]) > 0:
            self.draw_text(row, col, text[row][col])
        else:
            self.delete_texts(row, col)


    def get_cell_rectangle(self, row, col):
        return col * self.cell_size, row * self.cell_size, (col + 1) * self.cell_size, (row + 1) * self.cell_size


    def draw_rectangle(self, row, col, color, width=1):
        rect = self.get_cell_rectangle(row, col)
        elem_id = self.canvas.create_rectangle(rect, width=width, fill=color, outline='gray')
        self.save_elem_id(elem_id, row, col)


    def draw_text(self, row, col, text):
        rect = self.get_cell_rectangle(row, col)
        l = tk.Label(self.canvas, text=text)
        l.bind('<Button-1>', lambda event: self.switch_cell(event, row, col))
        elem_id = self.canvas.create_window(rect[0] + self.cell_size/2, rect[1] + self.cell_size/2, height=self.cell_size/3, window=l)
        self.save_elem_id(elem_id, row, col)
        self.save_text_id(elem_id, row, col)


    def draw_icon(self, row, col, icon):
        rect = self.get_cell_rectangle(row, col)
        elem_id = self.canvas.create_image(rect[0]+2, rect[1]+2, image=icon, anchor=tk.NW)
        self.canvas.icons[elem_id] = icon
        self.save_elem_id(elem_id, row, col)


    def save_elem_id(self, elem_id, row, col):
        if len(self.grid_elem_ids[row][col]) == 0:
            self.grid_elem_ids[row][col] = []
        self.grid_elem_ids[row][col].append(elem_id)


    def save_text_id(self, elem_id, row, col):
        if len(self.grid_text_ids[row][col]) == 0:
            self.grid_text_ids[row][col] = []
        self.grid_text_ids[row][col].append(elem_id)


    def delete_elems(self, row, col): 
        if 0 <= row < self.rows and 0 <= col < self.cols:
            for elem_id in self.grid_elem_ids[row][col]:
                self.canvas.delete(elem_id)
                if elem_id in self.canvas.icons:
                    del self.canvas.icons[elem_id]
            self.grid_elem_ids[row][col] = []


    def delete_texts(self, row, col):
        if 0 <= row < self.rows and 0 <= col < self.cols:
            for elem_id in self.grid_text_ids[row][col]:
                self.canvas.delete(elem_id)
                if elem_id in self.canvas.icons:
                    del self.canvas.icons[elem_id]
            self.grid_text_ids[row][col] = []
            self.board.text[row][col] = ''


    def display_board(self):
        self.canvas.delete(tk.ALL)
        for row in range(len(self.board.data)):
            for col in range(len(self.board.data[0])):
                self.update_board(row, col)


    def make_menu(self, win):
        top = tk.Menu(win)  # win=top-level window
        win.config(menu=top)  # set its menu option
        file_menu = tk.Menu(top)
        file_menu.add_command(label='Open...', command=self.load_board, underline=0)
        file_menu.add_command(label='Save...', command=lambda: self.save_board_to_file(), underline=0)
        file_menu.add_command(label='Quit', command=sys.exit, underline=0)
        top.add_cascade(label='File', menu=file_menu, underline=0)
        edit = tk.Menu(top, tearoff=False)
        edit.add_command(label='Clear', command=self.clear, underline=0)
        edit.add_separator()
        top.add_cascade(label='Edit', menu=edit, underline=0)



    def get_search_class(self):
        return search_class_map[self.search_class_text.get()]

    # funkcija koja se poziva na dugme SEARCH
    def do_search(self):
        self.reset()
        # koju strategiju pretrage koristiti
        search_class = self.get_search_class()
        search = search_class(self.board)
        # kog "agenta" koristiti
        initial_state = RobotState

        # pokreni pretragu, meri vreme izvrsavanja
        start = time.perf_counter()
        path, self.processed, states = search.search(initial_state)
        end = time.perf_counter()
        print(self.path)
        if path is not None:
            self.path = map(lambda x: x.position, path)
        else:
            self.path = []

        print('-'*15, 'DONE', '-'*15)
        print('Time: {0} ms'.format(end - start))
        print('Processed nodes: {0}'.format(len(self.processed)))
        print('States left: {0}'.format(len(states)))
        if path is None:
            print('-'*15, 'NO SOLUTION', '-'*15)
        else:
            if len(path) != 0:
                print('Total cost: {0}'.format(path[-1].get_current_cost()))
            # ako je bilo resenja, iscrtaj ga
            for idx, p in enumerate(self.path):
                text = self.board.text[p[0]][p[1]]
                if len(text) == 0:
                    text += str(idx)
                else:
                    text += ',' + str(idx)
                self.board.text[p[0]][p[1]] = text
                self.update_board(p[0], p[1])


    # funkcija za debagovanje, u eksperimentalnoj fazi :)
    def move_icon(self, from_position, to_position, has_box=None):
        f = self.board.data[from_position[0]][from_position[1]]
        t = self.board.data[to_position[0]][to_position[1]]
        if ',' in f:
            s = f.split(',')
            f = s[0]
            if has_box:
                t = 'b,' + s[1]
            else:
                t += ',' + s[1]
        else:
            t += ',' + f
            f = '.'
        self.board.data[from_position[0]][from_position[1]] = f
        self.update_board(from_position[0], from_position[1])
        self.board.data[to_position[0]][to_position[1]] = t
        self.update_board(to_position[0], to_position[1])
        self.root.update()


    def debug(self):
        self.reset()
        position = self.board.find_position('r')
        for idx, p in enumerate(self.processed):
            self.move_icon(position, p.position, hasattr(p, 'has_box') and p.has_box)
            position = p.position
            time.sleep(0.2)
