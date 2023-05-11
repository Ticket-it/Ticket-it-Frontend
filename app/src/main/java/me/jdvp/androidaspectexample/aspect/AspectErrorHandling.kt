package me.jdvp.androidaspectexample.aspect

import android.content.Context
import android.widget.Toast
import me.jdvp.androidaspectexample.utility.Logger
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.*


/**
 * Aspect file used to handle all errors
 */
@Aspect
class AspectErrorHandling {

    /**
     * Triggers when we have catch statement
     */
    @Pointcut("execution(void *catch(..))")
    fun methodsThatThrowException() {}

    /**
     * Advice of the corresponding pointcut
     */
    @Before("methodsThatThrowException()")
    fun logException(joinPoint: JoinPoint) {
        // Get the exception object from the join point's arguments array
        val exception = joinPoint.args.lastOrNull { it is Exception } as? Exception

        // Log the exception message if it is not null
        exception?.message?.let { message ->
            Logger.logItem("System Logging -> Exception caught: $message")
            Toast.makeText((joinPoint.target as? Context), message, Toast.LENGTH_SHORT).show()
        }
    }
}


