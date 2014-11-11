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

	private int page; 					// ���� ������ - ���ذ�
	private int cnt; 					// ���� ȭ���� ����¡�� �����ϴµ� �ʿ��� �������� �� - ���ذ�
	private int lineCount; 				// ȭ�鿡�� �����ִ� ������ �� - ������
	private int perPage; 				// �� ���������� ������ �Խù��� �� - ������
	private int first; 					// lineCount�� ù ��ȣ
	private int last; 					// lineCount�� ������ ��ȣ
	private boolean hasNext = false;	// next��ư ǥ������
	private boolean hasPrev = false;	// prev��ư ǥ������
	private int rowNum;					// cnt�� �������� ���� ����¡ ������ �ʿ��� ������ ��
	private String keyword;				// �˻� Ű����
	private Map<String, String> criMap;	// �˻�����, Ű����
	private Map<String, String> colMap;	// �˻�����, �˻����п� ���� sql��
	private List<String> values;		// criMap�� value���� dummy�� �迭
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
	
	//ex: 12�������� ���� �;��(��ȭ)
	//(  ( (int)(Math.ceil(12/(double)10)) ) * (10 * 5))  +1;
	//12������ / �� ���������� ������ �Խù� 10 = 1.2
	//ceil�� �ø��ϸ� 1.2 -> 2.0�� ��ȯ. ��ȯ���� int�� ��ȯ�Ͽ� 2�� ��
	//2*(�� ���������� ������ �Խù� 10 * ȭ�鿡�� �����ִ� ������ �� 5 = 50)
	//2*50 = 100
	//100 + 1 <- next�� �����ֱ� ���Ͽ� ����� ���� 1�� �����ְ� ��

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

	//	cnt�� ���Ͱ� ������ �Ǹ� ����¡�� �ʿ��� ��� ��Ұ� �ѹ��� ����� ��
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
		
		// �Խ����� ������ ������ ǥ��
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
		
		// type�� üũ�Ǿ��־ keyword�� ������ �˻��� �ȵǵ��� ����
		if(keyword == null || keyword.length() == 0 || types == null){
			return "";
		}
		
		criMap = new HashMap<String, String>();
		colMap = new HashMap<String, String>();
		// colMap ���úκ�. ���⼭�� sql������ �˻������� �� collumn�� �ȴ�.
		colMap.put("t", "title");
		colMap.put("w", "userid");
		colMap.put("c", "cont");
		
		for (String type : types) {
			criMap.put(type, keyword);
		}
		
		// sql�� �ۼ���. where���� �߰��Ǳ⿡ ���ۺκ��� where�� �ش�. where�� �յڷ� ���Ⱑ ��
		StringBuilder builder = new StringBuilder(" where ");
		
		Iterator<String> iter = criMap.keySet().iterator();
		
		values = new ArrayList<String>();
		
		// preparedStatement
		// ���� getkey���� values�� ��ҵ��� �Һ��Ͽ� ?�κ��� �����Ҷ� ?�� ������ŭ values�� ���� �Һ��ϰ� �ȴ�
		// ������ �� �Ŀ� ����� �� ���� ����Ϸ��� ?�� ���� �Һ��ϱ⶧���� ���� ����ؾ� �� ���� ����� �� ���� �ȴ�
		// �׷��� ?�� ������ ����(crimap�� size)��ŭ dummy data�� �ʿ���� �����ͷ� �־��༭ ���� �Һ��ϰ� �ϰ�
		// �� �����͸� ����� ����ϰ� ����� �ִ� ��
		for (int i = 0; i < criMap.size(); i++) {
			this.values.add("dummy");
		}
		
		while(iter.hasNext()){
			// dummy�����Ͱ� ���� �� values�� ������ ���� keyword�� �־��ش�
			String key = iter.next();
			values.add(criMap.get(key));
			
			// ù��° key�� colMap���� �˻��� collumn���� �������ְ� �ι�° key�� preparedStatement�� ���Ե� keyword�� �ڸ��̴�
			builder.append(colMap.get(key) + " like '%'||#{key}||'%' or ");
		}
		
		// substring�� �̿��Ͽ� ���� �� ������ �κ��� �ʿ���� " or "�� �������ش�
		// ù��° ���ڴ� ������ �� ���ڿ��� �κ��̰� �ι�°�� ���غ��� ���° ���ڱ��� ��ȯ�Ұ��ΰ��� �����ش�
		// ���⼭�� ���ڿ� ��ü�� ���̿��� -3��ŭ �߶󳻰� ��ȯ�ϴ°��̱⿡ "or "�κб��� �߶󳻰� ������ ���� ��ȯ�ϰ� �ȴ�
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