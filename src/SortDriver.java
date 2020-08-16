import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;

public class SortDriver {
	
	private static final int SIZE = 30000, RANGE = 5000;
	private static final Integer[] smallRand, rand, sorted, rev;
	
	static {
		smallRand = randomInts(20, 100);
		rand = randomInts(SIZE, RANGE);
		sorted = randomInts(SIZE, RANGE);
		rev = randomInts(SIZE, RANGE);

		Arrays.sort(sorted);
		Arrays.sort(rev, Collections.reverseOrder());
	}
	
	public static void main(String[] args) { // only methods with annotation are run
		for (Method alg : SortingAlgorithms.class.getDeclaredMethods()) {
			SortProperties p = alg.getAnnotation(SortProperties.class);
			if (p != null) demo(alg, p);
		}
		System.out.println();
	}
	
	private static Integer[] randomInts(int size, int range) {
		Integer[] array = new Integer[size];
		for (int i = 0; i < array.length; i++) array[i] = (int) (Math.random() * range) + 1;
		return array;
	}
	
	private static void demo(Method alg, SortProperties p) {
		Integer[] demoList = Arrays.copyOf(smallRand, smallRand.length);
		listProperties(p);
		
		System.out.println("\n\t" + Arrays.toString(demoList));
		testSort(alg, demoList);
		System.out.println("   >>\t" + Arrays.toString(demoList));
		
		System.out.printf("\n\t%d Random Values: %.3f ms", SIZE, testSort(alg, Arrays.copyOf(rand, rand.length)));
		System.out.printf("\n\t%d Sorted Values: %.3f ms", SIZE, testSort(alg, Arrays.copyOf(sorted, sorted.length)));
		System.out.printf("\n\t%d Reversed Values: %.3f ms", SIZE, testSort(alg, Arrays.copyOf(rev, rev.length)));
	}
	
	private static void listProperties(SortProperties p) {
		System.out.printf("\n\n%s:\n\n\tWorst Time Complexity: %s\n\tBest Time Complexity: %s\n", p.name(), p.worstTime(), p.bestTime());
		System.out.printf("\tAverage Time Complexity: %s\n\tWorst Space Complexity: %s\n\n", p.averageTime(), p.worstSpace());
		System.out.println(p.adaptive() ? "   +\tAdaptive" : "   -\tNot adaptive");
		System.out.println(p.stable() ? "   +\tStable" : "   -\tUnstable");
	}

	private static double testSort(Method alg, Integer[] data) {
		long startTime = 0l, endTime = 0l;
		Object[] invokeArgs = new Object[] { data };

		try {
			startTime = System.nanoTime();
			alg.invoke(null, invokeArgs);
			endTime = System.nanoTime();
		} catch (Exception e) {
			e.printStackTrace();
		}

		assert isSorted(data);
		return (double) (endTime - startTime) / 1000000;
	}
	
	private static <T extends Comparable<T>> boolean isSorted(T[] list) {
		for (int i = 0; i < list.length - 1; i++) {
			if (list[i].compareTo(list[i + 1]) > 0) return false;
		}
		return true;
	}
}
