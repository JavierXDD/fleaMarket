package com.wenLi.controller;

import java.io.File;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wenLi.entity.IndexPicModel;
import com.wenLi.entity.Menu;
import com.wenLi.service.IndexPicModelService;
import com.wenLi.service.MenuService;
import com.wenLi.util.entity.Page;

/**
 * 首页板块控制
 * @author xjw
 *
 */
@Controller
@RequestMapping(value = "/indexPic")
public class IndexPicModelController {
	@Resource
	private IndexPicModelService indexPicModelService;
	@Resource
	private MenuService menuService;
	
	//跳转到板块列表
    @RequestMapping(value="/toIndexPicList")
    public String toIndexPicList(Model model){
    	Page<IndexPicModel> indexPicList = indexPicModelService.getIndexPicList(1 ,5);
    	model.addAttribute("indexPicList", indexPicList);
    	Page<Menu> menuList = menuService.getMenuList(1 ,5);
    	model.addAttribute("menuList", menuList);
    	return "/jsp/manager/indexPic/indexPicList";
    }
    
	//跳转到板块详情页
	@RequestMapping(value="/toIndexPicDetail")
	public String toIndexPicDetail(Model model,
			@RequestParam(value = "id", required = true) String id) {
		IndexPicModel indexPic = indexPicModelService.findIndexPicById(id);		
		model.addAttribute("indexPic", indexPic);
		return "/jsp/manager/indexPic/indexPicDetail";
	}
	
	//跳转到板块编辑页
	@RequestMapping(value="/toIndexPicEdit")
	public String toIndexPicEdit(Model model,
			@RequestParam(value = "id", required = false) String id) {
		if(id != null && id !=""){			
			IndexPicModel indexPic = indexPicModelService.findIndexPicById(id);		
			model.addAttribute("indexPic", indexPic);
			model.addAttribute("msg", "编辑");
		}else{	
			Integer maxOrder = indexPicModelService.getMaxOrder();
			model.addAttribute("maxOrder", maxOrder);
			model.addAttribute("msg", "新建");			
		}

		return "/jsp/manager/indexPic/indexPicEdit";
	}
	
	//上传板块图片
	@RequestMapping(value="/upIndexPic")
	public String upIndexPic(Model model,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		
		// 添加真实文件
		// 工厂
		DiskFileItemFactory factory = new DiskFileItemFactory(1024 * 1024 * 1024,
				new File("C:/f/temp"));
		// 解析器
		ServletFileUpload sfu = new ServletFileUpload(factory);
		sfu.setHeaderEncoding("UTF-8");
		// 解析，得到List
		try {
			@SuppressWarnings("unchecked")
			List<FileItem> list = sfu.parseRequest(request);
			FileItem fi = list.get(0);
			
			//读取配置文件中的板块配图储存位置
			Properties props = new Properties();
			InputStream in = this.getClass().getClassLoader().getResourceAsStream("/properties/other.properties");			
			props.load(in);
			String menuPicUrl = props.getProperty("indexPicUrl");
			
			// 1. 得到文件保存的路径
			String root = request.getServletContext().getRealPath("media/"+menuPicUrl);
			// 2. 生成二层目录 1). 得到文件名称 2). 得到hashCode 3). 转发成16进制 4). 获取前二个字符用来生成目录
			String filename = fi.getName();// 获取上传的文件名称
			// 处理文件名的绝对路径问题
			int index = filename.lastIndexOf("\\");
			if (index != -1) {
				filename = filename.substring(index + 1);
			}
			// 给文件名称添加uuid前缀，处理文件同名问题
			String savename =  filename;
			// 1. 得到hashCode
			int hCode = filename.hashCode();
			String hex = Integer.toHexString(hCode);
			// 2. 获取hex的前两个字母，与root连接在一起，生成一个完整的路径
			File dirFile = new File(root, hex.charAt(0) + "/" + hex.charAt(1));
			// 3. 创建目录链
			dirFile.mkdirs();
			// 4. 创建目录文件
			File destFile = new File(dirFile, savename);
			// 5. 保存
			fi.write(destFile);

			String allUrl = dirFile + "\\" +savename;
			String[] splitStr=allUrl.split("\\\\");			
			String picUrl = splitStr[8]+"\\"+splitStr[9]+"\\"+splitStr[10]+"\\"+splitStr[11]+"\\"+splitStr[12];									
			
			String indexPicId = list.get(1).getString("utf-8");
			String title = list.get(2).getString("utf-8");
			String secondTitle = list.get(3).getString("utf-8");
			String buttonName = list.get(4).getString("utf-8");
			String buttonLink = list.get(5).getString("utf-8");
			//String picUrl = list.get(3).getString("utf-8");
			String picLink = list.get(6).getString("utf-8");
			Integer menuOrder = Integer.parseInt(list.get(7).getString("utf-8"));
			Integer isUse = Integer.parseInt(list.get(8).getString("utf-8"));
			String text = list.get(9).getString("utf-8");
			
			
			IndexPicModel indexPic = new IndexPicModel();
			indexPic.setId(indexPicId);
			indexPic.setTitle(title);
			indexPic.setSecondTitle(secondTitle);
			indexPic.setButtonName(buttonName);
			indexPic.setButtonLink(buttonLink);
			indexPic.setPicUrl(picUrl);
			indexPic.setPicLink(picLink);
			indexPic.setIsUse(isUse);
			indexPic.setText(text);
			indexPic.setMenuOrder(menuOrder);
			
			model.addAttribute("indexPic", indexPic);
		   	model.addAttribute("maxOrder", menuOrder);
			
		} catch (FileUploadException e) {
			if (e instanceof FileUploadBase.FileSizeLimitExceededException) {
				request.setAttribute("msg", "您上传的文件超出了100KB！");
				return "error";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		return "/jsp/manager/indexPic/indexPicEdit";
	}
	
	
	//新建、更新板块
	@RequestMapping(value="/saveOrUpdateIndexPic")
	public String saveOrUpdateIndexPic(Model model,
			@ModelAttribute("indexPic") IndexPicModel indexPic) {
		boolean isok = indexPicModelService.saveOrUpdateIndexPic(indexPic);
		if(isok){
			model.addAttribute("msg", "保存成功");
			return this.toIndexPicList(model);
		}else{
			model.addAttribute("msg", "保存失败");
			return this.toIndexPicList(model);
		}

	}
	
	//禁用板块
	@ResponseBody
    @RequestMapping(value="/forbiddenIndexPic")
    public String forbiddenIndexPic(Model model,
    		@RequestParam(value = "id", required = true) String id){
    	   	
    	boolean isOk = indexPicModelService.forbiddenIndexPic(id);
    	
    	if(isOk ){
    		return "success";
    	}else{
    		return "error";
    	}
    }
	
	//启用板块
	@ResponseBody
    @RequestMapping(value="/usingIndexPic")
    public String usingIndexPic(Model model,
    		@RequestParam(value = "id", required = true) String id){
    	   	
    	boolean isOk = indexPicModelService.usingIndexPic(id);
    	
    	if(isOk ){
    		return "success";
    	}else{
    		return "error";
    	}
    }


}
