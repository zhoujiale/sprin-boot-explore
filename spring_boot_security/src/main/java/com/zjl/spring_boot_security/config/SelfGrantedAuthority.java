package com.zjl.spring_boot_security.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.Assert;

import java.io.Serializable;

/**
 * @author zhou
 * @version 1.0
 * @className SelfGrantedAuthority
 * @description
 * @date 2021/04/25 20:54
 **/
public class SelfGrantedAuthority implements GrantedAuthority, Serializable {
    private  String authority;

    public SelfGrantedAuthority() {
    }

    public SelfGrantedAuthority(String role) {
        Assert.hasText(role, "A granted authority textual representation is required");
        this.authority = role;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else {
            return obj instanceof SelfGrantedAuthority ? this.authority.equals(((SelfGrantedAuthority)obj).authority) : false;
        }
    }

    @Override
    public int hashCode() {
        return this.authority.hashCode();
    }

    @Override
    public String toString() {
        return this.authority;
    }
}
