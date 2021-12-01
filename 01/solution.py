#!/usr/bin/env python

import fileinput

first = True
last = 0
increases = 0

for line in fileinput.input():
    number = int(line)

    if first:
        last = number
        first = False
        continue

    diff = number - last
    if (diff > 0):
        increases = increases + 1
    last = number

print(increases)
