package com.demo.spring.web.web.context;

import java.util.HashMap;
import java.util.Map;

/**
* 请求上下文信息
*/
public class RequestContext {

   /**
    * 请求名
    */
   private String requestName = null;

   /**
    * 业务属性
    */
   private Map<String, Object> propertyMap = new HashMap<String, Object>();

   /**
    * 获取请求名
    * 
    * @return 请求名
    */
   public String getRequestName() {
       return requestName;
   }

   /**
    * 设置请求名
    * 
    * @param requestName 请求名
    */
   public void setRequestName(String requestName) {
       this.requestName = requestName;
   }

   /**
    * 获取业务属性
    * 
    * @param key 业务属性key
    * @return 业务属性值
    */
   public Object getProperty(String key) {
       return propertyMap.get(key);
   }

   /**
    * 设置业务属性
    * 
    * @param key 业务属性key
    * @param value 业务属性值
    */
   public void setProperty(String key, Object value) {
       propertyMap.put(key, value);
   }

   public String getPropertyString(String key) {
       Object object = getProperty(key);

       if (object instanceof String) {
           return (String) object;
       }

       return null;
   }

   public void setPropertyString(String key, String value) {
       setProperty(key, value);
   }

   @Override
   public String toString() {
       StringBuilder sb = new StringBuilder();

       sb.append("requestName:");
       sb.append(requestName);
       sb.append(",");
       sb.append("properties:");
       sb.append(propertyMap.toString());

       return sb.toString();
   }
}