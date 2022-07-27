package com.mayeye.crud.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface FileServiceImpl {
	public void fileDownload(HttpServletRequest req, HttpServletResponse respon) throws Exception;
	public void dbfileDownload(HttpServletRequest req, HttpServletResponse respon, String fileName) throws Exception;
}
