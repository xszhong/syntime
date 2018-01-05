/******************************************************************************************
 * Copyright (c) 2017 Xiaoshi Zhong
 * All rights reserved. This program and the accompanying materials are made available
 * under the terms of the GNU lesser Public License v3 which accompanies this distribution,
 * and is available at http://www.gnu.org/licenses/lgpl.html
 * 
 * Contributors : Xiaoshi Zhong, zhongxiaoshi@gmail.com
 * ****************************************************************************************/

package ntu.scse.tool;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;

import ntu.scse.struct.BasicTaggedToken;
import ntu.scse.struct.TaggedToken;
import ntu.scse.tool.SynTimeRegex;
import ntu.scse.tool.SynTimeRegex.TokenType;

public class InduceTokenType {
	public static Set<TokenType> getTokenType(BasicTaggedToken taggedToken){
		return getTokenType(taggedToken.getToken(), taggedToken.getTag());
	}
	
	public static Set<TokenType> getTokenType(TaggedToken taggedToken){
		return getTokenType(taggedToken.getToken(), taggedToken.getTag());
	}
	
	public static Set<TokenType> getTokenType(String token, String posTag){
		Set<TokenType> tokenTypeSet = new HashSet<TokenType>();
		
		Matcher mYear1 = SynTimeRegex.YEAR_PATTERN_1.matcher(token);
		Matcher mYear2 = SynTimeRegex.YEAR_PATTERN_2.matcher(token);
		Matcher mMidYear = SynTimeRegex.YEAR_MID_PATTERN.matcher(token);
		Matcher mEraYear = SynTimeRegex.ERA_YEAR_PATTERN.matcher(token);
		if(mYear1.matches() || mYear2.matches() || mMidYear.matches() || mEraYear.matches())
			tokenTypeSet.add(TokenType.YEAR);
			
		Matcher mYearYear = SynTimeRegex.YEAR_YEAR_PATTERN.matcher(token);
		if(mYearYear.matches())
			tokenTypeSet.add(TokenType.YEAR_YEAR);
			
		Matcher mSeason = SynTimeRegex.SEASON_PATTERN.matcher(token);
		Matcher mSeasonMid = SynTimeRegex.SEASON_MID_PATTERN.matcher(token);
		if(mSeason.matches() && posTag.startsWith("NN") || mSeasonMid.matches())
			tokenTypeSet.add(TokenType.SEASON);
		
		Matcher mMonth = SynTimeRegex.MONTH_PATTERN.matcher(token);
		Matcher mMidMonth = SynTimeRegex.MONTH_MID_PATTERN.matcher(token);
		if((mMonth.matches() && posTag.startsWith("NN")) || mMidMonth.matches())
			tokenTypeSet.add(TokenType.MONTH);
		Matcher mMonthAbbr = SynTimeRegex.MONTH_ABBR_PATTERN.matcher(token);
		if((mMonthAbbr.matches() && posTag.startsWith("NN")))
			tokenTypeSet.add(TokenType.MONTH_ABBR);
		Matcher mMonthMonth = SynTimeRegex.MONTH_MONTH_PATTERN.matcher(token);
		if(mMonthMonth.matches())
			tokenTypeSet.add(TokenType.MONTH_MONTH);
		
		Matcher mYearMonth1 = SynTimeRegex.YEAR_MONTH_PATTERN_1.matcher(token);
		Matcher mYearMonth2 = SynTimeRegex.YEAR_MONTH_PATTERN_2.matcher(token);
		if(mYearMonth1.matches() || mYearMonth2.matches())
			tokenTypeSet.add(TokenType.YEAR_MONTH);
		
		Matcher mWeek = SynTimeRegex.WEEK_PATTERN.matcher(token);
		if(mWeek.matches() && posTag.startsWith("NN"))
			tokenTypeSet.add(TokenType.WEEK);
		
		Matcher mWeekAbbr = SynTimeRegex.WEEK_ABBR_PATTERN.matcher(token);
		if(mWeekAbbr.matches() && posTag.startsWith("NN"))
			tokenTypeSet.add(TokenType.WEEK_ABBR);
		
		Matcher mWeekWeek = SynTimeRegex.WEEK_WEEK_PATTERN.matcher(token);
		if(mWeekWeek.matches())
			tokenTypeSet.add(TokenType.WEEK_WEEK);
		
		Matcher mDate1 = SynTimeRegex.DATE_PATTERN_1.matcher(token);
		Matcher mDate2 = SynTimeRegex.DATE_PATTERN_2.matcher(token);
		Matcher mDate3 = SynTimeRegex.DATE_PATTERN_3.matcher(token);
		if(mDate1.matches() || mDate2.matches() || mDate3.matches())
			tokenTypeSet.add(TokenType.DATE);
		
		Matcher mTime1 = SynTimeRegex.TIME_PATTERN_1.matcher(token);
		Matcher mTime2 = SynTimeRegex.TIME_PATTERN_2.matcher(token);
		if(mTime1.matches() || mTime2.matches())
			tokenTypeSet.add(TokenType.TIME);
		Matcher mTimeTime = SynTimeRegex.TIME_TIME_PATTERN.matcher(token);
		if(mTimeTime.matches())
			tokenTypeSet.add(TokenType.TIME_TIME);
		
		Matcher mHalfDay1 = SynTimeRegex.HALFDAY_PATTERN_1.matcher(token);
		Matcher mHalfDay2 = SynTimeRegex.HALFDAY_PATTERN_2.matcher(token);
		if(mHalfDay1.matches() || mHalfDay2.matches())
			tokenTypeSet.add(TokenType.HALFDAY);
		Matcher mHalfDayHalfDay = SynTimeRegex.HALFDAY_HALFDAY_PATTERN.matcher(token);
		if(mHalfDayHalfDay.matches())
			tokenTypeSet.add(TokenType.HALFDAY_HALFDAY);
		
		Matcher mTimeZone = SynTimeRegex.TIME_ZONE_PATTERN.matcher(token);
		if(mTimeZone.matches())
			tokenTypeSet.add(TokenType.TIME_ZONE);
		
		Matcher mEra = SynTimeRegex.ERA_PATTERN.matcher(token);
		if(mEra.matches() && posTag.startsWith("NN"))
			tokenTypeSet.add(TokenType.ERA);
		
		Matcher mTimeUnit = SynTimeRegex.TIME_UNIT_PATTERN.matcher(token);
		if(mTimeUnit.matches() && posTag.startsWith("NN"))
			tokenTypeSet.add(TokenType.TIME_UNIT);
		
		Matcher mDuration = SynTimeRegex.DURATION_PATTERN.matcher(token);
		if(mDuration.matches() && (posTag.startsWith("NN") || posTag.equals("JJ") || posTag.equals("CD")))
			tokenTypeSet.add(TokenType.DURATION);
		
		Matcher mDurationDuration1 = SynTimeRegex.DURATION_DURATION_PATTERN_1.matcher(token);
		Matcher mDurationDuration2 = SynTimeRegex.DURATION_DURATION_PATTERN_2.matcher(token);
		if(! mDuration.matches() && ( mDurationDuration1.matches() || mDurationDuration2.matches()))
			tokenTypeSet.add(TokenType.DURATION_DURATION);
		
		Matcher mDayTime = SynTimeRegex.DAY_TIME_PATTERN.matcher(token);
		Matcher mDayTimeMid = SynTimeRegex.DAY_TIME_MID_PATTERN.matcher(token);
		if(mDayTime.matches() && (posTag.startsWith("NN") || posTag.equals("RB") || posTag.equals("JJ")) || mDayTimeMid.matches())
			tokenTypeSet.add(TokenType.DAY_TIME);
		
		Matcher mTimeline = SynTimeRegex.TIMELINE_PATTERN.matcher(token);
		if(mTimeline.matches() && (posTag.startsWith("NN") || posTag.equals("RB")))
			tokenTypeSet.add(TokenType.TIMELINE);
		
		Matcher mHoliday = SynTimeRegex.HOLIDAY_PATTERN.matcher(token);
		if(mHoliday.matches())
			tokenTypeSet.add(TokenType.HOLIDAY);
		
		Matcher mPeriod = SynTimeRegex.PERIOD_PATTERN.matcher(token);
		if(mPeriod.matches() && (posTag.startsWith("NN") || posTag.equals("RB") || posTag.equals("JJ")))
			tokenTypeSet.add(TokenType.PERIOD);
		
		Matcher mDecade = SynTimeRegex.DECADE_PATTERN.matcher(token);
		Matcher mDecadeMid = SynTimeRegex.DECADE_MID_PATTERN.matcher(token);
		if(mDecade.matches() && posTag.startsWith("NN") || mDecadeMid.matches())
			tokenTypeSet.add(TokenType.DECADE);
		
		Matcher mDigit1 = SynTimeRegex.DIGIT_PATTERN_1.matcher(token);
		Matcher mDigit2 = SynTimeRegex.DIGIT_PATTERN_2.matcher(token);
		Matcher mBasicNum1 = SynTimeRegex.BASIC_NUMBER_PATTERN_1.matcher(token);
		Matcher mBasicNum2 = SynTimeRegex.BASIC_NUMBER_PATTERN_2.matcher(token);
		Matcher mOrdinal1 = SynTimeRegex.ORDINAL_PATTERN_1.matcher(token);
		Matcher mOrdinal2 = SynTimeRegex.ORDINAL_PATTERN_2.matcher(token);
		if(mYear1.matches() || mYear2.matches())
			;
		else if(mDigit1.matches() || mDigit2.matches() || mBasicNum1.matches() || mBasicNum2.matches() 
			|| ((mOrdinal1.matches() || mOrdinal2.matches()) && (posTag.equals("JJ") || posTag.equals("CD") || posTag.equals("RB"))))
			tokenTypeSet.add(TokenType.NUMERAL);
		
		Matcher mDigitDigit = SynTimeRegex.DIGIT_DIGIT_PATTERN.matcher(token);
		Matcher mBasicNumNum = SynTimeRegex.BASIC_NUMBER_NUMBER_PATTERN.matcher(token);
		Matcher mOrdinalOrdinal = SynTimeRegex.ORDINAL_ORDINAL_PATTERN.matcher(token);
		if(! mYearYear.matches() && ! mBasicNum2.matches() && (mDigitDigit.matches() || mBasicNumNum.matches() || mOrdinalOrdinal.matches()))
			tokenTypeSet.add(TokenType.NUMERAL_NUMERAL);
		
		Matcher mInArticle = SynTimeRegex.INARTICLE_PATTERN.matcher(token);
		if(mInArticle.matches())
			tokenTypeSet.add(TokenType.INARTICLE);
		
		Matcher mPrefix1 = SynTimeRegex.PREFIX_PATTERN_1.matcher(token);
		Matcher mPrefix2 = SynTimeRegex.PREFIX_PATTERN_2.matcher(token);
		if(tokenTypeSet.isEmpty() && (mPrefix1.matches() && !posTag.startsWith("NN") || mPrefix2.matches() && posTag.startsWith("NN")))
			tokenTypeSet.add(TokenType.PREFIX);
		
		Matcher mSuffix = SynTimeRegex.SUFFIX_PATTERN.matcher(token);
		if(mSuffix.matches())
			tokenTypeSet.add(TokenType.SUFFIX);
		
		Matcher mLinkage = SynTimeRegex.LINKAGE_PATTERN.matcher(token);
		if(mLinkage.matches())
			tokenTypeSet.add(TokenType.LINKAGE);
		
		Matcher mComma = SynTimeRegex.COMMA_PATTERN.matcher(token);
		if(mComma.matches())
			tokenTypeSet.add(TokenType.COMMA);
		
		if(tokenTypeSet.size() > 1){
			System.out.println(token + "\t" + posTag + "\ttokenTypeSet size: " + tokenTypeSet.size() + "\t" + tokenTypeSet.toString());
		}

		return tokenTypeSet;
	}
	
