package com.hitachi.epdi2.constant;

/**
 * Shiva Created on 29/12/24
 */
public enum Role {

    ROLE_ADMIN("Admin"), ROLE_QC("QC");

    private final String displayValue;

    Role(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
