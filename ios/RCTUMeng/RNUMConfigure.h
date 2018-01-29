//
//  RNUMConfigure.h
//  UMComponent
//
//  Created by wyq.Cloudayc on 14/09/2017.
//  Copyright © 2017 Facebook. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface RNUMConfigure : NSObject

typedef NS_ENUM (NSUInteger, RNEScenarioType)
{
    RN_E_UM_NORMAL = 0,    // default value
    RN_E_UM_GAME   = 1,    // game
    RN_E_UM_DPLUS  = 4    // DPlus
};

+ (void)initWithAppkey:(NSString *)appkey channel:(NSString *)channel;

+ (void)setScenarioType:(RNEScenarioType)eSType;

@end
