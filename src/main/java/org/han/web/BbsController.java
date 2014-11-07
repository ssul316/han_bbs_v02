package org.han.web;

import java.util.List;

import javax.inject.Inject;

import org.han.service.BbsService;
import org.han.util.PageMaker;
import org.han.vo.BbsVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/bbs/*")
public class BbsController {
	
	@Inject
	BbsVO vo;
	
	@Inject
	BbsService service;
	
	@Inject
	PageMaker pm;
	
	private static Logger logger = LoggerFactory.getLogger(BbsController.class);
	
//	글쓰기 화면으로 이동
	@RequestMapping("/cboard")
	public String cboard() {
		return "bbs/cboard";
	}
	
//	게시글 등록 쿼리 실행부분
	@RequestMapping("/create")
	public String create(@ModelAttribute BbsVO vo) {
		service.create(vo);
		return "redirect:list";
	}
	
//	메인화면
	@RequestMapping("/list")
	public String list(@RequestParam(value="page", defaultValue="1")String page, Model model) {
		
//		화면에 출력할 목록 산출
		List<BbsVO> list = service.read(page);
		
//		pageMaker에 현재 페이지, 페이징에 필요한 총 데이터 set
		pm.setPage(page);
		pm.setCnt(list.get(0).getCnt());
		
//		model에 출력목록, pageMaker 집어넣음
		model.addAttribute("list", list);
		model.addAttribute("pageMaker", pm);
		return "bbs/list";
	}
}