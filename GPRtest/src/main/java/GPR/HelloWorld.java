package GPR;

import com.comsol.model.*; 
import com.comsol.model.util.*;

import smile.regression.GaussianProcessRegression;
import smile.regression.KernelMachine;
import smile.clustering.KMeans;
import smile.data.*;
import smile.math.MathEx;
import smile.math.kernel.GaussianKernel;
import smile.validation.CrossValidation;
import smile.validation.LOOCV;
import smile.validation.RMSE;

public class HelloWorld {
    
    public void test2DPlanes() throws Exception {
        System.out.println("2dplanes");

        MathEx.setSeed(19650218); // to get repeatable results.

        double[][] x = MathEx.clone(Planes.x);
        double[] y = Planes.y;

        int[] permutation = MathEx.permutate(x.length);
        double[][] datax = new double[4000][];
        double[] datay = new double[datax.length];
        for (int i = 0; i < datax.length; i++) {
            datax[i] = x[permutation[i]];
            datay[i] = y[permutation[i]];
        }

        CrossValidation cv = new CrossValidation(datax.length, 10);

        double[] prediction = cv.regression(datax, datay, (xi, yi) -> GaussianProcessRegression.fit(xi, yi, new GaussianKernel(34.866), 0.1));

        double[] sparsePrediction = cv.regression(10, datax, datay, (xi, yi) -> {
            KMeans kmeans = KMeans.fit(xi, 30);
            double[][] centers = kmeans.centroids;
            double r0 = 0.0;
            for (int l = 0; l < centers.length; l++) {
                for (int j = 0; j < l; j++) {
                    r0 += MathEx.distance(centers[l], centers[j]);
                }
            }
            r0 /= (2 * centers.length);
            System.out.println("Kernel width = " + r0);
            return GaussianProcessRegression.fit(xi, yi, centers, new GaussianKernel(r0), 0.1);
        });

        double[] nystromPrediction = cv.regression(datax, datay, (xi, yi) -> {
            KMeans kmeans = KMeans.fit(xi, 30);
            double[][] centers = kmeans.centroids;
            double r0 = 0.0;
            for (int l = 0; l < centers.length; l++) {
                for (int j = 0; j < l; j++) {
                    r0 += MathEx.distance(centers[l], centers[j]);
                }
            }
            r0 /= (2 * centers.length);
            System.out.println("Kernel width = " + r0);
            return GaussianProcessRegression.nystrom(xi, yi, centers, new GaussianKernel(r0), 0.1);
        });

        double rmse = RMSE.of(datay, prediction);
        double sparseRMSE = RMSE.of(datay, sparsePrediction);
        double nystromRMSE = RMSE.of(datay, nystromPrediction);

        System.out.println("Regular 10-CV RMSE = " + rmse);
        System.out.println("Sparse 10-CV RMSE = " + sparseRMSE);
        System.out.println("Nystrom 10-CV RMSE = " + nystromRMSE);
    }
    

    public static void main(String[] args) {
        HelloWorld tester = new HelloWorld();
        
        try {
			tester.test2DPlanes();
		} catch (Exception e) {
			System.out.println(e); 
            System.out.println("Planes Breaking");
		}
        
        ModelUtil.initStandalone(true);
        run(); 
    }
   
    public static Model run() {
        Model model = ModelUtil.create("Model"); 
        model.modelNode().create("comp1"); 
        model.geom().create("geom1", 3); 
        model.geom("geom1").feature().create("blk1", "Block"); 
        model.geom("geom1").feature("blk1").set("size", new String[]{"0.1", "0.2", "0.5"});
        model.geom("geom1").run("fin");
        return model; 
    } 
}
