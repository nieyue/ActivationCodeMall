package com.nieyue.util;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import javax.imageio.ImageIO;

/**
 * 文件读取类
 * @author yy
 *
 */
public class MyFile implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("线程开始了！");
		//createDir("");
		//delDir("D:\\datadir\\aaa\\bbb\\ccc\\sss");
		//delFile("D:\\datadir", "aae.class");
		//try {
			//String fileread = readFile("D:\\datadir\\aaa\\bbb\\ccc\\fc.png");
			//String filewrite = writeFile("D:\\datadir\\aaa\\bbb\\ccc\\ccc.png",fileread);
			//System.out.println(fileread);
			//System.out.println(filewrite);
			//String filewrite = imageRW("D:\\datadir\\aaa\\bbb\\ccc\\fc.png","D:\\datadir\\aaa\\bbb\\ccc\\ccc.jpg","png");
			//String filewrite = imageSub("D:\\datadir\\aaa\\bbb\\ccc\\jr.jpg","D:\\datadir\\aaa\\bbb\\ccc\\jr.psd","png");
		//System.out.println(filewrite);
		//} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		//}
		//System.out.println("线程结束了！");
	}
	public static void main(String[] args) {
//		MyFile mf=new MyFile();
//		Thread a=new Thread(mf);
//		a.start();

	new Thread(new MyFile(){
			public void run() {
				System.out.println("线程开始了！");
				 long start = System.currentTimeMillis();
				System.out.println(start);
				//delFile("D:/nieyue/tomcat/apache-tomcat-7.0.57-windows-x64/apache-tomcat-7.0.57/webapps/YaYaoXiangXiu/merchandisePicture/embroideryDetails","1448008547新浪扶冀.jpg");
				String adminIMGSPath="D:/nieyue/GIT/1/YaYaoMall/YaYaoMall/WebRoot/resources/adminUpload/1/黑茶/百两茶/百两茶_01.jpg";
				try {
					imageRW(adminIMGSPath, "D:/cha.png", "jpg");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				long end=System.currentTimeMillis();
				System.out.println(end-start);
				System.out.println("线程删除中！");
				/*File file=new File("D:/nieyue/tomcat/apache-tomcat-7.0.57-windows-x64/apache-tomcat-7.0.57/webapps/YaYaoXiangXiu/merchandisePicture/embroideryDetails/1448008547新浪扶冀.jpg");
				file.delete();
				file.deleteOnExit();*/
				
				System.out.println("线程结束了！");
				
			}
		}).start();
	
	}
	
	/**
	 * 创建文件夹  
	 * @param path
	 */
    public  void createDir(String path){
        File dir=new File(path);
        if(!dir.exists()){
            dir.mkdirs();
        }
    }
    /**
     * 创建新文件
     * @param path
     * @param filename
     * @throws IOException
     */
    public  void createFile(String path,String filename) throws IOException{
        File file=new File(path+"/"+filename);
        if (!file.getParentFile().exists()){
        	file.getParentFile().mkdirs();
        }
        if(!file.exists()){
        	file.createNewFile();
        }
    }
    /**
     * 删除文件     
     * @param path
     * @param filename
     */
    public  void delFile(String path,String filename){
        File file=new File(path+"/"+filename);
        if(file.exists()&&file.isFile())
            file.delete();
    }
   /**
    *  2.删除目录要利用File类的delete()方法删除目录时，
    *  必须保证该目录下没有文件或者子目录，否则删除失败，因此在实际应用中,
    *  我们要删除目录，必须利用递归删除该目录下的所有子目录和文件，然后再删除该目录。  
    * @param path
    */
         public  void delDir(String path){
             File dir=new File(path);
             if(dir.exists()){
                 File[] tmp=dir.listFiles();
                 for(int i=0;i<tmp.length;i++){
                     if(tmp[i].isDirectory()){
                         delDir(path+"/"+tmp[i].getName());
                     }
                     else{
                         tmp[i].delete();
                     }
                 }
                 dir.delete();
             }
         }
         
         /**
          * 利用FileInputStream读取文件
          * @param path
          * @return
          * @throws IOException
          */
         public  String readFile(String path) throws IOException{
             File file=new File(path);
             if(!file.exists()||file.isDirectory())
                 throw new FileNotFoundException();
             InputStream fis=new FileInputStream(file);
             InputStreamReader isr=new InputStreamReader(fis);
             BufferedReader br=new BufferedReader(isr);
             StringBuffer sb=new StringBuffer();
             while((br.readLine())!=null){
                 sb.append(br.readLine());    
                 //buf=new byte[1024];//重新生成，避免和上次读取的数据重复
             }
             br.close();
             isr.close();
             fis.close();
             return sb.toString();
         }   
         /**
          * 写入文件内容
          * @param fileName
          * @param fileContent
          * @return
          */
         public  String writeFile(String fileName, String fileContent)   
         {     
             try   
             {      
                 File f = new File(fileName);      
                 if (!f.exists())   
                 {       
                     f.createNewFile();      
                 }      
                 OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(f),"utf-8");      
                 BufferedWriter writer=new BufferedWriter(write);          
                 writer.write(fileContent);      
                 writer.close();   
                 
             } catch (Exception e)   
             {      
                 e.printStackTrace();     
             }
			return fileContent;  
         }  
         /**
          * 读写图片
          * @param fromPath
          * @param toPath
          * @param type
          * @return
          * @throws IOException
          */
         public  String imageRW(String fromPath,String toPath,String type) throws IOException{
        	BufferedImage image = ImageIO.read(new File(fromPath));//根据你实际情况改文件路径吧
        	 ImageIO.write(image, type, new File(toPath));
        	return image.toString();
         }
         /**
          * 把一个图片截取到另一个图片中
          * @param fromPath
          * @param toPath
          * @param type
          * @return
          * @throws IOException
          */
         public  String imageSub(String fromPath,String toPath,String type) throws IOException{
        	 InputStream in = new FileInputStream(fromPath);
        	 BufferedImage bi = ImageIO.read(in);
        	 File file = new File(toPath);
        	 bi = bi.getSubimage(0, 0, 800, 600);
        	 ImageIO.write(bi, type, file);
        	 in.close();
			return bi.toString();
         }
         
}
