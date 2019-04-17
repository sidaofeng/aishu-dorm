package com.waken.dorm.common.utils;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import org.dozer.DozerBeanMapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.TypeMappingOption;
import org.dozer.loader.api.TypeMappingOptions;
/**
 * @ClassName DozerMapper
 * @Description DozerMapper
 * @Author zhaoRong
 * @Date 2019/3/26 11:00
 **/
public class DozerMapper {
    private static DozerBeanMapper dozer = new DozerBeanMapper();

    public DozerMapper() {
    }

    public static DozerBeanMapper getMapper(final Class<?> s, final Class<?> d) {
        DozerBeanMapper mapper = new DozerBeanMapper();
        mapper.addMapping(new BeanMappingBuilder() {
            protected void configure() {
                this.mapping(s, d, new TypeMappingOption[]{TypeMappingOptions.mapNull(false), TypeMappingOptions.mapEmptyString(false), TypeMappingOptions.trimStrings(true)});
            }
        });
        return mapper;
    }

    public static <T> T map(Object source, Class<T> destinationClass) {
        return dozer.map(source, destinationClass);
    }

    public static <T> CopyOnWriteArrayList<T> mapList(Collection<?> sourceList, Class<T> destinationClass) {
        CopyOnWriteArrayList<T> destinationList = new CopyOnWriteArrayList<T>();
        Iterator<?> i$ = sourceList.iterator();

        while(i$.hasNext()) {
            Object sourceObject = i$.next();
            T destinationObject = dozer.map(sourceObject, destinationClass);
            destinationList.add(destinationObject);
        }

        return destinationList;
    }

    public static void copy(Object source, Object destinationObject) {
        dozer.map(source, destinationObject);
    }
}
