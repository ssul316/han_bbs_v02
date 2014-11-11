package org.han.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class PageMaker {

	private int page; 					// 현재 페이지 - 기준값
	private int cnt; 					// 현재 화면의 페이징을 구성하는데 필요한 데이터의 수 - 기준값
	private int lineCount; 				// 화면에서 보여주는 페이지 수 - 고정값
	private int perPage; 				// 한 페이지에서 보여줄 게시물의 수 - 고정값
	private int first; 					// lineCount의 첫 번호
	private int last; 					// lineCount의 마지막 번호
	private boolean hasNext = false;	// next버튼 표현여부
	private boolean hasPrev = false;	// prev버튼 표현여부
	private int rowNum;					// cnt를 생각하지 않은 페이징 구성에 필요한 데이터 값
	private String keyword;				// 검색 키워드
	private Map<String, String> criMap;	// 검색구분, 키워드
	private Map<String, String> colMap;	// 검색구분, 검색구분에 따른 sql문
	private List<String> values;		// criMap의 value값과 dummy의 배열
	private String[] types;
	
	
	

	
	public String[] getTypes() {
		return types;
	}

	public void setTypes(String[] types) {
		this.types = types;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}

	public static int getNumber(String str){
		try{
			return Integer.parseInt(str);
			//int로 변환이 가능한 String값이면 변환
		}catch(Exception e){
			return 1;
			//int로 변환이 불가능하다면 1을 반납
			//이런 방식으로 하면 에러가 발생해도 1이 return되기때문에 프로그램은 돌아는 간다
		}
	}

//	생성자들
	public PageMaker(){
		this(1);
	}
	
//	public PageMaker(String page){
//		this(page, 0);
//	}
	
	public PageMaker(int page){
		this(page,0);
	}
	//overloading
	
	public PageMaker(int page, int cnt) {
		this(page, cnt, 10,5);
	}
	//오버로딩
	
	public PageMaker(int page, int cnt, int lineCount, int perPage) {
		super();
		this.page = page;
		this.cnt = cnt;
		this.lineCount = lineCount;
		this.perPage = perPage;
		this.first = 1;
		this.last = 5;
		
	}
	
	
	
	public boolean isHasNext() {
		return hasNext;
	}

	public boolean isHasPrev() {
		return hasPrev;
	}

	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}

	public void setHasPrev(boolean hasPrev) {
		this.hasPrev = hasPrev;
	}

	public int getFirst() {
		return first;
	}

	public void setFirst(int first) {
		this.first = first;
	}

	public int getLast() {
		return last;
	}

	public void setLast(int last) {
		this.last = last;
	}

	public int getPage() {
		return page;
	}
	
//	public String getPage() {
//		return "" + page;
//	}

//	public void setPage(int page) {
//		this.page = page;
//	}
	
	public void setPage(int page) {
		this.page = page;
	}

	public int getCnt() {
		return cnt;
	}


	public int getLineCount() {
		return lineCount;
	}

	public void setLineCount(int lineCount) {
		this.lineCount = lineCount;
	}

	public int getPerPage() {
		return perPage;
	}

	public void setPerPage(int perPage) {
		this.perPage = perPage;
	}
	
