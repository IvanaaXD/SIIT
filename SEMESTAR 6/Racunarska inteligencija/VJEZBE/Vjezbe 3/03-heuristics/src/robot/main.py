
from game import RobotGame

if __name__ == '__main__':
    game = RobotGame(rows=15, cols=15, default_search='UCS')
    game.run()