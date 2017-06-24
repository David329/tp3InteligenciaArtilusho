/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import weka.classifiers.trees.J48;
import weka.classifiers.trees.RandomForest;
import weka.classifiers.trees.RandomTree;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import weka.classifiers.Evaluation;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.Utils;
import java.lang.Math;

/**
 *
 * @author win7
 */
public class Clasificador {

    public static double kappaJ48;
    public static double kappaRandomForest;
    public static double kappaRandomTree;
    public static String kappaMejorAlgoritmo;

//    public static void main(String[] args) throws Exception  {
//        int opcion=1;
//        double kappaJ48;
//        double kappaRandomForest;
//        double kappaRandomTree;
//        double kappaMejorAlgoritmo;
//        if(opcion==1)Clasificador.AlgoritmoJ48();
//        else if(opcion==2)Clasificador.AlgoritmoRandomForest();
//        else if(opcion==3)Clasificador.AlgoritmoRandomTree();
//        else if(opcion==4/*todos*/){
//            kappaJ48=Clasificador.AlgoritmoJ48();
//            kappaRandomForest=Clasificador.AlgoritmoRandomForest();
//            kappaRandomTree=Clasificador.AlgoritmoRandomTree();
//            kappaMejorAlgoritmo=Math.max(kappaJ48, Math.min(kappaRandomForest, kappaRandomTree));
//            
//            System.out.println("Mejor LushoKappa: "+kappaMejorAlgoritmo);
//        }
//        
//        
//// weka.core.SerializationHelper.write(J48ModelPath,J4tree);
//    }
    public static String AlgoritmoJ48() throws FileNotFoundException, IOException, Exception {
        double percent = 50.0;
        BufferedReader breader;
        breader = new BufferedReader(new FileReader("C:\\Users\\DZZ\\Pictures\\tp2\\IAMLTB3\\src\\java\\Services\\haberman.arff"));
        Instances inst = new Instances(breader);

        inst.setClassIndex(inst.numAttributes() - 1); //set the last column as the class attribute 

        J48 J4tree = new J48();
        int seed = 1;
        Random rnd = new Random(seed);
        inst.randomize(rnd);

        String[] options;

        options = weka.core.Utils.splitOptions("-C 0.25 -M 2");
        J4tree.setOptions(options);

        System.out.println("Performing " + percent + "%split evaluation");

        int trainSize = (int) Math.round(inst.numInstances() * percent / 100);

        int testSize = inst.numInstances() - trainSize;

        Instances train = new Instances(inst, 0, trainSize);
        Instances test = new Instances(inst, trainSize, testSize);

        J4tree.buildClassifier(train);

        Evaluation eval = new Evaluation(train);
        eval.evaluateModel(J4tree, test);

        breader.close();
        kappaJ48 = eval.kappa();
        String resultado = eval.kappa()
                + eval.toSummaryString("\nResults\n======\n", false)
                + "training performance results of: "
                + J4tree.getClass().getSimpleName()
                + "\n---------------------------------"
                + eval.toSummaryString("\nResults", true)
                + "fmeasure: " + eval.fMeasure(1) + " Precision: " + eval.precision(1) + " Recall: " + eval.recall(1)
                + eval.toMatrixString() + eval.toClassDetailsString() + "AUC = " + eval.areaUnderROC(1)
                + "Training complete, please validate trained model";
//        System.out.println(eval.toSummaryString("\nResults\n======\n", false));
//
//        System.out.println("training performance results of: " + J4tree.getClass().getSimpleName()
//                + "\n---------------------------------");
//        System.out.println(eval.toSummaryString("\nResults", true));
//        System.out.println("fmeasure: " + eval.fMeasure(1) + " Precision: " + eval.precision(1) + " Recall: " + eval.recall(1));
//        System.out.println(eval.toMatrixString());
//        System.out.println(eval.toClassDetailsString());
//        System.out.println("AUC = " + eval.areaUnderROC(1));
//        System.out.println("Training complete, please validate trained model");
//
//        return eval.kappa();
        return resultado;
    }

