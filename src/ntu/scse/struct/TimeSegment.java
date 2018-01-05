/******************************************************************************************
 * Copyright (c) 2017 Xiaoshi Zhong
 * All rights reserved. This program and the accompanying materials are made available
 * under the terms of the GNU lesser Public License v3 which accompanies this distribution,
 * and is available at http://www.gnu.org/licenses/lgpl.html
 * 
 * Contributors : Xiaoshi Zhong, zhongxiaoshi@gmail.com
 * ****************************************************************************************/

package ntu.scse.struct;

public class TimeSegment {
	private int beginTokenPosition;
	private int endTokenPosition;
	
	private int timeTokenPosition;
	
	public TimeSegment(int timeTokenPosition){
		this.timeTokenPosition = timeTokenPosition;
	}
	public TimeSegment(int timeTokenPosition, int beginTokenPosition, int endTokenPosition){
		this(timeTokenPosition);
		this.beginTokenPosition = beginTokenPosition;
		this.endTokenPosition = endTokenPosition;
	}
	
	public int getTimeTokenPosition(){
		return timeTokenPosition;
	}
	
	public int getBeginTokenPosition(){
		return beginTokenPosition;
	}
	public void setBeginTokenPosition(int beginTokenPosition){
		this.beginTokenPosition = beginTokenPosition;
	}
	
	public int getEndTokenPosition(){
		return endTokenPosition;
	}
	public void setEndTokenPosition(int endTokenPosition){
		this.endTokenPosition = endTokenPosition;
	}
}