	/***************************************************************/
	public static boolean isYear(TokenType tokenType){
		if(tokenType.equals(TokenType.YEAR) || tokenType.equals(TokenType.YEAR_YEAR))
			return true;
		return false;
	}
	public static boolean isYear(Set<TokenType> tokenTypeSet){
		for(TokenType tokenType : tokenTypeSet) {
			if(isYear(tokenType))
				return true;
		}
		return false;
	}
	public static boolean isYear(TaggedToken taggedToken){
		return isYear(taggedToken.getTokenTypeSet());
	}
	
	public static boolean isYearYear(TokenType tokenType){
		if(tokenType.equals(TokenType.YEAR_YEAR))
			return true;
		return false;
	}
	public static boolean isYearYear(Set<TokenType> tokenTypeSet){
		for(TokenType tokenType : tokenTypeSet) {
			if(isYearYear(tokenType))
				return true;
		}
		return false;
	}
	public static boolean isYearYear(TaggedToken taggedToken){
		return isYearYear(taggedToken.getTokenTypeSet());
	}
	
	public static boolean isSeason(TokenType tokenType){
		if(tokenType.equals(TokenType.SEASON))
			return true;
		return false;
	}
	public static boolean isSeason(Set<TokenType> tokenTypeSet){
		for(TokenType tokenType : tokenTypeSet) {
			if(isSeason(tokenType))
				return true;
		}
		return false;
	}
	public static boolean isSeason(TaggedToken taggedToken){
		return isSeason(taggedToken.getTokenTypeSet());
	}
	
