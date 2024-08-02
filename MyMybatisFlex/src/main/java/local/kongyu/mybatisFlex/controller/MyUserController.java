package local.kongyu.mybatisFlex.controller;

import com.mybatisflex.core.paginate.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import local.kongyu.mybatisFlex.entity.MyUser;
import local.kongyu.mybatisFlex.service.MyUserService;
import org.springframework.web.bind.annotation.RestController;
import java.io.Serializable;
import java.util.List;

/**
 *  控制层。
 *
 * @author 孔余
 * @since 1.0.0
 */
@RestController
@RequestMapping("/myUser")
public class MyUserController {

    @Autowired
    private MyUserService myUserService;

    /**
     * 添加。
     *
     * @param myUser 
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody MyUser myUser) {
        return myUserService.save(myUser);
    }

    /**
     * 根据主键删除。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable Serializable id) {
        return myUserService.removeById(id);
    }

    /**
     * 根据主键更新。
     *
     * @param myUser 
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody MyUser myUser) {
        return myUserService.updateById(myUser);
    }

    /**
     * 查询所有。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<MyUser> list() {
        return myUserService.list();
    }

    /**
     * 根据主键获取详细信息。
     *
     * @param id 主键
     * @return 详情
     */
    @GetMapping("getInfo/{id}")
    public MyUser getInfo(@PathVariable Serializable id) {
        return myUserService.getById(id);
    }

    /**
     * 分页查询。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<MyUser> page(Page<MyUser> page) {
        return myUserService.page(page);
    }

}
