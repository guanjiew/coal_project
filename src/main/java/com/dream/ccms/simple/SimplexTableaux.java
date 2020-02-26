package com.dream.ccms.simple;


public class SimplexTableaux {
    protected double[][] tableaux;
    protected int numberOfConstraints;
    protected int numberOfOriginalVariables;

    public SimplexMethod.Constraint[] getConstraintOp() {
        return constraintOp;
    }

    protected SimplexMethod.Constraint[] constraintOp;

    public SimplexTableaux(double[][] constraintLeftSide, double[] constraintRightSide,
                            SimplexMethod.Constraint[] constraintOperator, double[] objectiveFunction) {
        numberOfConstraints = constraintRightSide.length;
        numberOfOriginalVariables = objectiveFunction.length;
        constraintOp = constraintOperator;
        tableaux = new double[numberOfConstraints + 1][numberOfOriginalVariables + numberOfConstraints + 1];
        setConstraints(constraintLeftSide);
        setRightSideValues(constraintRightSide, constraintOperator);
        setObjective(objectiveFunction, numberOfConstraints);
        setSlackVariables(constraintOperator);
    }

    protected void setObjective(double[] objectiveFunction, int numberOfConstraints) {
        double[] objectiveTable = new double[objectiveFunction.length];
        for (int i = 0; i < objectiveFunction.length; i++) {
            objectiveTable[i] = -objectiveFunction[i];
        }
        if (numberOfOriginalVariables >= 0)
            System.arraycopy(objectiveTable, 0, tableaux[numberOfConstraints], 0, numberOfOriginalVariables);
    }

    protected void setRightSideValues(double[] constraintRightSide, SimplexMethod.Constraint[] constraintOperator) {
        for (int i = 0; i < numberOfConstraints; i++)
            tableaux[i][numberOfConstraints + numberOfOriginalVariables] = constraintRightSide[i];
    }

    protected void setConstraints(double[][] constraintLeftSide) {
        for (int i = 0; i < numberOfConstraints; i++) {
            if (numberOfOriginalVariables >= 0)
                System.arraycopy(constraintLeftSide[i], 0, tableaux[i], 0, numberOfOriginalVariables);
        }
    }

    protected void setSlackVariables(SimplexMethod.Constraint[] constraintOperator) {
        for (int i = 0; i < numberOfConstraints; i++) {
            if (constraintOperator[i].equals(SimplexMethod.Constraint.greatherThan)) {
                tableaux[i][numberOfOriginalVariables + i] = -1;
            } else if (constraintOperator[i].equals(SimplexMethod.Constraint.lessThan)) {
                tableaux[i][numberOfOriginalVariables + i] = 1;
            } else {
                tableaux[i][numberOfOriginalVariables + i] = 0;
            }
        }
    }

    public double[][] getTableaux() {
        return tableaux;
    }

    public int getNumberOfConstraint() {
        return numberOfConstraints;
    }

    public int getNumberOfOriginalVariable() {
        return numberOfOriginalVariables;
    }
}