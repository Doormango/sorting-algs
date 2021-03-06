import java.util.Stack;

/**
 * @author Dario Trinchero
 */
public class SortingAlgorithms {

	private static <T> void swap(T[] list, int i, int j) {
		T temp = list[i];
		list[i] = list[j];
		list[j] = temp;
	}

//	@SortProperties(name = "Selection Sort", worstTime = "O(n^2)", bestTime = "O(n^2)", averageTime = "O(n^2)", worstSpace = "O(1)", adaptive = false, stable = false)
	public static <T extends Comparable<T>> void selectionSort(T[] list) {
		for (int i = 0; i < list.length; i++) {
			int min = i;
			T value = list[min];

			for (int j = i + 1; j < list.length; j++) {
				if (list[j].compareTo(value) < 0) {
					min = j;
					value = list[min];
				}
			}

			if (min != i) swap(list, i, min);
		}
	}

//	@SortProperties(name = "Bubble Sort", worstTime = "O(n^2)", bestTime = "O(n)", averageTime = "O(n^2)", worstSpace = "O(1)", adaptive = true, stable = true)
	public static <T extends Comparable<T>> void bubbleSort(T[] list) {
		boolean sorted = false;
		int end = list.length;

		while (!sorted) {
			sorted = true;
			for (int i = 0; i + 1 < end; i++) {
				if (list[i].compareTo(list[i + 1]) > 0) {
					swap(list, i, i + 1);
					sorted = false;
				}
			}
			end--;
		}
	}
	
//	@SortProperties(name = "Cocktail Shaker Sort", worstTime = "O(n^2)", bestTime = "O(n)", averageTime = "O(n^2)", worstSpace = "O(1)", adaptive = true, stable = true)
	public static <T extends Comparable<T>> void cocktailSort(T[] list) {
		int start = 0, end = list.length - 2;
		boolean sorted;
		
		while (start <= end) {
			int newStart = end, newEnd = start;

			sorted = true;
			for (int i = start; i <= end; i++) {
				if (list[i].compareTo(list[i + 1]) > 0) { // forward pass
					swap(list, i, i + 1);
					sorted = false;
					newEnd = i;
				}
			}
			if (sorted) break;
			end = newEnd - 1;

			sorted = true;
			for (int i = end; i >= start; i--) {
				if (list[i].compareTo(list[i + 1]) > 0) { // reverse pass
					swap(list, i, i + 1);
					sorted = false;
					newStart = i;
				}
			}
			if (sorted) break;
			start = newStart + 1;
		}
	}
	
//	@SortProperties(name = "Odd-Even Sort", worstTime = "O(n^2)", bestTime = "O(n)", averageTime = "O(n^2)", worstSpace = "O(1)", adaptive = true, stable = true)
	public static <T extends Comparable<T>> void oddEvenSort(T[] list) { // often worse than bubble
		boolean sorted = false;
		
		while (!sorted) {
			sorted = true;
			for (int i = 1; i + 1 < list.length; i += 2) {
				if (list[i].compareTo(list[i + 1]) > 0) {
					swap(list, i, i + 1);
					sorted = false;
				}
			}
			
			for (int i = 0; i + 1 < list.length; i += 2) {
				if (list[i].compareTo(list[i + 1]) > 0) {
					swap(list, i, i + 1);
					sorted = false;
				}
			}
		}
	}
	
//	@SortProperties(name = "Comb Sort", worstTime = "O(n^2)", bestTime = "O(n*lg(n))", averageTime = "O(n^2 / 2^(# increments))", worstSpace = "O(1)", adaptive = true, stable = false)
	public static <T extends Comparable<T>> void combSort(T[] list) { // like bubble-shell hybrid
		int gap = list.length;
		double shrink = 1.3;
		boolean sorted = false;
		
		while (!sorted) {
			gap = (int) Math.floor(gap / shrink);
			if (gap <= 1) sorted = true;
			
			for (int i = 0; i + gap < list.length; i++) {
				if (list[i].compareTo(list[i + gap]) > 0) {
					swap(list, i, i + gap);
					sorted = false;
				}
			}
		}
	}
	
//	@SortProperties(name = "Gnome Sort", worstTime = "O(n^2)", bestTime = "O(n)", averageTime = "O(n^2)", worstSpace = "O(1)", adaptive = true, stable = true)
	public static <T extends Comparable<T>> void gnomeSort(T[] list) { // like bubble-insertion hybrid
		for (int i = 1; i < list.length; i++) {
			while (i > 0 && list[i - 1].compareTo(list[i]) > 0) {
				swap(list, i - 1, i);
				i--;
			}
		}
	}
	
