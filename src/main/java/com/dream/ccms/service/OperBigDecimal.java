package com.dream.ccms.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.dream.ccms.controller.request.Op;
import com.dream.ccms.controller.request.OperatorCoal;
import com.dream.ccms.entity.CokingCoal;
import com.dream.ccms.entity.Optimization;
import com.dream.ccms.entity.TargetValue;
import com.dream.ccms.simplex.SimplexMethod;
import com.dream.ccms.simplex.SimplexMethod.Constraint;
import com.dream.ccms.simplex.SimplexMethod.MaxOrMin;

public class OperBigDecimal{
	private BigDecimal[] objectiveFunc;

	public List<Optimization> operate(OperatorCoal pvalue, String mm) {
		MaxOrMin maxORmin = "max".equals(mm) ? MaxOrMin.MAXIMIZE : MaxOrMin.MINIMIZE;
		List<Optimization> opList = new ArrayList<Optimization>();
		List<CokingCoal> coalList = pvalue.getOpList();
		Op op = pvalue.getOp();
		int rows = op.opnum();
		int order = pvalue.getOrder();
		BigDecimal[] rightSide = this.right(pvalue);
		BigDecimal[][] leftSide = this.left(pvalue);
		Constraint[] constraintOperator = this.constraint(rows);
		if (order > 0) {
			BigDecimal[] extraRightSide = new BigDecimal[3 * rows + 1];
			System.arraycopy(rightSide, 0, extraRightSide, 0, rightSide.length);
			System.arraycopy(this.extraRight(rows), 0, extraRightSide, rightSide.length, rows);

			Constraint[] extraConstraintOperator = new Constraint[3 * rows + 1];
			System.arraycopy(constraintOperator, 0, extraConstraintOperator, 0, constraintOperator.length);
			System.arraycopy(this.extraConstraint(rows), 0, extraConstraintOperator, constraintOperator.length,
					this.extraConstraint(rows).length);
			BigDecimal[][] extraLeftSide = new BigDecimal[3 * rows + 1][coalList.size()];
			System.arraycopy(leftSide, 0, extraLeftSide, 0, leftSide.length);
			System.arraycopy(this.extraLeft(pvalue, order), 0, extraLeftSide, rightSide.length,
					this.extraLeft(pvalue, order).length);
			
			System.out.print("objectiveFunc ：");
			for (BigDecimal o : objectiveFunc)
				System.out.print(o + ", ");
			System.out.println();
			System.out.println("leftSide ：");
			for (BigDecimal[] ls : extraLeftSide) {
				for (BigDecimal l : ls)
					System.out.print(l + ", ");
				System.out.println();
			}
			System.out.print("Constraint ：");
			for (Constraint c : extraConstraintOperator)
				System.out.print(c + ", ");
			System.out.println();
			System.out.print("rightSide ：");
			for (BigDecimal r :extraRightSide)
				System.out.print(r + " , ");
			System.out.println();
			System.out.println();
			System.out.println("**********start operating***********************************");
			System.out.println();
			System.out.println();

			SimplexMethod simplex = new SimplexMethod.SimplexFactoryBuilder().setConstraintLeftSide(extraLeftSide)
					.setConstraintRightSide(extraRightSide).setObjectiveFunc(objectiveFunc)
					.setConstraintOperator(extraConstraintOperator).setMaxOrMin(maxORmin).build();
			try {
				simplex.runSimplex();
			} catch (Exception e) {
				System.out.println(e);
			} finally {
				if (simplex.getOptimalExtreme() != null) {
					int n = 0;
					for (CokingCoal coal : coalList) {
						opList.add(new Optimization(mm, coal, simplex.getOptimalExtreme()[n]));
						n++;
					}
				}
				System.out.println(mm + ":   " + Arrays.toString(simplex.getOptimalExtreme()));
				System.out.println(mm + ":   " + simplex.getOptimization());
			}
			return opList;

		} else {
			System.out.print("objectiveFunc ：");
			for (BigDecimal o : objectiveFunc)
				System.out.print(o + ", ");
			System.out.println();
			System.out.println("leftSide ：");
			for (BigDecimal[] ls : leftSide) {
				for (BigDecimal l : ls)
					System.out.print(l + ", ");
				System.out.println();
			}
			System.out.print("Constraint ：");
			for (Constraint c : constraintOperator)
				System.out.print(c + ", ");
			System.out.println();
			System.out.print("rightSide ：");
			for (BigDecimal r :rightSide)
				System.out.print(r + " , ");
			System.out.println();
			System.out.println();
			System.out.println("**********start operating***********************************");
			System.out.println();
			System.out.println();

			
			SimplexMethod simplex = new SimplexMethod.SimplexFactoryBuilder().setConstraintLeftSide(leftSide)
					.setConstraintRightSide(rightSide).setObjectiveFunc(objectiveFunc)
					.setConstraintOperator(constraintOperator).setMaxOrMin(maxORmin).build();
		
				simplex.runSimplex();
				System.out.println(mm + ":   " + Arrays.toString(simplex.getOptimalExtreme()));
				System.out.println(mm + ":   " + simplex.getOptimization());
				try {	
			} catch (Exception e) {
				System.out.println(e);
			} finally {
				if (simplex.getOptimalExtreme() != null) {
					int n = 0;
					for (CokingCoal coal : coalList) {
						opList.add(new Optimization(mm, coal, simplex.getOptimalExtreme()[n]));
						n++;
					}
				}
				System.out.println(mm + ":   " + Arrays.toString(simplex.getOptimalExtreme()));
				System.out.println(mm + ":   " + simplex.getOptimization());
			}
			return opList;
		}	
	}	
		public BigDecimal[][] left(OperatorCoal pvalue){

	List<CokingCoal> coalList = pvalue.getOpList();
	Op op = pvalue.getOp();	
	int rows = 2 * op.opnum() + 1;
	BigDecimal[][] leftSide = new BigDecimal[rows][coalList.size()];
	this.objectiveFunc = new BigDecimal[coalList.size()];
	int i = 0;
	for (CokingCoal coal : coalList) {
		int m = 0;
		if (op.isAd()) {
			leftSide[m++][i] = coal.getAd();
			leftSide[m++][i] = coal.getAd();
		}
		if (op.isVdaf()) {
			leftSide[m++][i] = coal.getVdaf();
			leftSide[m++][i] = coal.getVdaf();
		}
		if (op.isS()) {
			leftSide[m++][i] = coal.getS();
			leftSide[m++][i] = coal.getS();
		}
		if (op.isG()) {
			leftSide[m++][i] = coal.getG();
			leftSide[m++][i] = coal.getG();
		}
		if (op.isY()) {
			leftSide[m++][i] = coal.getY();
			leftSide[m++][i] = coal.getY();
		}
		if (op.isRe()) {
			leftSide[m++][i] = coal.getRe();
			leftSide[m++][i] = coal.getRe();
		}
		leftSide[m++][i] =  BigDecimal.ONE;

		objectiveFunc[i] =coal.getPrice();
		i++;
	}

	return leftSide;
}
public BigDecimal[] right(OperatorCoal pvalue) {	
	TargetValue tvalue = new TargetValue();
	tvalue = pvalue.getOpv();
	Op op = pvalue.getOp();
	int rows = 2 * op.opnum() + 1;
	BigDecimal[] rightSide = new BigDecimal[rows];
	int i = 0;
	if (op.isAd()) {
		rightSide[i++] = tvalue.getAd().add(tvalue.getAdScope());
		rightSide[i++] = tvalue.getAd().subtract(tvalue.getAdScope());
	}
	if (op.isVdaf()) {
		rightSide[i++] = tvalue.getVdaf().add (tvalue.getVdafScope());
		rightSide[i++] = tvalue.getVdaf().subtract(tvalue.getVdafScope());
	}
	if (op.isS()) {
		rightSide[i++] =  tvalue.getS().add(tvalue.getsScope());
		rightSide[i++] =  tvalue.getS().subtract(tvalue.getsScope());;
	}
	if (op.isG()) {
		rightSide[i++] = tvalue.getG().add(tvalue.getgScope());
		rightSide[i++] = tvalue.getG().subtract(tvalue.getgScope());
	}
	if (op.isY()) {
		rightSide[i++] = tvalue.getY().add(tvalue.getyScope());
		rightSide[i++] = tvalue.getY().subtract(tvalue.getyScope());
	}
	if (op.isRe()) {
		rightSide[i++] = tvalue.getRe().add(tvalue.getReScope());
		rightSide[i++] = tvalue.getRe().subtract(tvalue.getReScope());
	}

	rightSide[i] = BigDecimal.ONE;

	return rightSide;
}