	public static boolean isMonth(TokenType tokenType){
		if(tokenType.equals(TokenType.MONTH) || tokenType.equals(TokenType.MONTH_ABBR) || tokenType.equals(TokenType.MONTH_MONTH) || tokenType.equals(TokenType.YEAR_MONTH))
			return true;
		return false;
	}
	public static boolean isMonth(Set<TokenType> tokenTypeSet){
		for(TokenType tokenType : tokenTypeSet) {
			if(isMonth(tokenType))
				return true;
		}
		return false;
	}
	public static boolean isMonth(TaggedToken taggedToken){
		return isMonth(taggedToken.getTokenTypeSet());
	}
	
	public static boolean isMonthMonth(TokenType tokenType){
		if(tokenType.equals(TokenType.MONTH_MONTH))
			return true;
		return false;
	}
	public static boolean isMonthMonth(Set<TokenType> tokenTypeSet){
		for(TokenType tokenType : tokenTypeSet) {
			if(isMonthMonth(tokenType))
				return true;
		}
		return false;
	}
	public static boolean isMonthMonth(TaggedToken taggedToken){
		return isMonthMonth(taggedToken.getTokenTypeSet());
	}
	
	public static boolean isYearMonth(TokenType tokenType){
		if(tokenType.equals(TokenType.YEAR_MONTH))
			return true;
		return false;
	}
	public static boolean isYearMonth(Set<TokenType> tokenTypeSet){
		for(TokenType tokenType : tokenTypeSet) {
			if(isYearMonth(tokenType))
				return true;
		}
		return false;
	}
	public static boolean isYearMonth(TaggedToken taggedToken){
		return isYearMonth(taggedToken.getTokenTypeSet());
	}
	
