# react-native-umeng
`react-native-umeng`是基于友盟统计的react-native插件

#安装
> npm install https://github.com/fenglu09/react-native-umeng.git --save

> react-native link react-native-umeng

# 配置
## Android
1. 在`MainApplication.java`中初始化
```java
  @Override
  public void onCreate() {
    super.onCreate();
    SoLoader.init(this, /* native exopackage */ false);
    // 友盟统计初始化
    RNUMConfigure.init(this, UMENG_APP_KEY, "Umeng", UMConfigure.DEVICE_TYPE_PHONE, "");
  }
```

参数说明： RNUMConfture.init接口一共五个参数，其中第一个参数为Context，第二个参数为友盟Appkey，第三个参数为channel，第四个参数为应用类型（手机或平板），第五个参数为push的secret（如果没有使用push，可以为空）。

2. 在MainActivity中初始化
```java
@Override
public void onResume() {
    super.onResume();
    MobclickAgent.onResume(this);
}
@Override
protected void onPause() {
    super.onPause();
    MobclickAgent.onPause(this);
}
```
并且在onCreate中设置统计场景，以及发送的间隔
```java
MobclickAgent.setSessionContinueMillis(1000);
MobclickAgent.setScenarioType(this, EScenarioType.E_DUM_NORMAL);
```

## iOS

1. 在`Appdelegate.m`中添加初始化代码
```
#import "RNUMConfigure.h"

- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions
{
  [UMConfigure setLogEnabled:YES];
  [RNUMConfigure initWithAppkey:@"599d6d81c62dca07c5001db6" channel:@"App Store"];
  ...
}
```

2. 添加Framework的路径
`Build Settings` 的`Framework Search Paths`中添加`$(SRCROOT)/../node_modules/react-native-umeng/ios/RCTUMeng/Frameworks`
在`Build Settings ->Link -> Other Linker Flags`中加入友盟导入的包 
`-framework "UMAnalytics"、-framework "UMCommon"、-framework "UTDID"`
