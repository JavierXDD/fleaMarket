package com.wenLi.controller;

import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import com.wenLi.entity.Blog;
import com.wenLi.entity.Menu;
import com.wenLi.entity.Message;
import com.wenLi.service.BlogService;
import com.wenLi.service.MenuService;
import com.wenLi.service.MessageService;
import com.wenLi.util.entity.Page;

/**
 * 交易信息控制
 * @author xjw
 *
 */
@Controller
@RequestMapping(value = "/blog")
public class BlogController {
	@Resource
	private BlogService blogService;
	@Resource
	private MenuService menuService;
	@Resource
	private MessageService messageService;
	
	//跳转到自己交易信息列表
    @RequestMapping(value="/toMyBlog")
    public String toMyBlog(Model model,
    		@RequestParam(value = "id", required = true) String id){
    	Page<Blog> blogList = blogService.getBlogListByUid(1 ,10,id);
    	model.addAttribute("myBlogList", blogList);
    	return "/jsp/personInfo/myBlogList";
    }
	//分页自己交易信息列表
    @RequestMapping(value="/getMyBlog")
    public String getMyBlog(Model model,
			@RequestParam(value = "pc", required = true) String pageCode,
    		@RequestParam(value = "id", required = true) String id){
    	int pc = Integer.parseInt(pageCode);
    	Page<Blog> blogList = blogService.getBlogListByUid(pc ,10,id);
    	model.addAttribute("myBlogList", blogList);
    	return "/jsp/personInfo/myBlogList";
    }
    
	//删除自己交易信息
    @RequestMapping(value="/toMyBlogDelete")
    public String toMyBlogDelete(Model model,
			@RequestParam(value = "id", required = true) String id){
		Blog blog = blogService.findBlogById(id);
		String userId = blog.getUserId();
		boolean isok = blogService.deleteBlog(blog);
		model.addAttribute("isok", isok);
    	return this.toMyBlog(model,userId);
    }    
    
	//跳转到交易信息详情页
	@RequestMapping(value="/toMyBlogDetail")
	public String toMyBlogDetail(Model model,
			@RequestParam(value = "id", required = true) String id) {
		Blog blog = blogService.findBlogById(id);	
		Page<Message> messageList = messageService.findMessageByBlogId(1, 5, id);
		
		List<Menu> menuList = menuService.findUsingMenu();
		List<Menu> sonMenuList = menuService.findAllSonMenuList();
		List<Blog> newBlogList = blogService.findNewBlog(5);
		model.addAttribute("blog", blog);
		model.addAttribute("messageList", messageList);
		model.addAttribute("menuList", menuList);
		model.addAttribute("sonMenuList", sonMenuList);
		model.addAttribute("newBlogList", newBlogList);
		return "/jsp/item";
	}	
	
	//跳转到交易信息编辑页
	@RequestMapping(value="/toMyBlogEdit")
	public String toMyBlogEdit(Model model,
			@RequestParam(value = "id", required = false) String id) {
		if(id != null && id !=""){			
			Blog blog = blogService.findBlogById(id);
			List<Menu> menuList = menuService.findUsingMenu();
			model.addAttribute("blog", blog);
			model.addAttribute("menuList", menuList);
			model.addAttribute("msg", "编辑");
		}else{	
			List<Menu> menuList = menuService.findUsingMenu();
			model.addAttribute("menuList", menuList);
			Blog blog = new Blog();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			String createTime = dateFormat.format( new Date() ); 
			blog.setCreateTime(createTime);
			blog.setModifyTime(createTime);
			blog.setIsUse(0);
			model.addAttribute("blog", blog);
			model.addAttribute("msg", "新建");			
		}

		return "/jsp/personInfo/myBlogEdit";
	}
	
	//新建、更新交易信息
	@RequestMapping(value="/saveOrUpdateMyBlog")
	public String saveOrUpdateMyBlog(Model model,
			@ModelAttribute("blog") Blog blog) {
		boolean isok = blogService.saveOrUpdateMyBlog(blog);
		if(isok){
			model.addAttribute("msg", "保存成功!");
			return this.toMyBlog(model, blog.getUserId());
		}else{
			model.addAttribute("msg", "保存失败,请稍后再试。");
			return this.toMyBlog(model, blog.getUserId());
		}

	}
	