	public static boolean isWeek(TokenType tokenType){
		if(tokenType.equals(TokenType.WEEK) || tokenType.equals(TokenType.WEEK_ABBR) || tokenType.equals(TokenType.WEEK_WEEK))
			return true;
		return false;
	}
	public static boolean isWeek(Set<TokenType> tokenTypeSet){
		if(tokenTypeSet.isEmpty())
			return false;
		TokenType tokenType = tokenTypeSet.iterator().next();
		return isWeek(tokenType);
	}
	public static boolean isWeek(TaggedToken taggedToken){
		return isWeek(taggedToken.getTokenTypeSet());
	}
	
	public static boolean isWeekWeek(TokenType tokenType){
		if(tokenType.equals(TokenType.WEEK_WEEK))
			return true;
		return false;
	}
	public static boolean isWeekWeek(Set<TokenType> tokenTypeSet){
		for(TokenType tokenType : tokenTypeSet) {
			if(isWeekWeek(tokenType))
				return true;
		}
		return false;
	}
	public static boolean isWeekWeek(TaggedToken taggedToken){
		return isWeekWeek(taggedToken.getTokenTypeSet());
	}
	
	public static boolean isDate(TokenType tokenType){
		if(tokenType.equals(TokenType.DATE))
			return true;
		return false;
	}
	public static boolean isDate(Set<TokenType> tokenTypeSet){
		for(TokenType tokenType : tokenTypeSet) {
			if(isDate(tokenType))
				return true;
		}
		return false;
	}
	public static boolean isDate(TaggedToken taggedToken){
		return isDate(taggedToken.getTokenTypeSet());
	}
	
