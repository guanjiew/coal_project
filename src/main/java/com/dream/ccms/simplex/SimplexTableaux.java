package com.dream.ccms.simplex;

import java.math.BigDecimal;

public class SimplexTableaux {
    protected BigDecimal[][] tableaux;
    protected int numberOfConstraints;
    protected int numberOfOriginalVariables;

    public SimplexMethod.Constraint[] getConstraintOp() {
        return constraintOp;
    }

    protected SimplexMethod.Constraint[] constraintOp;

    public SimplexTableaux(BigDecimal[][] constraintLeftSide, BigDecimal[] constraintRightSide,
                            SimplexMethod.Constraint[] constraintOperator, BigDecimal[] objectiveFunction) {
        numberOfConstraints = constraintRightSide.length;
        numberOfOriginalVariables = objectiveFunction.length;
        constraintOp = constraintOperator;
        tableaux = new BigDecimal[numberOfConstraints + 1][numberOfOriginalVariables + numberOfConstraints + 1];
        setConstraints(constraintLeftSide);
        setRightSideValues(constraintRightSide, constraintOperator);
        setObjective(objectiveFunction, numberOfConstraints);
        setSlackVariables(constraintOperator);
    }

    protected void setObjective(BigDecimal[] objectiveFunction, int numberOfConstraints) {
    	BigDecimal[] objectiveTable = new BigDecimal[objectiveFunction.length];
        for (int i = 0; i < objectiveFunction.length; i++) {
            objectiveTable[i] = objectiveFunction[i].negate();
        }
        if (numberOfOriginalVariables >= 0)
            System.arraycopy(objectiveTable, 0, tableaux[numberOfConstraints], 0, numberOfOriginalVariables);
    }

    protected void setRightSideValues(BigDecimal[] constraintRightSide, SimplexMethod.Constraint[] constraintOperator) {
        for (int i = 0; i < numberOfConstraints; i++)
            tableaux[i][numberOfConstraints + numberOfOriginalVariables] = constraintRightSide[i];
    }

    protected void setConstraints(BigDecimal[][] constraintLeftSide) {
        for (int i = 0; i < numberOfConstraints; i++) {
            if (numberOfOriginalVariables >= 0)
                System.arraycopy(constraintLeftSide[i], 0, tableaux[i], 0, numberOfOriginalVariables);
        }
    }

    protected void setSlackVariables(SimplexMethod.Constraint[] constraintOperator) {
        for (int i = 0; i < numberOfConstraints; i++) {
            if (constraintOperator[i].equals(SimplexMethod.Constraint.greatherThan)) {
                tableaux[i][numberOfOriginalVariables + i] = BigDecimal.ONE.negate();
            } else if (constraintOperator[i].equals(SimplexMethod.Constraint.lessThan)) {
                tableaux[i][numberOfOriginalVariables + i] =  BigDecimal.ONE;
            } else {
                tableaux[i][numberOfOriginalVariables + i] =  BigDecimal.ZERO;
            }
        }
    }

    public BigDecimal[][] getTableaux() {
        return tableaux;
    }

    public int getNumberOfConstraint() {
        return numberOfConstraints;
    }

    public int getNumberOfOriginalVariable() {
        return numberOfOriginalVariables;
    }
}