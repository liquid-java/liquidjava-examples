import repair.regen.specification.StateRefinement;
import repair.regen.specification.StateSet;

@StateSet({"notready", "ready", "finished"})
public class Iterator {
	boolean hn;

	@StateRefinement(from = "notready(this)", to = "ready(this)")
	boolean hasNext(boolean hn) { return hn; }

	@StateRefinement(from = "ready(this)", to = "finished(this)")
	int next(boolean hn) { 
		if (hn) {
			return 1;
		} else {
			return -1;
		}
	}
}