	public static boolean isTime(TokenType tokenType){
		if(tokenType.equals(TokenType.TIME) || tokenType.equals(TokenType.TIME_TIME))
			return true;
		return false;
	}
	public static boolean isTime(Set<TokenType> tokenTypeSet){
		for(TokenType tokenType : tokenTypeSet) {
			if(isTime(tokenType))
				return true;
		}
		return false;
	}
	public static boolean isTime(TaggedToken taggedToken){
		return isTime(taggedToken.getTokenTypeSet());
	}
	
	public static boolean isTimeTime(TokenType tokenType){
		if(tokenType.equals(TokenType.TIME_TIME))
			return true;
		return false;
	}
	public static boolean isTimeTime(Set<TokenType> tokenTypeSet){
		for(TokenType tokenType : tokenTypeSet) {
			if(isTimeTime(tokenType))
				return true;
		}
		return false;
	}
	public static boolean isTimeTime(TaggedToken taggedToken){
		return isTimeTime(taggedToken.getTokenTypeSet());
	}
	
	public static boolean isHalfDay(TokenType tokenType){
		if(tokenType.equals(TokenType.HALFDAY) || tokenType.equals(TokenType.HALFDAY_HALFDAY))
			return true;
		return false;
	}
	public static boolean isHalfDay(Set<TokenType> tokenTypeSet){
		for(TokenType tokenType : tokenTypeSet) {
			if(isHalfDay(tokenType))
				return true;
		}
		return false;
	}
	public static boolean isHalfDay(TaggedToken taggedToken){
		return isHalfDay(taggedToken.getTokenTypeSet());
	}
	
	public static boolean isHalfDayHalfDay(TokenType tokenType){
		if(tokenType.equals(TokenType.HALFDAY_HALFDAY))
			return true;
		return false;
	}
	public static boolean isHalfDayHalfDay(Set<TokenType> tokenTypeSet){
		for(TokenType tokenType : tokenTypeSet) {
			if(isHalfDayHalfDay(tokenType))
				return true;
		}
		return false;
	}
	public static boolean isHalfDayHalfDay(TaggedToken taggedToken){
		return isHalfDayHalfDay(taggedToken.getTokenTypeSet());
	}
	
	public static boolean isTimeZone(TokenType tokenType){
		if(tokenType.equals(TokenType.TIME_ZONE))
			return true;
		return false;
	}
	public static boolean isTimeZone(Set<TokenType> tokenTypeSet){
		for(TokenType tokenType : tokenTypeSet) {
			if(isTimeZone(tokenType))
				return true;
		}
		return false;
	}
	public static boolean isTimeZone(TaggedToken taggedToken){
		return isTimeZone(taggedToken.getTokenTypeSet());
	}
	
	public static boolean isEra(TokenType tokenType){
		if(tokenType.equals(TokenType.ERA))
			return true;
		return false;
	}
	public static boolean isEra(Set<TokenType> tokenTypeSet){
		for(TokenType tokenType : tokenTypeSet) {
			if(isEra(tokenType))
				return true;
		}
		return false;
	}
	public static boolean isEra(TaggedToken taggedToken){
		return isEra(taggedToken.getTokenTypeSet());
	}
	
	public static boolean isTimeUnit(TokenType tokenType){
		if(tokenType.equals(TokenType.TIME_UNIT))
			return true;
		return false;
	}
	public static boolean isTimeUnit(Set<TokenType> tokenTypeSet){
		for(TokenType tokenType : tokenTypeSet) {
			if(isTimeUnit(tokenType))
				return true;
		}
		return false;
	}
	public static boolean isTimeUnit(TaggedToken taggedToken){
		return isTimeUnit(taggedToken.getTokenTypeSet());
	}
	
