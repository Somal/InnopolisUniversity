package database; //TODO: remove this line when submitting

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;

/**
 * TODO: This code should be implemented by a student
 */
public class MyFramework {

	/**
	 * TODO implement your own HashMap class!
	 * 
	 * @param <K>
	 *            key type
	 * @param <V>
	 *            value type
	 */
	public static class MyMap<K, V> implements Map<K, V> {
		int size = 0;
		ArrayList<Pair<K, V>> al = new ArrayList<Pair<K, V>>();

		@Override
		public void clear() {
			al = new ArrayList<Pair<K, V>>();
			size = 0;
		}

		@Override
		public boolean containsKey(Object arg0) {
			int hash = hashCode(arg0);
			Pair<K, V> pair = al.get(hash);
			if (pair == null)
				return false;
			return pair.first.equals(arg0);
		}

		@Override
		public boolean containsValue(Object arg0) {
			// TODO Auto-generated method stub
			for (int i = 0; i < size(); i++) {
				Pair<K, V> pair = al.get(i);
				if (pair.second.equals(arg0)) {
					// break;
					return true;
				}
			}
			return false;
		}

		@Override
		public Set<java.util.Map.Entry<K, V>> entrySet() {
			// TODO Auto-generated method stub
			Set<Map.Entry<K, V>> set = new HashSet<Map.Entry<K, V>>();

			Iterator<Pair<K, V>> it = al.iterator();
			while (it.hasNext()) {
				set.add(it.next());
			}
			return set;
		}

		@Override
		public V get(Object arg0) {
			int hash = hashCode(arg0);
			if (hash >= al.size())
				return null;
			Pair<K, V> pair = al.get(hash);
			if (pair == null)
				return null;
			return al.get(hash).second;
		}

		@Override
		public boolean isEmpty() {
			return size == 0;
		}

		@Override
		public Set<K> keySet() {
			// TODO Auto-generated method stub
			Set<K> set = new HashSet<K>();
			for (int i = 0; i < size(); i++) {
				Pair<K, V> pair = al.get(i);
				if (pair != null)
					set.add(pair.first);
			}

			return set;
		}

		@Override
		public V put(K arg0, V arg1) {
			int hash = hashCode(arg0);
			resize(hash + 1);
			V hashth = get(arg0);
			al.set(hash, new Pair<K, V>(arg0, arg1));
			if (hashth == null) {
				size++;
				return null;
			} else
				return hashth;
		}

		@Override
		public void putAll(Map<? extends K, ? extends V> arg0) {
			size += arg0.size();
			Collection<? extends V> values = arg0.values();
			Set<? extends K> keys = arg0.keySet();

			Iterator<? extends V> valuesIterator = values.iterator();
			Iterator<? extends K> keysIterator = keys.iterator();
			while (valuesIterator.hasNext()) {
				put(keysIterator.next(), valuesIterator.next());
			}

		}

		@Override
		public V remove(Object arg0) {
			size--;
			int hash = hashCode(arg0);
			V ans = get(arg0);
			al.set(hash, null);
			return ans;
		}

		@Override
		public int size() {
			return size;
		}

		@Override
		public Collection<V> values() {
			Collection<V> col = new ArrayList<V>();

			for (int i = 0; i < size(); i++) {
				Pair<K, V> pair = al.get(i);
				if (pair != null)
					col.add(pair.second);
			}

			return col;
		}

		public void resize(int newSize) {
			while (al.size() <= newSize)
				al.add(null);
		}

		public int hashCode(Object o) {
			if (o == null)
				return 0;
			else
				return (o.hashCode() % 100000)+1;
		}

		public class Pair<T1, T2> implements Map.Entry<T1, T2> {
			T1 first;
			T2 second;

			public Pair(T1 t1, T2 t2) {
				this.first = t1;
				this.second = t2;
			}

			@Override
			public T1 getKey() {
				return first;
			}

			@Override
			public T2 getValue() {
				return second;
			}

			@Override
			public T2 setValue(T2 value) {
				this.second = value;
				return null;
			}
		}

		public void show() {
			Iterator<Pair<K, V>> it = al.iterator();
			while (it.hasNext()) {
				Pair<K, V> pair = it.next();
				if (pair != null)
					System.out.println(pair.first + " " + pair.second);
			}
		}
	}

	/**
	 * TODO Implement your own binary search algorithms
	 * 
	 * @param data
	 *            - list of data to search. Assume data is sorted
	 * @param map
	 *            - method that converts object from the list to Comparable
	 *            object. Most likely this is a method that takes value from the
	 *            row. use map.apply(value) to start
	 * @param value
	 *            - value we are searching for
	 * @return index of the element in array if present, else -1
	 */
	@SuppressWarnings({ "rawtypes" })
	public static <T> int binarySearch(List<T> data, Function<Object, Comparable> map, Comparable value) {
		// Comparable fieldval = map.apply(row);
		// TODO implement

		if (data.isEmpty()) {
			return -1;
		}
		int midIndex = data.size() / 2;

		Comparable mid = map.apply(data.get(midIndex));
		int result = mid.compareTo(value);
		if (result == 0)
			return midIndex;
		if (result > 0)
			return binarySearch(data.subList(0, midIndex), map, value);
		else {
			int idx = binarySearch(data.subList(midIndex + 1, data.size()), map, value);
			return idx == -1 ? idx : midIndex + 1 + idx;
		}

	}

