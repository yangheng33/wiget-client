package com.metarnet.noc.parkservice.services;

import java.util.List;
import javax.jws.WebService;
import com.metarnet.noc.parkservice.model.Test;

@WebService
public interface ITestService
{
	List<Test> getTest( Test test );
}
