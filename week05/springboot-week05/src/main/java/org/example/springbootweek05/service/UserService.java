package org.example.springbootweek05.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.springbootweek05.entity.User;
import org.example.springbootweek05.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 蒋玉泽
* @description 针对表【t_user(用户表)】的数据库操作Service
* @createDate 2026-04-06 03:01:59
*/
@Service // 标记为服务层组件
@RequiredArgsConstructor // 构造器注入，替代@Autowired
@Slf4j
public  class UserService  {
    // 注入Mapper，final保证不可变

    private final UserMapper userMapper;

    public int add(User user) { return userMapper.insert(user); }
    public User getById(Long id) { return userMapper.selectById(id); }
    public List<User> list() { return userMapper.selectList(); }
    public int update(User user) { return userMapper.updateById(user); }
    public int delete(Long id) { return userMapper.deleteById(id); }
    public List<User> search(String username, Integer minAge) {
        return userMapper.selectByCondition(username, minAge);
    }

}
