
#import <AppKit/AppKit.h>
#import <Cocoa/Cocoa.h>
#import <Foundation/Foundation.h>
#import <objc/runtime.h>
#include "getdir.h"
static NSString* changed_bundle_path;
static NSString* changed_bundle_identifier;

@implementation NSBundle(changedBundle)
+ (void) changeBundlePath:(NSString*) bundlePath {
    changed_bundle_path = [bundlePath retain];

    Class originalClass = [NSBundle class];
    Method originalMeth = class_getClassMethod(originalClass, @selector(mainBundle));
    Method replacementMeth = class_getClassMethod([self class], @selector(changedBundle));
    method_exchangeImplementations(originalMeth, replacementMeth);
}

+ (NSBundle*) changedBundle {
    return [NSBundle bundleWithPath:changed_bundle_path];
}

+ (void) changeBundleIdentifier:(NSString*) bundleIdentifier {
    changed_bundle_identifier = [bundleIdentifier retain];

    Class originalClass = [NSBundle class];
    Method originalMeth = class_getInstanceMethod(originalClass, @selector(bundleIdentifier));
    Method replacementMeth = class_getInstanceMethod([self class], @selector(changedBundleIdentifier));
    method_exchangeImplementations(originalMeth, replacementMeth);
}

- (NSString*) changedBundleIdentifier {
    return changed_bundle_identifier;
}
@end



extern "C"{

    void change_bundle_path(const char* bundle_path){
        [NSBundle changeBundlePath:[NSString stringWithUTF8String:bundle_path]];
    }

    void change_bundle_identifier(const char* bundle_identifier){
        [NSBundle changeBundleIdentifier:[NSString stringWithUTF8String:bundle_identifier]];
    }

void printDirectory(){
    NSBundle *main = [NSBundle mainBundle];
    NSString *resourcePath = [main bundlePath];
    NSLog(@"bundle dir: %@\n", resourcePath);
}

    // int main(int argc, char* argv[]) {
    //     printDirectory();
    // }
        
}
