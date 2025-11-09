public class IteratorWrong {
	public static void main() {
		Iterator i = new Iterator(); // i -> "notready"
		boolean hn = true;
		// i.next() requires state "ready" but i is "notready"
		int x = i.next(hn); // shouldn't be accepted by the typechecker ;-;
	}
}

