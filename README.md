[![](https://jitpack.io/v/Levin-Li/ServiceSupport.svg)](https://jitpack.io/#Levin-Li/ServiceSupport)


服务支持工具包

 jar包发布在 https://jitpack.io，请参考以下内容
    
    <repositories>

        <repository>
            <id>jitpack.io</id>
            <url>https://jitpack.io</url>
        </repository>

    </repositories>
    
      <dependency>
         <groupId>com.github.Levin-Li</groupId>
         <artifactId>service-support</artifactId>
         <version>1.1.9-SNAPSHOT</version> 
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
   
   