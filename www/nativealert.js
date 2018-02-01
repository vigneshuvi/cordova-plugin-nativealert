/**
 *  alert.js
 *
 *  Created by Vignesh on 1/2/18.
 *  Copyright © 2018 Vignesh Uvi. All rights reserved.
 *
 */

// global cordova, module
module.exports = {
    showAlert: function (json, successCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, "Alert", "showAlert", [json]);
    }
};