	@SortProperties(name = "Insertion Sort", worstTime = "O(n^2)", bestTime = "O(n)", averageTime = "O(n^2)", worstSpace = "O(1)", adaptive = true, stable = true)
	public static <T extends Comparable<T>> void insertionSort(T[] list) {
		boolean sorted = true;
		for (int pos = list.length - 2; pos >= 0; pos--) { // place smallest value at 0 as sentinel
			if (list[pos].compareTo(list[pos + 1]) > 0) {
				swap(list, pos, pos + 1);
				sorted = false;
			}
		}
		if (sorted) return;
		
		int pos; // exploit sentinel to reduce comparisons
		for (int i = 2; i < list.length; i++) {
			T item = list[i];
			for (pos = i - 1; list[pos].compareTo(item) > 0; pos--) list[pos + 1] = list[pos];
			list[pos + 1] = item;
		}
	}

	/**
	 * Basic insertion sort on every sub-array of items spaced by gap; does not use sentinel
	 * 
	 * @param list array of values to be sorted
	 * @param gap space between values which are to be compared
	 */
	private static <T extends Comparable<T>> void insertionSort(T[] list, int gap) {
		int pos;
		for (int i = gap; i < list.length; i++) {
			T item = list[i];
			for (pos = i - gap; pos > -1 && list[pos].compareTo(item) > 0; pos -= gap) list[pos + gap] = list[pos];
			list[pos + gap] = item;
		}
	}

//	@SortProperties(name = "Shell Sort", worstTime = "unknown (gap-dependent)", bestTime = "O(n*lg(n))", averageTime = "unknown (gap-dependent)", worstSpace = "O(1)", adaptive = true, stable = false)
	public static <T extends Comparable<T>> void shellSort(T[] list) {
		final int[] GAPS = { 701, 301, 132, 57, 23, 10, 4, 1 }; // varies in implementation
		for (int gap : GAPS) insertionSort(list, gap);
	}

	private static <T extends Comparable<T>> void merge(T[] src, T[] dst, int lo, int mid, int hi) {
		int l = lo, r = mid + 1;
		for (int pos = lo; pos <= hi; pos++) {
			if (l > mid) dst[pos] = src[r++];
			else if (r > hi) dst[pos] = src[l++];
			else if (src[r].compareTo(src[l]) < 0) dst[pos] = src[r++];
			else dst[pos] = src[l++];
		}
	}

	private static <T extends Comparable<T>> void mergeSort(T[] src, T[] dst, int lo, int hi) {
		if (lo < hi) {
			int mid = lo + (hi - lo) / 2;
			mergeSort(dst, src, lo, mid);
			mergeSort(dst, src, mid + 1, hi);

			if (src[mid].compareTo(src[mid + 1]) <= 0) { // if already sorted
				System.arraycopy(src, lo, dst, lo, hi - lo + 1); // faster than "for (int i = lo; i <= hi; i++) dst[i] = src[i];"
				return;
			}
			
			merge(src, dst, lo, mid, hi);
		}
	}

	@SortProperties(name = "Merge Sort", worstTime = "O(n*lg(n))", bestTime = "O(n*lg(n))", averageTime = "O(n*lg(n))", worstSpace = "O(n)", adaptive = true, stable = true)
	public static <T extends Comparable<T>> void mergeSort(T[] list) {
		mergeSort(list.clone(), list, 0, list.length - 1);
	}
	
	/**
	 * Shuffles the elements in a given list in linear time.
	 * 
	 * @param list list to shuffle
	 */
	private static <T extends Comparable<T>> void fisherYatesShuffle(T[] list) {
		for (int i = 0; i < list.length - 1; i++) {
			int j = i + (int) (Math.random() * (list.length - i));
			if (i != j) swap(list, i, j);
		}
	}
	
	/**
	 * Returns the median of 3 comparable values, using 2 to 3 comparisons.
	 * 
	 * @param a first value
	 * @param b second value
	 * @param c third value
	 * @return median of three given values
	 */
	private static <T extends Comparable<T>> T median(T a, T b, T c) {
		if (a.compareTo(b) > 0) {
			if (b.compareTo(c) > 0) return b;
			else if (a.compareTo(c) > 0) return c;
			else return a;
		} else {
			if (a.compareTo(c) > 0) return a;
			else if (b.compareTo(c) > 0) return c;
			else return b;
		}
	}
	
