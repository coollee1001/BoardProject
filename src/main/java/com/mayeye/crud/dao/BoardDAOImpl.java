package com.mayeye.crud.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.mayeye.crud.dto.BoardDTO;

public interface BoardDAOImpl {
	
	// 모든 리스트 게시판 정보
	public List<BoardDTO> board_list(RowBounds rowBounds);
	
	// 게시판 글 작성
	public int board_insert(BoardDTO boardDTO);
	
	// 게시판 글 읽어오기(하나의 객체만)
	public BoardDTO board_read(int board_index);
	
	// 게시판 글 수정(읽어온 하나의 객체만 수정)
	public int board_modify(BoardDTO boardDTO);
	
	// 게시판 글 지우기(읽어온 하나의 객체만 삭제)
	public int board_delete(int board_index);
	
	// 게시판의 글 개수
	public int getContentCnt();
}
