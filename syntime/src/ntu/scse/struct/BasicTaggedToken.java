/******************************************************************************************
 * Copyright (c) 2017 Xiaoshi Zhong
 * All rights reserved. This program and the accompanying materials are made available
 * under the terms of the GNU lesser Public License v3 which accompanies this distribution,
 * and is available at http://www.gnu.org/licenses/lgpl.html
 * 
 * Contributors : Xiaoshi Zhong, zhongxiaoshi@gmail.com
 * ****************************************************************************************/

package ntu.scse.struct;

public class BasicTaggedToken {
	private String token;
	private String lemma;
	private String tag;	// the tag can be POS tag or label tag or other kinds of tags
	
	private int tokenPosition;
	
	private int beginCharPosition;
	private int endCharPosition;
	
	public BasicTaggedToken(String token, String tag){
		this.token = token;
		this.tag = tag;
	}
	
	public BasicTaggedToken(String token, String lemma, String tag){
		this(token, tag);
		this.lemma = lemma;
	}
	public BasicTaggedToken(String token, String lemma, String tag, int beginCharPosition, int endCharPosition){
		this(token, lemma, tag);
		this.beginCharPosition = beginCharPosition;
		this.endCharPosition = endCharPosition;
	}
	
	public BasicTaggedToken(String token, String lemma, String tag, int tokenPosition, int beginCharPosition, int endCharPosition){
		this(token, lemma, tag, beginCharPosition, endCharPosition);
		this.tokenPosition = tokenPosition;
	}
	
	public String getToken(){
		return token;
	}
	public String getLemma(){
		return lemma;
	}
	public String getTag(){
		return tag;
	}
	public void setTag(String tag){
		this.tag = tag;
	}
	
	public int getTokenPosition(){
		return tokenPosition;
	}
	
	public int getBeginCharPosition(){
		return beginCharPosition;
	}
	public void setBeginCharPosition(int beginCharPosition){
		this.beginCharPosition = beginCharPosition;
	}
	
	public int getEndCharPosition(){
		return endCharPosition;
	}
	public void setEndCharPosition(int endCharPosition){
		this.endCharPosition = endCharPosition;
	}
	
}
