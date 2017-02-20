package com.idea.service.impl;

import java.util.*;

import com.idea.service.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.renren.entity.SysMenuEntity;
import io.renren.entity.SysRoleEntity;
import io.renren.entity.SysUserEntity;
import io.renren.service.*;


/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
@Service

public class AuthorizationServiceImpl implements AuthorizationService {

    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private SysMenuService sysMenuService;


    /**
     * 根据用户名查找其角色
     * @param username
     * @return
     */
    public Set<String> findRoles(String username) {
        Set<String> roles = new HashSet<>();
        SysUserEntity sysUserEntity = sysUserService.queryByUserName(username);
        List<Long> roleids = new ArrayList<>();
        //userid==1 是管理员  拥有管理员角色
        if(sysUserEntity.getUserId() == 1){
            roles.add("管理员");
        }else{
            roleids = sysUserRoleService.queryRoleIdList(sysUserEntity.getUserId());
            for(Long roleid : roleids){
                SysRoleEntity role = sysRoleService.queryObject(roleid);
                roles.add(role.getRoleName());
            }
        }
        return roles;
    }

    /**
     * 根据用户名查找其权限
     * @param username
     * @return
     */
    public Set<String> findPermissions(String username) {
        List<String> perList = null;
        Set<String> permissions = new HashSet<>();
        SysUserEntity sysUserEntity = sysUserService.queryByUserName(username);
        //userid==1 说明是管理员 拥有所有权限
        if(sysUserEntity.getUserId() == 1){
            List<SysMenuEntity> menuList = sysMenuService.queryList(new HashMap<String, Object>());
            perList = new ArrayList<>(menuList.size());
            for(SysMenuEntity menu : menuList){
                perList.add(menu.getPerms());
            }
        }else{
            perList = sysUserService.queryAllPerms(sysUserEntity.getUserId());
        }
        for(String permiss : perList){
            permissions.add(permiss);
        }

        return permissions;
    }


}
