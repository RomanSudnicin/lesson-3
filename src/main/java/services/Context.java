package services;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by roman on 11.08.16.
 */
public class Context {
    Map<Class<?>, Object> context = new HashMap<>();

    public void add(Class<?> clazz, Object object) {
        if(!context.containsKey(clazz)){
            context.put(clazz,object);
        }
    }

    public Object get(Class<?> clazz){
        return context.get(clazz);
    }
}
