package com.mayeye.crud.dao;

import java.util.List;

import com.mayeye.crud.dto.ExcelDTO;

public interface ExcelDAOImpl {
	int inserExcel(ExcelDTO dto);
	List<ExcelDTO> read();
	int delete();
}
