package com.mayeye.crud.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mayeye.crud.dto.ExcelDTO;

@Repository
public class ExcelDAO implements ExcelDAOImpl{
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	private String NAMESPACE = "excel.";
	
	@Override
	public int inserExcel(ExcelDTO dto) {
		return sqlSessionTemplate.insert(NAMESPACE + "insertExcel", dto);
	}
	
	@Override
	public List<ExcelDTO> read() {
		return sqlSessionTemplate.selectList(NAMESPACE + "selectAll");
	}
	
	public int delete() {
		return sqlSessionTemplate.delete(NAMESPACE + "delete");
	}
}
