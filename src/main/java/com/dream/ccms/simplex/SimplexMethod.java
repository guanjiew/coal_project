package com.dream.ccms.simplex;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Random;

public class SimplexMethod {
    private BigDecimal[][] tableaux;
    private int numberOfConstraints;
    private int numberOfOriginalVariables;
    private int[] basicVariables;
    private BigDecimal optimization;
    private int Customize =5000;
    public BigDecimal getOptimization() {
        return optimization;
    }

    public BigDecimal[] getOptimalExtreme() {
        return optimalExtreme;
    }

    private BigDecimal[] optimalExtreme;
    private MaxOrMin maxOrMinium;
    private SimplexMethod.Constraint[] constraintsOperator;
    public enum Constraint {lessThan, equal, greatherThan}
    public enum MaxOrMin {MAXIMIZE, MINIMIZE}
    private SimplexMethod(BigDecimal[][] tableaux, int numberOfConstraint,
                          int numberOfOriginalVariable,SimplexMethod.Constraint[] constraintsOp,
                          MaxOrMin maxOrMin) {
        this.maxOrMinium = maxOrMin;
        this.constraintsOperator =constraintsOp;
        this.numberOfConstraints = numberOfConstraint;
        this.numberOfOriginalVariables = numberOfOriginalVariable;
        this.tableaux = tableaux;
        basicVariables = new int[numberOfConstraints];
        for (int i = 0; i < numberOfConstraints; i++)
            basicVariables[i] = numberOfOriginalVariables + i;
    }

    public void runSimplex() {
        /*
        * There are two phase for the simplex method
        * Phase1:Find any feasible extreme point in the feasible solution
        * When we encounter mixed operator problems,the first solution we have is not feasible.
        * Thus we need to go to phase1 and pivot any column until we find an feasible solution;
        * There is no rule of thumb of which column to pick as pivoting column, so we are going over
        * all the columns from start to end in order.
        * If there is no such feasible extreme point, then throw a feasible region undefined exception
        * If such feasible extreme point is founded, finish Phase 1 and go into Phase2 to perform
        * standard simplex method.
        * Phase2: From the current feasible extreme point, walk through the simplex table.
        * Throw a feasible region unbounded exception if there is no extreme point
        * */

        //Phase1
        show();
        if(!isExtremePoint(extremePoint())){
            boolean exception = true;
            System.out.println("Enter Phase1.Find an extreme point of the feasible region");
            for(int k =0; k <= Customize; k ++ ) {
                int j = new Random().nextInt(numberOfConstraints+numberOfOriginalVariables);
                int pivotRow = minPosRatioRule(j);
                if (pivotRow != -1) {
                    pivoting(pivotRow, j);
                    basicVariables[pivotRow] = j;
                }
                //show();
                if(isExtremePoint(extremePoint())){
                    exception = false;
                    System.out.println("Finish Phase1. Enter Phase 2");
                    break;
                }
            }
            if (exception) {
                throw new ArithmeticException("No optimal solution found as feasible region is undefined");
            }
        }

        //Phase2
        while (true) {
            //show();
            int pivotCol = pivotColumnEnter();
            if (pivotCol == -1) {
                optimization = optimalSolution();
                if(maxOrMinium.equals(MaxOrMin.MAXIMIZE)){
                optimalExtreme = Arrays.copyOfRange(extremePoint(),0, numberOfOriginalVariables);}
                else{
                    optimalExtreme = Arrays.copyOfRange(tableaux[numberOfConstraints],numberOfOriginalVariables,numberOfOriginalVariables+numberOfConstraints);
                }
                break;
            }
            int pivotRow = minPosRatioRule(pivotCol);
            if (pivotRow == -1) {
                throw new ArithmeticException("No optimal solution found as feasible region is unbounded");
            }
            pivoting(pivotRow, pivotCol);
            basicVariables[pivotRow] = pivotCol;
        }
    }

    private int pivotColumnEnter() {
        /*
         * Optimality Criterion：
         * If the objective row of a tableau has zero entries in the columns labeled by basic variables
         * and no negative entries in the columns labeled by nonbasic variables
         * Then the solution represented by the tableau is optimal
         * */
        int pivotCol = 0;
        for (int col = 1; col < numberOfConstraints + numberOfOriginalVariables; col++) {
            if (tableaux[numberOfConstraints][col].compareTo(tableaux[numberOfConstraints][pivotCol])==-1)
                pivotCol = col;
        }
        if (tableaux[numberOfConstraints][pivotCol].compareTo(BigDecimal.ZERO) >= 0)
            return -1;
        else
            return pivotCol;
    }

