package functions.utility;

import dataObjects.IDataSet;
import functions.feasibility.Feasibility;
import functions.feasibility.IFeasibility;
import generators.ISolutionGenerator;
import generators.SolutionGenerator;
import org.junit.jupiter.api.Test;
import reader.IReader;
import reader.Reader;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertTrue;

class RemoveNonClusteredInsertClusteredTest {

    @Test
    void solutions_generated_are_feasible_large() throws Throwable {
        IReader reader = new Reader();
        IDataSet dataSet = reader.readDataFromFile("larger_test_file");
        IFeasibility feasibility = new Feasibility(dataSet);

        Random random = new Random(11);
        ISolutionGenerator solutionGenerator = new SolutionGenerator(random);
        IOperator removeAndReinsert = new RemoveNonClusteredInsertClustered("",4,1,7,random,feasibility,dataSet);
        int vehicleAmount = dataSet.getVehicleAmount();
        int orderAmount = dataSet.getOrderAmount();
        int[] solution = solutionGenerator.createDummyStartSolution(vehicleAmount,orderAmount);
        solution = removeAndReinsert.apply(solution);
        int iterations = 50000;
        while(iterations>0) {
            solution = removeAndReinsert.apply(solution);
            assertTrue(feasibility.check(solution));
            iterations--;
        }
    }

    @Test
    void solutions_generated_are_feasible_medium() throws Throwable {
        IReader reader = new Reader();
        IDataSet dataSet = reader.readDataFromFile("medium_test_file");
        IFeasibility feasibility = new Feasibility(dataSet);

        Random random = new Random(11);
        ISolutionGenerator solutionGenerator = new SolutionGenerator(random);
        IOperator removeAndReinsert = new RemoveNonClusteredInsertClustered("",4,1,5,random,feasibility,dataSet);
        int vehicleAmount = dataSet.getVehicleAmount();
        int orderAmount = dataSet.getOrderAmount();
        int[] solution = solutionGenerator.createDummyStartSolution(vehicleAmount,orderAmount);
        solution = removeAndReinsert.apply(solution);
        int iterations = 100140;
        while(iterations>0) {
            solution = removeAndReinsert.apply(solution);
            assertTrue(feasibility.check(solution));
            iterations--;
        }
    }

    @Test
    void returns_correct_cluster_values() throws Exception {
        IReader reader = new Reader();
        IDataSet dataSet = reader.readDataFromFile("test_file");
        IFeasibility feasibility = new Feasibility(dataSet);

        Random random = new Random(11);
        ISolutionGenerator solutionGenerator = new SolutionGenerator(random);
        RemoveNonClusteredInsertClustered removeAndReinsert = new RemoveNonClusteredInsertClustered("",4,1,5,random,feasibility,dataSet);

        int[] solution = new int[]{1,1,2,2,0,3,3,4,4,0,0};
        List<List<Integer>> vehicleSchedules = removeAndReinsert.getVehicleSchedules(solution);
        removeAndReinsert.getOrderClusterValues(vehicleSchedules);

    }


}