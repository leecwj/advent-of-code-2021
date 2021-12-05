#!/usr/bin/env python

import fileinput

x = 0
y = 0

for line in fileinput.input():
    tokens = line.split(' ')

    direction = tokens[0]
    magnitude = int(tokens[1])

    if direction == "forward":
        x = x + magnitude
    elif direction == "down":
        y = y + magnitude
    elif direction == "up":
        y = y - magnitude

print(x * y)
