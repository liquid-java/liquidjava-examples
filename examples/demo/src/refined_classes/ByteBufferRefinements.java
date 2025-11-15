package refined_classes;

import liquidjava.specification.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.nio.ShortBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.FloatBuffer;
import java.nio.DoubleBuffer;

@ExternalRefinementsFor("java.nio.ByteBuffer")
@Ghost("int position")
@Ghost("int limit")
@Ghost("int capacity")
@Ghost("int mark")
@Ghost("boolean isReadOnly")
@Ghost("boolean isDirect")
@Ghost("ByteOrder byteOrder")
public interface ByteBufferRefinements {

	// ======== Constructors ======== //
	
	@Refinement("return != null")
	public abstract ByteBuffer allocate(@Refinement("_ >= 0") int capacity);
	
	@Refinement("return != null")
	public abstract ByteBuffer allocateDirect(@Refinement("_ >= 0") int capacity);
	
	@Refinement("return != null")
	public abstract ByteBuffer wrap(@Refinement("array != null") byte[] array);
	
	@Refinement("return != null")
	public abstract ByteBuffer wrap(@Refinement("array != null") byte[] array, @Refinement("_ >= 0") int offset, @Refinement("_ >= 0") int length);

	// ========= Observers ========== //
	
	// Note: position(), limit(), capacity(), remaining(), hasRemaining() are inherited from Buffer
	// and don't need to be redeclared here
	
	public abstract byte get(@Refinement("0 <= _ && _ < limit(this)") int index);
	
	public abstract boolean hasArray();
	
	@Refinement("return != null")
	public abstract byte[] array();
	
	@Refinement("return >= 0")
	public abstract int arrayOffset();
	
	public abstract char getChar(@Refinement("0 <= _ && _ <= limit(this) - 2") int index);
	
	public abstract short getShort(@Refinement("0 <= _ && _ <= limit(this) - 2") int index);
	
	public abstract int getInt(@Refinement("0 <= _ && _ <= limit(this) - 4") int index);
	
	public abstract long getLong(@Refinement("0 <= _ && _ <= limit(this) - 8") int index);
	
	public abstract float getFloat(@Refinement("0 <= _ && _ <= limit(this) - 4") int index);
	
	public abstract double getDouble(@Refinement("0 <= _ && _ <= limit(this) - 8") int index);
	
	@Refinement("return != null")
	public abstract ByteBuffer slice();
	
	@Refinement("return != null")
	public abstract ByteBuffer duplicate();
	
	@Refinement("return != null")
	public abstract ByteBuffer asReadOnlyBuffer();
	
	@Refinement("return != null")
	public abstract CharBuffer asCharBuffer();
	
	@Refinement("return != null")
	public abstract ShortBuffer asShortBuffer();
	
	@Refinement("return != null")
	public abstract IntBuffer asIntBuffer();
	
	@Refinement("return != null")
	public abstract LongBuffer asLongBuffer();
	
	@Refinement("return != null")
	public abstract FloatBuffer asFloatBuffer();
	
	@Refinement("return != null")
	public abstract DoubleBuffer asDoubleBuffer();
	
	@Refinement("return == isDirect(this)")
	public abstract boolean isDirect();
	
	@Refinement("return != null && return == byteOrder(this)")
	public abstract ByteOrder order();
	
	public abstract String toString();
	
	public abstract int hashCode();
	
	public abstract boolean equals(Object ob);
	
	public abstract int compareTo(@Refinement("_ != null") ByteBuffer that);

	// ========== Mutators ========== //
	
	@StateRefinement(from = "position(this) < limit(this)", to = "position(this) == position(old(this)) + 1")
	public abstract byte get();
	
	@StateRefinement(to = "position(this) >= position(old(this))")
	public abstract ByteBuffer get(@Refinement("dst != null") byte[] dst);
	
	@StateRefinement(to = "position(this) >= position(old(this))")
	public abstract ByteBuffer get(@Refinement("dst != null") byte[] dst, @Refinement("_ >= 0") int offset, @Refinement("_ >= 0") int length);
	
	@StateRefinement(from = "limit(this) - position(this) >= 2", to = "position(this) == position(old(this)) + 2")
	public abstract char getChar();
	
	@StateRefinement(from = "limit(this) - position(this) >= 2", to = "position(this) == position(old(this)) + 2")
	public abstract short getShort();
	
	@StateRefinement(from = "limit(this) - position(this) >= 4", to = "position(this) == position(old(this)) + 4")
	public abstract int getInt();
	
	@StateRefinement(from = "limit(this) - position(this) >= 8", to = "position(this) == position(old(this)) + 8")
	public abstract long getLong();
	
	@StateRefinement(from = "limit(this) - position(this) >= 4", to = "position(this) == position(old(this)) + 4")
	public abstract float getFloat();
	
