package refined_classes.in_progress;

import java.io.InputStream;

import liquidjava.specification.*;

@ExternalRefinementsFor("java.io.BufferedInputStream")
@Ghost("int afterMark")
@Ghost("int readLimit")
@Ghost("boolean isMarked")
@StateSet({ "open", "closed" })
public interface BufferedInputStreamRefinements {

	@StateRefinement(to = "open(this) && isMarked(this) == false")
	public abstract void BufferedInputStream(InputStream in);

	@StateRefinement(to = "open(this) && isMarked(this) == false ")
	public abstract void BufferedInputStream(InputStream in, @Refinement("_ > 0") int size);

	@StateRefinement(from = "open(this) && isMarked(this) == false")
	public abstract int read();

	@StateRefinement(from = "open(this)", to = "afterMark(this) == afterMark(old(this)) + 1")
	@Refinement("return >= -1")
	public abstract int read(byte[] b, @Refinement("_ >= 0") int off, @Refinement("_ > 0") int len);

	@StateRefinement(from = "open(this) && afterMark(this) >= afterMark(old(this))")
	@Refinement("return >= 0")
	public abstract long skip(@Refinement("_ >= 0") long n);

	@StateRefinement(from = "open(this)")
	@Refinement("return >= 0")
	public abstract int available();

	@StateRefinement(from = "open(this)", to = "isMarked(this) && readLimit(this) == readlimit && afterMark(this) == 0")
	public abstract void mark(@Refinement("_ >= 0") int readlimit);

	@StateRefinement(from = "open(this) && isMarked(this) && afterMark(this) <= readLimit(this)", to = "afterMark(this) == 0 && readLimit(this) == 0")
	public abstract void reset();

	public abstract boolean markSupported();

	@StateRefinement(from = "open(this)", to = "closed(this)")
	public abstract void close();
}
