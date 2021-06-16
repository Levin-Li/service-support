package com.levin.commons.service.support;

import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.utils.MapUtils;
import lombok.Data;
import lombok.experimental.Accessors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;


class SimpleVariableInjectorTest {

    @Data
    @Accessors(chain = true)
    class Org {

        String id;

        String name;

        String idPath;

        Org parent;

        public String getIdPath() {

            if (idPath == null) {

                idPath = "";

                Org tempParent = parent;

                while (tempParent != null) {
                    idPath += "." + tempParent.id;
                    tempParent = tempParent.parent;
                }

                idPath = "." + id;

            }

            return idPath;
        }

    }

    @Data
    @Accessors(chain = true)
    class User {

        boolean isAdmin = false;

        String id;

        String name;

        Org org;

    }

    @Data
    class Dto {


        @InjectVar(InjectConsts.ORG)
        Object org;

        @InjectVar(InjectConsts.USER)
        Object user;

        @InjectVar(InjectVar.GROOVY_PREFIX + InjectConsts.USER_ID)
        String userId;

        @InjectVar(InjectVar.GROOVY_PREFIX + InjectConsts.ORG_ID)
        String orgId;

        /**
         * 想要操作的目标组织 ID
         * 这个值必须存在
         * <p>
         * 可以本部门，也可以是下级部门，其它部门不允许
         * <p>
         * 如果是超级管理员，可以操作任意部门
         */
        @InjectVar(value = InjectVar.SPEL_PREFIX + "#" + InjectConsts.ORG_ID
                , isOverride = InjectVar.SPEL_PREFIX + "!#user.isAdmin" // 如果不是超级管理员
        )
        String targetOrgId;


        /**
         * 想要统计目标组织 ID
         * <p>
         * 可以本部门，也可以是下级部门
         * <p>
         * 如果是超级管理员，可以是任意部门，或是不指定，统计全部的部门
         */
        @InjectVar(value = InjectVar.GROOVY_PREFIX + InjectConsts.ORG_ID
                , isOverride = InjectVar.SPEL_PREFIX + "!#user.isAdmin" // 如果不是超级管理员
                , isRequired = InjectVar.SPEL_PREFIX + "!#user.isAdmin" // 如果不是超级管理员，那么值是必须的
        )
        String statTargetOrgId;


        @InjectVar(isRequired = InjectVar.SPEL_PREFIX + "false")
        String ipAddr;

    }

    Org 公司 = new Org().setId("1").setName("XX科技有限公司");

    Org 研发部 = new Org().setId("2").setParent(公司).setName("研发部");
    Org 研发一部 = new Org().setId("3").setParent(研发部).setName("研发1部");
    Org 研发二部 = new Org().setId("4").setParent(研发部).setName("研发2部");

    Org 销售部 = new Org().setId("5").setParent(公司).setName("销售部");

    /////////////////////////////////////////////////////////////////////////////////////

    User 超级管理员 = new User().setAdmin(true).setId("1").setName("超级管理员").setOrg(公司);

    User 普通管理员 = new User().setAdmin(false).setId("2").setName("管理员").setOrg(公司);

    User 张三 = new User().setId("2").setName("张三（程序员）").setOrg(研发部);

    User 李四 = new User().setId("2").setName("李四（销售）").setOrg(销售部);

    ////////////////////////////////////////////////////////////////////////////////////////

    @Test
    void getVariableResolvers() {

        getVariableResolversByUser(张三);

        getVariableResolversByUser(普通管理员);

        getVariableResolversByUser(超级管理员);


    }

    void getVariableResolversByUser(User user) {

        Map<String, Object> ctx = MapUtils
                .putFirst(InjectConsts.USER, user)
                .put(InjectConsts.ORG, user.org)
                .put(InjectConsts.IP_ADDR, "192.168.0.1")
                .build();

        SimpleVariableInjector injector = new SimpleVariableInjector() {
        };


        Dto dto = new Dto();

        dto.setTargetOrgId("9999");
        dto.setStatTargetOrgId("8888");

        injector.inject(dto, ctx);

        Assertions.assertEquals(dto.org, user.org);

        Assertions.assertEquals(dto.orgId, user.org.id);

        Assertions.assertEquals(dto.user, user);
        Assertions.assertEquals(dto.userId, user.id);

        if (!user.isAdmin) {
            Assertions.assertEquals(dto.targetOrgId, user.org.id);
            Assertions.assertEquals(dto.statTargetOrgId, user.org.id);
        } else {
            Assertions.assertEquals(dto.targetOrgId, "9999");
            Assertions.assertEquals(dto.statTargetOrgId, "8888");
        }

        System.out.println("--------ipaddr----------");

        Assertions.assertEquals(dto.getIpAddr(), ctx.getOrDefault(InjectConsts.IP_ADDR, null));


        System.out.println("------------------");
    }

    @Test
    void getSimpleVariableResolver() {
    }

    @Test
    void getSpelVariableResolver() {

    }

    @Test
    void getGroovyVariableResolver() {
    }

    @Test
    void inject() {

        // SimpleVariableInjector.defaultSimpleVariableInjector.

    }

    @Test
    void injectByVariableResolver() {
    }


}