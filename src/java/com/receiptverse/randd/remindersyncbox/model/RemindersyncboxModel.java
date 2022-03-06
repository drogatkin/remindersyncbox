// Webbee (C) 2021 Dmitriy Rogatkin
///////   application model class   //////////
// TODO modify the file for the application purpose
package com.receiptverse.randd.remindersyncbox.model;

import javax.sql.DataSource;
import org.aldan3.data.DOService;
import org.aldan3.model.Log;

import com.beegman.webbee.model.AppModel;
import com.beegman.webbee.model.Auth;
import com.beegman.webbee.base.BaseBehavior;

import com.receiptverse.randd.remindersyncbox.model.util.RemindersyncboxBehavior;
public class RemindersyncboxModel extends AppModel {

	@Override
	public String getAppName() {
		return "Remindersyncbox";
	}
	
	@Override
	protected String getServletName() {
		return "remindersyncbox servlet";
	}
 
	@Override
	protected DOService createDataService(DataSource datasource) {
		return new DOService(datasource) {

                     @Override      
                     public String normalizeElementName(String name) {
                         return name.toUpperCase();
                     }
                     
                     @Override
				     protected int getInsertUpdateVariant() {
				          return 2;
				     }
				     
		             @Override
                     protected String modifyColumn () {
                 		return " ALTER ";
                     }

                };
        }

	@Override
        public BaseBehavior getCommonBehavior() {
		return new RemindersyncboxBehavior ();
	}

	@Override
	protected void initServices() {
		super.initServices();
	}

}