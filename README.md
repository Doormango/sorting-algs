# Sorting Algorithms
A collection of common (and a few uncommon) sorting algorithms implemented in Java, and optimized as much as possible from known research.

## Algorithms Implemented

Currently, a total of 14 sorting algorithms have been implemented. Each algorithm is capable of sorting any array of generic items which are comparable.

* Selection sort
* Bubble sort
* Cocktail sort
* Odd-even sort
* Comb sort
* Gnome sort
* Insertion sort
* Shell sort
* Merge sort (top-down / recursive)
* Quick sort (with median-of-3 pivot selection & Fisher-Yates shuffle for performance guarantee)
* Heap sort
* Tree sort (using balanced AA-tree)
* Splay sort (using splay tree)
* Bogo sort (just for fun)

Implementations of sequential and binary search are also included.

## Execution and Testing

Execution, testing and comparison of the sorting algorithms is performed by `SortDriver.java`. Every algorithm in `SortingAlgorithms.java` has an associated annotation which stores the name, properties and time and space complexities of the algorithm. `SortDriver.java` will automatically test every method in `SortingAlgorithms.java` for which this annotation is present.

The advantage of this method is twofold: it allows slower algorithms to easily be excluded from testing, simply by commenting out the single-line annotation preceding the sorting method, and allows the driver to easily distinguish sorting methods from helper methods, even if they are also public, without storing explicitly calling each method manually.

Each algorithm is tested on a sorted, random and reverse-sorted list with size given by the final variable `SIZE` at the top of `SortDriver.java`, and range given by `RANGE`, located similarly. The algorithms are timed, and the running times are output. A small random list of only 20 items is also sorted just to demonstrate the algorithm working correctly.

## Future expansion

It is my plan to continue improving and expanding this library. The main additions planned are improvements or variations on the current algorithms, string sorts such as bucket sort, and one or two hybrid sorts like Timsort. The full list of planned future additions is below, in decreasing order of priority:

1. In-place merge sort
2. Merge sort (bottom-up / iterative)
3. Tim sort
4. Radix sort
5. Bucket sort
6. Counting sort
7. Shell sort with dynamic gaps
8. Tree sort using red-black tree
9. Smooth sort
10. Library sort
11. Tournament sort

One reason for the omission of (some of) the above sorts is the difficulty of finding accurate and thorough descriptions of the algorithms.

## Contributing

Pull requests are welcome - in fact, encouraged.

## Licence

This project is licensed under the [GNU GPLv3](https://choosealicense.com/licenses/gpl-3.0/)
licence.
