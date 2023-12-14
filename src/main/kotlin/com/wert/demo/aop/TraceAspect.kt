package com.wert.demo.aop

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.util.StopWatch


@Aspect
@Component
class TraceAspect {
    private val log: Logger = LoggerFactory.getLogger(this.javaClass.name)

    @Around("@annotation(com.wert.demo.aop.TraceTest)")
    fun doTrace(proceedingJoinPoint: ProceedingJoinPoint): Any? {
        val stopWatch = StopWatch()
        log.info("[@Trace Before] {}", proceedingJoinPoint.signature)
        stopWatch.start()
        val proceed = proceedingJoinPoint.proceed()
        stopWatch.stop()
        log.info("[@Trace After] {}, time = {}", proceedingJoinPoint.signature, stopWatch.shortSummary())
        return proceed
    }
}
