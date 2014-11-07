package org.han.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.han.vo.BbsVO;

public interface BbsMapper {
	
	@Insert("insert into tbl_bbs (bno, title, userid, cont)"
			+ " values(seq_bbs.nextval, #{title}, #{userid}, #{cont})")
	public void create(BbsVO vo);
	
	@Select("select bno," 
			+" (case when sysdate - regdate < 1 then '\"new\"' end)||title||(case when rcount = 0 then ' ' when rcount>0 then '['||rcount||']' end) title,"
			+"userid, regdate, vcount, cnt from"
			+" (select /*+index_desc(tbl_bbs pk_bbs)*/ rownum rn, bno, title, userid, regdate, vcount, rcount,  count(rownum) over() cnt from tbl_bbs where bno>0 and rownum<=(ceil(#{page}/5)*50)+1)"
			+" where rn > (#{page}-1)*10 and rn<=(#{page}*10)")
	public List<BbsVO> read(String page);
}