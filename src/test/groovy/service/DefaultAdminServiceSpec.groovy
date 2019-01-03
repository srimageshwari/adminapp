package service

import com.b2s.service.adminapp.dao.AdminDao
import com.b2s.service.adminapp.dao.JdbcAdminDao
import com.b2s.service.adminapp.dao.MapAdminDao
import com.b2s.service.adminapp.model.UserInfo
import com.b2s.service.adminapp.service.DefaultAdminService
import com.b2s.service.adminapp.service.UserValidator
import spock.lang.Specification
import spock.lang.Subject

/**
 * @author smuthuvel 2018-12-14
 */
class DefaultAdminServiceSpec extends Specification {

    @Subject
  JdbcAdminDao adminDao;
    UserValidator userValidator;
    DefaultAdminService defaultAdminService;

    def setup() {
         adminDao = Mock(JdbcAdminDao);
        userValidator = Mock(UserValidator);
        defaultAdminService = new DefaultAdminService(adminDao, userValidator)

    }

    def "should collect specified product from repository"() {

        given:
        "Assigning expected values"
        UserInfo user = (UserInfo.builder().setUserId(2).setPassword("Rachana*1").setName("Rachana").setAddress("vks colony")
                .setCity("tuty").setState("tn").setZip(890064).build())
       adminDao.get(2) >> user;

        when:
        UserInfo result = defaultAdminService.get(2);

        then:
        user == result
    }
}
