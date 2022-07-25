package com.mayeye.crud.dao;

import java.util.List;

import com.mayeye.crud.dto.BoardDTO;

public interface BoardDAOImpl {
	public List<BoardDTO> board_list();
	public int board_insert(BoardDTO boardDTO);
	public BoardDTO board_read(int board_index);
	public int board_modify(BoardDTO boardDTO);
	public int board_delete(int board_index);
}
