package org.scode.sioc.context;

import org.scode.sioc.beans.BeanDefinition;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wyh
 * @date 2019/3/17 2:52 PM
 */
public abstract class AbstractApplicationContext implements ApplicationContext {

    protected final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>(32);

    @Override
    public Object getBean(String beanName) {
        return this.beanDefinitionMap.get(beanName);
    }
}
