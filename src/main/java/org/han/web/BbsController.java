package org.han.web;

import java.util.Arrays;
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
	//public String list(@RequestParam(value="types", defaultValue="") String[] types, @ModelAttribute PageMaker pm, Model model) {
	public void list(@ModelAttribute PageMaker pm, Model model) {
		
		//pm.setTypeArr(types);

//		화면에 출력할 목록 산출
		// 검색어 및 getSql이 들어있는 PageMaker를 변수로 전달한다. 변수로 전달하지 않으면 getSql문이 작동하지 않으니 주의
//		List<BbsVO> list = service.list(pm);

//		pageMaker에 현재 페이지, 페이징에 필요한 총 데이터 set
//		if(list.isEmpty()){
//			pm.setCnt(0);
//		}else{
//			pm.setCnt(list.get(0).getCnt());
//		}

//		model에 출력목록, pageMaker 집어넣음
		logger.info("waaaagh!!!");
		model.addAttribute("list", service.list(pm));
//		model.addAttribute("pageMaker", pm);
//		return "bbs/list";
	}
	
	// 게시글 읽기
	@RequestMapping("/read")
	public String read(@RequestParam(value="bno", defaultValue="") int bno, Model model){
		model.addAttribute("vo", service.read(bno));
		return "bbs/read";
	}
	
	// 게시글 지우기
	@RequestMapping("/delete")
	public String delete(@RequestParam(value="bno", defaultValue="") int bno){
		service.delete(bno);
		return "redirect:list";
	}
	
	// 게시글 수정화면 이동
	@RequestMapping("/uboard")
	public String uboard(@ModelAttribute BbsVO vo, Model model){
		model.addAttribute("vo", service.read(vo.getBno()));
		return "bbs/uboard";
	}
	
	// 게시글 수정
	@RequestMapping("/update")
	public String update(@ModelAttribute BbsVO vo){
		service.update(vo);
		return "redirect:read?bno=" + vo.getBno();
	}
}