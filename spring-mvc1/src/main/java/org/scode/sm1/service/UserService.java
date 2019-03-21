package org.scode.sm1.service;

import org.scode.sm1.entity.User;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author wyh
 * @date 2019/3/21 8:55 PM
 * @description
 */
@Service
public class UserService {
    public User getUserInfo() {
        return new User(1, "王远航", 24, 12306, new Date(), new Date());
    }
}
