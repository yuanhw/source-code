package org.scode.m2.dto;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author: WangYuanHang
 * @Description:
 * @date: 2019/3/22 16:32
 */
@Data
@XmlRootElement
public class Customer implements Serializable {

    private static final long serialVersionUID = -7321150251277777942L;

    private Integer id;

    private String name;
}
