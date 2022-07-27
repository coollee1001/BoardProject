package com.mayeye.crud.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mayeye.crud.dao.ExcelDAO;
import com.mayeye.crud.dto.ExcelDTO;
import com.mayeye.crud.excel.ExcelRead;
import com.mayeye.crud.excel.ExcelReadOption;

@Service
public class ExcelService implements ExcelServiceImpl{
	@Value("${path.upload}")
	private String path_upload;
	
	@Autowired
	private ExcelDAO excelDAO;
	
	//엑셀 파일 업로드
		@Override
		public void excelUpload(String destFile) {
			 ExcelReadOption excelReadOption = new ExcelReadOption();
			 
			 //경로 재설정
			 File setFile = new File(path_upload + destFile);
	        
	        //파일경로 추가
	        excelReadOption.setFilePath(setFile.getAbsolutePath());
	        
	        //추출할 컬럼명 추가
	        excelReadOption.setOutputColumns("A", "B", "C");
	        
	        //시작행
	        excelReadOption.setStartRow(2);
	        
	        List<Map<String, String>>excelContent  = ExcelRead.read(excelReadOption);
	        
	        ExcelDTO dto = new ExcelDTO();
	        
	        try {
	        for(int i = 0; i<excelContent.size(); i++) {
	        	dto.setExcel_name(excelContent.get(i).get("A"));
	        	dto.setExcel_address(excelContent.get(i).get("B"));
	        	dto.setExcel_phone(excelContent.get(i).get("C"));
	        	excelDAO.inserExcel(dto);
	        }
	        	
	        }catch(Exception e) {
	            e.printStackTrace();
	        }
			
		}

		public List<ExcelDTO> excelRead() {
			List<ExcelDTO> excelDTO = excelDAO.read();
			return excelDTO;
		}
		
		public void delete() {
			excelDAO.delete();
		}
}
