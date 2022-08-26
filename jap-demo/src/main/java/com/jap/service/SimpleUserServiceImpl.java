package com.jap.service;

import cn.hutool.core.lang.UUID;
import com.fujieid.jap.core.JapUser;
import com.fujieid.jap.core.JapUserService;
import com.fujieid.jap.spring.boot.common.JapUserServiceType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: simple
 * @author: Xu Yuwen
 * @Date: 2022-08-02 09:51
 */
//service注解和必要的参数  用于确定使用那种认证策略
@Service(JapUserServiceType.SIMPLE)
public class SimpleUserServiceImpl implements JapUserService {

    private static final List<JapUser> userDatas = new ArrayList<>();

    static {
        // 模拟数据库中的数据
        userDatas.add(new JapUser().setUsername("jap").setPassword("jap").setUserId("jap"));
        for (int i = 0; i < 10; i++) {
            userDatas.add(new JapUser().setUsername("jap" + i).setPassword("jap" + i).setUserId(UUID.fastUUID().toString()));
        }
    }

    @Override
    public JapUser getByName(String username) {
        return userDatas.stream()
                .filter((user) -> user.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean validPassword(String password, JapUser user) {
        return user.getPassword().equals(password);
    }

}
