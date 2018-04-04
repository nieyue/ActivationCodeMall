package com.nieyue.shiro;

import com.nieyue.bean.Permission;
import com.nieyue.service.PermissionService;
import com.nieyue.service.RolePermissionService;
import com.nieyue.util.HttpClientUtil;
import io.swagger.models.Path;
import io.swagger.models.Swagger;
import io.swagger.models.Tag;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 聂跃 on 2018/4/3.
 * shiro工具类
 */
@Configuration
public class ShiroUtil {
    @Autowired
    PermissionService permissionService;
    @Value("${myPugin.activationCodeMallProjectDomainUrl}")
    String activationCodeMallProjectDomainUrl;
    /**
     * 初始化Swagger的权限数据到数据库
     */
    public boolean initPermission(){
        boolean b=false;
        String s = null;
        try {
            s = HttpClientUtil.doGet(activationCodeMallProjectDomainUrl+"/v2/api-docs");
        } catch (Exception e) {
           return b;
        }
        JSONObject json = JSONObject.fromObject(s);
        Map<String, Class> classMap = new HashMap<String, Class>();
        classMap.put("tags", Tag.class);
        Map<String,Class> pathMap = new HashMap<String, Class>();
        classMap.put("paths",pathMap.getClass());
        Swagger swagger= (Swagger) JSONObject.toBean(json, Swagger.class,classMap);
        List<Tag> tagslist=swagger.getTags();
        Map<String,Path> pathsMap=swagger.getPaths();
        for (Map.Entry<String, Path>  entry: pathsMap.entrySet()) {
            //System.out.println(entry.getKey());
            Permission permission =new Permission();
            // System.out.println(entry.getValue());
            JSONObject js=JSONObject.fromObject(entry.getValue());
            //System.out.println(js.get("post"));
            JSONObject ss=JSONObject.fromObject(js.get("post"));
            //System.out.println(ss.get("summary"));
            JSONArray sss=JSONArray.fromObject(ss.get("tags"));
            for (int i = 0; i < tagslist.size(); i++) {
                if(sss.get(0).equals(tagslist.get(i).getName())){
                    // System.out.println(sss.get(0));
                    //System.out.println(tagslist.get(i).getDescription());
                    permission.setManagerName(tagslist.get(i).getDescription());//权限管理名称
                    permission.setName(ss.get("summary").toString());//权限名称
                    permission.setRoute(entry.getKey());//权限路由
                    if(entry.getKey().indexOf("/list")>0
                    		||entry.getKey().indexOf("tool")>0
                    		||entry.getKey().indexOf("login")>0
                    		||entry.getKey().indexOf("register")>0
                    		||entry.getKey().indexOf("valid")>0
                    		||entry.getKey().indexOf("/count")>0
                    		||entry.getKey().indexOf("/load")>0){                    	
                    	permission.setType(0);//默认开放
                    }else{
                    	permission.setType(1);//默认鉴权
                    	
                    }
                    b=permissionService.addPermission(permission);
                    // System.out.println("---------------------");
                }
            }
        }
        return b;
    }

}
