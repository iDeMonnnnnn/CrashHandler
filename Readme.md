
[![](https://jitpack.io/v/DeMonLiu623/CrashHandler.svg)](https://jitpack.io/#DeMonLiu623/CrashHandler)

### ErrorCatch
**一个基于Thread.UncaughtExceptionHandler实现手机Android程序崩溃异常信息的库。**

本库的具体实现，可以参考：

 
### 使用

#### 工程的build.gradle

```
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```

#### Module的build.gradle


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
1. 为方便查看，收集的信息存储到txt文件中，然后默认保存在sd卡根目录/你的app_name/Crash/ (如：/storage/emulated/0/ErrorCatch/Crash/2018-09-21 09:42:59.text)
2. CrashHandler.getCrashReportFiles(Context ctx)方法可以返回所有的错误信息文件路径，可以根据文件路径上传到服务器，然后将其删除，防止重复上传。

### BUG or 问题
请E-mail：757454343@qq.com 联系我。

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