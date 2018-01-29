//
//  RNUMConfigure.m
//  UMComponent
//
//  Created by wyq.Cloudayc on 14/09/2017.
//  Copyright © 2017 Facebook. All rights reserved.
//

#import <UMCommon/UMCommon.h>
#import <UMAnalytics/MobClick.h>
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
+ (void)setScenarioType:(RNEScenarioType)eSType
{
    if (eSType == RN_E_UM_GAME) {
        [MobClick setScenarioType: E_UM_GAME];
    } else if (eSType == RN_E_UM_DPLUS) {
        [MobClick setScenarioType: E_UM_DPLUS];
    } else {
        [MobClick setScenarioType: E_UM_NORMAL];
    }
}
@end
