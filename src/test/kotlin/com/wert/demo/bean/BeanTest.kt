package com.wert.demo.bean

import com.wert.demo.Config
import com.wert.demo.TestBean
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


class BeanTest {

    @Test
    fun beanLifeCycle() {
        val ac = AnnotationConfigApplicationContext(Config::class.java)
        val bean = ac.getBean(TestBean::class.java)

        ac.close()
        Assertions.assertThat(bean.message).isEqualTo("빈 수동 등록 테스트")
    }

    @Test
    fun name() {
        val ac = AnnotationConfigApplicationContext(Config::class.java)
        val beanDefinitionNames: Array<String> = ac.getBeanDefinitionNames()
        for (beanDefinitionName in beanDefinitionNames) {
            val beanDefinition: BeanDefinition = ac.getBeanDefinition(beanDefinitionName)
            //  ROLE_APPLICATION : 직접 등록한 애플리케이션 빈
            //  ROLE_INFRASTRUCTURE : 스프링이 내부에서 사용하는 빈
            if (beanDefinition.role == BeanDefinition.ROLE_INFRASTRUCTURE) {
                val bean: Any = ac.getBean(beanDefinitionName)
                println("bean = $beanDefinitionName object = $bean")
            }
        }
    }
}
