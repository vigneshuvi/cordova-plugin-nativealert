//
//  Alert.m
//  Alert
//
//  Created by Vignesh on 1/2/18.
//  Copyright © 2018 Vignesh Uvi. All rights reserved.
//

#import "Alert.h"

@implementation Alert

/**
 * Plugin methods are executed on the UI thread.
 * If your plugin requires a non-trivial amount of processing or requires a blocking call,
 * you should make use of a background thread.
 */
- (void) showAlert:(CDVInvokedUrlCommand*)command {

    [self.commandDelegate runInBackground:^{

        // Get the call back ID and echo argument
        NSString *callbackId = [command callbackId];
        CDVPluginResult* result = nil;
        int count = [[command arguments] count];
        if ( count > 0) {
            NSString* jsonString = [command.arguments objectAtIndex:0];
            NSData *data = [jsonString dataUsingEncoding:NSUTF8StringEncoding];
            id json = [NSJSONSerialization JSONObjectWithData:data options:0 error:nil];
            if ([[json allKeys] count] > 0) {
                NSString *title = [json objectForKey:@"title"];
                NSString *message = [json objectForKey:@"message"];
                NSString *ok = [json objectForKey:@"success"];
                UIAlertController * alert=[UIAlertController alertControllerWithTitle:(title != nil ? title : "Alert") 
                                                                  message:(message != nil ? message : "")
                                                           preferredStyle:UIAlertControllerStyleAlert];

                UIAlertAction* yesButton = [UIAlertAction actionWithTitle: (ok != nil ? ok : "Ok")
                                                                    style:UIAlertActionStyleDefault
                                                                  handler:^(UIAlertAction * action)
                {
                       result = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:scheduleCallback];
                       [self.commandDelegate sendPluginResult:result callbackId:callbackId];

                }];
                [alert addAction:yesButton];
                [[self currentApplicationViewController] presentViewController:alert animated:YES completion:nil];
            } else { 
                result = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:@"Alert Argument was null"];
                [self.commandDelegate sendPluginResult:result callbackId:callbackId];
            }
        } else { 
            result = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:@"Alert Argument was null"];
            [self.commandDelegate sendPluginResult:result callbackId:callbackId];
        }
        
        
    }];
}

/**
 * Method to Get the Current Application View Controller
 *
 * @return the Application View Controller
 */
- (UIViewController *) currentApplicationViewController {
    UIWindow *window = [UIApplication sharedApplication].keyWindow;
    UIViewController *rootViewController = window.rootViewController;
    
    if([rootViewController isKindOfClass:[UIViewController class]]){
        return [[UIApplication sharedApplication]delegate].window.rootViewController;
    } else {
        UINavigationController *navigationController = (UINavigationController *)[[UIApplication sharedApplication]delegate].window.rootViewController;
        return(UIViewController *)[navigationController topViewController];
    }
    return nil;
}



@end