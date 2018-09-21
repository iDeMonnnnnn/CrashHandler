
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

### BUG or 问题
请E-mail：757454343@qq.com 联系我。