
public class TreeTest {
	public static void main(String[] args) {
		MyTree<Object> tree = new MyTree<Object>(2);
		/*
		 * int count=15; for (int i=0;i<count;i++){ tree.add(i); } for (int
		 * i=0;i<count;i++){ System.out.println("parent of "+i+" : "
		 * +tree.parentIndex(i)); }
		 * 
		 * for (int i=0;i<count;i++){ System.out.println("of "+i+" child1: "
		 * +tree.childIndex(i,0)+" child2: "+tree.childIndex(i, 1)); }
		 * 
		 * tree.postorderTraversal(0); System.out.println();
		 * tree.preorderTraversal(0);
		 */

		
		//System.out.println(" ".split(" ").length);
		ParseTree parseTree = new ParseTree("(2*-2/-2*(3*-2))");
		System.out.println(parseTree.result());

	}

}
