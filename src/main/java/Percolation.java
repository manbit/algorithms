import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    // dimension of the grid
    private int n;
    // 0 - open, 1 - closed
    private int[] sites;
    private WeightedQuickUnionUF wquf;
    private int openedNumber;

    // create size-by-size grid, with all sites blocked
    public Percolation(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException(String.format("size %d", size));
        }
        this.n = size;
        sites = new int[size * size + 2];
        wquf = new WeightedQuickUnionUF(size * size + 2);
        sites[0] = 1;
        sites[sites.length - 1] = 1;
    }

    // test client (optional)
    public static void main(String[] args) {

    }

    // open site (row row, column column) if it is not open already
    public void open(int row, int column) {
        if (!isValid(row, column)) {
            throw new IllegalArgumentException(String.format("Row %d, column %d", row, column));
        }

        if (isOpen(row, column)) {
            return;
        }

        final int index = getIndex(row, column);
        sites[index] = 1;
        openedNumber++;

        if (row == 1 && column == 1) {
            wquf.union(index, 0);
        }

        if (row == n && column == n) {
            wquf.union(index, sites.length - 1);
        }

        //        if the left site is open - union them
        if (column > 1 && isOpen(row, column - 1)) {
            wquf.union(index, getIndex(row, column - 1));
        }
        //        if the top site is open - union them
        if (row > 1 && isOpen(row - 1, column)) {
            wquf.union(index, getIndex(row - 1, column));
        }
        //        if the right site is open - union them
        if (column < n && isOpen(row, column + 1)) {
            wquf.union(index, getIndex(row, column + 1));
        }
        //        if the bottom site is open - union them
        if (row < n && isOpen((row + 1), column)) {
            wquf.union(index, getIndex(row + 1, column));
        }
    }

    // is site (row row, column column) open?
    public boolean isOpen(int row, int column) {
        if (!isValid(row, column)) {
            throw new IllegalArgumentException(String.format("Row %d, column %d", row, column));
        }
        return sites[getIndex(row, column)] == 1;
    }

    // is site (row row, column column) full?
    public boolean isFull(int row, int column) {
        if (!isValid(row, column)) {
            throw new IndexOutOfBoundsException(String.format("Row %d, column %d", row, column));
        }

        return this.wquf.connected(0, getIndex(row, column));
    }

    // does the system percolate?
    public boolean percolates() {
        return wquf.connected(0, n * n + 1);
    }

    private boolean isValid(int row, int column) {
        return row > 0 && row <= n * n && column > 0 && column <= n * n;
    }

    private int getIndex(int row, int column) {
        return (row - 1) * n + column;
    }

    public int numberOfOpenSites() {
        return openedNumber;
    }
}