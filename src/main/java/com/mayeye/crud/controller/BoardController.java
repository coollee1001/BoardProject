package com.mayeye.crud.controller;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mayeye.crud.dto.BoardDTO;
import com.mayeye.crud.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	
	//리스트 목록 조회
	@GetMapping("/list")
	public String list(Model model) {
		List<BoardDTO> contentList = boardService.getListContent();
		
		model.addAttribute("contentList", contentList);
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
		if(result.hasErrors()) {
			return "board/write";
		}
		boardService.addContent(writeContentDTO);
		
		return "board/write_success";
	}
	
	//상세 조회
	@GetMapping("/read")
	public String read(@RequestParam("board_index") int board_index,
			Model model) {
		BoardDTO readContentDTO = boardService.getContent(board_index);
		
		if(readContentDTO.getBoard_subject() == null) {
			return "error/error";
		}
		
		model.addAttribute("readContentDTO", readContentDTO);
		model.addAttribute("board_index", board_index);
		
		return "board/read";
	}
	
	//수정 화면
	@GetMapping("/modify")
	public String modify(@RequestParam("board_index") int board_index,
			@ModelAttribute("modifyContentDTO") BoardDTO modifyContentDTO,
			Model model) {
		
		model.addAttribute("board_index", board_index);
		
		BoardDTO tempContentDTO = boardService.getContent(board_index);
		
		modifyContentDTO.setBoard_index(board_index);
		modifyContentDTO.setBoard_subject(tempContentDTO.getBoard_subject());
		modifyContentDTO.setBoard_content(tempContentDTO.getBoard_content());
		modifyContentDTO.setBoard_file(tempContentDTO.getBoard_file());
		modifyContentDTO.setBoard_date(tempContentDTO.getBoard_date());
		
		return "board/modify";
	}
	
	//수정 처리
	@PostMapping("/modify_pro")
	public String modify_pro(@Valid @ModelAttribute("modifyContentDTO") BoardDTO modifyContentDTO,
				BindingResult result,
				Model model) {
		model.addAttribute("modifyContentDTO", modifyContentDTO);
		
		if(result.hasErrors()) {
			model.addAttribute("writeContentDTO", modifyContentDTO);
			return "board/modify";
		}
		
		model.addAttribute("modifyContentDTO", modifyContentDTO);
		boardService.modifyContent(modifyContentDTO);
		
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
