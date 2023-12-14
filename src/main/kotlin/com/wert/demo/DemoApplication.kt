package com.wert.demo

import com.wert.demo.properties.TranslateProperties
import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.FilterConfig
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.annotation.WebFilter
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.context.annotation.EnableAspectJAutoProxy
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView
import java.lang.Exception

//@EnableAspectJAutoProxy
@SpringBootApplication
@EnableConfigurationProperties(TranslateProperties::class)
class DemoApplication

fun main(args: Array<String>) {
	runApplication<DemoApplication>(*args)
}


@WebFilter(urlPatterns = ["/hello/*"])
class TestFilter: Filter {
	private val log: Logger = LoggerFactory.getLogger(this.javaClass.name)

	override fun init(filterConfig: FilterConfig?) {
		log.info("[TestFilter] init")
	}

	override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
		log.info("[TestFilter] doFilter")
		chain?.doFilter(request, response)
	}

	override fun destroy() {
		log.info("[TestFilter] destory")
	}
}


class TestInterceptor: HandlerInterceptor {
	private val log: Logger = LoggerFactory.getLogger(this.javaClass.name)

	override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
		log.info("[TestInterceptor] preHandle")
		return super.preHandle(request, response, handler)
	}

	override fun postHandle(
		request: HttpServletRequest,
		response: HttpServletResponse,
		handler: Any,
		modelAndView: ModelAndView?
	) {
		log.info("[TestInterceptor] postHandle")
		super.postHandle(request, response, handler, modelAndView)
	}

	override fun afterCompletion(
		request: HttpServletRequest,
		response: HttpServletResponse,
		handler: Any,
		ex: Exception?
	) {
		log.info("[TestInterceptor] afterCompletion")
		super.afterCompletion(request, response, handler, ex)
	}
}
