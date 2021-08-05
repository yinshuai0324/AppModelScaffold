package com.library.router

/**
 * 创建者：yinshuai
 * 创建时间：2021/7/16 08:52
 * 作用描述：路由页面管理
 */
class RouterPath {
    companion object {

        /***********分组***********/

        /**
         * 组-开屏页
         */
        const val GROUP_SPLASH = "splash"

        /**
         * 组-首页
         */
        const val GROUP_HOME = "home"

        /**
         * 组-登录页面
         */
        const val GROUP_LOGIN = "login"


        /***********页面***********/

        /**
         * 首页模块主页
         */
        const val PAGE_HOME_MAIN_ACTIVITY = "/model/HomeMainActivity"

        /**
         * 登录页面
         */
        const val PAGE_LOGIN_ACTIVITY = "/login/LoginActivity"


        /***********服务***********/

        /**
         * 登录模块对外提供的服务
         */
        const val SERVICE_LOGIN = "/service/LoginServiceImp"
    }
}