    public static String AlgoritmoRandomForest() throws FileNotFoundException, IOException, Exception {
        double percent = 50.0;
        BufferedReader breader;
        breader = new BufferedReader(new FileReader("C:\\Users\\DZZ\\Pictures\\tp2\\IAMLTB3\\src\\java\\Services\\haberman.arff"));
        Instances inst = new Instances(breader);

        inst.setClassIndex(inst.numAttributes() - 1); //set the last column as the class attribute 

        RandomForest randomForest = new RandomForest();
        int seed = 1;
        Random rnd = new Random(seed);
        inst.randomize(rnd);

        System.out.println("Performing " + percent + "%split evaluation");

        int trainSize = (int) Math.round(inst.numInstances() * percent / 100);

        int testSize = inst.numInstances() - trainSize;

        Instances train = new Instances(inst, 0, trainSize);
        Instances test = new Instances(inst, trainSize, testSize);

        randomForest.buildClassifier(train);

        Evaluation eval = new Evaluation(train);
        eval.evaluateModel(randomForest, test);

        breader.close();
        kappaRandomForest = eval.kappa();
        String resultado = eval.kappa()
                + eval.toSummaryString("\nResults\n======\n", false)
                + "training performance results of: "
                + randomForest.getClass().getSimpleName()
                + "\n---------------------------------"
                + eval.toSummaryString("\nResults", true)
                + "fmeasure: " + eval.fMeasure(1) + " Precision: " + eval.precision(1) + " Recall: " + eval.recall(1)
                + eval.toMatrixString() + eval.toClassDetailsString() + "AUC = " + eval.areaUnderROC(1)
                + "Training complete, please validate trained model";
//        System.out.println(eval.toSummaryString("\nResults\n======\n", false));
//        System.out.println("training performance results of: " + randomForest.getClass().getSimpleName()+ "\n---------------------------------");
//        System.out.println(eval.toSummaryString("\nResults", true));
//        System.out.println("fmeasure: " + eval.fMeasure(1) + " Precision: " + eval.precision(1) + " Recall: " + eval.recall(1));
//        System.out.println(eval.toMatrixString());
//        System.out.println(eval.toClassDetailsString());
//        System.out.println("AUC = " + eval.areaUnderROC(1));
//        System.out.println("Training complete, please validate trained model");
        return resultado;
    }

    public static String AlgoritmoRandomTree() throws FileNotFoundException, IOException, Exception {
        double percent = 50.0;
        BufferedReader breader;
        breader = new BufferedReader(new FileReader("C:\\Users\\DZZ\\Pictures\\tp2\\IAMLTB3\\src\\java\\Services\\haberman.arff"));
        Instances inst = new Instances(breader);

        inst.setClassIndex(inst.numAttributes() - 1); //set the last column as the class attribute 

        RandomTree randomTree = new RandomTree();
        int seed = 1;
        Random rnd = new Random(seed);
        inst.randomize(rnd);

        System.out.println("Performing " + percent + "%split evaluation");

        int trainSize = (int) Math.round(inst.numInstances() * percent / 100);

        int testSize = inst.numInstances() - trainSize;

        Instances train = new Instances(inst, 0, trainSize);
        Instances test = new Instances(inst, trainSize, testSize);

        randomTree.buildClassifier(train);

        Evaluation eval = new Evaluation(train);
        eval.evaluateModel(randomTree, test);

        breader.close();
        kappaRandomTree = eval.kappa();
        String resultado = eval.kappa()
                + eval.toSummaryString("\nResults\n======\n", false)
                + "training performance results of: "
                + randomTree.getClass().getSimpleName()
                + "\n---------------------------------"
                + eval.toSummaryString("\nResults", true)
                + "fmeasure: " + eval.fMeasure(1) + " Precision: " + eval.precision(1) + " Recall: " + eval.recall(1)
                + eval.toMatrixString() + eval.toClassDetailsString() + "AUC = " + eval.areaUnderROC(1)
                + "Training complete, please validate trained model";
//        System.out.println(eval.toSummaryString("\nResults\n======\n", false));
//
//        System.out.println("training performance results of: " + randomForest.getClass().getSimpleName()+ "\n---------------------------------");
//        System.out.println(eval.toSummaryString("\nResults", true));
//        System.out.println("fmeasure: " + eval.fMeasure(1) + " Precision: " + eval.precision(1) + " Recall: " + eval.recall(1));
//        System.out.println(eval.toMatrixString());
//        System.out.println(eval.toClassDetailsString());
//        System.out.println("AUC = " + eval.areaUnderROC(1));
//        System.out.println("Training complete, please validate trained model");
        return resultado;
    }

    public static String mejorAlgoritmo() throws Exception {
        String rkappaJ48, rkappaRandomForest, rkappaRandomTree;
        double mejorAlgo;

        rkappaJ48 = Clasificador.AlgoritmoJ48();
        rkappaRandomForest = Clasificador.AlgoritmoRandomForest();
        rkappaRandomTree = Clasificador.AlgoritmoRandomTree();

        mejorAlgo = Math.max(kappaJ48, Math.max(kappaRandomForest, kappaRandomTree));
        if (mejorAlgo == kappaJ48) {
            kappaMejorAlgoritmo = rkappaJ48;
        } else if (mejorAlgo == kappaRandomForest) {
            kappaMejorAlgoritmo = rkappaRandomForest;
        } else if (mejorAlgo == kappaRandomTree) {
            kappaMejorAlgoritmo = rkappaRandomTree;
        }

        return kappaMejorAlgoritmo;
    }
}
