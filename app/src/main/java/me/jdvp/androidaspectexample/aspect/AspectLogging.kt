@file:Suppress("unused")

package me.jdvp.androidaspectexample.aspect

import android.view.View
import android.widget.EditText
import android.widget.TextView
import me.jdvp.androidaspectexample.R
import me.jdvp.androidaspectexample.activity.account.Login
import me.jdvp.androidaspectexample.activity.account.Register
import me.jdvp.androidaspectexample.utility.Logger
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Pointcut


/**
 * Aspect file used to add logging logic at compile time to common actions
 */
@Aspect
class AspectLogging {
    /**
     * Indicates that the execution of the onCreate method with a single Bundle argument is
     * called from any class in my Activity package
     */
    @Pointcut("execution(* me.jdvp.*.activity.*.*.onCreate(android.os.Bundle))")
    fun onCreate() {
    }

    /**
     * Runs the code in this method before the onCreate method gets called.
     *
     * Note that the value in the annotation is the same as the pointcut in this file that I want to use
     */
    @Before("onCreate()")
    fun onCreateAdvice(joinPoint: JoinPoint?) {
        if (joinPoint?.getThis() != null) {
            Logger.logItem("System Logging -> onCreate called in " + joinPoint.getThis().javaClass.simpleName)
        }
    }

    /**
     * Captures the execution of any method named onClick in any class that has a return type of void.
     */
    @Pointcut("execution(void *.onClick(..))")
    fun onButtonClick() {
    }

    /**
     * Runs the code in this method before the onCreate method gets called.
     *
     * The value in the annotation mentions both the pointcut to use and the argument that I want to capture.
     */
    @Before("onButtonClick() && args(view)")
    fun onClickAdvice(view: View?) {
        val activityName = view?.context?.javaClass?.simpleName
        val buttonName = if (view is TextView) view.text else view?.javaClass?.simpleName

        if (activityName != null && buttonName != null) {
            val logMessage = "System Logging -> Button $buttonName is clicked in activity $activityName"
            Logger.logItem(logMessage)
        }

    }

    @Pointcut("execution(void *signIn(..))")
    fun onClickLoginPointcut() {

    }

    @Before("onClickLoginPointcut()")
    fun captureCredentialsBeforeLogin(joinPoint: JoinPoint) {
        val emailEditText = (joinPoint.target as Login).findViewById<EditText>(R.id.email_tv)
        val email = emailEditText.text.toString()
        Logger.logItem("System Logging -> Email: $email is trying to Log in")
    }

    @Pointcut("execution(void *signUp(..))")
    fun onClicksignupPointcut() {

    }

    @Before("onClicksignupPointcut()")
    fun captureNewUserDetails(joinPoint: JoinPoint) {
        val emailEditText = (joinPoint.target as Register).findViewById<EditText>(R.id.email_tv)
        val email = emailEditText.text.toString()
        Logger.logItem("System Logging -> Email: $email is trying to sign up")
    }

}