	public Constraint[] constraint(int rows) {
		rows=2*rows+1;
		Constraint[] constraintOperator = new SimplexMethod.Constraint[rows];
		for (int i = 0; i < rows - 1; i = i + 2) {
			constraintOperator[i] = SimplexMethod.Constraint.lessThan;
			constraintOperator[i + 1] = SimplexMethod.Constraint.greatherThan;
		}
		constraintOperator[rows - 1] = SimplexMethod.Constraint.equal;
       return  constraintOperator;
	}
	public   Constraint[] extraConstraint(int rows) {
		Constraint[] constraintOperator = new SimplexMethod.Constraint[rows];
		for (int i=0;i<rows;i++) {
			constraintOperator[i] = SimplexMethod.Constraint.greatherThan;
		}
		 return  constraintOperator;
	}
	public BigDecimal[] extraRight(int rows){
		
		BigDecimal[] rightSide = new BigDecimal[rows];
		for (int i=0;i<rows;i++) {
			rightSide[i]=BigDecimal.ZERO;
		}
		return rightSide;
	}
	public BigDecimal[][] extraLeft(OperatorCoal pvalue, int order) {

		List<CokingCoal> coalList = pvalue.getOpList();
		Op op = pvalue.getOp();
		int rows = op.opnum();
		BigDecimal[][] leftSide = new BigDecimal[rows][coalList.size()];		
		for ( int i=0;i<coalList.size();i++) {
			int m = 0;
			int n=0;
			if (op.isAd()) {
				leftSide[m++][i] = extrafactor(n++, i, order);
			
			}
			if (op.isVdaf()) {
				leftSide[m++][i] = extrafactor(n++, i, order);
			}
			if (op.isS()) {
				leftSide[m++][i] = extrafactor(n++, i, order);
			}
			if (op.isG()) {
				leftSide[m++][i] = extrafactor(n++, i, order);
			}
			if (op.isY()) {
				leftSide[m++][i] = extrafactor(n++, i, order);
			}
			if (op.isRe()) {
				leftSide[m++][i] = extrafactor(n++, i, order);
			}
			

		}
		return leftSide;

	}

	public BigDecimal extrafactor(int m, int i, int order) {
	
		if (i == order) {
			return BigDecimal.ONE;
		}
		if (i==1) {
				
		}
		if (i > order) {
			m++;	
			
		} 
		if (m == i) {			
			return BigDecimal.ONE.negate();
		}		
		return BigDecimal.ZERO;
	}
}
