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
package org.activiti.engine;

/**
 * Interface implemented by all classes that expose the Activiti services.
 * 
 * @author Joram Barrez 
 */
public interface EngineServices {//这个接口设计的多余,ProcessEngine继承这个接口之后,只是新增了获取版本号,流程引擎名称和关闭
  // 所以在flowable已经把这个接口中定义的方法全部迁移到ProcessEngine里面去定义然后把这个EngineServices接口删掉了

  RepositoryService getRepositoryService();
  
  RuntimeService getRuntimeService();
  
  FormService getFormService();
  
  TaskService getTaskService();
  
  HistoryService getHistoryService();
  
  IdentityService getIdentityService();
  
  ManagementService getManagementService();
  
}
