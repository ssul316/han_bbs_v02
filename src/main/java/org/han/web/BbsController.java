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
	//public String list(@RequestParam(value="types", defaultValue="") String[] types, @ModelAttribute PageMaker pm, Model model) {
	public void list(@ModelAttribute PageMaker pm, Model model) {
		
		//pm.setTypeArr(types);

//		ȭ�鿡 ����� ��� ����
		// �˻��� �� getSql�� ����ִ� PageMaker�� ������ �����Ѵ�. ������ �������� ������ getSql���� �۵����� ������ ����
//		List<BbsVO> list = service.list(pm);

//		pageMaker�� ���� ������, ����¡�� �ʿ��� �� ������ set
//		if(list.isEmpty()){
//			pm.setCnt(0);
//		}else{
//			pm.setCnt(list.get(0).getCnt());
//		}

//		model�� ��¸��, pageMaker �������
		logger.info("waaaagh!!!");
		model.addAttribute("list", service.list(pm));
//		model.addAttribute("pageMaker", pm);
//		return "bbs/list";
	}
	
	// �Խñ� �б�
	@RequestMapping("/read")
	public String read(@RequestParam(value="bno", defaultValue="") int bno, Model model){
		model.addAttribute("vo", service.read(bno));
		return "bbs/read";
	}
	
	// �Խñ� �����
	@RequestMapping("/delete")
	public String delete(@RequestParam(value="bno", defaultValue="") int bno){
		service.delete(bno);
		return "redirect:list";
	}
	
	// �Խñ� ����ȭ�� �̵�
	@RequestMapping("/uboard")
	public String uboard(@ModelAttribute BbsVO vo, Model model){
		model.addAttribute("vo", service.read(vo.getBno()));
		return "bbs/uboard";
	}
	
	// �Խñ� ����
	@RequestMapping("/update")
	public String update(@ModelAttribute BbsVO vo){
		service.update(vo);
		return "redirect:read?bno=" + vo.getBno();
	}
}