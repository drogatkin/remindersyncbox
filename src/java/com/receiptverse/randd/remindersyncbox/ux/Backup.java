package com.receiptverse.randd.remindersyncbox.ux;

import com.beegman.webbee.block.Restful;

import com.receiptverse.randd.remindersyncbox.model.RemindersyncboxModel;
import javax.json.JsonObject;
import javax.json.JsonReader;

import org.aldan3.util.DataConv;
import org.aldan3.util.Stream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import javax.json.Json;
import javax.json.JsonArray;

public class Backup extends Restful<JsonArray, String, RemindersyncboxModel> {
    // TODO backup/store name manipulation isolate in a separate method
	private static final String STORE_NAME = "myremiders.json";
	
	private static final String STORE_PROP = "store_dir";
	
    @Override
    protected String storeModel(JsonArray in) { 
    //	log("store model of: %s", null, in);
    	var storeDir = new File(getConfigValue(STORE_PROP, System.getProperty("user.home")));
    	if (!storeDir.exists()) 
    		if (!storeDir.mkdirs()) {
    			log(STR."Can't create \{storeDir}", null);
    			return """
    			           {"code":"ERROR"}
    			        """;
    		}
    		
    	var f = new File(storeDir, "myremiders.bak");
    	f.delete();
    	File f2 = new File(storeDir, STORE_NAME);
    	if (f2.exists())
    		f2.renameTo(f);
    	try (OutputStreamWriter ow = new OutputStreamWriter(new FileOutputStream(f2), "UTF-8")) {
    		ow.write(in.toString());
    		log(STR."stored in \{f2}", null);
    	} catch (IOException ie) {
    		log("", ie);
    		return """
    		           {"code":"ERROR"}
    		        """;
    	}
        return """
           {"code":"OK"}
        """;
    }
    
    @Override
    protected JsonArray readModel() {
    	try (JsonReader jsonReader = Json
				.createReader(new InputStreamReader(req.getInputStream(), DataConv.ifNull(getEncoding(), "utf-8")))) {
			return jsonReader.readArray();
    	} catch(IOException ioe) {
    		log("", ioe);
    	     throw new RuntimeException();
    	}
    }
    
    protected String loadModel(JsonArray in) {
    	var  f2 = new File(getConfigValue(STORE_PROP, System.getProperty("user.home")), STORE_NAME);
    	log("load model from %s", null, f2);
    	if (f2.exists()) { 
    		try (FileInputStream is = new FileInputStream(f2)){
    			return Stream.streamToString(is, "UTF-8", -1);
    		} catch (IOException ie) {
        		log("", ie);
        		throw new RuntimeException(""+ie);
        	}
    	}
    	throw new RuntimeException("File: "+f2+" not found");
		//return null;
	}
}