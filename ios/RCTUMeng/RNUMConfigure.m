//
//  RNUMConfigure.m
//  UMComponent
//
//  Created by wyq.Cloudayc on 14/09/2017.
//  Copyright © 2017 Facebook. All rights reserved.
//

#import <UMCommon/UMCommon.h>
#import <UMCommon/MobClick.h>
#import "RNUMConfigure.h"


//typedef NS_ENUM (NSUInteger, RNUMEScenarioType)
//{
//    NORMAL = 0,    // default value
//    GAME   = 1,    // game
//    DPLUS  = 4    // DPlus
//};

@implementation RNUMConfigure

+ (void)initWithAppkey:(NSString *)appkey channel:(NSString *)channel
{
    SEL sel = NSSelectorFromString(@"setWraperType:wrapperVersion:");
    if ([UMConfigure respondsToSelector:sel]) {
        [UMConfigure performSelector:sel withObject:@"react-native" withObject:@"1.0"];
    }
    [UMConfigure initWithAppkey:appkey channel:channel];
    [UMConfigure setLogEnabled:YES];
    // 设置加密
    [UMConfigure setEncryptEnabled:YES];
}

// 设置场景
//+ (void)setScenarioType:(RNEScenarioType)eSType
//{
//     // 新版统计中，没有集成游戏的情况，只有 E_UM_NORMAL 这个场景
//    [MobClick setScenarioType: E_UM_NORMAL];
//}
@end
