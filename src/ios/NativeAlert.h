//
//  NativeAlert.h
//  NativeAlert
//
//  Created by Vignesh on 1/2/18.
//  Copyright © 2018 Vignesh Uvi. All rights reserved.
//

#import <Cordova/CDV.h>

@interface NativeAlert : CDVPlugin

- (void) showAlert:(CDVInvokedUrlCommand*)command;

@end