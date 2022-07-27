package com.mayeye.crud.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mayeye.crud.dto.BoardDTO;
import com.mayeye.crud.dto.ExcelDTO;
import com.mayeye.crud.dto.PageDTO;
import com.mayeye.crud.service.BoardService;
import com.mayeye.crud.service.ExcelService;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private ExcelService excelService;
	
	
	//리스트 목록 조회
	@GetMapping("/list")
	public String list(Model model, @RequestParam(value = "page", defaultValue = "1") int page) {
		//게시판 리스트
		List<BoardDTO> contentList = boardService.getListContent(page);

		PageDTO pageDTO = boardService.getContentCnt(page);
		
		model.addAttribute("contentList", contentList);
		model.addAttribute("pageDTO", pageDTO);
		model.addAttribute("page", page);
		
		return "board/list";
	}
	
	//글쓰기
	@GetMapping("/write")
	public String write(@ModelAttribute("writeContentDTO") BoardDTO wirteContentDTO) {
		return "board/write";
	}
	
	//글쓰기 처리
	@PostMapping("/write_pro")
	public String write_pro(@Valid @ModelAttribute("writeContentDTO") BoardDTO writeContentDTO,
			BindingResult result) {

		//다른 필수항목이 없으면
		if(result.hasErrors()) {
			return "board/write";
		}
		
		//엑셀 필드가 비어있는지
		if(writeContentDTO.getUpload_excelFile().isEmpty()) {
			if(writeContentDTO.getUpload_excelFile().isEmpty()) {
				result.addError(new FieldError("writeContentDTO", "upload_excelFile", "필수로 입력해야합니다"));
			}
			return "board/write";
		}
		
		//확장자가 정말 엑셀인지 확인
		String fileName = writeContentDTO.getUpload_excelFile().getOriginalFilename(); 
		int pos = fileName.indexOf(".");
		String ext = fileName.substring(pos+1, fileName.length());		
		
		if(!"xlsx".equals(ext)) {
			result.addError(new FieldError("writeContentDTO", "upload_excelFile", "지원하는 엑셀 파일이 아닙니다"));
			System.out.println(ext);
			return "board/write";
		}
		
		boardService.addContent(writeContentDTO);
		
		return "board/write_success";
	}
	
	//상세 조회
	@GetMapping("/read")
	public String read(@RequestParam("board_index") int board_index,
			 @RequestParam("page") int page,
			Model model) {
		//기존에 있던 엑셀 데이터 지우기
		excelService.delete();
		BoardDTO readContentDTO = boardService.getContent(board_index);
		
		//get타입으로 넘어와서 인덱스를 사용자가 변경했을 시 커스텀 에러
		if(readContentDTO.getBoard_subject() == null) {
			return "error/error";
		}
		
		//읽어올 때 엑셀 데이터 작성
		excelService.excelUpload(readContentDTO.getBoard_excelFile());
		
		//작성 다 되면 데이터 불러와서 담기
		List<ExcelDTO> readExcelContentDTO = excelService.excelRead();
		
		model.addAttribute("readContentDTO", readContentDTO);
		model.addAttribute("board_index", board_index);
		model.addAttribute("readExcelContentDTO", readExcelContentDTO);
		model.addAttribute("page", page);
		
		return "board/read";
	}
	
	//수정 화면
	@GetMapping("/modify")
	public String modify(@RequestParam("board_index") int board_index,
			@ModelAttribute("modifyContentDTO") BoardDTO modifyContentDTO,
			@RequestParam("page") int page,
			Model model) {
		
		model.addAttribute("board_index", board_index);
		
		BoardDTO tempContentDTO = boardService.getContent(board_index);
		
		modifyContentDTO.setBoard_index(board_index);
		modifyContentDTO.setBoard_subject(tempContentDTO.getBoard_subject());
		modifyContentDTO.setBoard_content(tempContentDTO.getBoard_content());
		modifyContentDTO.setBoard_file(tempContentDTO.getBoard_file());
		modifyContentDTO.setBoard_date(tempContentDTO.getBoard_date());
		
		modifyContentDTO.setBoard_excelFile(tempContentDTO.getBoard_excelFile());
		
		model.addAttribute("page", page);
		
		return "board/modify";
	}
	
	//수정 처리
	@PostMapping("/modify_pro")
	public String modify_pro(@Valid @ModelAttribute("modifyContentDTO") BoardDTO modifyContentDTO,
				BindingResult result,
				@RequestParam("page") int page,
				Model model) {


		if(result.hasErrors()) {
			return "board/modify";
		}
		
		//새로운 엑셀 파일을 추가했을 시
		if(!modifyContentDTO.getUpload_excelFile().isEmpty()) {
			//확장자가 정말 엑셀인지 확인
			String fileName = modifyContentDTO.getUpload_excelFile().getOriginalFilename(); 
			int pos = fileName.indexOf(".");
			String ext = fileName.substring(pos+1, fileName.length()).toLowerCase();
			
			if(!"xlsx".equals(ext)) {
				result.addError(new FieldError("writeContentDTO", "upload_excelFile", "지원하는 엑셀 파일이 아닙니다"));
				return "board/write";
			}
		}
		
		boardService.modifyContent(modifyContentDTO);
		
		model.addAttribute("modifyContentDTO", modifyContentDTO);
		model.addAttribute("page", page);
		
		return "board/modify_success";
	}
	
	//삭제 처리
	@GetMapping("/delete")
	public String delete(@RequestParam("board_index") int board_index,
			Model model) {
		boardService.deleteContent(board_index);
		
		model.addAttribute("board_index", board_index);
		return "board/delete";
	}
	
	
	
	
}
