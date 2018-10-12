package com.webside.business.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.rkylin.cells.common.Row;
import com.rkylin.cells.common.Workbook;
import com.rkylin.cells.common.Worksheet;
import com.rkylin.cells.excel.ExcelReader;
import com.webside.base.basecontroller.BaseController;
import com.webside.business.model.CodeEntity;
import com.webside.business.model.FacilityEntity;
import com.webside.business.model.PropertyEntity;
import com.webside.business.service.CodeService;
import com.webside.business.service.PropertyService;
import com.webside.common.Common;
import com.webside.exception.SystemException;
import com.webside.shiro.ShiroAuthenticationManager;
import com.webside.util.WordToHtml;

@Controller
@Scope("prototype")
@RequestMapping("/file/")
public class FileController extends BaseController {

	@Autowired
	private PropertyService propertyService;

	@Autowired
	private CodeService codeService;
	
	private static final String FILE_STORE_PATH = "/data/uploadFiles";
	
	@RequestMapping("testUI.html")
	public String editUI(Model model, HttpServletRequest request, Long id) {
		try
		{
			return Common.BACKGROUND_PATH + "/loginfo/form";
		}catch(Exception e)
		{
			throw new SystemException(e);
		}
	}
	

	@RequestMapping("inquiryTemplateUI.html")
	public String inquiryTemplateUI(Model model, HttpServletRequest request) {
		try
		{
			return Common.BACKGROUND_PATH + "/property/inquirytemplate";
		}catch(Exception e)
		{
			throw new SystemException(e);
		}
	}

	@RequestMapping("entrustForceTemplateUI.html")
	public String entrustForceTemplateUI(Model model, HttpServletRequest request) {
		try
		{
			return Common.BACKGROUND_PATH + "/property/entrustforcetemplate";
		}catch(Exception e)
		{
			throw new SystemException(e);
		}
	}
	
	@RequestMapping("entrustTemplateUI.html")
	public String entrustTemplateUI(Model model, HttpServletRequest request) {
		try
		{
			return Common.BACKGROUND_PATH + "/property/entrusttemplate";
		}catch(Exception e)
		{
			throw new SystemException(e);
		}
	}
	
	@RequestMapping("facilityTemplateUI.html")
	public String facilityTemplateUI(Model model, HttpServletRequest request) {
		try
		{
			return Common.BACKGROUND_PATH + "/facility/uploadtemplate";
		}catch(Exception e)
		{
			throw new SystemException(e);
		}
	}
	
	private List<String> doRecvFile(MultipartHttpServletRequest multiRequest, String path) throws Exception {
		return doRecvFile(multiRequest, path, null);
	}
	
	private List<String> doRecvFile(MultipartHttpServletRequest multiRequest, String path, String name) throws Exception {
       //获取multiRequest 中所有的文件名
		File rootFile = new File(path);
        if(!rootFile.exists()) {
        	rootFile.mkdirs();
        }
        
        Iterator<String> iter=multiRequest.getFileNames();
        
        List<String> recvFiles = new ArrayList<String>();
        while(iter.hasNext())
        {
            //一次遍历所有文件
            MultipartFile file=multiRequest.getFile(iter.next().toString());
            if(file!=null)
            {
            	if (name == null || name.length() == 0) {
            		name = String.format("%d-%s", new Date().getTime(), file.getOriginalFilename().toLowerCase());
            	}
            	
                //上传
                file.transferTo(new File(path, name));
                
                recvFiles.add(name);

        	 	logger.info("recv file: " + name);
            }
        }
        
        return recvFiles;
	}
	
