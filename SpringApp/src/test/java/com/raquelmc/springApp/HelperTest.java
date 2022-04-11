package com.raquelmc.springApp;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.raquelmc.springApp.model.Datastream;
import com.raquelmc.springApp.model.Statistic;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HelperTest {
	
	@Autowired
	private Helper helper;
	private ArrayList<Datastream> info = new ArrayList<Datastream>();
	private Datastream datastream;
	
	@Before
	public void initialize() {		
		datastream = new Datastream("example", "feed_1", null);
		info.add(datastream);
	}
		
	@Test
	public void createStatisticTest() {
		List<Statistic> total = helper.createStatistic(info);
		assertEquals(total.size(),0);
	}
}
