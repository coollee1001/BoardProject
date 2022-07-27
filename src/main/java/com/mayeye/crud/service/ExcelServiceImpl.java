package com.mayeye.crud.service;

import java.util.List;

import com.mayeye.crud.dto.ExcelDTO;

public interface ExcelServiceImpl {

	public void excelUpload(String destFile);
	public List<ExcelDTO> excelRead();
}
