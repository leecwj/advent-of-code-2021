#!/usr/bin/env python
"""
Advent of Code 2021 Day 3 Part 2
Solution
"""

import fileinput


def b2d(b):
    result = 0
    for i in range(len(b)):
        if b[i] == '1':
            result = result + 2 ** (len(b) - 1 - i)
    return result


def rating(numbers, cull_fn):
    numbers = sorted(numbers)
    l = len(numbers[0])

    for i in range(l):
        zeros = len([x for x in numbers if x[i] == '0'])
        if cull_fn(len(numbers), zeros):
            numbers = numbers[zeros:]
        else:
            numbers = numbers[:zeros]

        if len(numbers) == 1:
            return b2d(numbers[0])

    return -1


def o2_rating(numbers):
    return rating(numbers, (lambda n, zeros: zeros <= n - zeros))


def co2_rating(numbers):
    return rating(numbers, (lambda n, zeros: n - zeros < zeros))


if __name__ == "__main__":
    numbers = []
    for line in fileinput.input():
        numbers.append(line[:-1])

    print(o2_rating(numbers) * co2_rating(numbers))
