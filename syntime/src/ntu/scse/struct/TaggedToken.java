/******************************************************************************************
 * Copyright (c) 2017 Xiaoshi Zhong
 * All rights reserved. This program and the accompanying materials are made available
 * under the terms of the GNU lesser Public License v3 which accompanies this distribution,
 * and is available at http://www.gnu.org/licenses/lgpl.html
 * 
 * Contributors : Xiaoshi Zhong, zhongxiaoshi@gmail.com
 * ****************************************************************************************/

package ntu.scse.struct;

import java.util.Set;

import ntu.scse.tool.InduceTokenType;
import ntu.scse.tool.SynTimeRegex.TokenType;

public class TaggedToken extends BasicTaggedToken {
	private Set<TokenType> tokenTypeSet;
	
	public TaggedToken(String token, String tag){
		super(token, tag);
	}
	
	public TaggedToken(String token, String lemma, String tag, int tokenPosition, int beginCharPosition, int endCharPosition){
		super(token, lemma, tag, tokenPosition, beginCharPosition, endCharPosition);
	}
	
	public Set<TokenType> getTokenTypeSet(){
		return tokenTypeSet;
	}
	public void setTokenTypeSet(Set<TokenType> tokenTypeSet){
		this.tokenTypeSet = tokenTypeSet;
	}
	public void addTokenType(TokenType tokenType){
		tokenTypeSet.add(tokenType);
	}
	public void removeTokenType(TokenType tokenType){
		this.tokenTypeSet.remove(tokenType);
	}
	public void removeTokenType(){
		this.tokenTypeSet.clear();
	}
	
	public boolean isYear(){
		return InduceTokenType.isYear(tokenTypeSet);
	}
	
	public boolean isYearYear(){
		return InduceTokenType.isYearYear(tokenTypeSet);
	}
	
	public boolean isSeason(){
		return InduceTokenType.isSeason(tokenTypeSet);
	}
	
	public boolean isMonth(){
		return InduceTokenType.isMonth(tokenTypeSet);
	}
	
	public boolean isMonthMonth(){
		return InduceTokenType.isMonthMonth(tokenTypeSet);
	}
	
	public boolean isYearMonth(){
		return InduceTokenType.isYearMonth(tokenTypeSet);
	}
	
	public boolean isWeek(){
		return InduceTokenType.isWeek(tokenTypeSet);
	}
	
	public boolean isWeekWeek(){
		return InduceTokenType.isWeekWeek(tokenTypeSet);
	}
	
	public boolean isDate(){
		return InduceTokenType.isDate(tokenTypeSet);
	}
	
	public boolean isTime(){
		return InduceTokenType.isTime(tokenTypeSet);
	}
	
	public boolean isTimeTime(){
		return InduceTokenType.isTimeTime(tokenTypeSet);
	}
	
	public boolean isHalfDay(){
		return InduceTokenType.isHalfDay(tokenTypeSet);
	}
	
	public boolean isHalfDayHalfDay(){
		return InduceTokenType.isHalfDayHalfDay(tokenTypeSet);
	}
	
	public boolean isTimeZone(){
		return InduceTokenType.isTimeZone(tokenTypeSet);
	}
	
	public boolean isEra(){
		return InduceTokenType.isEra(tokenTypeSet);
	}
	
	public boolean isTimeUnit(){
		return InduceTokenType.isTimeUnit(tokenTypeSet);
	}
	
	public boolean isDuration(){
		return InduceTokenType.isDuration(tokenTypeSet);
	}
	
	public boolean isDurationDuration(){
		return InduceTokenType.isDurationDuration(tokenTypeSet);
	}
	
	public boolean isDayTime(){
		return InduceTokenType.isDayTime(tokenTypeSet);
	}
	
	public boolean isTimeline(){
		return InduceTokenType.isTimeline(tokenTypeSet);
	}
	
	public boolean isHoliday(){
		return InduceTokenType.isHoliday(tokenTypeSet);
	}

	public boolean isPeriod(){
		return InduceTokenType.isPeriod(tokenTypeSet);
	}
	
	public boolean isDecade(){
		return InduceTokenType.isDecade(tokenTypeSet);
	}
	
	public boolean isNumeral(){
		return InduceTokenType.isNumeral(tokenTypeSet);
	}
	
	public boolean isNumeralNumeral(){
		return InduceTokenType.isNumeralNumeral(tokenTypeSet);
	}
	
	public boolean isInArticle(){
		return InduceTokenType.isInArticle(tokenTypeSet);
	}
	
	public boolean isPrefix(){
		return InduceTokenType.isPrefix(tokenTypeSet);
	}
	
	public boolean isSuffix(){
		return InduceTokenType.isSuffix(tokenTypeSet);
	}
	
	public boolean isLinkage(){
		return InduceTokenType.isLinkage(tokenTypeSet);
	}
	
	public boolean isComma(){
		return InduceTokenType.isComma(tokenTypeSet);
	}
	
	public boolean isTimeToken(){
		return InduceTokenType.isTimeToken(tokenTypeSet);
	}
	
	public boolean sameTokenTypeWith(TaggedToken taggedToken){
		Set<TokenType> temTokenTypeSet = taggedToken.getTokenTypeSet();
		if(temTokenTypeSet.isEmpty() || tokenTypeSet.isEmpty() || temTokenTypeSet.size() != tokenTypeSet.size())
			return false;
		for(TokenType tokenType : tokenTypeSet) {
			for(TokenType temTokenType : temTokenTypeSet) {
				if(tokenType.equals(temTokenType)) {
					return true;
				}
			}
		}
		return false;
	}
}
