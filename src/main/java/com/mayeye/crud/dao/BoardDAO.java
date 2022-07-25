package com.mayeye.crud.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mayeye.crud.dto.BoardDTO;

@Repository
public class BoardDAO implements BoardDAOImpl{
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	private String NAMESPACE = "board.";
	
	//게시판 리스트
	@Override
	public List<BoardDTO> board_list() {
		return sqlSessionTemplate.selectList(NAMESPACE + "getContentList");
	}
	
	//게시판 추가
	@Override
	public int board_insert(BoardDTO boardDTO) {
		return sqlSessionTemplate.insert(NAMESPACE + "insertContent", boardDTO);
	}
	
	//게시판 상세보기
	@Override
	public BoardDTO board_read(int board_index) {
		return sqlSessionTemplate.selectOne(NAMESPACE + "getContent", board_index);
	}
	
	//게시판 수정하기
	@Override
	public int board_modify(BoardDTO boardDTO) {
		return sqlSessionTemplate.update(NAMESPACE + "updateContent", boardDTO);
	}
	
	//게시판 내용 삭제
	@Override
	public int board_delete(int board_index) {
		return sqlSessionTemplate.delete(NAMESPACE + "deleteContent", board_index);
	}
}
