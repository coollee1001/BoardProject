package com.mayeye.crud.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mayeye.crud.service.FileService;

@RestController
@RequestMapping("/board/file")
public class FileController {
	
	@Autowired
	private FileService fileService;
	
	@GetMapping("/filedownload")
	public void fileDownload(HttpServletRequest req, HttpServletResponse respon) throws Exception {
		fileService.fileDownload(req, respon);
	}
	
	@GetMapping("/exceldownload")
	public void excelDownload(HttpServletRequest req, HttpServletResponse respon, @RequestParam("fileName") String fileName) throws Exception {
		fileService.dbfileDownload(req, respon, fileName);
	}
	
}	
