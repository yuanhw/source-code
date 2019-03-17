package org.scode.sioc.beans;

import org.scode.sioc.annotation.Component;

import java.io.File;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * 扫描类路径并且包装类信息为BeanDefinition集合
 * @author wyh
 * @date 2019/3/17 3:15 PM
 */
public class AnnotatedBeanDefinitionReader {

    private List<String> registryBeanClasses = new ArrayList<>();

    public void scan(String[] basePackages) {
        if (basePackages == null || basePackages.length == 0) {
            return;
        }
        for (String basePackage : basePackages) {
            // 包扫描
            doScan(basePackage);
        }
    }

    /**
     * 递归扫描包路径
     * @param basePackage
     */
    private void doScan(String basePackage) {
        URL url = this.getClass().getClassLoader().getResource(basePackage.replaceAll("\\.", File.separator));
        if (url == null) {
            return;
        }

        File root = new File(url.getFile());
        File[] files = root.listFiles();
        if (files == null) {
            return;
        }

        for (File childFile : files) {
            if (childFile.isDirectory()) {
                doScan(basePackage + "." + childFile.getName());
            } else if(childFile.getName().endsWith(".class")){
                // 保存扫描到的类的路径
                this.registryBeanClasses.add(basePackage + "." + childFile.getName().replaceAll("\\.class", ""));
            }
        }
    }

    /**
     * 返回所有扫描到的类路径
     * @return
     */
    public List<String> loadBeanDefinitions() {
        return registryBeanClasses;
    }

    public BeanDefinition registryBean(String className) {
        if (!this.registryBeanClasses.contains(className)) {
            return null;
        }

        BeanDefinition beanDefinition = new BeanDefinition();
        beanDefinition.setBeanClassName(className);
        beanDefinition.setBeanFactoryName(getLowerName(className.substring(className.lastIndexOf(".") + 1)));
        return beanDefinition;
    }

    private String getLowerName(String simpleClassName) {
        char[] chars = simpleClassName.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }

    /**
     * 是否受容器的管理
     * @param cls
     * @return
     */
    public boolean isCandidateComponent(Class<?> cls) {
        // 若是接口
        if (cls.isInterface()) {
            return false;
        }

        // 是否有标志被容器管理的注解
        return isCandidateAnnotation(cls);
    }

    /**
     * 递归的查看是否有@Component注解或者注解了它的注解
     * @param cls
     * @return
     */
    private boolean isCandidateAnnotation(Class<?> cls) {
        if (cls.isAnnotationPresent(Component.class)) {
            return true;
        }

        Annotation[] declaredAnnotations = cls.getDeclaredAnnotations();
        if (declaredAnnotations.length == 0) {
            return false;
        }

        for (Annotation declaredAnnotation : cls.getDeclaredAnnotations()) {
             if (isCandidateAnnotation(declaredAnnotation.annotationType())) {
                 return true;
             }
        }

        return false;
    }
}
