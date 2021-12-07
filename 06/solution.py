#!/usr/bin/env python

import fileinput

T = 7
DAYS = 80


def idx(l):
    return l if l >= 0 else T-1


def idy(l):
    return l if l >= 0 else T+1


def mitose(ls):
    return list(map(idx, ls)) + list(map(idy, [l for l in ls if l < 0]))


def next_gen(ls):
    return mitose(map(lambda x: x-1, ls))


def lantern(ls, steps):
    return next_gen(ls) if steps == 1 else lantern(next_gen(ls), steps - 1)


if __name__ == "__main__":
    line = fileinput.input().readline()
    ls = map(int, line.split(','))
    print(len(lantern(ls, DAYS)))
