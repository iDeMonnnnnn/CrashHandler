
[![](https://jitpack.io/v/DeMonLiu623/CrashHandler.svg)](https://jitpack.io/#DeMonLiu623/CrashHandler)

### CrashHandler
**一个基于Thread.UncaughtExceptionHandler实现收集Android程序崩溃异常信息的库。**

本库的具体实现，可以参考：[Android 收集程序崩溃异常信息](https://blog.csdn.net/DeMonliuhui/article/details/82798484)

### 使用

#### 工程的build.gradle

[latest_version](https://github.com/iDeMonnnnnn/CrashHandler/releases)

```
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```

#### Module的build.gradle

```
dependencies {
	        implementation 'com.github.DeMonLiu623:CrashHandler:$latest_version'
	}
```

#### ~~权限~~

~~需要读取设备内容的存储权限。~~

>兼容AndroidQ作用域存储后，不再需要存储权限。

#### 初始化
初始化后每次程序崩溃发生便会自动收集错误信息，并保存下来。

```java
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
         // 注册crashHandler
        // 异常处理，不需要处理时注释掉这两句即可！
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());
    }
}
```
#### 错误信息处理
1. 为方便查看，收集的信息存储到txt文件中，然后默认保存在作用域files/Crash/目录下
(如：```/storage/emulated/0/Android/data/com.demon.errorcatch/files/Crash/2022-01-11 18:09:15.log```)
2. 调用```CrashHandler.getCrashReportFiles(Context ctx)```方法可以返回所有的错误信息文件路径，你可以根据该文件路径，自己写方法将文件内容上传到指定的服务器，然后将上传成功的文件删除，防止下次调用重复上传。（**本框架没有自动上传的功能，需要自己处理！！！**）


#### 效果体验
[demon.apk](https://github.com/iDeMonnnnnn/CrashHandler/raw/master/demon.apk)

![]()![]()


### BUG or 问题
请在issues留言，定期回复。

### MIT License

```
Copyright (c) 2018 DeMon

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
