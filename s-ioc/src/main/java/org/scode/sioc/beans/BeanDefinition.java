package org.scode.sioc.beans;

import lombok.Data;

/**
 * @author wyh
 * @date 2019/3/17 3:11 PM
 */
@Data
public class BeanDefinition {
    /**
     * 交给容器管理的类的全名称
     */
    private String beanClassName;

    /**
     * 别名：1自定义，2类的首字母小写，3接口类型
     */
    private String beanFactoryName;
}