	//上传交易信息图片
	@RequestMapping(value="/upSurfacePic")
	public String upSurfacePic(Model model,
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
			String surfacePicUrl = props.getProperty("surfacePicUrl");
			
			// 1. 得到文件保存的路径
			String root = request.getServletContext().getRealPath("media/"+surfacePicUrl);
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
			
			String blogId = list.get(1).getString("utf-8");
			String title = list.get(2).getString("utf-8");
			String name = list.get(3).getString("utf-8");
			Integer price = Integer.parseInt(list.get(4).getString("utf-8"));
			String quality = list.get(5).getString("utf-8");
			String userTelephone = list.get(6).getString("utf-8");
			String userEmail = list.get(7).getString("utf-8");
			String createTime = list.get(8).getString("utf-8");
			String modifyTime = list.get(9).getString("utf-8");
			String text = list.get(10).getString("utf-8");
			String userId = list.get(11).getString("utf-8");
			String userName = list.get(12).getString("utf-8");
			String menuId = list.get(13).getString("utf-8");
			String menuName = list.get(14).getString("utf-8");
			String typeId = list.get(15).getString("utf-8");
			String typeName = list.get(16).getString("utf-8");
			String tags = list.get(17).getString("utf-8");
			Integer isUse = Integer.parseInt(list.get(18).getString("utf-8"));
		
			Blog blog = new Blog();
			blog.setSurfacePicUrl(picUrl);
			blog.setCreateTime(createTime);
			blog.setId(blogId);
			blog.setMenuId(menuId);
			blog.setMenuName(menuName);
			blog.setModifyTime(modifyTime);
			blog.setName(name);
			blog.setPrice(price);
			blog.setQuality(quality);
			blog.setTags(tags);
			blog.setText(text);
			blog.setTypeId(typeId);
			blog.setTitle(title);
			blog.setTypeName(typeName);
			blog.setUserEmail(userEmail);
			blog.setUserTelephone(userTelephone);
			blog.setUserId(userId);
			blog.setUserName(userName);
			blog.setIsUse(isUse);
			model.addAttribute("blog", blog);		   	
			List<Menu> menuList = menuService.findUsingMenu();
			model.addAttribute("menuList", menuList);
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
		return "/jsp/personInfo/myBlogEdit";
	}
	
	
	//跳转到所有交易信息列表
    @RequestMapping(value="/toManagerBlogList")
    public String toManagerBlogList(Model model){
    	Page<Blog> blogList = blogService.getUsingBlogList(1, 10);
    	model.addAttribute("blogList", blogList);
    	return "/jsp/manager/blog/blogList";
    }
    
    //分页所有交易信息列表
	@RequestMapping(value="/getManagerBlogList")
	public String getManagerBlogList(Model model,
			@RequestParam(value = "pc", required = true) String pageCode) {
		int pc = Integer.parseInt(pageCode);
		Page<Blog> blogList = blogService.getUsingBlogList(pc, 10);
		model.addAttribute("blogList", blogList);
		return "/jsp/manager/blog/blogList";
	}
	
	//跳转到未审核交易信息列表
    @RequestMapping(value="/toUncheckBlogList")
    public String toUncheckBlogList(Model model){
    	Page<Blog> blogList = blogService.getUncheckBlogList(1, 10);
    	model.addAttribute("blogList", blogList);
    	return "/jsp/manager/blog/uncheckBlogList";
    }
    //分页未审核交易信息列表
    @RequestMapping(value="/getUncheckBlogList")
    public String getUncheckBlogList(Model model,
			@RequestParam(value = "pc", required = true) String pageCode){
		int pc = Integer.parseInt(pageCode);
		Page<Blog> blogList = blogService.getUncheckBlogList(pc, 10);
		model.addAttribute("blogList", blogList);
    	return "/jsp/manager/blog/uncheckBlogList";
    }
    
    //关闭未审核交易信息列表
    @RequestMapping(value="/closeBlog")
    public String closeBlog(Model model,
			@RequestParam(value = "id", required = true) String id){
		Blog blog = blogService.findBlogById(id);
		boolean isok = blogService.closeBlog(blog);
    	return this.toUncheckBlogList(model);
    }
    
    //审核通过交易信息列表
    @RequestMapping(value="/passBlog")
    public String passBlog(Model model,
			@RequestParam(value = "id", required = true) String id){
		Blog blog = blogService.findBlogById(id);
		boolean isok = blogService.passBlog(blog);
    	return this.toUncheckBlogList(model);
    }
}
