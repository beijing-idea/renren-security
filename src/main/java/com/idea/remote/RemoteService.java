package com.idea.remote;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.zhangkaitao.shiro.chapter23.remote.PermissionContext;
import com.github.zhangkaitao.shiro.chapter23.remote.RemoteServiceInterface;

import com.idea.service.AuthorizationService;


/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-3-13
 * <p>Version: 1.0
 */
public class RemoteService implements RemoteServiceInterface {

    @Autowired
    private AuthorizationService authorizationService;

    @Override
    public PermissionContext getPermissions(String username) {
        PermissionContext permissionContext = new PermissionContext();
        permissionContext.setRoles(authorizationService.findRoles(username));
        permissionContext.setPermissions(authorizationService.findPermissions(username));
        return permissionContext;
    }
}
