@file:Suppress("unused")

package me.jdvp.androidaspectexample.aspect

import android.content.Context
import android.net.ConnectivityManager
import android.widget.Toast
import me.jdvp.androidaspectexample.utility.Logger
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.After
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut


/**
 * Aspect file used to add internet checking in each activity
 */
@Aspect
class AspectInternetChecking {

    /**
     * Triggers when any onCreate function is present, which will actually trigger on each activity start
     */
    @Pointcut("execution(* me.jdvp.*.activity.*.*.onCreate(android.os.Bundle))")
    fun onCreate() {
    }

    /**
     * Runs the code in this method before the onCreate method gets called.
     */
    @After("onCreate()")
    fun onCreateAdvice(joinPoint: JoinPoint?) {

        /**
         * Get context before checking for internet
         */
        var context: Context? = null
        val args = joinPoint?.args
        if (args != null && args.isNotEmpty() && args[0] is Context) {
            context = args[0] as Context
        } else {
            val target = joinPoint?.target
            if (target is Context) {
                context = target
            }
        }

        /**
         * Call a function to check if there are internet available or not
         */
        if (!isConnected(context)) {
            Logger.logItem("System Internet checking -> Internet lost")
            Toast.makeText(context, "No internet connection available", Toast.LENGTH_SHORT).show()
        }


    }

    /**
     * Returns whether the device has an internet connection or not
     */
    private fun isConnected(context: Context?): Boolean {
        val connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

}