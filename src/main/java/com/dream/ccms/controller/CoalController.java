package com.dream.ccms.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dream.ccms.controller.request.OperatorCoal;
import com.dream.ccms.controller.response.APIResponseStatusCode;
import com.dream.ccms.controller.response.RestfulResponse;
import com.dream.ccms.dao.CoalRepository;
import com.dream.ccms.dao.TargetRepository;
import com.dream.ccms.entity.CokingCoal;
import com.dream.ccms.entity.Optimization;
import com.dream.ccms.entity.TargetValue;
import com.dream.ccms.service.OperBigDecimal;
import com.dream.ccms.service.Operation;


@RestController
@CrossOrigin
@RequestMapping(value = "/coal")
public class CoalController {
	@Autowired
	private CoalRepository coalRespository;
	@Autowired
	private TargetRepository targetRespository;

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public RestfulResponse create(@RequestBody CokingCoal pageCoal) {
		CokingCoal coal = new CokingCoal();
		coal = pageCoal;
		coalRespository.saveAndFlush(coal);
		List<CokingCoal> coalList=coalRespository.findByObjectInactiveFalse();
		return new RestfulResponse(null, APIResponseStatusCode.SUCCESS, coalList);
	}
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public RestfulResponse update(@RequestBody List<CokingCoal> coaList) {
		
		coalRespository.saveAll(coaList);
		List<CokingCoal> coalList=coalRespository.findByObjectInactiveFalse();
		return new RestfulResponse(null, APIResponseStatusCode.SUCCESS, coalList);
	}


	@RequestMapping(value = "/loadValue", method = RequestMethod.GET)
	public RestfulResponse loadValue() {
		TargetValue tvalue = new TargetValue();
		List<TargetValue> valueList = targetRespository.findAll();
		if (!valueList.isEmpty() && valueList != null) {
			tvalue = valueList.get(valueList.size() - 1);
		}
		return new RestfulResponse(null, APIResponseStatusCode.SUCCESS, tvalue);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public RestfulResponse findAll() {
		List<CokingCoal> coalList = coalRespository.findByObjectInactiveFalse();

		/*
		 * List<CokingCoal> reList= coalList.stream() .filter(x->!x.isObjectInactive())
		 * .collect(Collectors.toList());
		 */
		return new RestfulResponse(null, APIResponseStatusCode.SUCCESS, coalList);

	}

	
	@RequestMapping(value = "/coalValue", method = RequestMethod.POST)
	public RestfulResponse targetValue(@RequestBody TargetValue pvalue) {
		TargetValue tvalue = new TargetValue();
		tvalue = pvalue;
		targetRespository.saveAndFlush(tvalue);
		return new RestfulResponse(null, APIResponseStatusCode.SUCCESS, tvalue);		
	}

	@RequestMapping(value = "/operate", method = RequestMethod.POST)
	public RestfulResponse operate(@RequestBody OperatorCoal pvalue) throws Exception {
		List<CokingCoal> retainList = pvalue.getRetainList();
		Optional<BigDecimal> retains = retainList.stream().map(CokingCoal::getMinPercent).reduce((a, b) -> a.add(b));
		BigDecimal retain = BigDecimal.ZERO;
		retain = retains.isPresent() ? retains.get() : BigDecimal.ZERO;
		System.out.print(retain);
		if (retain.compareTo(BigDecimal.ONE) >= 0 || retain.compareTo(BigDecimal.ZERO) < 0)
			throw new Exception("args is outbound");
		System.out.println(LocalDateTime.now() + "start");
		List<Optimization> maxList = new OperBigDecimal().operate(pvalue, "max");
		System.out.println(LocalDateTime.now() + "finish max");
		List<Optimization> opList = new OperBigDecimal().operate(pvalue, "min");
		System.out.println(LocalDateTime.now() + "finish min");
		if (opList==null) return null;
		opList.addAll(maxList);
		/*
		 * for (Optimization op : opList) { System.out.print(op.getCoal().getName() +
		 * ":     "); System.out.println(op.getPercent()); }
		 */

		/*
		 * final double arg = retain; opList.stream() .forEach(x ->
		 * x.setPercent(x.getPercent() * (1 - arg) + x.getCoal().getMinPercent()));
		 */
		if (retain.compareTo(BigDecimal.ZERO) ==1) {
			for (Optimization opti : opList) {
				for (CokingCoal coal : retainList) {
					if (coal.getId() == opti.getCoal().getId()) {
						opti.getCoal().setAd(coal.getAd());
						opti.getCoal().setVdaf(coal.getVdaf());
						opti.getCoal().setS(coal.getS());
						opti.getCoal().setY(coal.getY());
						opti.getCoal().setG(coal.getG());
						opti.getCoal().setRe(coal.getRe());
					}
				}
				opti.setPercent(opti.getPercent().multiply(BigDecimal.ONE.subtract(retain)).add(opti.getCoal().getMinPercent()));
			}
		}
		return new RestfulResponse(null, APIResponseStatusCode.SUCCESS, opList);
	}
	
	@RequestMapping(value = "/oper", method = RequestMethod.POST)
	public RestfulResponse oper(@RequestBody OperatorCoal pvalue) throws Exception {
		List<CokingCoal> retainList = pvalue.getRetainList();
		Optional<BigDecimal> retains = retainList.stream().map(CokingCoal::getMinPercent).reduce((a, b) -> a.add(b));
		BigDecimal retain = BigDecimal.ZERO;
		retain = retains.isPresent() ? retains.get() : BigDecimal.ZERO;
		
		if (retain.compareTo(BigDecimal.ONE) >= 0 || retain.compareTo(BigDecimal.ZERO) < 0)
			throw new Exception("args is outbound");
		System.out.println(LocalDateTime.now() + "start");
		List<Optimization> maxList = new Operation().operate(pvalue, "max");
		System.out.println(LocalDateTime.now() + "finish max");
		List<Optimization> opList = new Operation().operate(pvalue, "min");
		System.out.println(LocalDateTime.now() + "finish min");
		if (opList==null) return null;
		opList.addAll(maxList);
		
		if (retain.compareTo(BigDecimal.ZERO) ==1) {
			for (Optimization opti : opList) {
				for (CokingCoal coal : retainList) {
					if (coal.getId() == opti.getCoal().getId()) {
						opti.getCoal().setAd(coal.getAd());
						opti.getCoal().setVdaf(coal.getVdaf());
						opti.getCoal().setS(coal.getS());
						opti.getCoal().setY(coal.getY());
						opti.getCoal().setG(coal.getG());
						opti.getCoal().setRe(coal.getRe());
					}
				}
				opti.setPercent(opti.getPercent().multiply(BigDecimal.ONE.subtract(retain)).add(opti.getCoal().getMinPercent()));
			}
		}
		return new RestfulResponse(null, APIResponseStatusCode.SUCCESS, opList);
	}

	
}
