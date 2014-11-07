package org.han.service;

import java.util.List;

import javax.inject.Inject;

import org.han.mapper.BbsMapper;
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
	
	public List<BbsVO> read(String page){
		return mapper.read(page);
	}
}