    private int minPosRatioRule(int pivotCol) {
        /*
        The selection of the departing variable:
        The ratios of the rightmost column entries to the corresponding entries in the pivotal column
        were determined by how much we could increase the entering variable.
        These ratios are called theta-ratios.
        The smallest nonnegative theta-ratio is the largest possible value for the entering variable.
        If all the entries in the pivotal column above the objective row are either zero or negative,
         then the entering variable can be made as large as we wish.
        Hence, the given problem has no finite optimal solution, and we can stop.
         */
        int pivotRow = -1;
        for (int i = 0; i < numberOfConstraints; i++) {
            if (tableaux[i][pivotCol].compareTo(BigDecimal.ZERO) <= 0) {
                continue;
            } else if (pivotRow == -1) pivotRow = i;
            else if ((tableaux[i][numberOfConstraints + numberOfOriginalVariables].divide(tableaux[i][pivotCol])).compareTo(
                    (tableaux[pivotRow][numberOfConstraints + numberOfOriginalVariables].divide(tableaux[pivotRow][pivotCol])))==1)
                pivotRow = i;
        }
        return pivotRow;
    }

    private void pivoting(int pivotRow, int pivotCol) {
        /*
        Step a. Locate and circle the entry at the intersection of the pivotal row and pivotal column.
         This entry is called the pivot.
        Step b. If the pivot is k, multiply the pivotal row by l / k , making the entry in the pivot position
        in the new tableau equal to 1.
        Step c. Add suitable multiples of the new pivotal row to all other rows (including the objective row),
         so that all other elements in the pivotal column become zero.
        Step d. In the new tableau, replace the label on the pivotal row by the entering variable.
        These four steps constitute a process called pivoting.
        */
        for (int i = 0; i <= numberOfConstraints; i++) {
            for (int j = 0; j <= numberOfConstraints + numberOfOriginalVariables; j++) {
                if (i != pivotRow && j != pivotCol) {
                    tableaux[i][j] = tableaux[i][j].subtract(tableaux[pivotRow][j].multiply(tableaux[i][pivotCol]).divide( tableaux[pivotRow][pivotCol]));
                }
            }
        }
        for (int i = 0; i <= numberOfConstraints; i++)
            if (i != pivotRow)
                tableaux[i][pivotCol] = BigDecimal.ZERO;

        for (int j = 0; j <= numberOfConstraints + numberOfOriginalVariables; j++)
            if (j != pivotCol)
                tableaux[pivotRow][j] = tableaux[pivotRow][j].divide(tableaux[pivotRow][pivotCol]);
        tableaux[pivotRow][pivotCol] = BigDecimal.ONE;
    }

    public BigDecimal optimalSolution() {
        /*Read off the bottom right corner of the finlal tableaux for the optimal solution*/
        return tableaux[numberOfConstraints][numberOfConstraints + numberOfOriginalVariables];
    }

    public BigDecimal[] extremePoint() {
        /*Get the extreme point for each tableau*/
    	BigDecimal[] x = new BigDecimal[numberOfOriginalVariables + numberOfConstraints];
        for (int i = 0; i < numberOfConstraints; i++)
            x[basicVariables[i]] = tableaux[i][numberOfConstraints + numberOfOriginalVariables];
        return x;
    }

    private boolean isExtremePoint(BigDecimal[] point){
        /* Return true if point is an extreme point of the feasible region*/
    	BigDecimal[] pointValue = new BigDecimal[numberOfConstraints];
        for(int j =0;j<numberOfConstraints;j++) {
        	BigDecimal current=BigDecimal.ZERO;
            for (int i = 0; i < numberOfOriginalVariables + numberOfConstraints; i++) {
                current = current.add(point[i].multiply(tableaux[j][i]));
            }
            pointValue[j]= current;
        }
        for(int k=0; k<numberOfConstraints;k++){
            if(constraintsOperator[k].equals(Constraint.greatherThan)){
                if(pointValue[k].compareTo(tableaux[k][numberOfConstraints+numberOfOriginalVariables])==-1){
                    return false;
                }
            }
            else if(constraintsOperator[k].equals(Constraint.lessThan)){
                if(pointValue[k] .compareTo(tableaux[k][numberOfConstraints+numberOfOriginalVariables])==1){
                    return false;
                }
            }
            else{
                if(pointValue[k] != tableaux[k][numberOfConstraints+numberOfOriginalVariables]){
                    return false;
                }
            }
        }
        return true;
    }

