package q1.extract_method.refactored;

import java.util.List;

class Graph {
	String name;
	boolean contains(String p) {
		return name.contains(p);
	}
}
class Node extends Graph {
}
class Edge extends Graph {
}

public class A {
	public static void main(String[] args) {
		A a = new A();
		a.m1(null, null);
		a.m2(null, null);
	}
	
   Node m1(List<Node> nodes, String p) {
      extractedMethod(nodes, p);
      return null;
   }

   Edge m2(List<Edge> edgeList, String p) {
	  extractedMethod(edgeList, p);
      return null;
   }

   <T extends Graph> void extractedMethod(List<T> list, String p) {
		for (T object: list) {
			if (object.contains(p))
				System.out.println(object);
		}
		// other implementation
		return;
	}

}