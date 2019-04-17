package com.waken.dorm.common.utils;

import java.io.IOException;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.fasterxml.jackson.databind.JavaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;



/**
 * @ClassName JsonMapper
 * @Description 简单封装Jackson，实现JSON String<->Java Object的Mapper.封装不同的输出风格, 使用不同的builder函数创建实例.
 * @Author zhaoRong
 * @Date 2019/3/21 20:23
 **/
public class JsonMapper {
    private static Logger logger = LoggerFactory.getLogger(JsonMapper.class);

    private ObjectMapper mapper;

    public JsonMapper() {
        this(null);
    }
    /**
     * 将json格式的数据转化为所对应的对象
     * @param object 要转化的json对象
     * @param classList 转化对象中的字段拥有的对象列表{private List<Data> data}，列表中的元素为Data.class
     * @param toChangeClass 将要转化的对象
     * @return object可转化为toChangeClass对象
     */
    public Object jsonToObject(Object object, List<Class<?>> classList, Class<?> toChangeClass){
        Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();
        JSONObject jsonObject=JSONObject.fromObject(String.valueOf(object));
        Object toChangeClas = null;
        if(classList == null){
            toChangeClas =JSONObject.toBean(jsonObject, toChangeClass);
        }else{
            for(Class<?> cla:classList){
                String tr = String.valueOf(cla.getSimpleName());
                classMap.put(tr.toLowerCase(), cla);
            }
            toChangeClas =JSONObject.toBean(jsonObject, toChangeClass,classMap);
        }
        return toChangeClas;
    }

