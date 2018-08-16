
[![](https://jitpack.io/v/DeMonLiu623/CrashHandler.svg)](https://jitpack.io/#DeMonLiu623/CrashHandler)

### 使用

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