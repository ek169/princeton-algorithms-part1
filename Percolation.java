import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private WeightedQuickUnionUF ufGrid;

    private boolean[][] grid;

    private WeightedQuickUnionUF noBackWashUfGrid;

    private int size;

    public Percolation(int n) {

        size = n;
        grid = new boolean[size+1][size+1];
        ufGrid = new WeightedQuickUnionUF(size * size + 2);
        noBackWashUfGrid = new WeightedQuickUnionUF(size*size+2);

    }

    public void open(int row, int col) {
        grid[row][col] = true;
        int ufRepresentation = convertGridToUF(row, col);
        connectOpenUF(row + 1, col, ufRepresentation);
        connectOpenUF(row - 1, col, ufRepresentation);
        connectOpenUF(row, col - 1, ufRepresentation);
        connectOpenUF(row, col + 1, ufRepresentation);

        if (row == 1) {
            connectOpenUF(row, col, 0);
        }
        if (row == size) {
            connectOpenUF(row, col, size * size + 1);
        }
    }

    public boolean isOpen(int row, int col) {
        if ((row > 0 && row < size + 1) && (col > 0 && col < size + 1)) {
            return grid[row][col];
        } else {
            return false;
        }
    }


    public boolean isFull(int row, int col) {
        if ((row > 0 && row < size + 1) && (col > 0 && col < size + 1)) {
            return ufGrid.connected(0, convertGridToUF(row, col));
        } else {
            throw new IndexOutOfBoundsException();
        }

    }

    public int numberOfOpenSites() {
        int openSites = 0;
        for (int row = 1; row < size+1; row++) {
            for (int col = 1; col < size+1; col++) {
                if (grid[row][col]) {
                    openSites++;
                }
            }
        }
        return openSites;
    }

    public boolean percolates() {
        if (ufGrid.connected(0, size*size+1)) {
            return true;
        }
        return false;

    }

    private int convertGridToUF(int row, int col) {
        return (size * row - (size - col)) + 1;
    }



    private void connectOpenUF(int row, int col, int ufRepresentation) {
        if (isOpen(row, col)) {
            ufGrid.union(convertGridToUF(row, col), ufRepresentation);
            noBackWashUfGrid.union(convertGridToUF(row, col), ufRepresentation);
        }
        return;

    }



}
