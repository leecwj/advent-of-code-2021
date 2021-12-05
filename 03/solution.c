#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <string.h>

void process_line(int* counts, int countsc, char* line);
int gamma(int* counts, int countsc);
int epsilon(int* counts, int countsc);

int main(int argc, char** argv) {

    int* counts;
    int countsc;
    bool first = true;

    char* line = NULL;
    size_t linecap = 0;
    ssize_t read;

    while ((read = getline(&line, &linecap, stdin)) != -1) {

        if (first) {
            countsc = read - 1;
            counts = malloc(sizeof(int) * countsc);
            memset(counts, 0, read - 1);
            first = !first;
        }

        process_line(counts, countsc, line);
    }

    printf("%d\n", gamma(counts, countsc) *epsilon(counts, countsc));

    return 0;
}

void process_line(int* counts, int countsc, char* line) {
    for (int i = 0; i < countsc; i++) {
       if (line[i] == '0') {
            counts[i] = counts[i] - 1;
        } else if (line[i] == '1') {
            counts[i] = counts[i] + 1;
        } 
    }
}

int gamma(int* counts, int countsc) {

    int result = 0;

    for (int i = 0; i < countsc; i++) {
        if (counts[countsc - 1 - i] > 0) {
            result = result + (1 << i);
        }
    }

    return result;
}


int epsilon(int* counts, int countsc) {
    int mask = 1 << countsc;
    mask = mask - 1;

    return ~gamma(counts, countsc) & mask;
}
