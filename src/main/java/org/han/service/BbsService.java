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
		List<BbsVO> list = mapper.list(pm);
		if(list.isEmpty()){
			pm.setCnt(0);
		}else{
			pm.setCnt(list.get(0).getCnt());
		}
		return list;
	}
	
	public BbsVO read(int bno) {
		return mapper.read(bno);
	}
	
	public void delete(int bno) {
		mapper.delete(bno);
	}
	
	public void update(BbsVO vo) {
		mapper.update(vo);
	}
}