	public static boolean isDuration(TokenType tokenType){
		if(tokenType.equals(TokenType.DURATION) || tokenType.equals(TokenType.DURATION_DURATION))
			return true;
		return false;
	}
	public static boolean isDuration(Set<TokenType> tokenTypeSet){
		for(TokenType tokenType : tokenTypeSet) {
			if(isDuration(tokenType))
				return true;
		}
		return false;
	}
	public static boolean isDuration(TaggedToken taggedToken){
		return isDuration(taggedToken.getTokenTypeSet());
	}
	
	public static boolean isDurationDuration(TokenType tokenType){
		if(tokenType.equals(TokenType.DURATION_DURATION))
			return true;
		return false;
	}
	public static boolean isDurationDuration(Set<TokenType> tokenTypeSet){
		for(TokenType tokenType : tokenTypeSet) {
			if(isDurationDuration(tokenType))
				return true;
		}
		return false;
	}
	public static boolean isDurationDuration(TaggedToken taggedToken){
		return isDurationDuration(taggedToken.getTokenTypeSet());
	}
	
	public static boolean isDayTime(TokenType tokenType){
		if(tokenType.equals(TokenType.DAY_TIME))
			return true;
		return false;
	}
	public static boolean isDayTime(Set<TokenType> tokenTypeSet){
		for(TokenType tokenType : tokenTypeSet) {
			if(isDayTime(tokenType))
				return true;
		}
		return false;
	}
	public static boolean isDayTime(TaggedToken taggedToken){
		return isDayTime(taggedToken.getTokenTypeSet());
	}
	
	public static boolean isTimeline(TokenType tokenType){
		if(tokenType.equals(TokenType.TIMELINE))
			return true;
		return false;
	}
	public static boolean isTimeline(Set<TokenType> tokenTypeSet){
		for(TokenType tokenType : tokenTypeSet) {
			if(isTimeline(tokenType))
				return true;
		}
		return false;
	}
	public static boolean isTimeline(TaggedToken taggedToken){
		return isTimeline(taggedToken.getTokenTypeSet());
	}
	
	public static boolean isHoliday(TokenType tokenType){
		if(tokenType.equals(TokenType.HOLIDAY))
			return true;
		return false;
	}
	public static boolean isHoliday(Set<TokenType> tokenTypeSet){
		for(TokenType tokenType : tokenTypeSet) {
			if(isHoliday(tokenType))
				return true;
		}
		return false;
	}
	public static boolean isHoliday(TaggedToken taggedToken){
		return isHoliday(taggedToken.getTokenTypeSet());
	}
	
	public static boolean isPeriod(TokenType tokenType){
		if(tokenType.equals(TokenType.PERIOD))
			return true;
		return false;
	}
	public static boolean isPeriod(Set<TokenType> tokenTypeSet){
		for(TokenType tokenType : tokenTypeSet) {
			if(isPeriod(tokenType))
				return true;
		}
		return false;
	}
	public static boolean isPeriod(TaggedToken taggedToken){
		return isPeriod(taggedToken.getTokenTypeSet());
	}
	
	public static boolean isDecade(TokenType tokenType){
		if(tokenType.equals(TokenType.DECADE))
			return true;
		return false;
	}
	public static boolean isDecade(Set<TokenType> tokenTypeSet){
		for(TokenType tokenType : tokenTypeSet) {
			if(isDecade(tokenType))
				return true;
		}
		return false;
	}
	public static boolean isDecade(TaggedToken taggedToken){
		return isDecade(taggedToken.getTokenTypeSet());
	}
	
	public static boolean isNumeral(TokenType tokenType){
		if(tokenType.equals(TokenType.NUMERAL) || tokenType.equals(TokenType.NUMERAL_NUMERAL))
			return true;
		return false;
	}
	public static boolean isNumeral(Set<TokenType> tokenTypeSet){
		for(TokenType tokenType : tokenTypeSet) {
			if(isNumeral(tokenType))
				return true;
		}
		return false;
	}
	public static boolean isNumeral(TaggedToken taggedToken){
		return isNumeral(taggedToken.getTokenTypeSet());
	}
	
