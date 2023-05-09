package me.jdvp.androidaspectexample.aspect

import android.app.Application
import android.content.Context
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.gson.Gson
import me.jdvp.androidaspectexample.R
import me.jdvp.androidaspectexample.activity.account.Login
import me.jdvp.androidaspectexample.activity.account.Register
import me.jdvp.androidaspectexample.model.error.ErrorResponse
import me.jdvp.androidaspectexample.model.user.CreateUserResponse
import me.jdvp.androidaspectexample.utility.Logger
import okhttp3.ResponseBody
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.*
import retrofit2.Call
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException


/**
 * Aspect file used to add logging logic at compile time to common actions
 */
@Aspect
class AspectErrorHandling {

    // Define a new pointcut that matches all methods that have a "throws" clause
    @Pointcut("execution(void *catch(..))")
    fun methodsThatThrowException() {}

    // Add a "before" advice to the new pointcut that logs the exception
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


