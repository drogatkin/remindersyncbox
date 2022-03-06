// Webbee (C) 2021 Dmitriy Rogatkin
///////   application common behavior class   //////////
// TODO modify the file for the application behavior purpose
package com.receiptverse.randd.remindersyncbox.model.util;
import javax.servlet.http.HttpServletRequest;

import com.beegman.webbee.base.BaseBehavior;
import com.receiptverse.randd.remindersyncbox.model.RemindersyncboxModel;

public class RemindersyncboxBehavior extends BaseBehavior<RemindersyncboxModel> {
    public RemindersyncboxBehavior () {
         super();
               
        isPublic = true;

    }
    
    public boolean isMobileApp(HttpServletRequest req) {
		String userAgent = req.getHeader("user-agent");

		return (userAgent != null && userAgent.startsWith("mobile:"));
	}
}
