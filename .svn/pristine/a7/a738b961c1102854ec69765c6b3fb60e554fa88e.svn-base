package com.webside.util;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.PicturesManager;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.usermodel.PictureType;
import org.apache.poi.xwpf.converter.core.FileImageExtractor;
import org.apache.poi.xwpf.converter.core.FileURIResolver;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.w3c.dom.Document;

/**
 * word 转换成html
 */
public class WordToHtml {
    
    /**
     * 2007版本word转换成html
     * @throws IOException
     */
    public static String docx2Html(String filepath, String fileName, String downUrl, File outFile) throws IOException {
        File f = new File(filepath, fileName);  
        if (!f.exists()) {  
            return null;  
        } else {  
            if (f.getName().endsWith(".docx") || f.getName().endsWith(".DOCX")) {  
                  
                // 1) 加载word文档生成 XWPFDocument对象  
                InputStream in = new FileInputStream(f);  
                XWPFDocument document = new XWPFDocument(in);  
  
                // 2) 解析 XHTML配置 (这里设置IURIResolver来设置图片存放的目录)  
                File imageFolderFile = new File(filepath);  
                XHTMLOptions options = XHTMLOptions.create().URIResolver(new FileURIResolver(imageFolderFile));  
                options.setExtractor(new FileImageExtractor(imageFolderFile));  
                options.setIgnoreStylesIfUnused(false);  
                options.setFragment(true);  
                  
				if (outFile == null) {
					//也可以使用字符数组流获取解析的内容
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					XHTMLConverter.getInstance().convert(document, baos, options);
					String content = baos.toString();
					baos.close();
					return content;
				} else {
					OutputStream out = new FileOutputStream(outFile); 
                	// 3) 将 XWPFDocument转换成XHTML  
					XHTMLConverter.getInstance().convert(document, out, options);  
                    out.close();
                    return null;  
                }
            } else {  
                System.err.println("Enter only MS Office 2007+ files");  
                return null;  
            }  
        }
    }  
    
    /**
     * 
     * doc转换成html
     * @throws IOException
     * @throws TransformerException
     * @throws ParserConfigurationException
     * @Return outFile=null，返回解析的字符串，否则返回null
     */
    public static String doc2Html(String filepath, String fileName, String downUrl, File outFile) throws IOException, TransformerException, ParserConfigurationException {
        InputStream input = new FileInputStream(new File(filepath, fileName));
        HWPFDocument wordDocument = new HWPFDocument(input);
        WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument());
        //设置图片存放的位置
        wordToHtmlConverter.setPicturesManager(new PicturesManager() {
            public String savePicture(byte[] content, PictureType pictureType, String suggestedName, float widthInches, float heightInches) {
                if(content == null || content.length == 0) {
                	return "";
                }
            	
            	File imgPath = new File(filepath);
                if(!imgPath.exists()){//图片目录不存在则创建
                    imgPath.mkdirs();
                }
                File file = new File(filepath, suggestedName);
                try {
                    OutputStream os = new FileOutputStream(file);
                    os.write(content);
                    os.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return downUrl  + suggestedName;
            }
        });
        
        //解析word文档
        wordToHtmlConverter.processDocument(wordDocument);
        Document htmlDocument = wordToHtmlConverter.getDocument();
        
        OutputStream outStream = null;
        ByteArrayOutputStream baos = null;
        if(outFile == null) {
        	 //也可以使用字符数组流获取解析的内容
            baos = new ByteArrayOutputStream(); 
            outStream = new BufferedOutputStream(baos);
        } else {
        	outStream = new FileOutputStream(outFile);
        }

        DOMSource domSource = new DOMSource(htmlDocument);
        StreamResult streamResult = new StreamResult(outStream);

        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer serializer = factory.newTransformer();
        serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
        serializer.setOutputProperty(OutputKeys.INDENT, "yes");
        serializer.setOutputProperty(OutputKeys.METHOD, "html");
        
        serializer.transform(domSource, streamResult);
        
        if(outFile == null) {
        	//也可以使用字符数组流获取解析的内容
            String content = baos.toString();
            baos.close();
            return content;
        } else {
        	outStream.close();
        	return null;
        }
    }

	public static String word2Html(String filepath, String fileName, String downUrl, File outFile) throws IOException, TransformerException, ParserConfigurationException {
		String name = fileName.toLowerCase();
		
		if(name.toLowerCase().endsWith(".docx")) {
			return docx2Html(filepath, fileName, downUrl, outFile);
		} else if(name.toLowerCase().endsWith(".doc")) {
			return doc2Html(filepath, fileName, downUrl, outFile);
		}
		
		return null;
	}
}