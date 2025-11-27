
from game import RobotGame

if __name__ == '__main__':
    game = RobotGame(rows=15, cols=15, board_file_path='boards/gs_vs_astar.brd')
    game.run()