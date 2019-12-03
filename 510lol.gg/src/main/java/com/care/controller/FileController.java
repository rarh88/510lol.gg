package com.care.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.care.dto.boardDTO;
import com.care.dto.upload_imgDTO;
import com.care.service.BoardService;
import com.care.service.UpDownService;

@Controller
public class FileController {
	
	private static final String path = "C:\\test\\";
	
	@Autowired
	private UpDownService us;
	
	@Autowired
	private BoardService bs;
	
	@Autowired
	private boardController bc;
	
	@RequestMapping("updownck")
	public String updownok(MultipartHttpServletRequest mtfRequest, boardDTO bdto,
			HttpServletRequest request, HttpServletResponse response) throws IOException {

		bs.saveboard(bdto);
		
		int folderName=bs.sequence_maxnum();
		System.out.println(folderName);
		
		System.out.println(folderName);
		File Folder = new File(path+folderName);
		if (!Folder.exists()) {
			try{
			    Folder.mkdir(); //폴더 생성합니다.
			    System.out.println("폴더가 생성되었습니다.");
		        } 
		        catch(Exception e){
			    e.getStackTrace();
			}        
	         }else {
			System.out.println("이미 폴더가 생성되어 있습니다.");
		}
		MultipartFile mf = mtfRequest.getFile("file");

        System.out.println(mf);
        
        String originFileName = mf.getOriginalFilename();
        System.out.println(originFileName);
        long fileSize = mf.getSize(); // 

        String filepath = path + folderName +"\\"+originFileName;
        String fileurl = folderName+"/"+originFileName;
        
        us.insertfile(Integer.toString(folderName),fileurl,originFileName);
       
        try {
            mf.transferTo(new File(filepath));
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return "redirect:totalboard";
    }
	
	@RequestMapping("filedown")
	public String filedown(Model model,HttpServletRequest request,HttpServletResponse response) throws IOException {
		String num = request.getParameter("num");
        String idgroup = request.getParameter("idgroup");
        upload_imgDTO updto = us.downck(num);
       
		System.out.println(updto.getImgname());
		byte[] fileByte= FileUtils.readFileToByteArray(new File(path+updto.getImgname()));
		response.setContentType("application/octet-stream");
		response.setContentLength(fileByte.length);
		response.setHeader("Content-Disposition","attachment;filename=\"" + URLEncoder.encode(updto.getOrifile(),"UTF-8")+"\";");             
        response.setHeader("Content-Transfer-Encoding", "binary;");
        response.getOutputStream().write(fileByte);
       
        response.getOutputStream().flush();
        response.getOutputStream().close();
        
        
        model.addAttribute("num",num);
        model.addAttribute("idgroup",idgroup);
        model.addAttribute("fileurl",updto.getImgname());
        return "redirect:view";
	}
	
	@RequestMapping(value="filedelete",produces="application/text;charset=utf-8")
	@ResponseBody
	public String filedelete(boardDTO bdto) {
		String num = Integer.toString(bdto.getNum());
		String idgroup = Integer.toString(bdto.getIdgroup());
	
		System.out.println("==================");
		System.out.println(num);
		System.out.println(idgroup);
		System.out.println("==================");
	
		us.filedelete(num);
		
		
		File folder = new File(path+"\\"+num);
		try {
		    while(folder.exists()) {
			File[] folder_list = folder.listFiles(); //파일리스트 얻어오기
					
			for (int j = 0; j < folder_list.length; j++) {
				folder_list[j].delete(); //파일 삭제 
				System.out.println("파일이 삭제되었습니다.");
				
			}
					
			if(folder_list.length == 0 && folder.isDirectory()){ 
				folder.delete(); //대상폴더 삭제
				System.out.println("폴더가 삭제되었습니다.");
				return "1";
			}
	            }
		 } catch (Exception e) {
			e.getStackTrace();
		}
			return "0";
	}
	
	

//    @RequestMapping(value = "requestupload2")
//    public String requestupload2(MultipartHttpServletRequest mtfRequest) {
//        List<MultipartFile> fileList = mtfRequest.getFiles("file");
//        String src = mtfRequest.getParameter("src");
//        System.out.println("src value : " + src);
//
//        String path = "C:\\image\\";
//
//        for (MultipartFile mf : fileList) {
//            String originFileName = mf.getOriginalFilename(); // ���� ���� ��
//            long fileSize = mf.getSize(); // ���� ������
//
//            System.out.println("originFileName : " + originFileName);
//            System.out.println("fileSize : " + fileSize);
//
//            String safeFile = path + System.currentTimeMillis() + originFileName;
//            try {
//                mf.transferTo(new File(safeFile));
//            } catch (IllegalStateException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            } catch (IOException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//        }
//
//        return "redirect:/";
//
//    }
	
	
}