	private static <T extends Comparable<T>> int partition(T[] list, int lo, int hi) {
		int l = lo, h = hi;
		T pivot = median(list[lo], list[lo + (hi - lo) / 2], list[hi]); // better estimate of true median
		
		while (true) {
			while (list[l].compareTo(pivot) < 0) l++;
			while (list[h].compareTo(pivot) > 0) h--;
			
			if (l >= h) return h;
			
			swap(list, l, h);
			l++;
			h--;
		}
	}
	
	private static <T extends Comparable<T>> void quickSort(T[] list, int lo, int hi) {
		if (lo < hi) {
			int p = partition(list, lo, hi);
			quickSort(list, lo, p);
			quickSort(list, p + 1, hi);
		}
	}

	@SortProperties(name = "Quicksort", worstTime = "O(n^2)", bestTime = "O(n*lg(n))", averageTime = "O(n*lg(n))", worstSpace = "O(n*lg(n))", adaptive = true, stable = false)
	public static <T extends Comparable<T>> void quickSort(T[] list) {
//		fisherYatesShuffle(list); // optional shuffle for performance guarantee
		quickSort(list, 0, list.length - 1);
	}
	
	/**
	 * Repair the heap with root node at given index.
	 * 
	 * @param heap array representation of heap
	 * @param root index of root node to sink
	 * @param end final index in heap
	 */
	private static <T extends Comparable<T>> void sink(T[] heap, int root, int end) {
		while (2 * root < end) {
			int child = 2 * root;
			if (child < end && heap[child].compareTo(heap[child + 1]) < 0) child++;
			if (heap[child].compareTo(heap[root]) <= 0) break;
			swap(heap, root, child);
			root = child;
		}
	}
	
	/**
	 * Constructs a heap using items in given array.
	 * 
	 * @param list array of items to put into heap
	 * @param end index of last item in array
	 */
	private static <T extends Comparable<T>> void heapify(T[] list, int end) {
		for (int i = end / 2; i >= 0; i--) sink(list, i, end);
	}
	
	@SortProperties(name = "Heapsort", worstTime = "O(n*lg(n))", bestTime = "O(n)", averageTime = "O(n*lg(n))", worstSpace = "O(1)", adaptive = true, stable = false)
	public static <T extends Comparable<T>> void heapSort(T[] list) {
		int end = list.length - 1; // end of heap & start of sorted section
		heapify(list, end);
		while (end > 0) {
			swap(list, 0, end--);
			sink(list, 0, end);
		}
	}
	
	/**
	 * Self-balancing binary search tree (AA tree) used in {@link SortingAlgorithms#treeSort(Comparable[])}.
	 */
	private static class AATree <T extends Comparable<T>> {
		private Node root = null;
		private int index = 0;

		private class Node {
			T data = null;
			int level = 1;
			Node left = null, right = null;

			public Node(T data) {
				this.data = data;
			}
		}

		private Node skew(Node tree) {
			if (tree == null) return null;
			else if (tree.left == null) return tree;
			else if (tree.left.level == tree.level) return rotateRight(tree);
			else return tree;
		}

		private Node split(Node tree) {
			if (tree == null) return null;
			else if (tree.right == null || tree.right.right == null) return tree;
			else if (tree.level == tree.right.right.level) {
				Node temp = rotateLeft(tree);
				temp.level++;
				return temp;
			} else return tree;
		}
		
		private Node rotateRight(Node tree) {
			Node temp = tree.left;
			tree.left = temp.right;
			temp.right = tree;
			return temp;
		}

		private Node rotateLeft(Node tree) {
			Node temp = tree.right;
			tree.right = temp.left;
			temp.left = tree;
			return temp;
		}

		private Node insert(Node tree, T item) {
			if (tree == null) tree = new Node(item);
			else if (item.compareTo(tree.data) < 0) tree.left = insert(tree.left, item);
			else tree.right = insert(tree.right, item);
			return split(skew(tree));
		}
		
		private void insert(T item) {
			root = insert(root, item);
		}

