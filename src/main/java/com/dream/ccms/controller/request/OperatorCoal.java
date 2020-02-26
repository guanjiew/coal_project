package com.dream.ccms.controller.request;

import java.util.List;

import com.dream.ccms.entity.CokingCoal;
import com.dream.ccms.entity.TargetValue;

public class OperatorCoal {

	private TargetValue opv =null;
	private List<CokingCoal> opList=null;
	private List<CokingCoal> retainList=null;
	private Op op=null;
	private int order=-1;
	public TargetValue getOpv() {
		return opv;
	}
	public void setOpv(TargetValue opv) {
		this.opv = opv;
	}
	public List<CokingCoal> getOpList() {
		return opList;
	}
	public void setOpList(List<CokingCoal> opList) {
		this.opList = opList;
	}
	public Op getOp() {
		return op;
	}
	public void setOp(Op op) {
		this.op = op;
	}
	public List<CokingCoal> getRetainList() {
		return retainList;
	}
	public void setRetainList(List<CokingCoal> retainList) {
		this.retainList = retainList;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	
	
	
}
