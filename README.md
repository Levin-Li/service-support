[![](https://jitpack.io/v/Levin-Li/service-support.svg)](https://jitpack.io/#Levin-Li/service-support)


服务支持工具包

 注意每次构建必须先执行 clean 操作

 jar包发布在 [https://jitpack.io](https://jitpack.io)，请参考以下内容
    
    <repositories>

        <repository>
            <id>jitpack.io</id>
            <url>https://jitpack.io</url>
        </repository>

    </repositories>
    
      <dependency>
         <groupId>com.github.Levin-Li</groupId>
         <artifactId>service-support</artifactId>
         <version>1.1.18-SNAPSHOT</version> 
      </dependency>


一、根据 JPA @Entity 注解 生成同包下 E_+类名 的类，主要是把字段名称常量化。
 
    实体类 Person.java
    
    @Entity
    class Person { 
      
      String name;
      
      int age;
      
    }
    
    生成的类 E_Person.java
     
    interface E_Person{
      public final String name ="name";
      public final String age ="age";
    }


二、提供通用的 Spring 业务注解扫描和应用的辅助工具，比如实现 MyBatis，Spring data等通过注解自动把接口实例化成 Bean，并注册到Spring IOC Context。

   1、业务注解
      
      @Target({ElementType.TYPE})
      @Retention(RetentionPolicy.RUNTIME)
      @Documented
      public @interface API {
      
      }
      

   2、方法拦截代理
      代理支持3种接口 
      AOP联盟            org.aopalliance.intercept.MethodInterceptor
      JDK动态代理         java.lang.reflect.InvocationHandler
      Spring下Cglib代理  org.springframework.cglib.proxy.MethodInterceptor
      
      //JDK动态代理         java.lang.reflect.InvocationHandler
      public class JdkProxyHandler implements InvocationHandler {
      
          @Autowired
          JpaDao jpaDao;
      
          @Override
          public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
              
              //业务逻辑...
              return " JdkProxyHandler invoke " +  methon.getName();
      
          }
      
      }
         
     //AOP联盟 org.aopalliance.intercept.MethodInterceptor     
     public class AopProxyHandler implements org.aopalliance.intercept.MethodInterceptor {
       
         @Override
         public Object invoke(MethodInvocation invocation) throws Throwable {
     
             //业务逻辑...
             return " AopProxyHandler " + getClass() + jpaDao;
     
         }
     
     }

     // Spring下Cglib代理  org.springframework.cglib.proxy.MethodInterceptor
     
     public class CglibProxyHandler implements org.springframework.cglib.proxy.MethodInterceptor {
      
         @Override
         public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
     
             //业务逻辑...
             return " CglibProxyHandler " + getClass() + jpaDao;
    
         }
     
     }


   3、应用注解
      
      @API
      public interface CustomerService {
      
        String findNameById(Long id);
      
        String renameById(Long id, String newName);
      
      }
   
     
     //应用 
      public cass CustomerController{
      
       // 直接注入 CustomerService
       @Autowired
       CustomerService customerService; 
      
       public String renameById(Long id, String newName){
       
          //  
          
          return customerService.renameById(id,newName)
          
       }
      
      }
   
   4、启用注解扫描配置
   
     //扫描有 API 注册的类，指定方法拦截的类 invocationHandlerClass = JdkProxyHandler.class
     @ProxyBeanScans({ 
             @ProxyBeanScan(scanType = API.class, invocationHandlerClass = JdkProxyHandler.class
                     , basePackages = {"com.levin."}) 
     })
     
     //启用代理Bean，自动把有 @API 注解的类或是接口，注册到 Spring IOC 容器中 
     @EnableProxyBean(registerTypes = {API.class})
 
     @Configuration
     public class TestConfiguration {
    
     }
   
   
   5、插件模块
   
   插件的实例 Bean 会被自动注册到 [PluginManager](./src/main/java/com/levin/commons/plugin/PluginManager.java)
   
   插件也可以通过实现配置接口手动注册到 [PluginManager]
   
   //插件注册接口
   public interface PluginConfigurer {
   
       /**
        * 配置插件
        *
        * @param pluginManager
        */
       void configPlugin(PluginManager pluginManager);
   
   }
   
   
   
     /**
      * 插件规范
      *
      * @author llw
      */
     public interface Plugin extends Identifiable<String> {
     
         /**
          * 插件类型
          * <p>
          * 如系统插件，应用插件
          *
          * @return
          */
         default String getType() {
             return "";
         }
     
         /**
          * 插件拥有的数据资源
          * <p>
          * 插件定义的资源不包含菜单
          *
          * <p>
          * <p>
          * 资源：比如地区资源，用户资源，部门资源，文档资源，栏目资源
          * 正常需要和权限模块结合处理
          * 资源通常是树形结构
          *
          * @return
          */
         default List<DataItem> getDataItems() {
             return Collections.EMPTY_LIST;
         }
     
         /**
          * 获取菜单项
          * <p>
          * 菜单的权限由权限管理模块处理
          *
          * @return
          */
         default List<MenuItem> getMenuItems() {
             return Collections.EMPTY_LIST;
         }
     
         /**
          * 插件实现该方法，接收发送给插件的事件
          *
          * @param events
          * @return 返回事件是否已经接受
          */
         boolean onEvent(Object... events);
     
         /**
          * 销毁插件
          */
         default void destroy() throws PluginException {
         }
     
     
     }
   