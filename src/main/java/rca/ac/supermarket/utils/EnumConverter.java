package rca.ac.supermarket.utils;

import rca.ac.supermarket.enums.UserRole;

public class EnumConverter {

    public static UserRole getUserRole(String role) {
        try {
            return UserRole.valueOf(role.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid role: " + role);
        }
    }
}
