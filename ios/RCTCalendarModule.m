//
//  RCTCalendarModule.m
//  RNNativeModules
//
//  Created by  Volodymyr Borysov on 28/03/2024.
//

#import "RCTCalendarModule.h"
#import <React/RCTLog.h>

@implementation RCTCalendarModule

RCT_EXPORT_MODULE();

-(NSDictionary *)constantsToExport
{
 return @{ @"DEFAULT_EVENT_NAME": @"New Event" };
}

RCT_EXPORT_METHOD(createCalendarEvent:(NSString *)name location: (NSString *)location)
{
  RCTLogInfo(@"Pretending to create an event %@ at %@", name, location);
}

@end
