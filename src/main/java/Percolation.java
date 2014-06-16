/**
 * By convention, the indices i and j are integers between 1 and N, where (1, 1) is the upper-left site:
 * Throw a java.lang.IndexOutOfBoundsException if either i or j is outside this range.
 * The constructor should take time proportional to N^2; all methods should take constant time plus a
 * constant number of calls to the union-find methods union(), find(), connected(), and count().
 */

public class Percolation {
    /**
     * create N-by-N grid, with all sites blocked
      */
    public Percolation(int N) {
        //TODO implement
    }

    /**
     *  open site (row i, column j) if it is not already
     * @param i
     * @param j
     */
    public void open(int i, int j) {
        //TODO implement
    }

    // is site (row i, column j) open?
    public boolean isOpen(int i, int j) {
        //TODO implement
        return false;
    }

    // is site (row i, column j) full?
    public boolean isFull(int i, int j) {
        // TODO implement
        return false;
    }

    // does the system percolate?
    public boolean percolates() {
        // TODO implement
        return false;
    }
}
