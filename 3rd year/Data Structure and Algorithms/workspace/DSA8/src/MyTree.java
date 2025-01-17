import java.util.ArrayList;

public class MyTree<T> {// k-ary tree
	ArrayList<T> list = new ArrayList<T>(10);
	int k = 0;

	public <T> MyTree(int degree) {
		this.k = degree;
	}

	public void resize(int newSize) {
		while (list.size() <= newSize)
			list.add(null);
	}

	public void add(T node) {
		list.add(node);
	}

	public boolean add(int parent, int childNumber, T data) {
		boolean answer = true;
		int childIndex = childIndex(parent, childNumber);
		resize(childIndex);
		if (get(childIndex) != null)
			answer = false;
		else {
			list.set(childIndex, data);
			answer = true;
		}

		return answer;
	}

	public int childIndex(int parent, int childNumber) {
		return k * parent + childNumber + 1;
	}

	public int parentIndex(int child) {
		int index = (child - 1) / k;
		return index;
	}

	public T get(int index) {
		return (index >= list.size()) ? null : list.get(index);
	}

	public void preorderTraversal(int i) {
		visit(i);
		for (int j = 0; j < this.k; j++) {
			int child = childIndex(i, j);
			if (get(child) != null)
				preorderTraversal(child);
		}
	}

	public void visit(int index) {
		System.out.print(get(index).toString() + " ");
	}

	public void postorderTraversal(int i) {
		for (int j = 0; j < this.k; j++) {
			int child = childIndex(i, j);
			if (get(child) != null)
				postorderTraversal(child);
		}
		visit(i);
	}
	
	void set(int index, T data){
		resize(index);
		list.set(index, data);
	}


}
