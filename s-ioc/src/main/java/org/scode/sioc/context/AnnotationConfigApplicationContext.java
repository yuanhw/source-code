package org.scode.sioc.context;

import org.scode.sioc.beans.AnnotatedBeanDefinitionReader;
import org.scode.sioc.beans.BeanDefinition;

import java.util.List;

/**
 * @author wyh
 * @date 2019/3/17 2:37 PM
 */
public class AnnotationConfigApplicationContext extends AbstractApplicationContext {

    private final AnnotatedBeanDefinitionReader reader;

    public AnnotationConfigApplicationContext() {
        this.reader = new AnnotatedBeanDefinitionReader();
    }

    public AnnotationConfigApplicationContext(String... basePackages) {
        this();
        scan(basePackages);
        refresh();
    }

    private void scan(String[] basePackages) {
        if (basePackages == null || basePackages.length == 0) {
            return;
        }

        this.reader.scan(basePackages);
    }

    /**
     * IOC核心实现方法
     */
    private void refresh() {
        // 加载扫描到的类路径集合
        List<String> beanDefinitions = this.reader.loadBeanDefinitions();

        // 进行注册BeanDefinition
        doRegistry(beanDefinitions);
    }

    /**
     * 注册BeanDefinition到Map中
     * @param beanDefinitions
     */
    private void doRegistry(List<String> beanDefinitions) {
        // 注册bean名称三种情况：
        // 1. 默认是类名首字母小写
        // 2. 自定义名称
        // 3. 接口名称
        try {
            for (String className : beanDefinitions) {
                Class<?> cls = Class.forName(className);

                if(!this.reader.isCandidateComponent(cls)) {
                    continue;
                }

                BeanDefinition beanDefinition = this.reader.registryBean(className);
                if (beanDefinition == null) {
                    continue;
                }

                this.beanDefinitionMap.put(beanDefinition.getBeanFactoryName(), beanDefinition);

                Class<?>[] interfaces = cls.getInterfaces();
                if (interfaces == null) {
                    continue;
                }
                for (Class<?> anInterface : interfaces) {
                    this.beanDefinitionMap.put(anInterface.getName(), beanDefinition);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
