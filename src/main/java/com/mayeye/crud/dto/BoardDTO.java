package com.mayeye.crud.dto;


import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

public class BoardDTO {
	
	private int board_index;
	
	@NotBlank
	private String board_subject;
	
	@NotBlank
	private String board_content;

	private MultipartFile upload_file;
	private String board_file;
	
	private String board_date;
	
	private MultipartFile upload_excelFile;
	private String board_excelFile;
	
	
	
	public int getBoard_index() {
		return board_index;
	}
	public void setBoard_index(int board_index) {
		this.board_index = board_index;
	}
	public String getBoard_subject() {
		return board_subject;
	}
	public void setBoard_subject(String board_subject) {
		this.board_subject = board_subject;
	}
	public String getBoard_content() {
		return board_content;
	}
	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}
	
	
	
	public MultipartFile getUpload_file() {
		return upload_file;
	}
	public void setUpload_file(MultipartFile upload_file) {
		this.upload_file = upload_file;
	}
	public String getBoard_file() {
		return board_file;
	}
	public void setBoard_file(String board_file) {
		this.board_file = board_file;
	}
	
	public String getBoard_date() {
		return board_date;
	}
	public void setBoard_date(String board_date) {
		this.board_date = board_date;
	}
	
	
	public MultipartFile getUpload_excelFile() {
		return upload_excelFile;
	}
	public void setUpload_excelFile(MultipartFile upload_excelFile) {
		this.upload_excelFile = upload_excelFile;
	}
	public String getBoard_excelFile() {
		return board_excelFile;
	}
	public void setBoard_excelFile(String board_excelFile) {
		this.board_excelFile = board_excelFile;
	}
	
	
	
	
}
