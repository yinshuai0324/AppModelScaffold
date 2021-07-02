package com.library.network.ssl

import okhttp3.OkHttpClient
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager

class SSLManager {

    companion object {

        /**
         * 获取Https OkHttpClient
         */
        fun getSSLSocketFactory(): OkHttpClient.Builder {
            val sc = SSLContext.getInstance("SSL")
            val httpX509TrustManager = HttpX509TrustManager()
            val trustAllCerts: Array<TrustManager> = arrayOf(httpX509TrustManager)
            sc.init(null, trustAllCerts, java.security.SecureRandom())
            val sslSocketFactory = sc.socketFactory
            val builder = OkHttpClient.Builder()
            builder.sslSocketFactory(sslSocketFactory, httpX509TrustManager)
            builder.hostnameVerifier { _, _ -> true }
            return builder
        }
    }
}