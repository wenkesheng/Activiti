/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.activiti.engine.impl.cfg;

import java.io.InputStream;

import org.activiti.engine.ProcessEngineConfiguration;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;


/**
 * @author Tom Baeyens
 */
public class BeansConfigurationHelper {

  public static ProcessEngineConfiguration parseProcessEngineConfiguration(Resource springResource, String beanName) {
    //实例化Spring框架中的DefaultListableBeanFactory类
    DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
    XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
    //设置验证模式为XSD,当然也支持DTD方式验证
    xmlBeanDefinitionReader.setValidationMode(XmlBeanDefinitionReader.VALIDATION_XSD);
    //加载读取到的springResource资源并交给Spring进行解析
    xmlBeanDefinitionReader.loadBeanDefinitions(springResource);
    //通过beanFactory对象获取ProcessEngineConfigurationImpl实例对象
    ProcessEngineConfigurationImpl processEngineConfiguration = (ProcessEngineConfigurationImpl) beanFactory.getBean(beanName);
    //将beanFactory对象使用SpringBeanFactoryProxyMap进行封装
    processEngineConfiguration.setBeans(new SpringBeanFactoryProxyMap(beanFactory));
    return processEngineConfiguration;
  }

  public static ProcessEngineConfiguration parseProcessEngineConfigurationFromInputStream(InputStream inputStream, String beanName) {
    //将inputStream对象转化为Spring中的Resource对象
    Resource springResource = new InputStreamResource(inputStream);
    //实例化流程配置文件中的Bean
    return parseProcessEngineConfiguration(springResource, beanName);
  }

  public static ProcessEngineConfiguration parseProcessEngineConfigurationFromResource(String resource, String beanName) {
    Resource springResource = new ClassPathResource(resource);
    return parseProcessEngineConfiguration(springResource, beanName);
  }

}
