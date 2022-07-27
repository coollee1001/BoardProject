package com.mayeye.crud.service;

import java.io.File;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mayeye.crud.dao.BoardDAO;
import com.mayeye.crud.dto.BoardDTO;
import com.mayeye.crud.dto.PageDTO;

@Service
public class BoardService implements BoardServiceImpl{
	@Value("${path.upload}")
	private String path_upload;
	
	@Value("${page.listcnt}")
	private int page_listcnt;
	
	@Value("${page.paginationcnt}")
	private int page_paginationcnt;
	
	@Autowired
	private BoardDAO boardDAO;
	
	//read, 리스트 불러오기
	@Override
	public List<BoardDTO> getListContent(int page) {
		int start = (page - 1) * page_listcnt;
		RowBounds rowBounds = new RowBounds(start, page_listcnt);
		List<BoardDTO> list = boardDAO.board_list(rowBounds); 
		
		return list;
	}
	
	//read, 페이징 세팅
	@Override
	public PageDTO getContentCnt(int currentPage) {
		int content_cnt = boardDAO.getContentCnt();		
		PageDTO pageDTO = new PageDTO(content_cnt, currentPage, page_listcnt, page_paginationcnt);
		
		return pageDTO;
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
		MultipartFile upload_excelFile = boardDTO.getUpload_excelFile();
		
		String convertFile = null;
		//사이즈가 0보다 크면, 데이터가 있으면
		if(upload_file.getSize() > 0) {
			convertFile = saveUploadFile(upload_file);
			boardDTO.setBoard_file(convertFile);
		}	
		
		//엑셀 파일이라면
		if(upload_excelFile.getSize() > 0) {
			convertFile = saveUploadFile(upload_excelFile);
			boardDTO.setBoard_excelFile(convertFile);
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
		MultipartFile upload_excelFile = boardDTO.getUpload_excelFile();
		
		//수정 전 파일 경로
		String old_Imagepath = boardDTO.getBoard_file();
		String old_excelpath = boardDTO.getBoard_excelFile();
		
		String convertFile = null;
		//사이즈가 0보다 크면, 데이터가 있으면
		if(upload_file.getSize() > 0) {
			convertFile = saveUploadFile(upload_file);
			boardDTO.setBoard_file(convertFile);
		}
		
		//엑셀 파일이라면
		if(upload_excelFile.getSize() > 0) {
			convertFile = saveUploadFile(upload_excelFile);
			boardDTO.setBoard_excelFile(convertFile);
		}

		int res = boardDAO.board_modify(boardDTO);
		
		//수정 완료 후 수정 전 파일 삭제
		if(res > 0) {
			if(upload_file.getSize() > 0) {
				File old_Image = new File(path_upload + old_Imagepath);
				if(old_Image != null) {
					old_Image.delete();
				}
			}
			
			if(upload_excelFile.getSize() > 0) {
				File old_Excel = new File(path_upload + old_excelpath);
				
				if(old_Excel != null) {
					old_Excel.delete();
				}
			}			
		}		
	}
	
	//delete, 삭제하기
	@Override
	public void deleteContent(int board_index) {
		BoardDTO dto = boardDAO.board_read(board_index);
		int res = boardDAO.board_delete(board_index);
		//삭제 완료 되면 파일도 삭제
		if(res > 0) {
			File file = new File(path_upload + dto.getBoard_file());
			File excelfile = new File(path_upload + dto.getBoard_excelFile());
			if(file != null) {
				file.delete();
			}
			if(excelfile != null) {
				excelfile.delete();
			}			
		}
	}
	
	
}
