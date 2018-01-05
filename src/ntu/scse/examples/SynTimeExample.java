/******************************************************************************************
 * Copyright (c) 2017 Xiaoshi Zhong
 * All rights reserved. This program and the accompanying materials are made available
 * under the terms of the GNU lesser Public License v3 which accompanies this distribution,
 * and is available at http://www.gnu.org/licenses/lgpl.html
 * 
 * Contributors : Xiaoshi Zhong, zhongxiaoshi@gmail.com
 * ****************************************************************************************/

package ntu.scse.examples;

import ntu.scse.main.SynTime;

public class SynTimeExample {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String inputTmlDir = "resources/te3-platinum/tml/";
		String outputTmlDir = "resources/te3-platinum/output-timeml/";
		
		String inputTmlFile = "resources/te3-platinum/tml/AP_20130322.tml";
		String outputTmlFile = "resources/te3-platinum/output-timeml/AP_20130322.tml";
		
		String inputText = "The last 6 months surviving member of the team which first conquered Everest in 6 a.m. 17 Jan 1953 has died in a Derbyshire nursing home.";
		String date = "2016-10-10";
		
		SynTime synTime = new SynTime();
		
		/**
		 * Input TimeML folder, output TimeML folder
		 * */
		synTime.extractTimexFromTmlFolder(inputTmlDir, outputTmlDir);
		
		/**
		 * Input TimeML file, output TimeML file
		 * */
		synTime.extractTimexFromTmlFile(inputTmlFile, outputTmlFile);
		
		/**
		 * Input plain text, output tagged TimeML text
		 * */
		String tmlText = synTime.extractTimexFromText(inputText, date);
		System.out.println(tmlText);
	}
}