	public static boolean isNumeralNumeral(TokenType tokenType){
		if(tokenType.equals(TokenType.NUMERAL_NUMERAL))
			return true;
		return false;
	}
	public static boolean isNumeralNumeral(Set<TokenType> tokenTypeSet){
		for(TokenType tokenType : tokenTypeSet) {
			if(isNumeralNumeral(tokenType))
				return true;
		}
		return false;
	}
	public static boolean isNumeralNumeral(TaggedToken taggedToken){
		return isNumeralNumeral(taggedToken.getTokenTypeSet());
	}

	public static boolean isInArticle(TokenType tokenType){
		if(tokenType.equals(TokenType.INARTICLE))
			return true;
		return false;
	}
	public static boolean isInArticle(Set<TokenType> tokenTypeSet){
		for(TokenType tokenType : tokenTypeSet) {
			if(isInArticle(tokenType))
				return true;
		}
		return false;
	}
	public static boolean isInArticle(TaggedToken taggedToken){
		return isInArticle(taggedToken.getTokenTypeSet());
	}
	
	public static boolean isPrefix(TokenType tokenType){
		if(tokenType.equals(TokenType.PREFIX))
			return true;
		return false;
	}
	public static boolean isPrefix(Set<TokenType> tokenTypeSet){
		for(TokenType tokenType : tokenTypeSet) {
			if(isPrefix(tokenType))
				return true;
		}
		return false;
	}
	public static boolean isPrefix(TaggedToken taggedToken){
		return isPrefix(taggedToken.getTokenTypeSet());
	}
	
	public static boolean isSuffix(TokenType tokenType){
		if(tokenType.equals(TokenType.SUFFIX))
			return true;
		return false;
	}
	public static boolean isSuffix(Set<TokenType> tokenTypeSet){
		for(TokenType tokenType : tokenTypeSet) {
			if(isSuffix(tokenType))
				return true;
		}
		return false;
	}
	public static boolean isSuffix(TaggedToken taggedToken){
		return isSuffix(taggedToken.getTokenTypeSet());
	}
	
	public static boolean isLinkage(TokenType tokenType){
		if(tokenType.equals(TokenType.LINKAGE))
			return true;
		return false;
	}
	public static boolean isLinkage(Set<TokenType> tokenTypeSet){
		for(TokenType tokenType : tokenTypeSet) {
			if(isLinkage(tokenType))
				return true;
		}
		return false;
	}
	public static boolean isLinkage(TaggedToken taggedToken){
		return isLinkage(taggedToken.getTokenTypeSet());
	}
	
	public static boolean isComma(TokenType tokenType){
		if(tokenType.equals(TokenType.COMMA))
			return true;
		return false;
	}
	public static boolean isComma(Set<TokenType> tokenTypeSet){
		for(TokenType tokenType : tokenTypeSet) {
			if(isComma(tokenType))
				return true;
		}
		return false;
	}
	public static boolean isComma(TaggedToken taggedToken){
		return isComma(taggedToken.getTokenTypeSet());
	}
	
	public static boolean isTimeToken(TokenType tokenType){
		if(isYear(tokenType) || isSeason(tokenType) || isMonth(tokenType) || isWeek(tokenType) || isDate(tokenType) || isTime(tokenType) || isHalfDay(tokenType)
			|| isTimeZone(tokenType) || isEra(tokenType) || isTimeUnit(tokenType) || isDuration(tokenType) || isDayTime(tokenType) || isTimeline(tokenType)
			|| isHoliday(tokenType) || isPeriod(tokenType) || isDecade(tokenType))
				return true;
		return false;
	}
	public static boolean isTimeToken(Set<TokenType> tokenTypeSet){
		for(TokenType tokenType : tokenTypeSet) {
			if(isTimeToken(tokenType))
				return true;
		}
		return false;
	}
	public static boolean isTimeToken(TaggedToken taggedToken){
		return isTimeToken(taggedToken.getTokenTypeSet());
	}
}
