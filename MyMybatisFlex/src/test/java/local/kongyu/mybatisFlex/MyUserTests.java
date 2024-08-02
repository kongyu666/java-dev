package local.kongyu.mybatisFlex;

import local.kongyu.mybatisFlex.bo.MyUserGroupByBO;
import local.kongyu.mybatisFlex.bo.MyUserJoinOrderBO;
import local.kongyu.mybatisFlex.entity.MyUserEntity;
import local.kongyu.mybatisFlex.service.MyUserService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class MyUserTests {
    private final MyUserService myUserService;

    @Test
    void listUser() {
        List<MyUserEntity> list = myUserService.listUser();
        System.out.println(list);
    }

    @Test
    void listPageUser() {
        List<MyUserEntity> myUserEntityList = myUserService.listPageUser(1, 20);
        System.out.println(myUserEntityList);
    }

    @Test
    void getUser() {
        MyUserEntity myUserEntity = myUserService.getUser(100);
        System.out.println(myUserEntity);
    }

    @Test
    void countUser() {
        long userCount = myUserService.count();
        System.out.println(userCount);
    }

    @Test
    void groupUser() {
        List<MyUserGroupByBO> list = myUserService.groupUser();
        System.out.println(list);
    }

    @Test
    void joinOrder() {
        List<MyUserJoinOrderBO> list = myUserService.joinOrder();
        System.out.println(list);
    }

}