	/*
     *采用spring提供的上传文件的方法
     */
    @RequestMapping(value="upload", method=RequestMethod.POST)
    public Object upload(HttpServletRequest request) throws IllegalStateException, IOException
    {
    	Map<String, Object> map = new HashMap<String, Object>();
    	
    	try {
    		 //将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
            CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(
                    request.getSession().getServletContext());
//        	String path = request.getSession().getServletContext().getRealPath(FILE_STORE_PATH)	+ "/upload2/";
        	String path = FILE_STORE_PATH + "/upload1/";
            
            File rootFile = new File(path);
            if(!rootFile.exists()) {
            	rootFile.mkdirs();
            }
            
            List<String> files = new ArrayList<String>();
            //检查form中是否有enctype="multipart/form-data"
            if(multipartResolver.isMultipart(request))
            {
                //将request变成多部分request
                MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)request;
                files = doRecvFile(multiRequest, path);
            }
    		map.put("success", Boolean.TRUE);
			map.put("data", files);
			map.put("message", "更新成功");
    	} catch(Exception e) {
			map.put("success", Boolean.FALSE);
			map.put("data", null);
			map.put("message", "更新失败");
    	}       
        
		return map;
    }
    
    @RequestMapping(method = {RequestMethod.POST}, value = {"upload2"})  
    @ResponseBody  
    public Object upload2(HttpServletRequest request, HttpServletResponse response) throws Exception {  
    	Map<String, Object> map = new HashMap<String, Object>();
    	try {  
                boolean isMultipart = ServletFileUpload.isMultipartContent(request);  
                if (isMultipart) {  

//                	String path = request.getSession().getServletContext().getRealPath(FILE_STORE_PATH)	+ "/upload2/";
                	String path = FILE_STORE_PATH + "/upload2/";
                    
                    
                    List<String> files = doRecvFile((MultipartHttpServletRequest)request, path);
                    
                    if(files.isEmpty()) {
                        map.put("success", Boolean.FALSE);
            			map.put("message", "没有收到上传的文件");
                    } else {
                    	map.put("success", Boolean.TRUE);
                    	map.put("data", files.get(0));
                    }
                }  
            } catch (Exception e) {  
            	e.printStackTrace();
                logger.error(e.getMessage(), e);

                map.put("success", Boolean.FALSE);
    			map.put("message", e.getLocalizedMessage());
            }
		return map;  
    } 
    
    @RequestMapping(method = {RequestMethod.POST}, value = {"uploadExcel"})  
    @ResponseBody  
    public Object uploadExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {  
    	Map<String, Object> map = new HashMap<String, Object>();
    	try {  
                boolean isMultipart = ServletFileUpload.isMultipartContent(request);  
                if (isMultipart) {  
//                	String path = request.getSession().getServletContext().getRealPath(FILE_STORE_PATH)	+ "/uploadExcel/";
                	String path = FILE_STORE_PATH + "/uploadExcel/";
                    
                    List<String> files = doRecvFile((MultipartHttpServletRequest)request, path);
                   
                    
                    if(files.isEmpty()) {
                        map.put("success", Boolean.FALSE);
            			map.put("message", "没有收到上传的文件");
                    } else {
                    	map.put("success", Boolean.TRUE);
                    	map.put("data", paserExcelFile(new FileInputStream(path +"/" +files.get(0))));
                    }
                }  
            } catch (Exception e) {  
            	e.printStackTrace();
                logger.error(e.getMessage(), e);

                map.put("success", Boolean.FALSE);
    			map.put("message", e.getLocalizedMessage());
            }
		return map;  
    }
    
    @RequestMapping(method = {RequestMethod.POST}, value = {"uploadInquiryTemplate"})  
    @ResponseBody  
    public Object uploadInquiryTemplate(HttpServletRequest request, HttpServletResponse response) throws Exception {  
//    	String path = request.getSession().getServletContext().getRealPath(FILE_STORE_PATH)	+ "/template/";
    	String path = FILE_STORE_PATH + "/template/";
    	
    	String downUrl = String.format("http://%s:%d%s/file/downloadTemplate.html?name=", 
    			request.getServerName(),
    			request.getServerPort(),
    			request.getContextPath());
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	try {  
                boolean isMultipart = ServletFileUpload.isMultipartContent(request);  
                if (isMultipart) {  
                    
                    
                    List<String> files = doRecvFile((MultipartHttpServletRequest)request, path);
                    
                    if(files.isEmpty()) {
                        map.put("success", Boolean.FALSE);
            			map.put("message", "没有收到上传的文件");
                    } else {
                    	map.put("success", Boolean.TRUE);
                    	String html = WordToHtml.word2Html(path, files.get(0), downUrl, null);
                    	PropertyEntity t = new PropertyEntity();
                    	t.setId(PropertyService.KEY_Inquiry_TEMPLATE);
                    	t.setContent(html);
                    	t.setNote("报价模板html文");
						propertyService.insert(t);
                    	map.put("data", html);
                    }
                }  
            } catch (Exception e) {  
                logger.error(e.getMessage(), e);
                map.put("success", Boolean.FALSE);
    			map.put("message", e.getLocalizedMessage());
            }
		return map;  
    }
    
    @RequestMapping(method = {RequestMethod.POST}, value = {"uploadEntrustTemplate"})  
    @ResponseBody  
    public Object uploadEntrustTemplate(HttpServletRequest request, HttpServletResponse response) throws Exception {  
    	
//    	String path = request.getSession().getServletContext().getRealPath(FILE_STORE_PATH)	+ "/template/";
    	String path = FILE_STORE_PATH + "/template/";
    	
    	String downUrl = String.format("http://%s:%d%s/file/downloadTemplate.html?name=", 
    			request.getServerName(),
    			request.getServerPort(),
    			request.getContextPath());
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	try {  
                boolean isMultipart = ServletFileUpload.isMultipartContent(request);  
                if (isMultipart) {  
                    
                    
                    List<String> files = doRecvFile((MultipartHttpServletRequest)request, path);
                    
                    if(files.isEmpty()) {
                        map.put("success", Boolean.FALSE);
            			map.put("message", "没有收到上传的文件");
                    } else {
                    	map.put("success", Boolean.TRUE);
                    	String html = WordToHtml.word2Html(path, files.get(0), downUrl, null);
                    	PropertyEntity t = new PropertyEntity();
                    	t.setId(PropertyService.KEY_Entrust_TEMPLATE);
                    	t.setContent(html);
                    	t.setNote("报价模板html文");
						propertyService.insert(t);
                    	map.put("data", html);
                    }
                }  
            } catch (Exception e) {  
                logger.error(e.getMessage(), e);
                map.put("success", Boolean.FALSE);
    			map.put("message", e.getLocalizedMessage());
            }
		return map;  
    }
    
    @RequestMapping(method = {RequestMethod.POST}, value = {"uploadEntrustforceTemplate"})  
    @ResponseBody  
    public Object uploadEntrustforceTemplate(HttpServletRequest request, HttpServletResponse response) throws Exception {  
    	
//    	String path = request.getSession().getServletContext().getRealPath(FILE_STORE_PATH)	+ "/template/";
    	String path = FILE_STORE_PATH + "/template/";
    	
    	String downUrl = String.format("http://%s:%d%s/file/downloadTemplate.html?name=", 
    			request.getServerName(),
    			request.getServerPort(),
    			request.getContextPath());
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	try {  
                boolean isMultipart = ServletFileUpload.isMultipartContent(request);  
                if (isMultipart) {  
                    
                    
                    List<String> files = doRecvFile((MultipartHttpServletRequest)request, path);
                    
                    if(files.isEmpty()) {
                        map.put("success", Boolean.FALSE);
            			map.put("message", "没有收到上传的文件");
                    } else {
                    	map.put("success", Boolean.TRUE);
                    	String html = WordToHtml.word2Html(path, files.get(0), downUrl, null);
                    	PropertyEntity t = new PropertyEntity();
                    	t.setId(PropertyService.KEY_Entrustforce_TEMPLATE);
                    	t.setContent(html);
                    	t.setNote("报价模板html文");
						propertyService.insert(t);
                    	map.put("data", html);
                    }
                }  
            } catch (Exception e) {  
                logger.error(e.getMessage(), e);
                map.put("success", Boolean.FALSE);
    			map.put("message", e.getLocalizedMessage());
            }
		return map;  
    }
    @RequestMapping(method = {RequestMethod.POST}, value = {"uploadTemplate"})  
    @ResponseBody  
    public Object uploadTemplate(HttpServletRequest request, HttpServletResponse response) throws Exception {  
    	
//    	String path = request.getSession().getServletContext().getRealPath(FILE_STORE_PATH)	+ "/template/";
    	String path = FILE_STORE_PATH + "/template/";
    	
    	String downUrl = String.format("http://%s:%d%s/file/downloadTemplate.html?name=", 
    			request.getServerName(),
    			request.getServerPort(),
    			request.getContextPath());
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	try {  
                boolean isMultipart = ServletFileUpload.isMultipartContent(request);  
                if (isMultipart) {  
                    
                    
                    List<String> files = doRecvFile((MultipartHttpServletRequest)request, path);
                    
                    if(files.isEmpty()) {
                        map.put("success", Boolean.FALSE);
            			map.put("message", "没有收到上传的文件");
                    } else {
                    	map.put("success", Boolean.TRUE);
                    	String html = WordToHtml.word2Html(path, files.get(0), downUrl, null);
                    	map.put("data", html);
                    }
                }  
            } catch (Exception e) {  
                logger.error(e.getMessage(), e);
                map.put("success", Boolean.FALSE);
    			map.put("message", e.getLocalizedMessage());
            }
		return map;  
    }
    
    @RequestMapping(method = {RequestMethod.POST}, value = {"uploadFacilityTemplate"})  
    @ResponseBody  
    public Object uploadFacilityTemplate(HttpServletRequest request, HttpServletResponse response) throws Exception {  
    	
//    	String path = request.getSession().getServletContext().getRealPath(FILE_STORE_PATH)	+ "/template/";
    	String path = FILE_STORE_PATH + "/template/";
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	try {  
                boolean isMultipart = ServletFileUpload.isMultipartContent(request);  
                if (isMultipart) {  
                    
                    
                    List<String> files = doRecvFile((MultipartHttpServletRequest)request, path, FacilityTemplateName);
                    
                    if(files.isEmpty()) {
                        map.put("success", Boolean.FALSE);
            			map.put("message", "没有收到上传的文件");
                    } else {
                    	map.put("success", Boolean.TRUE);
                    	map.put("data", FacilityTemplateName);
                    }
                }  
            } catch (Exception e) {  
                logger.error(e.getMessage(), e);
                map.put("success", Boolean.FALSE);
    			map.put("message", e.getLocalizedMessage());
            }
		return map;  
    }
    
    @RequestMapping(method = {RequestMethod.POST}, value = {"downloadFacilityTemplate.html"})  
    @ResponseBody  
    public Object downloadFacilityTemplate(HttpServletRequest request, HttpServletResponse response) throws Exception {  
    	
//    	String path = request.getSession().getServletContext().getRealPath(FILE_STORE_PATH)	+ "/template/";
    	String path = FILE_STORE_PATH + "/template/";
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	try {
    		File file = new File(path, FacilityTemplateName);
        	if(file.exists()) {
        		//处理文件名有中文问题    
        		String name = "模板.xls";
        		String agent = request.getHeader("User-Agent").toUpperCase();
        		if (agent.indexOf("MSIE") > 0 || agent.indexOf("EDGE") > 0) {
        			name= URLEncoder.encode(name,"UTF-8");
        		} else {
        			name= new String(name.getBytes(), "ISO-8859-1");
        		}
        		
        		// 设置响应头
        		response.setContentType("application/vnd.ms-excel");
        		// 执行文件写入
        		response.setHeader("Content-Disposition", "attachment;filename=" + name);
        		response.setCharacterEncoding("UTF-8");
        		// 设置响应头
        		response.setContentLength((int) file.length());
        		IOUtils.copy(new FileInputStream(file), response.getOutputStream());
        		map.put("success", Boolean.TRUE);
            } else {
            	map.put("success", Boolean.FALSE);
            	map.put("message", "下载出错，请联系管理员");
            	logger.error("downloadFacilityTemplate error: not exists file." );
            }
    	} catch(Exception e) {
    		map.put("success", Boolean.FALSE);
        	map.put("message", "下载出错，请联系管理员!");
    		logger.error("downloadFacilityTemplate error: " + e.getLocalizedMessage());
    	}
    	return map;
    }
    
    @RequestMapping(method = {RequestMethod.GET}, value = {"downloadTemplate.html"})  
    @ResponseBody  
    public void downloadTemplate(HttpServletRequest request, HttpServletResponse response, @RequestParam(value="name")String name) throws Exception {  
    	
//    	String path = request.getSession().getServletContext().getRealPath(FILE_STORE_PATH)	+ "/template/";
    	String path = FILE_STORE_PATH + "/template/";
    	
    	File file = new File(path, name);
    	if(file.exists()) {
    		response.setContentLength((int) file.length());
    		IOUtils.copy(new FileInputStream(file), response.getOutputStream());
    	}
    }
    
    @RequestMapping(method = {RequestMethod.POST}, value = {"delete2.html"})  
    @ResponseBody  
    public Object delete2(HttpServletRequest request, @RequestParam(value="delFile") String delFile) throws Exception {  
    	Map<String, Object> map = new HashMap<String, Object>();
    	try {  
//        	String path = request.getSession().getServletContext().getRealPath(FILE_STORE_PATH)	+ "/upload2/";
        	String path = FILE_STORE_PATH + "/upload2/";
        	 
        	 	new File(path, delFile).delete();
        	 	map.put("success", Boolean.TRUE);
        	 	logger.info("delete file: " + delFile);
            } catch (Exception e) {  
            	e.printStackTrace();
                logger.error(e.getMessage(), e);
                map.put("success", Boolean.FALSE);
    			map.put("message", e.getLocalizedMessage());
            }
		return map;  
    }
    
    @RequestMapping(method = {RequestMethod.GET}, value = {"download2.html"})  
    @ResponseBody  
    public void download2(HttpServletRequest request, HttpServletResponse response, @RequestParam(value="name")String name) throws Exception {  
    	
    	if(name.endsWith(".jpg") || name.endsWith(".jpeg")){
    		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
    	} else if(name.endsWith(".png")) {
    		response.setContentType(MediaType.IMAGE_PNG_VALUE);
    	}
    	
//    	String path = request.getSession().getServletContext().getRealPath(FILE_STORE_PATH)	+ "/upload2/";
    	String path = FILE_STORE_PATH + "/upload2/";
    	
    	File file = new File(path, name);
    	if(file.exists()) {
    		response.setContentLength((int) file.length());
    		IOUtils.copy(new FileInputStream(file), response.getOutputStream());
    	}
    }
    
    @RequestMapping(method = {RequestMethod.GET}, value = {"downloadEntrustFile.html"})  
    @ResponseBody  
    public Object downloadEntrustFile(HttpServletRequest request, HttpServletResponse response,@RequestParam(value="name")String name) throws Exception {  
    	
//    	String path = request.getSession().getServletContext().getRealPath(FILE_STORE_PATH)	+ "/upload2/";
    	String path = FILE_STORE_PATH + "/upload2/";
    			
    	Map<String, Object> map = new HashMap<String, Object>();
    	try {
    		File file = new File(path, name);
        	if(file.exists()) {
        		//处理文件名有中文问题    
        		String agent = request.getHeader("User-Agent").toUpperCase();
        		if (agent.indexOf("MSIE") > 0 || agent.indexOf("EDGE") > 0) {
        			name= URLEncoder.encode(name,"UTF-8");
        		} else {
        			name= new String(name.getBytes(), "ISO-8859-1");
        		}
        		
        		// 设置响应头
        		response.setContentType("application/vnd.ms-excel");
        		// 执行文件写入
        		response.setHeader("Content-Disposition", "attachment;filename=" + name);
        		response.setCharacterEncoding("UTF-8");
        		// 获取输出流
        		response.setContentLength((int) file.length());
        		IOUtils.copy(new FileInputStream(file), response.getOutputStream());
        		map.put("success", Boolean.TRUE);
            } else {
            	map.put("success", Boolean.FALSE);
            	map.put("message", "下载出错，请联系管理员");
            	logger.error("downloadFacilityTemplate error: not exists file." );
            }
    	} catch(Exception e) {
    		map.put("success", Boolean.FALSE);
        	map.put("message", "下载出错，请联系管理员!");
    		logger.error("downloadFacilityTemplate error: " + e.getLocalizedMessage());
    	}
    	return map;
    }
    
    @RequestMapping(method = {RequestMethod.POST}, value = {"webupload"})  
    @ResponseBody  
    public void webUploader(HttpServletRequest request, HttpServletResponse response) throws Exception {  
        try {  
                boolean isMultipart = ServletFileUpload.isMultipartContent(request);  
                if (isMultipart) {  
                    FileItemFactory factory = new DiskFileItemFactory();  
                    ServletFileUpload upload = new ServletFileUpload(factory);  
       
                    // 得到所有的表单域，它们目前都被当作FileItem  
                    List<FileItem> fileItems = upload.parseRequest(request);  
       
                    String id = "";  
                    String fileName = "";  
                    // 如果大于1说明是分片处理  
                    int chunks = 1;  
                    int chunk = 0;  
                    FileItem tempFileItem = null;  
       
                    for (FileItem fileItem : fileItems) {  
                        if (fileItem.getFieldName().equals("id")) {  
                            id = fileItem.getString();  
                        } else if (fileItem.getFieldName().equals("name")) {  
                            fileName = new String(fileItem.getString().getBytes("ISO-8859-1"), "UTF-8");  
                        } else if (fileItem.getFieldName().equals("chunks")) {  
                            chunks = NumberUtils.toInt(fileItem.getString());  
                        } else if (fileItem.getFieldName().equals("chunk")) {  
                            chunk = NumberUtils.toInt(fileItem.getString());  
                        } else if (fileItem.getFieldName().equals("multiFile")) {  
                            tempFileItem = fileItem;  
                        }  
                    }  

                    String filePath = request.getSession().getServletContext().getRealPath("upload1");
                    //session中的参数设置获取是我自己的原因,文件名你们可以直接使用fileName,这个是原来的文件名  
                      
                    String realname = String.format("%s-%s", new Date().getTime(), fileName);
                    // 临时目录用来存放所有分片文件  
                    String tempFileDir = filePath + id;  
                    File parentFileDir = new File(tempFileDir);  
                    if (!parentFileDir.exists()) {  
                        parentFileDir.mkdirs();  
                    }  
                    // 分片处理时，前台会多次调用上传接口，每次都会上传文件的一部分到后台  
                    File tempPartFile = new File(parentFileDir, realname + "_" + chunk + ".part");  
                    FileUtils.copyInputStreamToFile(tempFileItem.getInputStream(), tempPartFile);  
       
                    // 是否全部上传完成  
                    // 所有分片都存在才说明整个文件上传完成  
                    boolean uploadDone = true;  
                    for (int i = 0; i < chunks; i++) {  
                        File partFile = new File(parentFileDir, realname + "_" + i + ".part");  
                        if (!partFile.exists()) {  
                            uploadDone = false;  
                        }  
                    }  
                    // 所有分片文件都上传完成  
                    // 将所有分片文件合并到一个文件中  
                    if (uploadDone) {  
                        // 得到 destTempFile 就是最终的文件  
                        File destTempFile = new File(filePath, realname);  
                        for (int i = 0; i < chunks; i++) {  
                            File partFile = new File(parentFileDir, realname + "_" + i + ".part");  
                            FileOutputStream destTempfos = new FileOutputStream(destTempFile, true);  
                            //遍历"所有分片文件"到"最终文件"中  
                            FileUtils.copyFile(partFile, destTempfos);  
                            destTempfos.close();  
                        }  
                        // 删除临时目录中的分片文件  
                        FileUtils.deleteDirectory(parentFileDir);  
                    } else {  
                        // 临时文件创建失败  
                        if (chunk == chunks -1) {  
                            FileUtils.deleteDirectory(parentFileDir);  
                        }  
                    }  
                      
                }  
            } catch (Exception e) {  
                logger.error(e.getMessage(), e);  
            }  
    }  
    
    private List<FacilityEntity>  paserExcelFile(InputStream inputStream) {
    	try {
			
			Workbook workbook = ExcelReader.read(inputStream);
			
			return queryColumnNames(workbook, 0, 0, 0);
			
		} catch (Exception e) {
			e.printStackTrace();
        }
    	
    	return null;
    }
    
    private List<FacilityEntity> queryColumnNames(Workbook wookbook, int sheetIndex, int cloumnRowNo, int fromColumnNo) {
    	
		int sheetCount = wookbook.getSheetCount();
		if (sheetIndex < sheetCount) {
			Worksheet worksheet = wookbook.getSheet(sheetIndex);
			Row row = worksheet.getRow(cloumnRowNo);
			int cellCount = row.getCount();
			if (fromColumnNo < cellCount) {
				String[] columnNames = new String[cellCount - fromColumnNo];
				for (int i = 0; i < columnNames.length; i++) {
					columnNames[i] = row.getCell(i + fromColumnNo).getValue().toString();
				}
				
				return queryData(worksheet, columnNames, cloumnRowNo + 1, fromColumnNo);
			}
		}
		return null;
	}
	
    private List<FacilityEntity> queryData(Worksheet worksheet, String[] columnNames, int rowStart, int fromColumnNo) {
    	int rowTotal = worksheet.getRowCount();
    	List<FacilityEntity> resultList = new ArrayList<FacilityEntity>();
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
    	SimpleDateFormat formatter3 = new SimpleDateFormat("yyyy/MM/dd");
    	
    	HashMap<String, String> mapFacilityState = new HashMap<String, String>();
		HashMap<String, String> mapIfState = new HashMap<String, String>();
		List<CodeEntity> codeFacilityState = codeService.valueAndName("facilityState");
		List<CodeEntity> codeIfTest = codeService.valueAndName("ifTest");
		for (CodeEntity codeEntity : codeFacilityState) {
			mapFacilityState.put(codeEntity.getName(), codeEntity.getValue());
		}
		for (CodeEntity codeEntity : codeIfTest) {
			mapIfState.put(codeEntity.getName(), codeEntity.getValue());
		}
		
    	for (int i = rowStart; i < rowTotal; i++) {
    		Row row = worksheet.getRow(i);
    		int cellCount = row.getCount();
    		FacilityEntity entity = new FacilityEntity();
    		entity.setId("未保存");
    		entity.setOperater(ShiroAuthenticationManager.getUserAccountName());
    		if (fromColumnNo < cellCount) {
    			for (int j = 0; j < columnNames.length; j++) {
    				if (FacilityFieldMap.containsKey(columnNames[j])){
    					String fieldName = FacilityFieldMap.get(columnNames[j]);
    					
    					try {
    						if ("ifTest".equals(fieldName)) {
        						String val = row.getCell(j + fromColumnNo).getValue().toString();
        						try {
        							if (Integer.parseInt(val) >= 0) {
        								entity.setIfTest(val);
        							} else {
        								entity.setIfTest("1");
        							}
        						} catch(Exception e5) {
        							if (mapIfState.containsKey(val)) {
        								entity.setIfTest(mapIfState.get(val));
        							}else {
        								entity.setIfTest("1");
        							}
        						}
        					} else if ("facilityState".equals(fieldName)) {
        						String val = row.getCell(j + fromColumnNo).getValue().toString();
        						try {
        							if (Integer.parseInt(val) >= 0) {
        								entity.setFacilityState(val);
        							} else {
        								entity.setFacilityState("8");
        							}
        						} catch(Exception e5) {
        							if (mapFacilityState.containsKey(val)) {
        								entity.setFacilityState(mapFacilityState.get(val));
        							}else {
        								entity.setFacilityState("8");
        							}
        						}
        					} else {
        						Field field = FacilityEntity.class.getDeclaredField(fieldName);
    							field.setAccessible(true);
    							Class<?> cls = field.getType();
    							if (Date.class.equals(cls)) {
    								try {
    									field.set(entity, formatter.parse(row.getCell(j + fromColumnNo).getValue().toString()));
    								} catch(Exception e3) {
    									try {
        									field.set(entity, formatter2.parse(row.getCell(j + fromColumnNo).getValue().toString()));
        								} catch(Exception e4) {
        									field.set(entity, formatter3.parse(row.getCell(j + fromColumnNo).getValue().toString()));
        								}
    								}
    								
    							} else if(Integer.class.equals(cls)) {
    								field.set(entity, Integer.parseInt(row.getCell(j + fromColumnNo).getValue().toString()));
    							} else if(Long.class.equals(cls)) {
    								field.set(entity, Long.parseLong(row.getCell(j + fromColumnNo).getValue().toString()));
    							} else if(BigDecimal.class.equals(cls)) {
    								field.set(entity, BigDecimal.valueOf(Double.parseDouble((row.getCell(j + fromColumnNo).getValue().toString()))));
    							} else {
    								field.set(entity, row.getCell(j + fromColumnNo).getValue());
    							}
        					}
							
						} catch (Exception e) {
							//e.printStackTrace();
						}
    				}
    			}
    			String fno = entity.getFactoryNo();
    			if(fno != null && !fno.trim().isEmpty()) {
    				resultList.add(entity);
    			}
    		}
		}
		
		return resultList;
	}
    
    private final String FacilityTemplateName = "FacilityTemplate.xls";
    private static Map<String, String> FacilityFieldMap = new HashMap<String, String>();
    static {
    	//FacilityFieldMap.put("编号","id");
    	FacilityFieldMap.put("企业编号","cid");
    	FacilityFieldMap.put("企业","companyName");
    	FacilityFieldMap.put("出厂编号","factoryNo");
    	FacilityFieldMap.put("仪器名称","facilityName");
    	FacilityFieldMap.put("仪器类型","facilityType");
    	FacilityFieldMap.put("规格型号","model");
    	FacilityFieldMap.put("准确度等级","accuracy");
    	FacilityFieldMap.put("测量范围","testRange");
    	FacilityFieldMap.put("仪器状态","facilityState");
    	FacilityFieldMap.put("是否可检测","ifTest");
    	FacilityFieldMap.put("报价","lastRealPrice");
    	FacilityFieldMap.put("责任部门","belongOrgan");
    	FacilityFieldMap.put("管理编号","manageNo");
    	FacilityFieldMap.put("价格","price");
    	FacilityFieldMap.put("生产厂家","manufacturer");
    	FacilityFieldMap.put("采购地","localSource");
    	FacilityFieldMap.put("购买日期","purchaseDate");
    	FacilityFieldMap.put("有效日期","expirationDate");
    	FacilityFieldMap.put("保管人","keeper");
    	FacilityFieldMap.put("备注","note");
    	FacilityFieldMap.put("仪器附件","facilityAttach");
    }
}
