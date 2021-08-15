![](https://img.shields.io/badge/platform-Android-yellow.svg) ![](https://img.shields.io/badge/license-MIT-red) ![](https://img.shields.io/badge/version-v0.1.1-orange) ![](https://img.shields.io/badge/language-kotlin-brightgreen) ![](https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat) 

## 项目简介
    提供一套以组件化开发的脚手架，能够快速进入开发


## 项目背景

    基于Jetpack组件，并且使用Kotlin语言进行开发  
    目的是为了快速进行开发，拉下代码基于本项目就能直接开发。  
    本项目使用了大量的Kotlin特性和语法，简化了大量的代码。
    并且封装了一些常用的的功能。能够开箱即用，快速开发
    
## 项目运行环境

    桌面系统：MacOS、Windows、Linux...
    AndroidStudio版本：建议4.1.0+
    JDK版本：1.8.0
    Gradle版本：6.7.1
    支持Android版本：api21+
 

## 项目架构图
    
![](https://github.com/yinshuai0324/AppModelScaffold/blob/main/docs/项目架构图.png)
    
    项目架构图持续完善中...
    
## 项目结构图

![](https://github.com/yinshuai0324/AppModelScaffold/blob/main/docs/项目结构图.png)
    
## 项目功能

- [x] 组件化
- [x] 模块可独立运行也可整个打包运行
- [x] 网络请求集成封装
- [x] 支持多个Host切换
- [x] 路由集成封装
- [x] Base基类封装
- [x] Fragment和Activity的默认缺省页
- [ ] 日志工具封装
- [ ] 自定义UI库
- [ ] 热更新
- [ ] 更多功能持续更新中...


## 使用文档

  使用前务必读一遍此文档
  
#### 配置部分
    配置文件在项目根目录的app-config.gradle文件里面配置相关配置,基本上基础配置都已经涵盖了.
    然后如果遇到编译报错的问题可以查阅[问题自查文档.md]文档看看有没有记录此问题
    
#### 新建业务模块
    第一步:在business文件夹下新建一个AndroidModel
    第二部:修改Model的build.gradle文件,引入根目录的公共配置app-model-build.gradle
    第三步:在model下的main文件夹内新建alone文件夹,把复制一份清单文件放在里面,这个是独立运行时才会用到
    第四步:在业务模块下新建application包,在里面创建此模块的application类,用于model的SDK初始化
    第五步:在ModelApplicationManage里面注册刚刚创建好的application类,宿主App会自动初始化model的application
    第六步:在宿主app的build.gradle文件里面依赖新建的Mdoel模块
    

## 文档持续更新中...