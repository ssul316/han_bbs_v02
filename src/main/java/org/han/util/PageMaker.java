package org.han.util;

import org.springframework.stereotype.Service;

@Service
public class PageMaker {

	private int page; 					// ���� ������ - ���ذ�
	private int cnt; 					// ��ü ����¡�� �����ϴµ� �ʿ��� �������� �� - ���ذ�
	private int lineCount; 				// ȭ�鿡�� �����ִ� ������ �� - ������
	private int perPage; 				// �� ���������� ������ �Խù��� �� - ������
	private int first; 					// lineCount�� ù ��ȣ
	private int last; 					// lineCount�� ������ ��ȣ
	private boolean hasNext = false;	// next��ư ǥ������
	private boolean hasPrev = false;	// prev��ư ǥ������
	private int rowNum;
	
	public static int getNumber(String str){
		try{
			return Integer.parseInt(str);
			//int�� ��ȯ�� ������ String���̸� ��ȯ
		}catch(Exception e){
			return 1;
			//int�� ��ȯ�� �Ұ����ϴٸ� 1�� �ݳ�
			//�̷� ������� �ϸ� ������ �߻��ص� 1�� return�Ǳ⶧���� ���α׷��� ���ƴ� ����
		}
	}

//	�����ڵ�
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
	//�����ε�
	
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
//		
//		return (  ( (int)(Math.ceil(pageNum/(double)perPage)) ) * (perPage * lineCount))  +1;
//	 	
//	}
//	
//	public int getRowNum(){
//		
//		return (  ( (int)(Math.ceil(page/(double)perPage)) ) * (perPage * lineCount))  +1;
//	 	
//	}
	
	
	public int getRowNum() {
		return rowNum;
	}
	
	public void setRowNum(String num) {
		this.rowNum = (  ( (int)(Math.ceil(getNumber(num)/(double)perPage)) ) * (perPage * lineCount))  +1;
	}
	
	
	//ex: 12�������� ���� �;��(��ȭ)
	//(  ( (int)(Math.ceil(12/(double)10)) ) * (10 * 5))  +1;
	//12������ / �� ���������� ������ �Խù� 10 = 1.2
	//ceil�� �ø��ϸ� 1.2 -> 2.0�� ��ȯ. ��ȯ���� int�� ��ȯ�Ͽ� 2�� ��
	//2*(�� ���������� ������ �Խù� 10 * ȭ�鿡�� �����ִ� ������ �� 5 = 50)
	//2*50 = 100
	//100 + 1 <- next�� �����ֱ� ���Ͽ� ����� ���� 1�� �����ְ� ��

//	private int page; //���� ������
//	private int cnt; //���� �������� �����ϴµ� �ʿ��� �������� ��
//	private int lineCount; //ȭ�鿡�� �����ִ� ������ ��
//	private int perPage; //�� ���������� ������ �Խù��� ��
//	private int first; //���������� ������ ù��° �Խù�
//	private int last; //���������� ������ ������ �Խù�


	public int getRnFirst(){
		
		return getRnLast() - perPage;
	}
	
	public int getRnLast(){
		
		return (page * perPage);
	}
	
//	cnt�� ���Ͱ� ������ �Ǹ� ����¡�� �ʿ��� ��� ��Ұ� �ѹ��� ����� ��
//	last�� ��� ������ ������ ���ڸ� �����ؾ� �ϹǷ� �߰� ������ ��
	public void setCnt(int cnt) {
		this.cnt = cnt;
//		if(cnt == getRowNum(page)){
//			this.setHasNext(true);
//		}else{
//			this.setHasNext(false);
//		}
//		if(((lineCount*perPage)+1) < getRowNum(page)){
//			this.setHasPrev(true);
//		}else{
//			this.setHasPrev(false);
//		}
//		setFirst(1+(((getRowNum(page)-((lineCount*perPage)+1))/(lineCount*perPage))*5));
//		setLast(5+(((getRowNum(page)-((lineCount*perPage)+1))/(lineCount*perPage))*5));

		if(cnt == rowNum){
			this.setHasNext(true);
		}else{
			this.setHasNext(false);
		}
		if(((lineCount*perPage)+1) < rowNum){
			this.setHasPrev(true);
		}else{
			this.setHasPrev(false);
		}
//		setFirst(1+(((rowNum-((lineCount*perPage)+1))/(lineCount*perPage))*5));
//		setLast(5+(((rowNum-((lineCount*perPage)+1))/(lineCount*perPage))*5));
		setLast((int)(Math.ceil((double)page/perPage))*perPage);
		setFirst(last - (perPage-1));
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






