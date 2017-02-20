package com.idea.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import io.renren.entity.SysUserEntity;
import io.renren.utils.ShiroUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.springframework.beans.factory.annotation.Autowired;

import io.renren.service.SysUserService;


/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-2-15
 * <p>Version: 1.0
 */
public class SysUserFilter extends PathMatchingFilter {
    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        SysUserEntity user = ShiroUtils.getUserEntity();
        request.setAttribute("user", user.getUsername());
        return true;
    }
}
