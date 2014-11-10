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
		vo.setTitle("으아앙");
		vo.setUserid("han00");
		vo.setCont("졸림ㅋ");
		
		service.create(vo);
	}
	
//	@Test
//	public void listTest() {
//		List<BbsVO> list = service.list("1");
//		for (BbsVO obj : list) {
//			System.out.println(obj);
//		}
//	}
	
	@Test
	public void readTest() {
		logger.info(service.read(524));
	}
	
	@Test
	public void updateTest() {
		vo.setBno(524);
		vo.setTitle("더 월드!!!");
		vo.setCont("그리고 시간은 움직인다.");
		
		service.update(vo);
	}
}
