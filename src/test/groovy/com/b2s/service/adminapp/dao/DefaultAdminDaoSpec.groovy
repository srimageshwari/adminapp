package com.b2s.service.adminapp.dao

import com.b2s.service.adminapp.AdminApplication
import com.b2s.service.adminapp.model.UserInfo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

/**
 * @author smuthuvel 2018-12-27
 */
@ContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = [AdminApplication.class])
@ActiveProfiles('local')
class DefaultAdminDaoSpec extends Specification {
    @Autowired
    private JdbcAdminDao adminDao;

    def "attempt to save user"() {

        given:
        def user = [UserInfo.builder().setUserId(3).setPassword("Rachana*1").setName("Rachana").setAddress("gks colony")
                            .setCity("tuty").setState("tn").setZip(890064).build(),
                    UserInfo.builder().setUserId(4).setPassword("Rachana*1").setName("Rachana").setAddress("gks colony")
                            .setCity("tuty").setState("tn").setZip(890064).build()];

        when:
        def result = adminDao.insert(user);

        then:
        user == result
    }


    def "attempt to save user - failure"() {
        given:
        List<UserInfo> userInfo = null

        when:
        adminDao.insert(userInfo)
        then:
        IllegalArgumentException e = thrown
        e.message == "userInfo cant be empty." as String
    }
}