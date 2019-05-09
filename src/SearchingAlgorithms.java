/**
 * @author Dario Trinchero
 */
public class SearchingAlgorithms {

	@SearchProperties(name = "Sequential Search", worstTime = "O(n)", bestTime = "", averageTime = "") // TODO Fill this in
	public static <T extends Comparable<T>> int sequentialSearch(T[] list, T item) {
		for (int i = 0; i < list.length; i++) {
			if (list[i].compareTo(item) == 0) return i;
		}
		return -1;
	}

	@SearchProperties(name = "Binary Search", worstTime = "O(n)", bestTime = "", averageTime = "") // TODO Fill this in
	public static <T extends Comparable<T>> int binarySearch(T[] list, T item) { // Requires sorted list
		int lo = 0, hi = list.length - 1, mid = lo + (hi - lo) / 2;

		while (lo < hi) {
			if (list[mid].compareTo(item) == 0) return mid;
			else if (list[mid].compareTo(item) < 0) lo = mid;
			else hi = mid;
			mid = lo + (hi - lo) / 2;
		}

		if (list[mid].compareTo(item) == 0) return mid;
		return -1;
	}
}
