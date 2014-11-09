package org.han.service;

import java.util.List;

import javax.inject.Inject;

import org.han.mapper.BbsMapper;
import org.han.util.PageMaker;
import org.han.vo.BbsVO;
import org.springframework.stereotype.Service;

@Service
public class BbsService {
	
	@Inject
	BbsMapper mapper;
	
	@Inject
	BbsVO vo;
	
	public void create(BbsVO vo){
		mapper.create(vo);
	}
	
	public List<BbsVO> list(PageMaker pm){
		return mapper.list(pm);
	}
	
	public BbsVO read(int bno) {
		return mapper.read(bno);
	}
	
	public void delete(int bno) {
		mapper.delete(bno);
	}
}
