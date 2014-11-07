package org.han.util;

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
	
	public PageMaker(int page){
		this(page, 0);
	}
	
	public PageMaker(String pageStr){
		this(getNumber(pageStr),0);
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

	public void setPage(int page) {
		this.page = page;
	}
	
	public void setPage(String page) {
		this.page = getNumber(page);
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
	
//	cnt의 세터가 실행이 되면 페이징에 필요한 모든 요소가 한번에 계산이 됨
//	last의 경우 마지막 페이지 숫자를 생각해야 하므로 추가 로직이 들어감
	public void setCnt(int cnt) {
		this.cnt = cnt;
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

	@Override
	public String toString() {
		return "PageMaker [page=" + page + ", cnt=" + cnt + ", lineCount="
				+ lineCount + ", perPage=" + perPage + ", first=" + first
				+ ", last=" + last + ", hasNext=" + hasNext + ", hasPrev="
				+ hasPrev + ", rowNum=" + rowNum + "]";
	}

//	public static void main(String[] args) {
//
//		PageMaker maker = new PageMaker(12);
//		System.out.println(maker.getRowNum());
//	}
//	
}