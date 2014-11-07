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
	
//	�۾��� ȭ������ �̵�
	@RequestMapping("/cboard")
	public String cboard() {
		return "bbs/cboard";
	}
	
//	�Խñ� ��� ���� ����κ�
	@RequestMapping("/create")
	public String create(@ModelAttribute BbsVO vo) {
		service.create(vo);
		return "redirect:list";
	}
	
//	����ȭ��
	@RequestMapping("/list")
	public String list(@RequestParam(value="page", defaultValue="1")String page, Model model) {
		
//		ȭ�鿡 ����� ��� ����
		List<BbsVO> list = service.read(page);
		
//		pageMaker�� ���� ������, ����¡�� �ʿ��� �� ������ set
		pm.setPage(page);
		pm.setCnt(list.get(0).getCnt());
		
//		model�� ��¸��, pageMaker �������
		model.addAttribute("list", list);
		model.addAttribute("pageMaker", pm);
		return "bbs/list";
	}
}