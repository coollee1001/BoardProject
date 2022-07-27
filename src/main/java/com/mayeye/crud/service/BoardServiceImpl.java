package com.mayeye.crud.service;

import java.util.List;

import com.mayeye.crud.dto.BoardDTO;
import com.mayeye.crud.dto.PageDTO;

public interface BoardServiceImpl {
	public List<BoardDTO> getListContent(int page);
	public PageDTO getContentCnt(int currentPage);
	public void addContent(BoardDTO boardDTO);
	public BoardDTO getContent(int board_index);
	public void modifyContent(BoardDTO boardDTO);
	public void deleteContent(int board_index);
	
}
