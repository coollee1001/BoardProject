package com.mayeye.crud.dto;

public class PageDTO {

	// 최소 페이지 번호
	private int min;
	// 최대 페이지 번호
	private int max;
	// 이전 버튼의 페이지 번호
	private int prevPage;
	// 다음 버튼의 페이지 번호
	private int nextPage;
	// 전체 페이지 개수
	private int pageCnt;
	// 현재 페이지 번호
	private int currentPage;
	
	/**
	 * 
	 * @param contentCnt 전체글 개수
	 * @param currentPage 현재 글 번호
	 * @param contentPageCnt 페이지당 글의 개수
	 * @param paginationCnt 페이지 버튼의 개수
	 */
	public PageDTO(int contentCnt, int currentPage, int contentPageCnt, int paginationCnt) {
		// 현재 페이지 번호 설정
		this.currentPage = currentPage;
		
		// 전체 페이지 개수 (전체 글 개수(DB 불러와서 받을 것) / 페이지당 글 개수(설정값))
		pageCnt = contentCnt / contentPageCnt;
		
		//나머지를 구해서 0보다 많으면 페이지 개수 추가
		if(contentCnt % contentPageCnt > 0) {
			pageCnt++;
		}
		
		min = ((currentPage - 1) / contentPageCnt) * contentPageCnt + 1;
		max = min + paginationCnt - 1;
		
		
		if(max > pageCnt) {
			max = pageCnt;
		}
		
		prevPage = min - 1;
		nextPage = max + 1;
		if(nextPage > pageCnt) {
			nextPage = pageCnt;
		}
	}
	
	
	public int getMin() {
		return min;
	}
	public int getMax() {
		return max;
	}
	public int getPrevPage() {
		return prevPage;
	}
	public int getNextPage() {
		return nextPage;
	}
	public int getPageCnt() {
		return pageCnt;
	}
	public int getCurrentPage() {
		return currentPage;
	}
}
