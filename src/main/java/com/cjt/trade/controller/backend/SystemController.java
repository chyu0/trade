package com.cjt.trade.controller.backend;

import com.cjt.trade.controller.BaseController;
import com.cjt.trade.dto.ResultDTO;
import com.cjt.trade.model.Admin;
import com.cjt.trade.model.Website;
import com.cjt.trade.service.IAdminServcie;
import com.cjt.trade.service.IUploadService;
import com.cjt.trade.service.IWebsiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * @author wulitaotao
 */
@Controller
@RequestMapping("/backend")
public class SystemController extends BaseController {

    @Resource
    private IWebsiteService websiteService;

    @Resource
    private IAdminServcie adminServcie;

    @Autowired
    private IUploadService uploadService;

    /**
     * 网站信息页
     */
    @RequestMapping(value = "/website")
    public String website(Model model) {
        model.addAttribute("website", websiteService.getWebsite());
        return "backend/system/website";
    }

    @ResponseBody
    @RequestMapping(value = "/updateWebsite")
    public ResultDTO updateWebsite(MultipartFile file, Website website) throws IOException {
        if (file != null) {
            website.setLogoUrl(uploadService.uploadFile(file.getInputStream(), file.getOriginalFilename()));
        }
        if (website.getId() == null) {
            websiteService.insertWebSite(website);
        } else {
            websiteService.updateWebsite(website);
        }
        return success();
    }

    @RequestMapping(value = "/admin")
    public String admin() {
        return "backend/system/admin";
    }

    @RequestMapping(value = "/getAllAdmins")
    @ResponseBody
    public List<Admin> getAllAdmins() {
        return adminServcie.getAllAdmins();
    }

    @RequestMapping(value = "/addAdmin")
    @ResponseBody
    public ResultDTO<Object> addAdmin(Admin admin) {
        String account = admin.getAccount();
        int result = adminServcie.getAdminCountByAccount(account);
        if (result > 0) {
            return failed(account + "已经存在");
        } else {
            adminServcie.addAdmin(admin);
            return success();
        }
    }

    @RequestMapping(value = "/deleteAdmin")
    @ResponseBody
    public ResultDTO deleteAdmin(int id) {
        adminServcie.deleteAdmin(id);
        return success();
    }

    @RequestMapping(value = "/getAdminById")
    @ResponseBody
    public Admin getAdminById(int id) {
        return adminServcie.getAdminById(id);
    }

    @RequestMapping(value = "/updateAdmin")
    public String updateAdmin(Admin admin) {
        if (admin != null) {
            adminServcie.updateAdmin(admin);
        }
        return admin();
    }
}