	@SuppressWarnings({ "rawtypes" })
	public static <T> int binarySearchOrNext(List<T> data, Function<Object, Comparable> map, Comparable value) {
		if (data.isEmpty())
			return 0;

		int midIndex = data.size() / 2;
		Comparable mid = map.apply(data.get(midIndex));
		int res = mid.compareTo(value);
		if (res == 0)
			return midIndex;
		if (res > 0)
			return binarySearchOrNext(data.subList(0, midIndex), map, value);
		if (res < 0)
			return midIndex + 1 + binarySearchOrNext(data.subList(midIndex + 1, data.size()), map, value);

		return 0;

	}

	/**
	 * TODO Implement your own sort that is VERY fast
	 * 
	 * @param data
	 *            data to sort
	 * @param map
	 *            - method that converts object from the list to Comparable
	 *            object. Most likely this is a method that takes value from the
	 *            row. use map.apply(value) to start
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static <T> void sort(List<T> data, Function<Object, Comparable> map) {
		// bucketSort(data, map);
		countingSort(data);
		// smoothSort(data,map);
	}

	public static <T> void print(List<T> al) {
		for (int i = 0; i < al.size(); i++)
			System.out.print(al.get(i) + " ");
		System.out.println();
	}

	/**
	 * TODO Your own hash function with uniform distribution for input strings
	 * 
	 * @param string
	 *            any string
	 * @return hash for the string
	 */
	public static int hash(String string) {
		int answer = 0;
		int key = 257;// 51
		int ppow = 1;
		int procent = Integer.MAX_VALUE;
		for (int i = 0; i < string.length(); i++) {
			int code = (int) string.charAt(i);
			answer = (answer + code * ppow) % procent;
			ppow = (ppow * key) % procent;
		}
		return Math.abs(answer);
		// return Math.abs(string.hashCode());
	}

	/**
	 * TODO Your own hash function with uniform distribution for floats
	 * 
	 * @param flt
	 *            floating point number
	 * @return hash code
	 */
	public static int hash(Float flt) {
		return hash(Double.toString(flt));
		// return Math.round(flt);
	}

	public static int hash(Integer flt) {
		// TODO: implement
		return Math.abs(flt);
	}

	/**
	 * TODO entry point for assignment #2 task
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO: here you will implement code at assignment #2
		// you should submit this file excluding package declaration

		// TODO: remove code below at assignment submission
		MyFrameworkTests.testYourBinarySearch();
		MyFrameworkTests.testYourSorting();
		MyFrameworkTests.testYourHashmap();
		MyFrameworkTests.testYourIntHash();
		MyFrameworkTests.testYourFloatHash();
		MyFrameworkTests.testYourStringHash();
		// testYourStringHash();
	}

	public static <T> void bucketSort(List<T> data, Function<Object, Comparable> map) {
		int sizeBucket = 100;
		int max = 10000000;
		int count = data.size();

		Object[] ps = new Object[(int) ((max / sizeBucket) + 1)];

		ArrayList<T>[] bucket = new ArrayList[(max / sizeBucket) + 1];
		for (int i = 0; i < (max / sizeBucket) + 1; i++)
			bucket[i] = new ArrayList<T>();

		Iterator<T> it = data.iterator();
		while (it.hasNext()) {
			T current = it.next();
			int cur = (Integer) current;
			boolean flag = bucket[cur / sizeBucket].add(current);
		}

		// print(store[1]);
		// parallel
		ExecutorService application = Executors.newCachedThreadPool();
		for (int i = 0; i < (max / sizeBucket) + 1; i++) {
			// partSort(bucket[i], map);
			ps[i] = new PartSorting<T>(bucket[i], map);
			((PartSorting<T>) ps[i]).run();
			// application.execute((Runnable) ps[i]);

		}
		// application.shutdown();

		int k = 0;
		for (int i = 0; i < (max / sizeBucket) + 1; i++)
			if (!bucket[i].isEmpty())
				for (int j = 0; j < bucket[i].size(); j++) {
					data.set(k, bucket[i].get(j));
					k++;
				}

	}

	public static <T> void countingSort(List<T> data) {
		MyMap<T, Integer> map = new MyMap<T, Integer>();

		for (T elem : data) {
			Integer previous = map.get(elem);
			if (previous == null)
				map.put(elem, 1);
			else
				map.put(elem, previous + 1);
		}

		int k = 0;
		for (T key : map.keySet())
			for (int i = 0; i < map.get(key); i++) {
				data.set(k, key);
				k++;
			}

	}

	public static <T> void smoothSort(List<T> data, Function<Object, Comparable> map) {
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		ArrayList<Boolean> flags = new ArrayList<Boolean>();
		numbers.add(1);
		numbers.add(1);
		flags.add(false);
		flags.add(false);
		int i = 1;
		while (numbers.get(i) < data.size()) {
			i++;
			numbers.add(numbers.get(i - 1) + numbers.get(i - 2) + 1);
			flags.add(false);
		}

	}

	public static void testYourStringHash() {
		System.out.println("============== TEST StringHash =============");
		int runs = 1000000;
		int maxHash = 1001;
		int[] hashes = new int[maxHash];
		Random r = new Random();
		for (int run = 0; run < runs; run++) {
			char[] chars = new char[1 + r.nextInt(50)];
			for (int i = 0; i < chars.length; i++) {
				chars[i] = (char) r.nextInt();
			}
			String s = new String(chars);
			hashes[MyFramework.hash(s) % maxHash]++;
		}
		Arrays.sort(hashes);
		if ((double) (hashes[maxHash - 1]) / hashes[0] > 1.3) {
			System.out.println("[ERR] seems like your string hash is not uniform :(");
		}
	}
}