    public JsonMapper(JsonInclude.Include include) {
        mapper = new ObjectMappingCustomer();
        // 设置输出时包含属性的风格
        if (include != null) {
            mapper.setSerializationInclusion(include);
        }
        // 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    /**
     * 创建只输出非Null且非Empty(如List.isEmpty)的属性到Json字符串的Mapper,建议在外部接口中使用.
     */
    public static JsonMapper nonEmptyMapper() {
        return new JsonMapper(Include.NON_EMPTY);
    }

    /**
     * 创建只输出初始值被改变的属性到Json字符串的Mapper, 最节约的存储方式，建议在内部接口中使用。
     */
    public static JsonMapper nonDefaultMapper() {
        return new JsonMapper(Include.NON_DEFAULT);
    }

    /**
     * 转换extjs的form填充json
     * @param data
     * @return
     */
    public static Object toJsonFormResult(Object data){
        return new JsonForm(data);
    }

    /**
     * Object可以是POJO，也可以是Collection或数组。
     * 如果对象为Null, 返回"null".
     * 如果集合为空集合, 返回"[]".
     */
    public String toJson(Object object) {

        try {
            return mapper.writeValueAsString(object);
        } catch (IOException e) {
            logger.warn("write to json string error:" + object, e);
            return null;
        }
    }

    /**
     * 反序列化POJO或简单Collection如List<String>.
     *
     * 如果JSON字符串为Null或"null"字符串, 返回Null.
     * 如果JSON字符串为"[]", 返回空集合.
     *
     * 如需反序列化复杂Collection如List<MyBean>, 请使用fromJson(String, JavaType)
     *
     * @see #fromJson(String, JavaType)
     */
    public <T> T fromJson(String jsonString, Class<T> clazz) {
        if (StringUtils.isEmpty(jsonString)) {
            return null;
        }

        try {
            return mapper.readValue(jsonString, clazz);
        } catch (IOException e) {
            logger.warn("parse json string error:" + jsonString, e);
            return null;
        }
    }

    /**
     * 反序列化复杂Collection如List<Bean>, 先使用createCollectionType()或contructMapType()构造类型, 然后调用本函数.
     *
     * TODO @see #createCollectionType(Class, Class...)
     */
    @SuppressWarnings("unchecked")
    public <T> T fromJson(String jsonString, JavaType javaType) {
        if (StringUtils.isEmpty(jsonString)) {
            return null;
        }

        try {
            return (T) mapper.readValue(jsonString, javaType);
        } catch (IOException e) {
            logger.warn("parse json string error:" + jsonString, e);
            return null;
        }
    }

    /**
     * 构造Collection类型.
     */
    public JavaType contructCollectionType(Class<? extends Collection<?>> collectionClass, Class<?> elementClass) {
        return mapper.getTypeFactory().constructCollectionType(collectionClass, elementClass);
    }

    /**
     * 构造Map类型.
     */
    public JavaType contructMapType(Class<? extends Map<Object,Object>> mapClass, Class<?> keyClass, Class<?> valueClass) {
        return mapper.getTypeFactory().constructMapType(mapClass, keyClass, valueClass);
    }

    /**
     * 当JSON里只含有Bean的部分屬性時，更新一個已存在Bean，只覆蓋該部分的屬性.
     */
    public void update(String jsonString, Object object) {
        try {
            mapper.readerForUpdating(object).readValue(jsonString);
        } catch (JsonProcessingException e) {
            logger.warn("update json string:" + jsonString + " to object:" + object + " error.", e);
        } catch (IOException e) {
            logger.warn("update json string:" + jsonString + " to object:" + object + " error.", e);
        }
    }

    /**
     * 輸出JSONP格式數據.
     */
    public String toJsonP(String functionName, Object object) {
        return toJson(new JSONPObject(functionName, object));
    }

    /**
     * 設定是否使用Enum的toString函數來讀寫Enum,
     * 為False時時使用Enum的name()函數來讀寫Enum, 默認為False.
     * 注意本函數一定要在Mapper創建後, 所有的讀寫動作之前調用.
     */
    public void enableEnumUseToString() {
        mapper.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
        mapper.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
    }

    /**
     * 支持使用Jaxb的Annotation，使得POJO上的annotation不用与Jackson耦合。
     * 默认会先查找jaxb的annotation，如果找不到再找jackson的。
     */
    /*public void enableJaxbAnnotation() {
        JaxbAnnotationModule module = new JaxbAnnotationModule();
        mapper.registerModule(module);
    }*/

    /**
     * 取出Mapper做进一步的设置或使用其他序列化API.
     */
    public ObjectMapper getMapper() {
        return mapper;
    }
    /**
     * 一个类似与多重map结构的Object转换为Map
     * @param result Object
     * @return
     * @throws IllegalAccessException
     */
    public  Map<String, Object> objectToMap(Object result) throws IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        parseJSONTOMap(map,String.valueOf(result),null);
        return map;
    }
    public  void parseJSONTOMap(Map<String, Object> jsonMap,String jsonStr,String parentKey){
        //字符串转换成JSON对象
        JSONObject json = JSONObject.fromObject(jsonStr);
        //最外层JSON解析
        for(Object k : json.keySet()){
            //JSONObject 实际上相当于一个Map集合，所以我们可以通过Key值获取Value
            Object v = json.get(k);
            //构造一个包含上层keyName的完整keyName
            String fullKey = (null == parentKey || parentKey.trim().equals("") ? k.toString() : parentKey +"_" + k);

            if(v instanceof JSONArray){
                //如果内层还是数组的话，继续解析
                Iterator<?> it = ((JSONArray) v).iterator();
                while(it.hasNext()){
                    JSONObject json2 = (JSONObject)it.next();
                    parseJSONTOMap(jsonMap,json2.toString(),fullKey);
                }
            } else if(isNested(v)){
                parseJSONTOMap(jsonMap,v.toString(),fullKey);
            }
            else{
                jsonMap.put(fullKey, v);
            }
        }
    }

    public  boolean isNested(Object jsonObj){

        return jsonObj.toString().contains("{");
    }
}

class JsonForm {

    Boolean success;

    Object data;

    public JsonForm(Object data) {
        this.data = data;
        this.success = true;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
