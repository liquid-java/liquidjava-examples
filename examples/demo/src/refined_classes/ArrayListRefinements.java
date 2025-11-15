package refined_classes;
import java.util.ArrayList;
import liquidjava.specification.Refinement;
import liquidjava.specification.StateRefinement;
import liquidjava.specification.ExternalRefinementsFor;
import liquidjava.specification.Ghost;

@ExternalRefinementsFor("java.util.ArrayList")
@Ghost("int size")
public interface ArrayListRefinements<E> {

	@StateRefinement(to = "size(this) == 0")
	public abstract void ArrayList();

	@StateRefinement(to = "size(this) == size(old(this)) + 1")
	public abstract boolean add(E e);

	@StateRefinement(from = "size(this) > 0")
	public abstract E get(@Refinement("_ >= 0 && _ < size(this)") int index);

	// @StateRefinement(from = "size(this) > 0", to = "size(this) == size(old(this)) - 1")
	// public abstract void remove(@Refinement("_ >= 0 && _ < size(this)") int index);
}

class ArrayListRefinementsTest {
	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		list.add(3);

		// list.get(4);
	}
}
