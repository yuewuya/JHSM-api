package test.cc.api;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.cc.model.Admin;
import test.cc.repository.AdminRepository;
import test.cc.service.AdminService;
import test.cc.util.BaseBeanUtil;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 陈诚[os1040]
 * company qianmi.com
 * Date    2018/03/19.
 */
@RestController
@RequestMapping("/admin")
public class AdminApi {

    @Autowired
    private AdminService adminService;

    @Autowired
    private AdminRepository adminRepository;

    @RequestMapping("/add")
    public Object add(@RequestBody Admin admin){
        if (adminService.findById(admin.getId()) != null) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("msg","电话号码已存在");
            jsonObject.put("code","0");
            return jsonObject;
        }
        return BaseBeanUtil.setData(adminService.add(admin),1);
    }

    @RequestMapping("/list")
    public Object list(){
//        return BaseBeanUtil.setData(6,adminService.findAll());
        return BaseBeanUtil.setData((int)adminRepository.count(),adminRepository.findAll());
    }

    @RequestMapping("/login")
    public Object login(@RequestBody Admin admin, HttpSession session){
        Admin user = adminRepository.findAllByIdAndAndPwd(admin.getId(), admin.getPwd());
        if (user != null){
            session.setAttribute("admin", user);
            user.setPwd("菜鸡想看我密码？");
            return BaseBeanUtil.setData(user, 1);
        }
        return BaseBeanUtil.setCode(0, "账号密码错误！");
    }

    @RequestMapping("/names")
    public Object adminNames(){
        List<Admin> list = adminRepository.findAll();
        List<String> names = list.stream().map(admin -> admin.getName()).collect(Collectors.toList());
        return BaseBeanUtil.setData(list.size(), names);
    }

    @RequestMapping("/edit")
    public Object edit(@RequestBody Admin admin){
        adminRepository.save(admin);
        return BaseBeanUtil.setCode(1, "修改成功");
    }

    @RequestMapping("/delete")
    public Object delete(@RequestBody Admin admin){
        adminRepository.deleteById(admin.getId());
        return BaseBeanUtil.setCode(1, "");
    }


}
