package com.nicolas.gdxbeispiel.common;

public class BeispielInfo {

    private final String name;
    private final Class<? extends BeispielBase> clazz;

    public BeispielInfo(Class<? extends BeispielBase> clazz) {
        this.clazz = clazz;
        name = clazz.getSimpleName();
    }

    public String getName() {
        return name;
    }

    public Class<?> getClazz() {
        return clazz;
    }
}
