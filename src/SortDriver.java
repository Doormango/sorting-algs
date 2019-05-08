import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;

public class SortDriver {
	
	private static final int SIZE = 30000, RANGE = 5000;
	private static final Integer[] smallRand, rand, sorted, rev;
	
	static {
		smallRand = random(20, 100);
		rand = random(SIZE, RANGE);
		sorted = random(SIZE, RANGE);
		rev = random(SIZE, RANGE);
		Arrays.sort(sorted);
		Arrays.sort(rev, Collections.reverseOrder());
	}
	
	public static void main(String[] args) { // Only methods with annotation are run
		for (Method alg : SortingAlgorithms.class.getDeclaredMethods()) {
			SortProperties p = alg.getAnnotation(SortProperties.class);
			if (p != null) demo(alg, p);
		}
	}
	
	private static Integer[] random(int size, int range) {
		Integer[] array = new Integer[size];
		for (int i = 0; i < array.length; i++) array[i] = (int) (Math.random() * range) + 1;
		return array;
	}
	
	private static void demo(Method alg, SortProperties p) {
		Integer[] demoList = Arrays.copyOf(smallRand, smallRand.length);
		listProperties(p);
		
		System.out.println("\n\t" + Arrays.toString(demoList));
		sort(demoList, alg);
		System.out.println("   >>\t" + Arrays.toString(demoList));
		
		System.out.printf("\n\t%d Random Values: %.3f ms", SIZE, time(alg, rand));
		System.out.printf("\n\t%d Sorted Values: %.3f ms", SIZE, time(alg, sorted));
		System.out.printf("\n\t%d Reversed Values: %.3f ms", SIZE, time(alg, rev));
	}
	
	private static void listProperties(SortProperties p) {
		System.out.printf("\n\n%s:\n\n\tWorst Time Complexity: %s\n\tBest Time Complexity: %s\n", p.name(), p.worstTime(), p.bestTime());
		System.out.printf("\tAverage Time Complexity: %s\n\tWorst Space Complexity: %s\n\n", p.averageTime(), p.worstSpace());
		System.out.println(p.adaptive() ? "   +\tAdaptive" : "   -\tNot adaptive");
		System.out.println(p.stable() ? "   +\tStable" : "   -\tUnstable");
	}
	
	private static void sort(Integer[] array, Method m) {
		try {
			if (m.getDeclaringClass() == SortingAlgorithms.class) m.invoke(null, new Object[] { array });	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static double time(Method alg, Integer[] data) {
		Integer[] array = Arrays.copyOf(data, data.length);
		long startTime = System.nanoTime();
		sort(array, alg);
		return (double) (System.nanoTime() - startTime) / 1000000;
	}
}
