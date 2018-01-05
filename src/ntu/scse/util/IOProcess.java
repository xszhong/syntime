/******************************************************************************************
 * Copyright (c) 2013 Xiaoshi Zhong
 * All rights reserved. This program and the accompanying materials are made available
 * under the terms of the GNU lesser Public License v3 which accompanies this distribution,
 * and is available at http://www.gnu.org/licenses/lgpl.html
 * 
 * Contributors: Xiaoshi Zhong
 * ****************************************************************************************/

package ntu.scse.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;

public class IOProcess {
	
	/**
	 * Create a new file based on three parameters: filename, isAppend, and encoding.
	 * @param filename: filename in full path
	 * @param isAppend: indicates whether the file to be created is appended in the end or an empty file
	 * @param encoding: file encoding
	 * @return OutputStreamWriter	:
	 * @throws IOException
	 */
	public static OutputStreamWriter newWriter(String filename, boolean isAppend, String encoding){

		File file = new File(filename);
		OutputStreamWriter output = null;
		
		try {
			if(! file.exists()){
				/**
				 * If the file does not exist, then create the folder and its child folders for the full path.
				 * */
				int lastIndexOfSlash = filename.lastIndexOf("/");
				if(lastIndexOfSlash != -1){
					String pathname = filename.substring(0, lastIndexOfSlash);
					createPath(pathname);
				}
				
				/**
				 * Create the new file.
				 * */
				file.createNewFile();
			}else if(! isAppend){
				/**
				 * If create a new empty file, then delete the exist file after that create the new file.
				 * */
				file.delete();
				file.createNewFile();
			}
			/**
			 * Create the OutputStreamWriter.
			 * */
			output = new OutputStreamWriter(new FileOutputStream(file,true), encoding);	
		} catch (IOException e) {
			e.printStackTrace();
		} finally { }
		
		return output;
	}
	
	/**
	 * Set the default isAppend as false.
	 */
	public static OutputStreamWriter newWriter(String filename, String encoding){
		return newWriter(filename, false, encoding);
	}
	
	/**
	 * Set the default encoding as UTF-8.
	 */
	public static OutputStreamWriter newWriter(String filename, boolean isAppend){
		return newWriter(filename, isAppend, "UTF-8");
	}
	
	/**
	 * Set the default encoding as UTF-8.
	 * */
	public static OutputStreamWriter newWriter(File file, boolean isAppend){
		return newWriter(file.toString(), isAppend, "UTF-8");
	}
	
	/**
	 * Set the default isAppend as false and the default encoding as UTF-8.
	 */
	public static OutputStreamWriter newWriter(String filename){
		return newWriter(filename, false, "UTF-8");
	}
	
	/**
	 * Set the default isAppend as false and the default encoding as UTF-8.
	 * */
	public static OutputStreamWriter newWriter(File file){
		return newWriter(file.toString(), false, "UTF-8");
	}
	
	/**
	 * Save a file based on three parameters: filename, content, and encoding.
	 * @param filename: filename in full path
	 * @param content: the content to be saved as the file
	 * @param encoding: file encoding
	 * */
	public static boolean saveFile(String filename, String content, String encoding){
		try {
			OutputStreamWriter output = newWriter(filename, encoding);
			output.write(content);
			output.close();			
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally { }
		return false;
	}
	
	public static boolean saveFile(File file, String content, String encoding){
		return saveFile(file.toString(), content, encoding);
	}
	
	/**
	 * Set the default encoding as UTF-8
	 * */
	public static boolean saveFile(String filename, String content) {
		return saveFile(filename, content, "UTF-8");
	}
	
	public static boolean saveFile(File file, String content){
		return saveFile(file.toString(), content);
	}

	/**
	 * Get the whole files under the folder
	 * @param folder: the folder name
	 */
	public static File[] getFiles(String folder){
		return new File(folder).listFiles();		
	}
	
	/**
	 * Get the whole files under the folder
	 */
	public static File[] getFiles(File folder){
		return folder.listFiles();
	}
	
	/**
	 * Create a new buffered reader according to a file and set the default encoding as "UTF-8"
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static BufferedReader newReader(File file) {
		return newReader(file, "UTF-8");
	}
	
	/**
	 * Create a new buffered reader according to filename and set the default encoding as "UTF-8"
	 * 
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static BufferedReader newReader(String filename) {
		return newReader(filename, "UTF-8");
	}
	
	/**
	 * Read a new file as BufferedReader based on two parameters: file and encoding.
	 * @param file
	 * @param encoding
	 * */
	public static BufferedReader newReader(File file, String encoding){
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(file), encoding));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally { }
		return br;
	}
	
	/**
	 * Read a new file as BufferedReader
	 * */
	public static BufferedReader newReader(String filename, String encoding){
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(filename)), encoding));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally { }
		return br;
	}
	
	/**
	 * Read a file as BufferedBuffer from an URL
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public static BufferedReader newReader(URL url){
		return newReader(url, "UTF-8");
	}
	
	/**
	 * Read a file as BufferedReader
	 * */
	public static BufferedReader newReader(URL url, String encoding){
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(url.openStream(), encoding));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally { }
		return br;
	}
	
	/**
	 * Check whether does a file exist or not according to its filename.
	 * */
	public static boolean isFileExist(String filename){
		File file = new File(filename);
		if(file.exists())
			return true;
		return false;
	}
	
	public static void createPath(String path){
		File filePath = new File(path);
		if(! filePath.exists())
			filePath.mkdirs();
	}
	
	/**
	 * Delete a file if it exists
	 * */
	public static void deleteFile(String filename){
		File file = new File(filename);
		if(file.exists())
			file.delete();
	}
	
	/**
	 * Check a path to make sure it ends with "/"
	 * */
	public static String checkPath(String path){
		if(! path.endsWith("/"))
			path += "/";
		return path;
	}
	
	/**
	 * Throw a runtime exception that cannot find the path
	 * */
	public static String findNoPath(String path){
		throw new RuntimeException("Cannot find the path: " + path);
	}
	
	/**
	 * Throw a runtime exception that cannot find the file
	 * */
	public static String findNoFile(String filename){
		throw new RuntimeException("Cannot find the file: " + filename);
	}
}
