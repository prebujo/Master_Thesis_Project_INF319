package main;

import Heuristic.IHeuristic;
import Heuristic.RandomHeuristic;
import dataObjects.IDataSet;
import functions.*;
import functions.feasibility.*;
import functions.utility.*;
import reader.IReader;
import reader.Reader;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Throwable {
        System.out.println("Welcome to Preben's PDP solver! Please enter the name of the file you would like to read: ");
        Scanner scan = new Scanner(System.in);
        String fileName = scan.nextLine();

        IReader reader = new Reader();

        //creates a dataset from file
        IDataSet dataset = reader.readDataFromFile(fileName);

        System.out.println("Amount of Vehicles: "+dataset.getVehicleAmount());


        System.out.println("Amount of Orders: "+dataset.getOrderAmount());

        System.out.println("Amount of Factories: "+dataset.getFactoryAmount());

        System.out.println("Amount of stops: " +dataset.getStopAmount());

        System.out.println("Amount of weight Dimension: "+dataset.getWeightDimensions()[0]);

        System.out.println("Amount of distance Dimension: "+dataset.getDistanceDimensions()[0]);

        System.out.println("Factory Node 8: "+dataset.getFactories()[7]);
        System.out.println("Factory Node 2: "+dataset.getFactories()[1]);
        System.out.println("Factory Node 9: "+dataset.getFactories()[8]);
        System.out.println("Factory Node 10: "+dataset.getFactories()[9]);
        System.out.println("Factory Node 11: "+dataset.getFactories()[10]);

//        System.out.println("Factory Set 2: " + dataset.getFactorySet(1));
//        System.out.println("Factory Set 3: " + dataset.getFactorySet(2));

        System.out.println("Factory Stop Capacity 1: " + dataset.getFactoryStopCapacities()[0]);
//        System.out.println("Factory Stop Capacity 2: " + dataset.getFactoryStopCapacities()[1]);
//        System.out.println("Factory Stop Capacity 3: " + dataset.getFactoryStopCapacities()[2]);

        System.out.println("Location Node 2: "+dataset.getLocations()[1]);
//        System.out.println("Location Node 8: "+dataset.getLocations()[7]);
//        System.out.println("Location Node 9: "+dataset.getLocations()[8]);
//        System.out.println("Location Node 9: "+dataset.getLocations()[5]);

        System.out.println("Vehicle 1 can visit Node 8: "+dataset.getVehicleCanVisitNode()[0][8]);
        System.out.println("Vehicle 2 can visit Node 2: "+dataset.getVehicleCanVisitNode()[1][2]);
        System.out.println("Vehicle 1 can visit Node 6: "+dataset.getVehicleCanVisitNode()[0][6]);

        System.out.println("Vehicle 1 can Pickup Node 1: "+dataset.getVehicleCanPickupOrder()[0][1]);
        System.out.println("Vehicle 2 can Pickup Node 2: "+dataset.getVehicleCanPickupOrder()[1][2]);
        System.out.println("Vehicle 2 can Pickup Node 4: "+dataset.getVehicleCanPickupOrder()[1][4]);

        System.out.println("Vehicle 1 Starting Node: " + dataset.getVehicleStartingLocations()[0] + " and ending Node: " + dataset.getVehicleDestinationLocations()[0]);

//        System.out.println("Vehicle 2 Starting Node: " + dataset.getVehicleStartingLocations()[1] + " and ending Node: " + dataset.getVehicleDestinationLocations()[1]);

        System.out.println("Vehicle 1 Capacity: " + dataset.getVehicleWeightCapacities()[0]);
//        System.out.println("Vehicle 2 Capacity: " + dataset.getVehicleWeightCapacities()[1]);

        System.out.println("Order 1 Weight: " + dataset.getOrderWeights()[0]);
//        System.out.println("Order 3 Weight: " + dataset.getOrderWeights()[2]);

        System.out.println("Order 1 Penalty: " + dataset.getOrderPenalties()[0]);
//        System.out.println("Order 3 Penalty: " + dataset.getOrderPenalties()[2]);

        System.out.println("Distance interval 0: " + dataset.getDistanceIntervals()[0][0]);
//        System.out.println("Distance interval 2: " + dataset.getDistanceIntervals()[2]);

        System.out.println("Weight interval 0: " + dataset.getWeightIntervals()[0][0]);
//        System.out.println("Weight interval 2: " + dataset.getWeightIntervals()[2]);

        System.out.println("Cost per km 1 1 1: " + dataset.getKmCostMatrices()[0][0][0]);
//        System.out.println("Cost per km 2 1 2: " + dataset.getKmCostMatrices()[1][0][1]);

        System.out.println("Cost per kg 1 1 1: " + dataset.getKgCostMatrices()[0][0][0]);
//        System.out.println("Cost per kg 2 1 1: " + dataset.getKgCostMatrices()[1][0][0]);

        System.out.println("Cost fixed 1 1 1: " + dataset.getFixCostMatrices()[0][0][0]);
//        System.out.println("Cost fixed 2 1 2: " + dataset.getFixCostMatrices()[1][0][1]);

        System.out.println("Cost per stop 1 3 : " + dataset.getStopCosts()[0][2]);
        System.out.println("Cost per stop 1 1: " + dataset.getStopCosts()[0][0]);

        System.out.println("Amount of timewindows node 3: " + dataset.getTimeWindowAmounts()[2]);
//        System.out.println("Amount of timewindows node 8: " + dataset.getTimeWindowAmounts()[7]);

        System.out.println("lower TimeWindow 1 4 : " + dataset.getLowerTimeWindows()[0][3]);
        System.out.println("lower Timewindow 1 1: " + dataset.getLowerTimeWindows()[0][0]);

        System.out.println("upper TimeWindow 1 4 : " + dataset.getUpperTimeWindows()[0][3]);
        System.out.println("upper Timewindow 2 2: " + dataset.getUpperTimeWindows()[0][0]);

//        System.out.println("Travel Time 1 8 9 : " + dataset.getTravelTimes()[0][7][8]);
        System.out.println("Travel Time 1 2 3 : " + dataset.getTravelTimes()[0][0][2]);

//        System.out.println("Travel Distance 8 9 : " + dataset.getTravelDistances()[7][8]);
        System.out.println("Travel Distance 2 3 : " + dataset.getTravelDistances()[0][2]);

        //superSimple possible solution
//        int[] solutionRepresentation = {0,1,3};

        IFeasibility feasible5 = new CollectiveCheck(dataset);
        IFeasibility feasibility = new Feasibility(dataset);
        //simple possible solution
        int[] solutionRepresentation = {1,2,9,8,0,0,0,0,0,0,0};

        System.out.println("Is strange solution feasible? -> "+feasibility.check(solutionRepresentation));
        System.out.println("Is strange solution feasible? -> "+feasible5.check(solutionRepresentation));

        Random random = new Random(2);

        ISolutionGenerator solutionGenerator = new SolutionGenerator(random);
        int[] dummySolution = solutionGenerator.createDummySolution(dataset.getVehicleAmount(),dataset.getOrderAmount());

        for (int i = 0;i<5;i++) {


            System.out.println("Dummy Solution: ");
            for (int j = 0; j < dummySolution.length; j++) {
                System.out.print(dummySolution[j] + " ");
            }
            System.out.println();

            System.out.println("Randomized dummy solution: ");

            dummySolution = solutionGenerator.randomize(dummySolution);
        }
        int i = 5;

        while(i>0){
            System.out.println("Randomly assigned Solution: ");
            dummySolution = solutionGenerator.randomlyAssignOrders(dataset.getVehicleAmount(),dataset.getOrderAmount());
            for (int j = 0; j < dummySolution.length; j++) {
                System.out.print(dummySolution[j] + " ");
            }
            i--;
            System.out.println();
            System.out.println("Solution is feasible? -> "+ feasible5.check(dummySolution));
            System.out.println("Solution is feasible? -> "+ feasibility.check(dummySolution));
        }



        ObjectiveFunction objectiveFunction = new ObjectiveFunction(dataset);

        int solution = objectiveFunction.calculateSolution(solutionRepresentation);

        System.out.println("Solution:" + solution);

        IFeasibility feasible1 = new WeightAndVolumeFeasible(dataset);
        IFeasibility feasible2 = new FactoryDockFeasible(dataset);
        IFeasibility feasible3 = new TimeFeasible(dataset);
        IFeasibility feasible4 = new OrderVehicleAndOccuranceFeasibility(dataset);

        System.out.println("Solution is Weight and Volume feasible-> "+ feasible1.check(solutionRepresentation) );
        System.out.println("Solution is Factory Dock feasible-> "+ feasible2.check(solutionRepresentation) );
        System.out.println("Solution is Time feasible-> "+ feasible3.check(solutionRepresentation) );
        System.out.println("Solution is Order feasible-> "+ feasible4.check(solutionRepresentation) );

        IHeuristic heuristic = new RandomHeuristic(dataset, random);

        System.out.println("Best solution: ");

        dummySolution = heuristic.optimize().getBestSolution();
        for (int j = 0; j < dummySolution.length; j++) {
            System.out.print(dummySolution[j] + " ");
        }
        System.out.println();
        int resultCost = objectiveFunction.calculateSolution(dummySolution);
        int best = resultCost;
        System.out.println("Solution objective: "+ resultCost);
        System.out.println("Solution feasible? ->"+feasible5.check(dummySolution));

        IOperator swap2 = new SwapTwo(dataset,random,feasibility, "swap2");
        IOperator removeAndReinsert = new RemoveAndReinsert(dataset,random,feasibility, 2,2, "r&r2_2");

        int counter = 0;

        while(resultCost==best){
            dummySolution = swap2.apply(dummySolution);
            resultCost = objectiveFunction.calculateSolution(dummySolution);
            counter++;
        }

        System.out.println("Performing successful 2-swap: ");

        for (int j = 0; j < dummySolution.length; j++) {
            System.out.print(dummySolution[j] + " ");
        }

        System.out.println();

        System.out.println("Took " + counter + " iterations..");

        int k = 10;

        System.out.println(dummySolution.toString());

        while(k>0) {
            do {
                dummySolution = solutionGenerator.randomlyAssignOrders(dataset.getVehicleAmount(), dataset.getOrderAmount());
            }while(!feasibility.check(dummySolution));
            System.out.println("Solution before ReInsert: ");
            for (int j = 0; j < dummySolution.length; j++) {
                System.out.print(dummySolution[j] + " ");
            }
            System.out.println();
            resultCost = objectiveFunction.calculateSolution(dummySolution);
            counter = 0;
            best = resultCost;
            while (resultCost == best) {
                dummySolution = removeAndReinsert.apply(dummySolution);
                resultCost = objectiveFunction.calculateSolution(dummySolution);
                counter++;
            }
            System.out.println("Performing successful remove and reinsert: ");
            for (int j = 0; j < dummySolution.length; j++) {
                System.out.print(dummySolution[j] + " ");
            }
            System.out.println();
            System.out.println("Took " + counter + " iterations..");

            k--;
        }


    }
}