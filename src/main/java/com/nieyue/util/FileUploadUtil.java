package com.nieyue.util;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
/**
 * 文件上传类
 * @author yy
 *
 */
public class FileUploadUtil {
	/**
	 * 效率上传图片
	 * @param request
	 * @param response
	 * @return
	 */
	public static String CommonsMultipartResolverFileUpload(HttpServletRequest request,HttpServletResponse response){
		String fileName = null;
		//创建一个通用的多部分解析器  
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
        //判断 request 是否有文件上传,即多部分请求  
        if(multipartResolver.isMultipart(request)){  
            //转换成多部分request    
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;  
            //取得request中的所有文件名  
            Iterator<String> iter = multiRequest.getFileNames();  
            while(iter.hasNext()){  
                //记录上传过程起始时的时间，用来计算上传时间  
                //int pre = (int) System.currentTimeMillis();  
                //取得上传文件  
                MultipartFile file = multiRequest.getFile(iter.next());  
                if(file != null){  
                    //取得当前上传文件的文件名称  
                    String myFileName = file.getOriginalFilename();  
                    //如果名称不为“”,说明该文件存在，否则说明该文件不存在  
                    if(myFileName.trim() !=""){  
                        System.out.println(myFileName);  
                        //重命名上传后的文件名  
                         fileName = DateUtil.timeStamp()+ file.getOriginalFilename();  
                        //定义上传路径  
                        String path = request.getSession().getServletContext().getRealPath("resources/userUpload");
                        File localFile = new File(path,fileName);  
                        try {
							file.transferTo(localFile);
						} catch (IllegalStateException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}  
                    }  
                }  
                //记录上传该文件后的时间  
                //int finaltime = (int) System.currentTimeMillis();  
                //System.out.println(finaltime - pre);  
            }  
              
        }
        
		return request.getContextPath()+"/resources/userUpload/"+fileName;
	}
	/**
	 * FormData上传图片 单个图片上传，先删除原图片
	 * rooturl /resources/userUpload
	 * imgdir sellerId or userID 
	 * oldImgUrl /resources/userUpload/{imgdir}/11.jpg
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	public static String FormDataFileUpload(MultipartFile file,HttpSession session,String path,String rooturl,String imgdir,String oldImgUrl) throws IllegalStateException, IOException{
		String fileName = null;
		//取得当前上传文件的文件名称  
		if(!file.isEmpty()){
	        //重命名上传后的文件名  
            fileName = DateUtil.timeStamp()+ file.getOriginalFilename();  
           //定义上传路径  
            if(path==null){
            	path = session.getServletContext().getRealPath("/");
            }
           //删除原图片
           if(oldImgUrl!=null){
        	   String oldpath=path;
        	   if(oldpath==null){
        		oldpath = session.getServletContext().getRealPath("/");
        	   }
           final File oldfile = new File(oldpath,oldImgUrl);  
           new Thread(new Runnable(){
				public void run() {
					if(oldfile.exists()&&oldfile.isFile())
						oldfile.delete();
				}
			}).start();
           }
           //创建路径
           if(imgdir!=null){
           MyFile myfile=new MyFile();
           myfile.createDir(path+rooturl+"/"+imgdir);
           }
           //建立新图片
           File localFile = new File(path+rooturl+"/"+imgdir,fileName);  
           
			file.transferTo(localFile);
		
           }
		return rooturl+"/"+imgdir+"/"+fileName;
	}
	/**
	 * FormData上传图片 商品图片
	 * rooturl /resources/sellerUpload 
	 * imgdir sellerid 示例 4
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 * @throws IllegalStateException
	 */
	public static String FormDataMerImgFileUpload(MultipartFile file,HttpSession session,String path,String rooturl,String imgdir) throws IllegalStateException, IOException{
		//取得当前上传文件的文件名称  
		String fileName = null;
		//定义上传路径  
		if(path==null){
			 path = session.getServletContext().getRealPath("/");
		}
		if(!file.isEmpty()){
	        //重命名上传后的文件名  
            fileName = DateUtil.timeStamp()+ file.getOriginalFilename();  
         //创建路径
           if(imgdir!=null){
           MyFile myfile=new MyFile();
           myfile.createDir(path+rooturl+"/"+imgdir);
           }
           //建立新图片
           File localFile = new File(path+rooturl+"/"+imgdir,fileName);  
			file.transferTo(localFile);
           }
		return rooturl+"/"+imgdir+"/"+fileName;
	}
	/**
	 * FormData更新 商品图片
	 * rooturl /resources/userUpload
	 * imgdir sellerid 示例 4
	 * oldImgUrl /resources/userUpload/11.jpg
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	public static String updateFormDataMerImgFileUpload(MultipartFile file,HttpSession session,String path,String rooturl,String imgdir,String oldImgUrl) throws IllegalStateException, IOException{
		//取得当前上传文件的文件名称  
		String fileName = null;
		//定义上传路径  
		if(path==null){
			 path = session.getServletContext().getRealPath("/");
		}
		if(!file.isEmpty()){
	        //重命名上传后的文件名  
            fileName = DateUtil.timeStamp()+ file.getOriginalFilename();  
          //删除原图片
            if(oldImgUrl!=null){
            	String oldpath=path;
         	   if(oldpath==null){
         		oldpath = session.getServletContext().getRealPath("/");
         	   }
            final File oldfile = new File(oldpath,oldImgUrl);  
            new Thread(new Runnable(){
 				public void run() {
 					if(oldfile.exists()&&oldfile.isFile())
 						oldfile.delete();
 				}
 			}).start();
            }
           //建立新图片
           File localFile = new File(path+rooturl+"/"+imgdir,fileName);  
			file.transferTo(localFile);
		
           }
		return rooturl+"/"+imgdir+"/"+fileName;
	}
	/**
	 * 删除图片 商品图片
	 * imgdir 实例 /resources/userUpload/11.jpg 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	public static boolean delMerImgFile(HttpSession session,String path,String imgUrl) {
			//创建路径
			if(imgUrl!=null){
				//定义商品图片路径  
				if(path==null){
					 path = session.getServletContext().getRealPath("/");
				}
				MyFile myFile=new MyFile();
				myFile.delFile(path, imgUrl);
				return true;
			}
			return false;
			
	}
}
