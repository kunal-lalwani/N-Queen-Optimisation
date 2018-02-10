/**
 * Created by kunal.lalwani on 10-Nov-17.
 */
public class SAalgorithm {

    public int[] solve(int n, int maxNumOfIterations, double temperature, double coolingFactor) {
        int[] r = UtiliesSolving.generateRandomState(n);

        int costToBeat = UtiliesSolving.getHeuristicCost(r);

        for (int x = 0; x < maxNumOfIterations && costToBeat > 0; x++) {
            r = makeMove(r, costToBeat, temperature);
            costToBeat = UtiliesSolving.getHeuristicCost(r);
            temperature = Math.max(temperature * coolingFactor, 0.01);
        }

        return costToBeat == 0 ? r : null; 
    }

    private int[] makeMove(int r[], int costToBeat, double temp) {
        int n = r.length;

        while (true) {
            int nCol = (int) (Math.random() * n);
            int nRow = (int) (Math.random() * n);
            int tmpRow = r[nCol];
            r[nCol] = nRow;

            int cost = UtiliesSolving.getHeuristicCost(r);
            if (cost < costToBeat)
                return r;

            int dE = costToBeat - cost;
            double acceptProb = Math.min(1, Math.exp(dE / temp));

            if (Math.random() < acceptProb)
                return r;

            r[nCol] = tmpRow;
        }


    }

}
