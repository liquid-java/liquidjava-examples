package refined_classes;

import liquidjava.specification.*;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;

@ExternalRefinementsFor("java.util.HashMap")
@StateSet({ "empty", "non-empty" })
@RefinementAlias("Empty(HashMap h) { size(h) == 0 }")
@RefinementAlias("NotEmpty(HashMap h) { size(h) > 0 }")
@RefinementAlias("Insert(HashMap h) { size(h) == size(old(h)) + 1 && (size(h) == capacity(h) * loadFactor(h) --> (capacity(h) == capacity(old(h)) * 2)) }") // TODO: remove because its aproximatelly double and probably useless
@RefinementAlias("Remove(HashMap h) { size(h) == size(old(h)) - 1 }")
@Ghost("int capacity")
@Ghost("int size")
@Ghost("float loadFactor")
public abstract class HashMapRefinements<K, V> {

	// ======== Constructors ======== //

	@StateRefinement(to = "Empty(this)")
	@StateRefinement(to = "capacity(this) == initialCapacity")
	@StateRefinement(to = "loadFactor(this) == loadFactor")
	public abstract void HashMap(int initialCapacity, float loadFactor);

	@StateRefinement(to = "Empty(this)")
	@StateRefinement(to = "capacity(this) == initialCapacity")
	@StateRefinement(to = "loadFactor(this) == 0.75f")
	public abstract void HashMap(int initialCapacity);

	@StateRefinement(to = "Empty(this)")
	@StateRefinement(to = "capacity(this) == 16")
	@StateRefinement(to = "loadFactor(this) == 0.75f")
	public abstract void HashMap();

	@StateRefinement(to = "Empty(this) || NotEmpty(this)")
	@StateRefinement(to = "capacity(this) == 0 || capacity(this) > 0") // Size of m is inaccessible
	@StateRefinement(to = "loadFactor(this) == 0.75f")
	public abstract void HashMap(Map<? extends K, ? extends V> m);

	// ======== Observers ========= //

	@Refinement("return == size(this)")
	public abstract int size();

	@Refinement("return == Empty(this)")
	public abstract boolean isEmpty();

	public abstract V get(Object key);

	public abstract boolean containsKey(Object key);

	public abstract Set<K> keySet();

	public abstract Collection<V> values();

	public abstract Set<Map.Entry<K,V>> entrySet();

	// ========= Mutators ========= //

	@StateRefinement(to = "Insert(this)")
	public abstract V put(K key, V value);

	@StateRefinement(to = "size(this) >= size(old(this))")
	@StateRefinement(to = "capacity(this) >= capacity(old(this))")
	public abstract void putAll(@Refinement("m != null") Map<? extends K, ? extends V> m);

	@StateRefinement(to = "Remove(this)")
	public abstract V remove(Object key);

	@StateRefinement(to = "Empty(this)")
	public abstract void clear();

	@StateRefinement(to = "Insert(this) || size(this) = size(old(this))")
	public abstract V computeIfAbsent(K key, @Refinement("mappingFunction != null") Function<? super K,? extends V> mappingFunction);

	@StateRefinement(to = "Remove(this) || size(this) = size(old(this))")
	public abstract V computeIfPresent(K key, @Refinement("remappingFunction != null") Function<? super K,? extends V> remappingFunction);

	@StateRefinement(to = "Insert(this) || Remove(this) || size(this) = size(old(this))")
	public abstract V compute(K key, @Refinement("remappingFunction != null") BiFunction<? super K,? super V,? extends V> remappingFunction);

	@StateRefinement(to = "Insert(this) || Remove(this) || size(this) = size(old(this))")
	public abstract V merge(K key, V value, @Refinement("remappingFunction != null") BiFunction<? super V,? super V,? extends V> remappingFunction);

	/* public static newHashMap(int numMappings) Not included because its static */
}
