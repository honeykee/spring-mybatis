package com.example.springmybatis.reflect;

import com.example.springmybatis.reflect.entity.MyEntity;
import com.example.springmybatis.reflect.vo.MyVO;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;



public class TestReflect {

    public static void main(String[] args) {
        MyEntity myEntity = new MyEntity(111,"zsc","honeykee",true,123.4d, (short) 12,new Date());
        System.out.println(myEntity.toString());

        Object object = entityToVo(MyEntity.class , myEntity, MyVO.class);
        System.out.println(object.toString());
    }


    //反射测试获取将一个对象的值赋值到另外一个对象，两个对象具有相同的属性以及getter和setter
    public static Object entityToVo(Class srcClass,Object srcObject,Class dstClass  ){    // srcObject-->dstObject
        try{
            Object bean = dstClass.newInstance();
            Field[] fields = dstClass.getDeclaredFields();
            for ( Field field : fields ) {
                String fieldName  = field.getName();
                Class types = field.getType();

                field.setAccessible(true);
                String methodName = fieldName.replaceFirst(fieldName.substring(0,1), fieldName.substring(0,1).toUpperCase() );
                Method setMethod = dstClass.getDeclaredMethod("set" + methodName ,types );
                Method getMethod = srcClass.getDeclaredMethod("get" + methodName ) ;

                Object entityValue =  getMethod.invoke(srcObject); //获取原始对象的值
                setMethod.invoke(bean,entityValue ); //为目标对象赋值
                System.out.println("get");

            }
            return bean ;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
