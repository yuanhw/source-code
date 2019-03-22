package org.scode.m2;

import com.thoughtworks.xstream.io.xml.StaxDriver;
import org.junit.Test;
import org.scode.m2.dto.Customer;
import org.scode.m2.entity.User;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Date;

/**
 * @author: WangYuanHang
 * @Description:
 * @date: 2019/3/22 14:21
 */
public class UserControllerTest {

    @Test
    public void testUserInfo() {
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.add("id", "1");
        form.add("name", "ddd");
        form.add("age", "123");
        restTemplate.postForLocation("http://localhost:8081/getHandler41", form);
    }

    @Test
    public void testGetImgTest() {
        RestTemplate restTemplate = new RestTemplate();
        byte[] res = restTemplate.getForObject("http://localhost:8081/getImg", byte[].class);
        System.out.println(res.length);
    }

    @Test
    public void testMatrix() {
        RestTemplate restTemplate = new RestTemplate();
        String res = restTemplate.getForObject("http://localhost:8081/matrix/{id};id=1;name=wyh", String.class, 123);
        System.out.println(res);
    }

    @Test
    public void testXmlInfo() {
        RestTemplate restTemplate = createRestTemplate();

        User user = new User(1, "wyh", 24, 12345, new Date(), new Date());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.valueOf("application/xml;UTF-8"));
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_XML));

        HttpEntity<User> httpEntity = new HttpEntity<>(user, httpHeaders);
        ResponseEntity<User> res = restTemplate.exchange("http://localhost:8081/xmlInfo", HttpMethod.POST, httpEntity, User.class);
        System.out.println(res.getBody());
    }

    private RestTemplate createRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();

        // xml转换
        MarshallingHttpMessageConverter converter = new MarshallingHttpMessageConverter();

        // XStream流化器，使用stax技术处理xml
        XStreamMarshaller xStreamMarshaller = new XStreamMarshaller();
        xStreamMarshaller.setStreamDriver(new StaxDriver());
        xStreamMarshaller.setAnnotatedClasses(Customer.class);

        converter.setMarshaller(xStreamMarshaller);
        converter.setUnmarshaller(xStreamMarshaller);

        // json转换
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();

        restTemplate.getMessageConverters().add(converter);
        restTemplate.getMessageConverters().add(jsonConverter);

        return restTemplate;
    }
}
