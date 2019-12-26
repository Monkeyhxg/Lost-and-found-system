package com.gui.springbootdemo.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName MapUtil  Map 工具类
 * @Description TODO
 * @Author MI
 * @Date 2019/12/26 11:51
 * @Version 1.0
 */
public class MapUtil {


    /**
     * @Description  将对象转为Map
     * @Author  xingming
     * @Date   2019/12/26 20:18
     * @Param
     * @Return
     * @Exception
     */
    public static Map<String, Object> objectToMap(Object obj) throws Exception {
        if(obj == null)
            return null;

        java.util.Map<String, Object> map = new HashMap<String, Object>();

        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor property : propertyDescriptors) {
            String key = property.getName();
            if (key.compareToIgnoreCase("class") == 0) {
                continue;
            }
            Method getter = property.getReadMethod();
            Object value = getter!=null ? getter.invoke(obj) : null;
            map.put(key, value);
        }

        return map;
    }

    /**
     * @Description  将Map 转为对象
     * @Author  xingming
     * @Date   2019/12/26 20:19
     * @Param
     * @Return
     * @Exception
     */
    public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) throws Exception {
        if (map == null)
            return null;

        Object obj = beanClass.newInstance();

        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor property : propertyDescriptors) {
            Method setter = property.getWriteMethod();
            if (setter != null) {
                setter.invoke(obj, map.get(property.getName()));
            }
        }

        return obj;
    }
}
