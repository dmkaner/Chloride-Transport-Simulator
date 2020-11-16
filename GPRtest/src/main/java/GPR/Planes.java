package GPR;

import smile.data.formula.Formula;
import smile.io.Read;
import smile.util.Paths;
import smile.data.*;


public class Planes {

    public static DataFrame data;
    public static Formula formula = Formula.lhs("y");

    public static double[][] x;
    public static double[] y;

    static {
        try {
            data = Read.arff("https://raw.githubusercontent.com/renatopp/arff-datasets/master/regression/2dplanes.arff");

            x = formula.x(data).toArray(false, CategoricalEncoder.DUMMY);
            y = formula.y(data).toDoubleArray();
        } catch (Exception ex) {
            System.err.println("Failed to load 'Planes': " + ex);
            System.exit(-1);
        }
    }
}
