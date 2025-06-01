package com.nicolas.gdxbeispiel.common;

import com.badlogic.gdx.utils.reflect.ClassReflection;

public class BeispielFactory {

    public static BeispielBase newBeispiel(String name){
        if (name == null || name.isEmpty()){
            throw new IllegalArgumentException("Name ist notwendig!");
        }

        BeispielInfo info = BeispielInfos.find(name);

        try {
            return (BeispielBase) ClassReflection.newInstance(info.getClazz());
        } catch (Exception e){
            throw new RuntimeException("Kann das Beispiel mi drm Namen " + name + " nicht finden");
        }
    }

    private BeispielFactory(){}
}
