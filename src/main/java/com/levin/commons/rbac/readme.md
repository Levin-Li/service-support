### RBAC 核心概念

1、资源对象 Res

    资源有归属的域，类型，资源标识，资源操作列表

2、资源操作 Res.Action

    通常是资源类型对应的操作

3、资源许可 Permission

    许可对特定资源的操作许可，核心要素：资源域、资源类型，资源，操作

4、角色对象 RoleObject

    角色 关联 许可 Permission 列表

5、用户对象 UserObject

    关联 角色 RoleObject 列表

6、权限检查注解 ResAuthorize

    注解加在类或方法上面，可以拦截方法校验
    注意，可以设置空格覆盖类的定义

7、菜单对象 MenuItem

    把菜单和权限分离，菜单需要设定要求的权限。

### 应用

在控制器类上 加入注解 ResAuthorize

注意，可以设置空格覆盖类定义
