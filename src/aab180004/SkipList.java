/* Starter code for LP2 */

// Change this to netid of any member of team
package aab180004;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

import static java.lang.Math.min;

// Skeleton for skip list implementation.

public class SkipList<T extends Comparable<? super T>> {
    static final int PossibleLevels = 33;

    static class Entry<E> {
	E element;
	Entry<E>[] next;
	Entry prev;

	public Entry(E x, int lev) {
	    element = x;
	    next = new Entry[lev];
	    // add more code if needed
	}

	public E getElement() {
	    return element;
	}
    }

    Entry<T> head,tail;
    int size, maxLevel;
    Entry<T>[] last;
    Random random;

    // Constructor
    public SkipList() {
        head = new Entry<T>(null,PossibleLevels);
        tail = new Entry<T>(null,PossibleLevels);
        size = 0;
        maxLevel = 1;
        last = new Entry[PossibleLevels];
        for (int i = 0; i < PossibleLevels; i++) {
            head.next[i] = tail;
        }
        random = new Random();
    }

    public void find(T x){
        Entry<T> p = head;

        for(int i  = maxLevel-1; i>=0; i-- ){
            while (p.next[i].element != null && p.next[i].element.compareTo(x) < 0){
                p = p.next[i];
            }
            last[i] = p;
        }
    }

    public int chooseLevel(){
        int lev = 1 + Integer.numberOfTrailingZeros(random.nextInt());
        lev = min(lev,maxLevel+1);
        return lev;
    }

    // Add x to list. If x already exists, reject it. Returns true if new node is added to list
    public boolean add(T x) {
        if(contains(x))
            return false;
        int lev = chooseLevel();
        Entry<T> ent = new Entry<>(x,lev);
        for(int i=0 ; i< lev ; i++){
            ent.next[i] = last[i].next[i];
            last[i].next[i] = ent;
        }
        ent.next[0].prev = ent;
        ent.prev = last[0];
        size++;
	    return true;
    }

    // Find smallest element that is greater or equal to x
    public T ceiling(T x) {
	return null;
    }

    // Does list contain x?
    public boolean contains(T x) {
        find(x);
	    return last[0].next[0] != null && last[0].next[0].element == x;
    }

    // Return first element of list
    public T first() {
	return null;
    }

    // Find largest element that is less than or equal to x
    public T floor(T x) {
	return null;
    }

    // Return element at index n of list.  First element is at index 0.
    public T get(int n) {
	return getLinear(n);
    }

    // O(n) algorithm for get(n)
    public T getLinear(int n) {
        if(n<0 || n > size-1 )
            throw new NoSuchElementException();
        Entry<T> p = head;

        for(int i=0; i<=n; i++)
            p = p.next[0];

	    return p.element;
    }

    // Optional operation: Eligible for EC.
    // O(log n) expected time for get(n). Requires maintenance of spans, as discussed in class.
    public T getLog(int n) {
        return null;
    }

    // Is the list empty?
    public boolean isEmpty() {
	return false;
    }

    // Iterate through the elements of list in sorted order
    public Iterator<T> iterator() {
	return null;
    }

    // Return last element of list
    public T last() {
	return null;
    }

    // Optional operation: Reorganize the elements of the list into a perfect skip list
    // Not a standard operation in skip lists. Eligible for EC.
    public void rebuild() {
	
    }

    // Remove x from list.  Removed element is returned. Return null if x not in list
    public T remove(T x) {
        if(!contains(x))
            return null;
        Entry<T> ent = last[0].next[0];

        for(int i= 0; i < ent.next.length; i++){
            last[i].next[i] = ent.next[i];
        }
        size--;

	    return ent.element;
    }

    // Return the number of elements in the list
    public int size() {
	return 0;
    }

    public static void main(String[] args){
        SkipList<Integer> sl= new SkipList<>();

        sl.add(100);
    }
}
