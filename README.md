# Cordova - Native Alert(Dialog) - Plugin

Simple "Cordova Native Alert(Dialog) Plugin" is Helps to use native dialog views and it will support both iOS and Android platforms.

## Features

- It won't block the Javascript thread.
- Will get Native alert look.


## Platforms

- iOS
- Andriod

## Steps to install

Create a new Cordova Project

		$ cordova create uvialert com.uviexample.uvialertapp NativeAlert
		$ cd uvialert

Install the plugin

		$ cordova plugin add  https://github.com/vigneshuvi/cordova-plugin-nativealert.git

Edit `www/js/index.js` and add the following code inside `onDeviceReady` and save the file.

```js
        var success = function(message) {
            if (message == "Yes") {
                // Write Logout code.
            } else if (message == "No") {
                // Write your code.
            }
            console.log(message);
        }
        
        var failure = function() {
            console.log("Error calling alert Plugin");
        }

        var alertJson = {}
        alertJson ["title"] = "Native Alert";
        alertJson ["message"] = "Are you sure want to logout the app?";
        alertJson ["okButton"] = "Yes";
        alertJson ["cancelButton"] = "No";
        setTimeout(function() { 
        	nativealert.showAlert(JSON.stringify(alertJson), success, failure); 
        }, 3000);
```

Add platforms

		$ cordova platform add android
		$ cordova prepare 

		$ cordova platform add ios
		$ cordova prepare ios

Build the code 

    	$ cordova build     
    
Run the code

    	$ cordova run 

## More Info

For more information on setting up Cordova see [the documentation](http://cordova.apache.org/docs/en/latest/guide_cli_index.md.html#The%20Command-Line%20Interface)

For more info on plugins see the [Plugin Development Guide](http://cordova.apache.org/docs/en/latest/guide/hybrid/plugins/index.html)

___

#### Do you like it?

Do you like this repo? Share it on Twitter, Facebook, Google+ or anywhere you like so that more of us can use it and help. Thanks!


Created by [Vignesh](http://vigneshuvi.github.io/) 
