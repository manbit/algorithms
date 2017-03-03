import edu.princeton.cs.algorithms.WeightedQuickUnionUF;
import edu.princeton.cs.introcs.In;

public class Percolation {

    private int[] openClose;
    private WeightedQuickUnionUF uf;
    private int size;
    private int n;
    private int openedNumber;

    public Percolation(int n) {
        this.n = n;
        size = n * n + 2;
        uf = new WeightedQuickUnionUF(size);
        openClose = new int[size];
//       Open virtual top and buttom
        openClose[0] = 1;
        openClose[size - 1] = 1;
    }                // create n-by-n grid, with all sites blocked

    public static void main(String[] args) {
    }   // test client (optional)

    private int yxToId(int y, int x) {
        return (y - 1) * n + x;
    }

    private int getTopIndex(int row, int col) {
        if (row > 1) {
            return yxToId(row - 1, col);
        }
        if (row == 1) {
            return 0;
        }
        return -1;
    }

    private int getButtomIndex(int row, int col) {
        if (row < n) {
            return yxToId(row + 1, col);
        } else if (row == n) {
            return size - 1;
        }
        return -1;
    }

    private int getLeftIndex(int row, int col) {
        if (col > 1) {
            return yxToId(row, col - 1);
        }
        return -1;
    }

    private int getRightIndex(int row, int col) {
        if (col < n && col > 1) {
            return yxToId(row, col - 1);
        } else if (col < n) {
            return yxToId(row, 2);
        }
        return -1;
    }

    public void open(int row, int col) {
        int index = yxToId(row, col);
        if (!isOpen(row, col)) {
            openClose[index] = 1;
            openedNumber++;
        }
        int topIndex = getTopIndex(row, col);
        if (topIndex != -1 && isOpen(topIndex) && !uf.connected(index, topIndex)) {
            uf.union(index, topIndex);
        }
        int rigthIndex = getRightIndex(row, col);
        if (rigthIndex != -1 && isOpen(rigthIndex) && !uf.connected(index, rigthIndex)) {
            uf.union(index, rigthIndex);
        }
        int buttomIndex = getButtomIndex(row, col);
        if (buttomIndex != -1 && isOpen(buttomIndex) && !uf.connected(index, buttomIndex)) {
            uf.union(index, buttomIndex);
        }
        int leftIndex = getLeftIndex(row, col);
        if (leftIndex != -1 && isOpen(leftIndex) && !uf.connected(index, leftIndex)) {
            uf.union(index, leftIndex);
        }
    }    // open site (row, col) if it is not open already

    public boolean isOpen(int row, int col) {
        return openClose[yxToId(row, col)] == 1;
    }  // is site (row, col) open?

    public boolean isOpen(int index) {
        return openClose[index] == 1;
    }  // is site (row, col) open?

    public boolean isFull(int row, int col) {
        return openClose[yxToId(row, col)] == 0;
    }  // is site (row, col) full?

    public int numberOfOpenSites() {
        return openedNumber;
    }       // number of open sites

    public boolean percolates() {
        return uf.connected(0, size - 1);
    }              // does the system percolate?
}