//	public int getRowNum(int pageNum){
//		return (  ( (int)(Math.ceil(pageNum/(double)perPage)) ) * (perPage * lineCount))  +1;
//	}
//	
//	public int getRowNum(){
//		return (  ( (int)(Math.ceil(page/(double)perPage)) ) * (perPage * lineCount))  +1;
//	}
	
	public int getRowNum() {
		return rowNum;
	}
	
	public void setRowNum() {
		this.rowNum = (  ( (int)(Math.ceil(page/(double)perPage)) ) * (lineCount * perPage))  +1;
	}
	
	//ex: 12페이지를 보고 싶어요(명화)
	//(  ( (int)(Math.ceil(12/(double)10)) ) * (10 * 5))  +1;
	//12페이지 / 한 페이지에서 보여줄 게시물 10 = 1.2
	//ceil로 올림하면 1.2 -> 2.0로 변환. 변환값을 int로 변환하여 2가 됨
	//2*(한 페이지에서 보여줄 게시물 10 * 화면에서 보여주는 페이지 수 5 = 50)
	//2*50 = 100
	//100 + 1 <- next를 보여주기 위하여 산출된 값에 1을 더해주게 됨

	public int getRnFirst(){
		
		return getRnLast() - lineCount;
	}
	
	public int getRnLast(){
		
		return (page * lineCount);
	}

	public Map<String, String> getCriMap() {
		return criMap;
	}

	public void setCriMap(Map<String, String> criMap) {
		this.criMap = criMap;
	}

	public Map<String, String> getColMap() {
		return colMap;
	}

	public void setColMap(Map<String, String> colMap) {
		this.colMap = colMap;
	}

	public List<String> getValues() {
		return values;
	}

	public void setValues(List<String> values) {
		this.values = values;
	}

	//	cnt의 세터가 실행이 되면 페이징에 필요한 모든 요소가 한번에 계산이 됨
	public void setCnt(int cnt) {
		this.cnt = cnt;
		setPaging();
	}
	
	public void setPaging() {
		
		setRowNum();
		
		if(cnt == rowNum){
			this.setHasNext(true);
		}else{
			this.setHasNext(false);
		}
		
		if(((perPage*lineCount)+1) < cnt){
			this.setHasPrev(true);
		}else{
			this.setHasPrev(false);
		}
		
		setLast((int)(Math.ceil((double)page/perPage))*perPage);
		setFirst(last - (perPage-1));
		
		// 게시판의 마지막 페이지 표시
		if(cnt != rowNum){
			setLast(last - (int)(Math.floor((rowNum-cnt)/10)));
		}
	}
	
	public String checked(String type){
		
		if(types == null || types.length == 0){
			return "";
		}
		for(int i = 0; i < types.length; i++){
			
			if(types[i].equals(type)){
				return "checked";
			}
		}
		return "";
		
	}

	public String getSql() {
		
		// type이 체크되어있어도 keyword가 없으면 검색이 안되도록 설정
		if(keyword == null || keyword.length() == 0 || types == null){
			return "";
		}
		
		criMap = new HashMap<String, String>();
		colMap = new HashMap<String, String>();
		// colMap 세팅부분. 여기서는 sql구문의 검색기준이 될 collumn이 된다.
		colMap.put("t", "title");
		colMap.put("w", "userid");
		colMap.put("c", "cont");
		
		for (String type : types) {
			criMap.put(type, keyword);
		}
		
		// sql문 작성용. where문이 추가되기에 시작부분을 where로 준다. where의 앞뒤로 띄어쓰기가 들어감
		StringBuilder builder = new StringBuilder(" where ");
		
		Iterator<String> iter = criMap.keySet().iterator();
		
		values = new ArrayList<String>();
		
		// preparedStatement
		// 차후 getkey에서 values의 요소들을 소비하여 ?부분을 생성할때 ?의 갯수만큼 values의 값을 소비하게 된다
		// 문제는 그 후에 제대로 된 값을 사용하려면 ?로 값을 소비하기때문에 정작 사용해야 할 값을 사용할 수 없게 된다
		// 그래서 ?가 생성될 갯수(crimap의 size)만큼 dummy data를 필요없는 데이터로 넣어줘서 먼저 소비하게 하고
		// 본 데이터를 제대로 사용하게 만들어 주는 것
		for (int i = 0; i < criMap.size(); i++) {
			this.values.add("dummy");
		}
		
		while(iter.hasNext()){
			// dummy데이터가 먼저 들어간 values에 실제로 사용될 keyword를 넣어준다
			String key = iter.next();
			values.add(criMap.get(key));
			
			// 첫번째 key는 colMap에서 검색할 collumn값을 대입해주고 두번째 key는 preparedStatement로 대입될 keyword의 자리이다
			builder.append(colMap.get(key) + " like '%'||#{key}||'%' or ");
		}
		
		// substring을 이용하여 구문 맨 마지막 부분의 필요없는 " or "를 제거해준다
		// 첫번째 숫자는 기준이 될 문자열의 부분이고 두번째는 기준부터 몇번째 글자까지 반환할것인가를 정해준다
		// 여기서는 문자열 전체의 길이에서 -3만큼 잘라내고 반환하는것이기에 "or "부분까지 잘라내고 나머지 값을 반환하게 된다
		return builder.substring(0, builder.length()-3);
		
	}
	
	public String getKey() {
		return values.remove(0);
	}

	@Override
	public String toString() {
		return "PageMaker [page=" + page + ", cnt=" + cnt + ", lineCount="
				+ lineCount + ", perPage=" + perPage + ", first=" + first
				+ ", last=" + last + ", hasNext=" + hasNext + ", hasPrev="
				+ hasPrev + ", rowNum=" + rowNum + ", keyword=" + keyword
				+ ", types=" + Arrays.toString(types) + ", criMap="
				+ criMap + ", colMap=" + colMap + ", values=" + values + "]";
	}


//	public static void main(String[] args) {
//
//		PageMaker maker = new PageMaker(12);
//		System.out.println(maker.getRowNum());
//	}
//	
}