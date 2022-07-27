package com.mayeye.crud.service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

@Service
public class FileService implements FileServiceImpl{
	
	@Value("${path.filedownload}")
	private String path_filedownload;
	
	@Value("${path.upload}")
	private String path_upload;
	
	@Override
	public void fileDownload(HttpServletRequest req, HttpServletResponse respon) throws Exception {
		// TODO Auto-generated method stub
		try {
			String fileName = "db.xlsx";
			
			//파일 객체 생성
			File file = new File(path_filedownload, fileName);
			BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
			
			//User-Agent : 운영체제에 따른 홈페이지 접근 확인
			String header = req.getHeader("User-Agent");
			String re_fileName;
			
			
			//헤더에서 홈페이지 접근에 따른 인코딩 설정
			if((header.contains("MSIE")) || (header.contains("Trident")) || (header.contains("Edge"))) {
				//인터넷 익스플로러 10이하, 11버전, 엣지
				re_fileName = URLEncoder.encode(fileName, "UTF-8");
			}else {
				//나머지 브라우저
				re_fileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
			}
			
			//형식을 모르는 파일첨부용 contentType 설정
			respon.setContentType("application/octet-stream");
			//다운로드
			respon.setHeader("Content-Disposition", "attachment; filename=\""+ re_fileName + "\"");
			
			//파일 복사(스트림으로하면 속도 저하가 있기 때문)
			FileCopyUtils.copy(in, respon.getOutputStream());
			in.close();
			
			respon.getOutputStream().flush();
			respon.getOutputStream().close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception("download error");
		}
		
	}
	
	
	public void dbfileDownload(HttpServletRequest req, HttpServletResponse respon, String fileName) throws Exception {
		// TODO Auto-generated method stub
		try {
			
			//파일 객체 생성
			File file = new File(path_upload, fileName);
			BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
			
			//User-Agent : 운영체제에 따른 홈페이지 접근 확인
			String header = req.getHeader("User-Agent");
			String re_fileName;
			
			
			//헤더에서 홈페이지 접근에 따른 인코딩 설정
			if((header.contains("MSIE")) || (header.contains("Trident")) || (header.contains("Edge"))) {
				//인터넷 익스플로러 10이하, 11버전, 엣지
				re_fileName = URLEncoder.encode(fileName, "UTF-8");
			}else {
				//나머지 브라우저
				re_fileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
			}
			
			//형식을 모르는 파일첨부용 contentType 설정
			respon.setContentType("application/octet-stream");
			//다운로드
			respon.setHeader("Content-Disposition", "attachment; filename=\""+ re_fileName + "\"");
			
			//파일 복사(스트림으로하면 속도 저하가 있기 때문)
			FileCopyUtils.copy(in, respon.getOutputStream());
			in.close();
			
			respon.getOutputStream().flush();
			respon.getOutputStream().close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception("download error");
		}
		
	}
	
}
