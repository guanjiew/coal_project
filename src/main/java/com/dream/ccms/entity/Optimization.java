package com.dream.ccms.entity;

import java.math.BigDecimal;

public class Optimization {
	
	private CokingCoal coal = null;
	private String pattern = null;
	private double dpercent = 0;
	private BigDecimal percent = BigDecimal.ZERO;
	
	public Optimization(String mm, CokingCoal coal, BigDecimal bpercent) {
		super();
		this.coal = coal;
		this.pattern = mm;		
		this.percent = bpercent.setScale(4, BigDecimal.ROUND_HALF_UP);		
	}
	public Optimization( CokingCoal coal, BigDecimal bpercent) {
		super();
		this.coal = coal;	
		this.percent = bpercent.setScale(4, BigDecimal.ROUND_HALF_UP);		
	}
	
	public Optimization(String mm, CokingCoal coal, double dpercent) {
		super();
		this.coal = coal;
		this.pattern = mm;
		BigDecimal b = new BigDecimal(dpercent);
		this.dpercent = b.setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
		
	}

	public Optimization(CokingCoal coal, double dpercent) {
		super();
		this.coal = coal;
		BigDecimal b = new BigDecimal(dpercent);
		this.dpercent = b.setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
		;
	}

	public CokingCoal getCoal() {
		return coal;
	}

	public void setCoal(CokingCoal coal) {
		this.coal = coal;
	}

	
	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	public double getDpercent() {
		return dpercent;
	}
	public void setDpercent(double dpercent) {
		this.dpercent = dpercent;
	}
	public BigDecimal getPercent() {
		return percent;
	}
	public void setPercent(BigDecimal percent) {
		this.percent = percent;
	}

	

}