	@StateRefinement(from = "limit(this) - position(this) >= 8", to = "position(this) == position(old(this)) + 8")
	public abstract double getDouble();
	
	@StateRefinement(to = "position(this) >= 0")
	public abstract ByteBuffer position(@Refinement("_ >= 0") int newPosition);
	
	@StateRefinement(to = "limit(this) >= position(this)")
	public abstract ByteBuffer limit(@Refinement("_ >= 0") int newLimit);
	
	@StateRefinement(to = "position(this) == 0 && limit(this) == capacity(this) && mark(this) == -1")
	public abstract ByteBuffer clear();
	
	@StateRefinement(from = "position(this) >= 0", to = "limit(this) == position(old(this)) && position(this) == 0 && mark(this) == -1")
	public abstract ByteBuffer flip();
	
	@StateRefinement(to = "position(this) == 0 && mark(this) == -1")
	public abstract ByteBuffer rewind();
	
	@StateRefinement(to = "mark(this) == position(this)")
	public abstract ByteBuffer mark();
	
	@StateRefinement(from = "mark(this) >= 0 && mark(this) <= position(this)", to = "position(this) == mark(old(this))")
	public abstract ByteBuffer reset();
	
	@StateRefinement(from = "position(this) < limit(this) && !isReadOnly(this)", to = "position(this) == position(old(this)) + 1")
	public abstract ByteBuffer put(byte b);
	
	@StateRefinement(from = "!isReadOnly(this)")
	public abstract ByteBuffer put(@Refinement("0 <= _ && _ < limit(this)") int index, byte b);
	
	@StateRefinement(from = "!isReadOnly(this)", to = "position(this) >= position(old(this))")
	public abstract ByteBuffer put(@Refinement("src != null && src != this") ByteBuffer src);
	
	@StateRefinement(from = "!isReadOnly(this)", to = "position(this) >= position(old(this))")
	public abstract ByteBuffer put(@Refinement("src != null") byte[] src, @Refinement("_ >= 0") int offset, @Refinement("_ >= 0") int length);
	
	@StateRefinement(from = "!isReadOnly(this)", to = "position(this) >= position(old(this))")
	public abstract ByteBuffer put(@Refinement("src != null") byte[] src);
	
	@StateRefinement(from = "limit(this) - position(this) >= 2 && !isReadOnly(this)", to = "position(this) == position(old(this)) + 2")
	public abstract ByteBuffer putChar(char value);
	
	@StateRefinement(from = "!isReadOnly(this)")
	public abstract ByteBuffer putChar(@Refinement("_ >= 0 && _ <= limit(this) - 2") int index, char value);
	
	@StateRefinement(from = "limit(this) - position(this) >= 2 && !isReadOnly(this)", to = "position(this) == position(old(this)) + 2")
	public abstract ByteBuffer putShort(short value);
	
	@StateRefinement(from = "!isReadOnly(this)")
	public abstract ByteBuffer putShort(@Refinement("_ >= 0 && _ <= limit(this) - 2") int index, short value);
	
	@StateRefinement(from = "limit(this) - position(this) >= 4 && !isReadOnly(this)", to = "position(this) == position(old(this)) + 4")
	public abstract ByteBuffer putInt(int value);
	
	@StateRefinement(from = "!isReadOnly(this)")
	public abstract ByteBuffer putInt(@Refinement("0 <= _ && _ <= limit(this) - 4") int index, int value);
	
	@StateRefinement(from = "limit(this) - position(this) >= 8 && !isReadOnly(this)", to = "position(this) == position(old(this)) + 8")
	public abstract ByteBuffer putLong(long value);
	
	@StateRefinement(from = "!isReadOnly(this)")
	public abstract ByteBuffer putLong(@Refinement("0 <= _ && _ <= limit(this) - 8") int index, long value);
	
	@StateRefinement(from = "limit(this) - position(this) >= 4 && !isReadOnly(this)", to = "position(this) == position(old(this)) + 4")
	public abstract ByteBuffer putFloat(float value);
	
	@StateRefinement(from = "!isReadOnly(this)")
	public abstract ByteBuffer putFloat(@Refinement("0 <= _ && _ <= limit(this) - 4") int index, float value);
	
	@StateRefinement(from = "limit(this) - position(this) >= 8 && !isReadOnly(this)", to = "position(this) == position(old(this)) + 8")
	public abstract ByteBuffer putDouble(double value);
	
	@StateRefinement(from = "!isReadOnly(this)")
	public abstract ByteBuffer putDouble(@Refinement("0 <= _ && _ <= limit(this) - 8") int index, double value);
	
	@StateRefinement(from = "!isReadOnly(this)", to = "position(this) == limit(old(this)) - position(old(this)) && limit(this) == capacity(this) && mark(this) == -1")
	public abstract ByteBuffer compact();
	
	public abstract ByteBuffer order(@Refinement("bo != null") ByteOrder bo);
}
