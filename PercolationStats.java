import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private int[] percolateTotal;
    private int size;



    public PercolationStats(int n, int trials) {
        size = n;
        percolateTotal = new int[trials];
        for (int t = 0; t < trials; t++) {
            Percolation newTrial = new Percolation(n);
            while (!newTrial.percolates()) {
                int newRow = StdRandom.uniform(1, n+1);
                int newCol = StdRandom.uniform(1, n+1);
                if (!newTrial.isOpen(newRow, newCol)) {
                    newTrial.open(newRow, newCol);
                }
            }
            percolateTotal[t] = newTrial.numberOfOpenSites();

        }
    }

    public double mean() {
        return StdStats.mean(percolateTotal)/((double) size*size);
    }

    public double stddev() {
        return StdStats.stddev(percolateTotal)/((double) (size*size));

    }

    public double confidenceLo() {
        return mean() - (1.96 * stddev() / Math.sqrt(percolateTotal.length));
    }

    public double confidenceHi() {
        return mean() + (1.96 * stddev()) / Math.sqrt(percolateTotal.length);
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        if (args.length == 2 && (n > 0 && trials > 0)) {
            PercolationStats newPercolation = new PercolationStats(n, trials);
            System.out.println("Mean: " + Double.toString(newPercolation.mean()));
            System.out.println("Stddev: " + Double.toString(newPercolation.stddev()));
            System.out.println("Confidence interval: [" +
                    Double.toString(newPercolation.confidenceLo()) + " "
                    + Double.toString(newPercolation.confidenceHi()) + "]");
        } else {
            throw new IllegalArgumentException(
                    "Your entry must be (int gridSize) (int numTrials)"
            );
        }
    }
}