    public void show() {
        System.out.println("Number of Constrains = " + numberOfConstraints);
        System.out.println("Number of Variables = " + numberOfOriginalVariables);
        for (int i = 0; i <= numberOfConstraints; i++) {
            for (int j = 0; j <= numberOfConstraints + numberOfOriginalVariables; j++) {
                System.out.printf("%7.2f ", tableaux[i][j]);
            }
            System.out.println();
        }
        System.out.println(Arrays.toString(basicVariables));
        System.out.println("Extreme Point = " + Arrays.toString(extremePoint()));
        System.out.println(isExtremePoint(extremePoint()));
        System.out.println("Current Value = " + optimalSolution());
        for (int i = 0; i < numberOfConstraints; i++)
            if (basicVariables[i] < numberOfOriginalVariables)
                System.out.println("x_" + basicVariables[i] + " = " + tableaux[i][numberOfConstraints + numberOfOriginalVariables]);
        System.out.println();
    }

    public static class SimplexFactoryBuilder {
    	BigDecimal[][] constraintLeftSide;
    	BigDecimal[] objectiveFunc;
        SimplexMethod.Constraint[] constraintOperator;
        BigDecimal[] constraintRightSide;
        SimplexMethod.MaxOrMin maxOrMin;

        public SimplexFactoryBuilder setConstraintLeftSide(BigDecimal[][] constraintLeftSide) {
            this.constraintLeftSide = constraintLeftSide;
            return this;
        }

        public SimplexFactoryBuilder setObjectiveFunc(BigDecimal[] objectiveFunc) {
            this.objectiveFunc = objectiveFunc;
            return this;
        }

        public SimplexFactoryBuilder setConstraintOperator(Constraint[] constraintOperator) {
            this.constraintOperator = constraintOperator;
            return this;
        }

        public SimplexFactoryBuilder setConstraintRightSide(BigDecimal[] constraintRightSide) {
            this.constraintRightSide = constraintRightSide;
            return this;
        }

        public SimplexFactoryBuilder setMaxOrMin(MaxOrMin maxOrMin) {
            this.maxOrMin = maxOrMin;
            return this;
        }

        public SimplexMethod build() {
            SimplexTableaux tableaux;
            if (maxOrMin.equals(MaxOrMin.MAXIMIZE)) {
                tableaux = new SimplexTableaux(constraintLeftSide,constraintRightSide,constraintOperator,objectiveFunc);
            } else {
                int numConstraints = constraintRightSide.length;
                int numVariables = objectiveFunc.length;
                BigDecimal[][] newLeftConstraint = constraintLeftSide.clone();
                BigDecimal[] newConstraintRightSide = constraintRightSide.clone();

                for (int i = 0; i < numConstraints; i++) {
                    if (constraintOperator[i].equals(Constraint.lessThan)) {
                        for (int j = 0; j < numVariables; j++) {
                            if (newLeftConstraint[i][j].compareTo(BigDecimal.ZERO) != 0) {
                                newLeftConstraint[i][j] = newLeftConstraint[i][j].negate();
                            }
                        }
                        newConstraintRightSide[i] = newConstraintRightSide[i].negate();
                    }
                    if (constraintOperator[i].equals(SimplexMethod.Constraint.equal)) {
                    	BigDecimal[] equalConstraint = new BigDecimal[numVariables];
                        for (int j = 0; j < numVariables; j++) {
                            if (newLeftConstraint[i][j].compareTo(BigDecimal.ZERO) != 0) {
                                equalConstraint[j] = newLeftConstraint[i][j].negate();
                            }
                        }
                        BigDecimal[][] newLeftFunc = new BigDecimal[newLeftConstraint.length + 1][];
                        BigDecimal[] newestRight = new BigDecimal[newConstraintRightSide.length+1];
                        for (int n = 0; n < newLeftConstraint.length; n++) {
                            newLeftFunc[n] = newLeftConstraint[n];
                            newestRight[n] = newConstraintRightSide[n];
                        }
                        newLeftFunc[newLeftConstraint.length] = equalConstraint;
                        newLeftConstraint = newLeftFunc.clone();
                        newestRight[newConstraintRightSide.length] = newConstraintRightSide[i].negate();
                        newConstraintRightSide = newestRight.clone();
                    }
                }
                BigDecimal[][] transpose = new BigDecimal[newLeftConstraint[0].length][newLeftConstraint.length];
                for (int k = 0; k < newLeftConstraint.length; k++) {
                    for (int j = 0; j < newLeftConstraint[0].length; j++) {
                        transpose[j][k] = newLeftConstraint[k][j];
                    }
                }
                SimplexMethod.Constraint[] newConstraintOperator = new SimplexMethod.Constraint[transpose.length];
                for (int m = 0; m < transpose.length; m++) {
                    newConstraintOperator[m] = SimplexMethod.Constraint.lessThan;
                }
                tableaux = new SimplexTableaux(transpose, objectiveFunc, newConstraintOperator, newConstraintRightSide);
            }
            return new SimplexMethod(tableaux.getTableaux(), tableaux.getNumberOfConstraint(), tableaux.getNumberOfOriginalVariable(),tableaux.getConstraintOp(),maxOrMin);
        }
    }
}