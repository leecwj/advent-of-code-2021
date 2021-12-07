#!/usr/bin/env python

import fileinput


class Line(object):
    def __init__(self, p1, p2):
        self.p1 = p1
        self.p2 = p2

    def is_horizontal(self):
        return self.p1[1] == self.p2[1]

    def is_vertical(self):
        return self.p1[0] == self.p2[0]


class Lines(object):
    def __init__(self):
        self._points = {}

    def increment_point(self, p):
        if self._points.get(p) is None:
            self._points[p] = 1
        else:
            self._points[p] = self._points[p] + 1

    def add_line(self, line):
        if line.is_horizontal():
            x1 = min(line.p1[0], line.p2[0])
            x2 = max(line.p1[0], line.p2[0])
            y = line.p1[1]
            for i in range(x1, x2+1):
                self.increment_point((i, y))
        elif line.is_vertical():
            y1 = min(line.p1[1], line.p2[1])
            y2 = max(line.p1[1], line.p2[1])
            x = line.p1[0]
            for i in range(y1, y2+1):
                self.increment_point((x, i))

    def intersection_count(self):
        return len([x for x in self._points.values() if x > 1])


if __name__ == "__main__":

    ls = Lines()

    for line in fileinput.input():
        ps = line.split("->")
        p1x, p1y = map(int, ps[0].split(","))
        p2x, p2y = map(int, ps[1].split(","))

        l = Line((p1x, p1y), (p2x, p2y))
        ls.add_line(l)

    print(ls.intersection_count())
