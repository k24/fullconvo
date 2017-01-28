package com.github.k24.fullconvo.jsonic;

import com.github.k24.fullconvo.Fullconvo;
import com.github.k24.fullconvo.convo.Convo;
import com.github.k24.fullconvo.convo.JsonConvoFactory;
import com.github.k24.fullconvo.convo.MapConvoFactory;
import com.github.k24.fullconvo.convo.ObjectConvoFactory;
import net.arnx.jsonic.JSON;

import java.util.Map;

/**
 * Created by k24 on 2017/01/18.
 */
public class JsonicFullconvo {
    private JsonicFullconvo() {
        //no instance
    }

    public static Fullconvo create() {
        final JSON jsonic = new JSON();
        return new Fullconvo.Builder()
                .jsonConvoFactory(new JsonConvoFactory() {
                    @Override
                    public Convo from(String json) {
                        return new JsonicJsonConvo(jsonic, json);
                    }
                })
                .mapConvoFactory(new MapConvoFactory() {
                    @Override
                    public Convo from(Map<String, Object> map) {
                        return new JsonicMapConvo(jsonic, map);
                    }
                })
                .objectConvoFactory(new ObjectConvoFactory() {
                    @Override
                    public Convo from(Object object) {
                        return new JsonicObjectConvo(jsonic, object);
                    }
                })
                .build();
    }
}
