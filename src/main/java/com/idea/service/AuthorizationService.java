package com.idea.service;

import java.util.Set;


/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
public interface AuthorizationService {

    /**
     * 根据用户名查找其角色
     * @param username
     * @return
     */
    public Set<String> findRoles(String username);

    /**
     * 根据用户名查找权限字符串
     * @param username
     * @return
     */
    public Set<String> findPermissions(String username);


}
