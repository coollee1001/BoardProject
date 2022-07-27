package com.mayeye.crud.excel;

import java.util.ArrayList;
import java.util.List;

public class ExcelReadOption {
	//엑셀 경로
	private String filePath;
	
	//칼럼명
	private List<String> outputColumns;
	
	//추출 시작 행번호
	private int startRow;

	
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public List<String> getOutputColumns() {
		//List로 칼럼 설정 추가 후 return
		List<String> temp = new ArrayList<String>();
		temp.addAll(outputColumns);
		return temp;
	}

	public void setOutputColumns(List<String> outputColumns) {
		//List로 칼럼 설정 추가
		List<String> temp = new ArrayList<String>();
		temp.addAll(outputColumns);
		this.outputColumns = temp;
	}
	
	//... : 가변인자
	public void setOutputColumns(String... outputColumns) {
		//여러 문자열이 들어왔을 때
		if(this.outputColumns == null) {
			this.outputColumns = new ArrayList<String>();
		}
		
		for(String outputColumn : outputColumns) {
			this.outputColumns.add(outputColumn);
		}
	}

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}
	
	
}
