#!/usr/bin/env python

import fileinput

MODE = 2

class Submarine(object):

    x = 0
    y = 0
    aim = 0

    def execute_v1(self, direction, magnitude):
        if direction == "forward":
            self.x = self.x + magnitude
        elif direction == "down":
            self.y = self.y + magnitude
        elif direction == "up":
            self.y = self.y - magnitude

    def execute_v2(self, direction, magnitude):
        if direction == "forward":
            self.x = self.x + magnitude
            self.y = self.y + self.aim * magnitude
        elif direction == "down":
            self.aim = self.aim + magnitude
        elif direction == "up":
            self.aim = self.aim - magnitude


if __name__ == "__main__":
    s = Submarine()

    for line in fileinput.input():
        tokens = line.split(' ')

        direction = tokens[0]
        magnitude = int(tokens[1])

        if MODE == 1:
            s.execute_v1(direction, magnitude)
        elif MODE == 2:
            s.execute_v2(direction, magnitude)

    print(s.x * s.y)
