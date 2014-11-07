package org.han.web;

import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.han.service.BbsService;
import org.han.vo.BbsVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/**/*-context.xml" })
public class BbsServiceTest {
	
	static Logger logger = Logger.getLogger(BbsService.class);

	@Inject
	BbsService service;
	
	@Inject
	BbsVO vo;
	
	@Test
	public void createTest() {
		vo.setTitle("À¸¾Æ¾Ó");
		vo.setUserid("han00");
		vo.setCont("Á¹¸²¤»");
		
		service.create(vo);
	}
	
	@Test
	public void readTest() {
		List<BbsVO> list = service.read("1");
		for (BbsVO obj : list) {
			System.out.println(obj);
		}
	}

}
