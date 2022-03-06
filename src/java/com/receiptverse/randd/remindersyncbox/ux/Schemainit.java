package com.receiptverse.randd.remindersyncbox.ux;

import com.beegman.webbee.block.Systemsetup;
import com.beegman.webbee.model.Setup;
import com.receiptverse.randd.remindersyncbox.model.RemindersyncboxModel;

public class Schemainit extends Systemsetup<Setup, RemindersyncboxModel> {

	@Override
	protected String getDefaultModelPackage() {
             return getAppModel().getClass().getPackage().getName();
        }
}
