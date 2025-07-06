package com.nicolas.gdxbeispiel.common;

import com.nicolas.gdxbeispiel.*;
import com.nicolas.gdxbeispiel.utils.BasicsBitmapFont;

import java.util.*;

public class BeispielInfos {

    public static final List<BeispielInfo> ALL = Arrays.asList(
        BasicsApplicationListener.BEISPIEL_INFO,
        BasicsGDX.BEISPIEL_INFO,
        BasicsInputListening.BEISPIEL_INFO,
        BasicsInputPolling.BEISPIEL_INFO,
        BasicsModule.BEISPIEL_INFO,
        GdxReflection.BEISPIEL_INFO,
        BasicsOrthographicCamera.BEISPIEL_INFO,
        BasicsViewport.BEISPIEL_INFO,
        BasicsSpriteBatch.BEISPIEL_INFO,
        BasicsShapeRenderer.BEISPIEL_INFO,
        BasicsBitmapFont.BEISPIEL_INFO,
        BasicsPooling.BEISPIEL_INFO
    );

    public static List<String> getBeispielNames(){
        List<String> ret = new ArrayList<>();

        for (BeispielInfo info : ALL){
            ret.add(info.getName());
        }
        Collections.sort(ret);
        return ret;
    }

    public static BeispielInfo find( String name){
        if (name == null || name.isEmpty()){
            throw new IllegalArgumentException("Der Name wird benötigt!");
        }

        BeispielInfo ret = null;

        for(BeispielInfo info : ALL){
            if (info.getName().equals(name)){
                ret = info;
                break;
            }
        }

        if (ret == null){
            throw new IllegalArgumentException("Kan das Beispiel mit " + name + " nucht finden!");
        }

        return ret;
    }

    private BeispielInfos(){}

}
