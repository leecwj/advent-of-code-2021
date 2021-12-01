#!/usr/bin/env python

import fileinput

MEASUREMENTS = 3

last = []
increases = 0

for line in fileinput.input():
    number = int(line)

    if len(last) < MEASUREMENTS:
        last.append(number)
        continue

    nextt = last[1:]
    nextt.append(number)
    diff = sum(nextt) - sum(last)
    if (diff > 0):
        increases = increases + 1
    last = nextt

print(increases)
