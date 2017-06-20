package com.zhihe.zdata.util;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;


public class JsonUtils {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static <T> String bean2Json(T bean) {
        try {
            return objectMapper.writeValueAsString(bean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String map2Json(Map map) {
        try {
            return objectMapper.writeValueAsString(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String list2Json(List list) {
        try {
            return objectMapper.writeValueAsString(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static <T> T json2Bean(String json, Class<T> beanClass) {
        try {
            return objectMapper.readValue(json, beanClass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> List<T> json2List(String json, Class<T> beanClass) {
        try {

            return (List<T>)objectMapper.readValue(json, getCollectionType(List.class, beanClass));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Map json2Map(String json) {
        try {

            return (Map)objectMapper.readValue(json, Map.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

    /**
     * 获取泛型的Collection Type
     *
     * @param collectionClass 泛型的Collection
     * @param elementClasses  元素类
     * @return 对应类型
     * @since 1.0
     */
    public static Object json2ListBean(String json, Class<?> collectionClass, Class<?>... elementClasses) throws Exception {
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
        return objectMapper.readValue(json, javaType);
    }

    public static void main(String[] args)  {
        try {
            //3.解析json保存link信息
//            String jsonLink = "[{\"channelId\":\"778109159068274689\",\"budget\":100},{\"channelId\":\"778109159068274690\",\"budget\":200}]";
//            List<ChannelLink> links = (List<ChannelLink>) JsonUtils.json2ListBean(jsonLink, ArrayList.class, ChannelLink.class);

            String json = "{\"ext1\":\"\",\"ext2\":\"2\"}";
            System.out.print((Map)objectMapper.readValue(json, Map.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