		private void inOrder(Node tree, T[] list) {
			if (tree != null) {
				inOrder(tree.left, list);
				list[index++] = tree.data;
				inOrder(tree.right, list);
			}
		}

		private void inOrder(T[] list) {
			index = 0;
			inOrder(root, list);
		}
	}

	@SortProperties(name = "Binary Tree Sort", worstTime = "O(n*lg(n))", bestTime = "O(n*lg(n))", averageTime = "O(n*lg(n))", worstSpace = "O(n)", adaptive = false, stable = true)
	public static <T extends Comparable<T>> void treeSort(T[] list) {
		AATree<T> tree = new AATree<>();
		for (T item : list) tree.insert(item);
		tree.inOrder(list);
	}
	
	/**
	 * Self-balancing binary search tree (splay tree) used in {@link SortingAlgorithms#splaySort(Comparable[])}
	 */
	public static class SplayTree <T extends Comparable<T>> { // TODO combine trees using inheritance
		private Node root = null;

		private class Node {
			private T data;
			private Node left = null, right = null;

			public Node(T item) {
				this.data = item;
			}
		}
		
		public void insert(T item) {
			if (root == null) {
				root = new Node(item);
				return;
			}
			root = splay(root, item);

			Node newNode = new Node(item);
			if (item.compareTo(root.data) < 0) {
				newNode.left = root.left;
				newNode.right = root;
				root.left = null;
			} else {
				newNode.right = root.right;
				newNode.left = root;
				root.right = null;
			}
			root = newNode;
		}

		private Node splay(Node tree, T item) {
			if (tree == null) return null;

			int cmp1 = item.compareTo(tree.data);
			if (cmp1 < 0) {
				if (tree.left == null) return tree; // item not in tree

				int cmp2 = item.compareTo(tree.left.data);
				if (cmp2 < 0) {
					tree.left.left = splay(tree.left.left, item);
					tree = rotateRight(tree);
				} else if (cmp2 > 0) {
					tree.left.right = splay(tree.left.right, item);
					if (tree.left.right != null) tree.left = rotateLeft(tree.left);
				}

				if (tree.left == null) return tree;
				else return rotateRight(tree);
			} else if (cmp1 > 0) {
				if (tree.right == null) return tree;

				int cmp2 = item.compareTo(tree.right.data);
				if (cmp2 < 0) {
					tree.right.left = splay(tree.right.left, item);
					if (tree.right.left != null) tree.right = rotateRight(tree.right);
				} else if (cmp2 > 0) {
					tree.right.right = splay(tree.right.right, item);
					tree = rotateLeft(tree);
				}

				if (tree.right == null) return tree;
				else return rotateLeft(tree);
			} else return tree;
		}

		private Node rotateRight(Node tree) {
			Node temp = tree.left;
			tree.left = temp.right;
			temp.right = tree;
			return temp;
		}

		private Node rotateLeft(Node tree) {
			Node temp = tree.right;
			tree.right = temp.left;
			temp.left = tree;
			return temp;
		}
		
		private void inOrder(T[] list) {
			int i = 0;
			Stack<Node> stack = new Stack<>();
			Node node = root;
			while (!stack.isEmpty() || node != null) {
				if (node != null) {
					stack.push(node);
					node = node.left;
				} else {
					node = stack.pop();
					list[i++] = node.data;
					node = node.right;
				}
			}
		}
	}

	@SortProperties(name = "Splaysort", worstTime = "O(n*lg(n))", bestTime = "", averageTime = "", worstSpace = "", adaptive = true, stable = false) // TODO fill this in
	public static <T extends Comparable<T>> void splaySort(T[] list) {
		SplayTree<T> tree = new SplayTree<>();
		for (T item : list) tree.insert(item);
		tree.inOrder(list);
	}

	private static <T extends Comparable<T>> boolean isSorted(T[] list) { // useful for assertions
		for (int i = 0; i < list.length - 1; i++) {
			if (list[i].compareTo(list[i + 1]) > 0) return false;
		}
		return true;
	}
	
//	@SortProperties(name = "Bogosort", worstTime = "unbounded", bestTime = "O(n)", averageTime = "O((n+1)!)", worstSpace = "O(1)", adaptive = false, stable = false)
	public static <T extends Comparable<T>> void bogoSort(T[] list) { // technically adaptive: linear on sorted data?
		while (!isSorted(list)) fisherYatesShuffle(list);
	}
}
