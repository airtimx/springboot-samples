package org.example.springbootweek04dome.entuty;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
class TeamTest {

    @Test
    void testTeam() {
        // 手动创建Team对象进行测试
        Team team = new Team();
        team.setLeader("张三");
        team.setAge(32);
        team.setEmail("njuit@qq.com");
        team.setPhone("13900001111");
        team.setCreateTime(LocalDate.of(2019, 1, 1));
        
        log.info("team: {}", team);
        assertTrue( team.getLeader().length() >= 2 && team.getLeader().length() <= 10);
        assertTrue( team.getAge() >=20 && team.getAge() <= 60);
        assertTrue(team.getEmail().matches( "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$"));
        assertTrue(team.getPhone().matches( "^[0-9]{11}$"));
        assertTrue(team.getCreateTime().isBefore(LocalDate.now()));
}
}