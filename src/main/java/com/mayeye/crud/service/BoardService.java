package com.mayeye.crud.service;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mayeye.crud.dao.BoardDAO;
import com.mayeye.crud.dto.BoardDTO;

@Service
public class BoardService implements BoardServiceImpl{
	@Value("${path.upload}")
	private String path_upload;
	
	@Autowired
	private BoardDAO boardDAO;
	
	//read, 리스트 불러오기
	@Override
	public List<BoardDTO> getListContent() {
		List<BoardDTO> getList = boardDAO.board_list();
		return getList;
	}
	
	//파일이름 재정의 후 재정의된 파일 이름으로 서버 로컬에 저장
	private String saveUploadFile(MultipartFile upload_file) {
		
		//파일이름 재정의(시스템 시간_업로드파일 원본이름)
		String file_name = System.currentTimeMillis() + "_" + upload_file.getOriginalFilename();
		
		try {
			upload_file.transferTo(new File(path_upload + "/" + file_name));
		}catch(Exception e) {
			e.printStackTrace();
		}
		return file_name;
	}
	
	//insert, 게시판 추가
	@Override
	public void addContent(BoardDTO boardDTO) {
		MultipartFile upload_file = boardDTO.getUpload_file();
		
		//사이즈가 0보다 크면, 데이터가 있으면
		if(upload_file.getSize() > 0) {
			String convertFile = saveUploadFile(upload_file);
			boardDTO.setBoard_file(convertFile);
		}
		
		boardDAO.board_insert(boardDTO);
	}
	
	//read, 자세히 보기
	@Override
	public BoardDTO getContent(int board_index) {
		BoardDTO boardDTO = boardDAO.board_read(board_index);
		return boardDTO;
	}
	
	//update, 수정하기
	@Override
	public void modifyContent(BoardDTO boardDTO) {
		MultipartFile upload_file = boardDTO.getUpload_file();
		
		//사이즈가 0보다 크면, 데이터가 있으면
		if(upload_file.getSize() > 0) {
			String convertFile = saveUploadFile(upload_file);
			boardDTO.setBoard_file(convertFile);
		}
		
		boardDAO.board_modify(boardDTO);
	}
	
	//delete, 삭제하기
	@Override
	public void deleteContent(int board_index) {
		boardDAO.board_delete(board_index);
		
	}
	
	
}
