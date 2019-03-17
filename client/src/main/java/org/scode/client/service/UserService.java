package org.scode.client.service;

import org.scode.client.entity.User;
import org.scode.sioc.annotation.Service;

/**
 * @author wyh
 * @date 2019/3/17 12:39 PM
 */
@Service
public class UserService {
    public User getUserInfo() {
        return new User(1, "wyh");
    }
}
