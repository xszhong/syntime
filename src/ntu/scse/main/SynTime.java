/******************************************************************************************
 * Copyright (c) 2017 Xiaoshi Zhong
 * All rights reserved. This program and the accompanying materials are made available
 * under the terms of the GNU lesser Public License v3 which accompanies this distribution,
 * and is available at http://www.gnu.org/licenses/lgpl.html
 * 
 * Contributors : Xiaoshi Zhong, zhongxiaoshi@gmail.com
 * ****************************************************************************************/

package ntu.scse.main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import ntu.scse.struct.Article;
import ntu.scse.struct.TaggedToken;
import ntu.scse.struct.TimeSegment;
import ntu.scse.tool.InduceTokenType;
import ntu.scse.tool.ParseTimeML;
import ntu.scse.tool.StanfordPipeline;
import ntu.scse.tool.TimeML;
import ntu.scse.tool.SynTimeRegex.TokenType;
import ntu.scse.util.IOProcess;

public class SynTime {
	private StanfordPipeline pipeline;
	private ParseTimeML parseTimeML;
	
	public SynTime(){
		pipeline = new StanfordPipeline();
		parseTimeML = new ParseTimeML();
	}
	
	public void extractTimexFromTmlFolder(String inputTmlDir, String outputTmlDir){
		inputTmlDir = IOProcess.checkPath(inputTmlDir);
		outputTmlDir = IOProcess.checkPath(outputTmlDir);
		
		File[] inputTmlFiles = IOProcess.getFiles(inputTmlDir);
		if(inputTmlFiles == null)
			IOProcess.findNoPath(inputTmlDir);
		
		for(File inputTmlFile : inputTmlFiles){
			String filename = inputTmlFile.getName();
			extractTimexFromTmlFile(inputTmlFile, outputTmlDir + filename);
		}
	}
	
	public void extractTimexFromTmlFile(String inputTmlFile, String outputTmlFile){
		extractTimexFromTmlFile(new File(inputTmlFile), outputTmlFile);
	}
	
	public void extractTimexFromTmlFile(File inputTmlFile, String outputTmlFile){
		String docId = TimeML.extractTimeMLDocId(inputTmlFile);
		String dct = TimeML.extractTimeMLDCT(inputTmlFile);
		
		Article article = parseTimeML.parseTimeML(inputTmlFile);
		
		String result = extractTimex(article, dct);
		TimeML.saveToTimeML(docId, dct, result, outputTmlFile);
	}
	
	public String extractTimexFromText(String inputText, String date){
		List<TaggedToken> taggedTokenList = pipeline.tagging(inputText);
		Article article = new Article(inputText, taggedTokenList);
		
		return extractTimex(article, date);
	}
	
	private String extractTimex(Article article, String date){		
		String text = article.getText();
		List<TaggedToken> taggedTokenList = article.getTaggedTokenList();
		List<Integer> timeTokenList = identifyTimeToken(taggedTokenList);
		
		List<TimeSegment> timeSegmentList = identifyTimeSegment(taggedTokenList, timeTokenList);
		
//		String tmlText = InduceTimeMLText(article, segmentList, date);
		String tmlText = InduceTimeMLText(text, taggedTokenList, timeSegmentList, date);
		
		taggedTokenList.clear();
		timeTokenList.clear();
		timeSegmentList.clear();
		
		return tmlText;		
	}
	
	private String InduceTimeMLText(Article article, List<TimeSegment> timeSegmentList, String date){
		String text = article.getText();
		List<TaggedToken> taggedTokenList = article.getTaggedTokenList();
		return InduceTimeMLText(text, taggedTokenList, timeSegmentList, date);
	}
	
	private String InduceTimeMLText(String text, List<TaggedToken> taggedTokenList, List<TimeSegment> timeSegmentList, String date){
		StringBuffer tmlSb = new StringBuffer();
		String type = "DATE";
		String value = date;
		int tid = 1;
		int lastCharPosition = 0;
			
		if(! timeSegmentList.isEmpty()){
			boolean isTimex = true;
			int timexBeginTokenPosition = timeSegmentList.get(0).getBeginTokenPosition();
			int timexEndTokenPosition = timeSegmentList.get(0).getEndTokenPosition();

			for(int i = 1; i < timeSegmentList.size(); i++){
				TimeSegment timeSegment = timeSegmentList.get(i);
				int segmentBeginTokenPosiiton = timeSegment.getBeginTokenPosition();
				int segmentEndTokenPosition = timeSegment.getEndTokenPosition();
				if(timexEndTokenPosition + 1 == segmentBeginTokenPosiiton || timexEndTokenPosition > segmentBeginTokenPosiiton){
					isTimex = false;
				}else if(timexEndTokenPosition == segmentBeginTokenPosiiton){
					if(taggedTokenList.get(segmentBeginTokenPosiiton).isComma()){
						if(segmentBeginTokenPosiiton == 0 || segmentBeginTokenPosiiton + 1 == taggedTokenList.size())
							isTimex = true;
						else{
							TaggedToken commaPreToken = taggedTokenList.get(segmentBeginTokenPosiiton - 1);
							TaggedToken commaSufToken = taggedTokenList.get(segmentBeginTokenPosiiton + 1);
							
							if((commaPreToken.isTimeToken() || commaPreToken.isNumeral()) && commaSufToken.isTimeToken() && ! commaPreToken.sameTokenTypeWith(commaSufToken))
								isTimex = false;
							else
								isTimex = true;
						}
					}else if(taggedTokenList.get(segmentBeginTokenPosiiton).isLinkage())
						isTimex = true;
					else
						isTimex = false;
				}else
					isTimex = true;
				
				if(! isTimex){
					timexEndTokenPosition = segmentEndTokenPosition;
				}else{
					if(taggedTokenList.get(timexBeginTokenPosition).isComma() || taggedTokenList.get(timexBeginTokenPosition).isLinkage() || taggedTokenList.get(timexBeginTokenPosition).getTag().equals("IN"))
						timexBeginTokenPosition ++;
					if(taggedTokenList.get(timexEndTokenPosition).isComma() || taggedTokenList.get(timexEndTokenPosition).isLinkage())
						timexEndTokenPosition --;
					
					TaggedToken timexBeginTaggedToken = taggedTokenList.get(timexBeginTokenPosition);
					TaggedToken timexEndTaggedToken = taggedTokenList.get(timexEndTokenPosition);
					String timexEndToken = timexEndTaggedToken.getToken();
					int beginCharPosition = timexBeginTaggedToken.getBeginCharPosition();
					int endCharPosition = timexEndTaggedToken.getEndCharPosition();
					
					tmlSb.append(text.substring(lastCharPosition, beginCharPosition));
					lastCharPosition = beginCharPosition;
						
					for(int index = timexBeginTokenPosition; index <= timexEndTokenPosition; index ++){
						TaggedToken temTaggedToken = taggedTokenList.get(index);
						if(temTaggedToken.isYearYear() || temTaggedToken.isYearMonth() || temTaggedToken.isMonthMonth() || temTaggedToken.isWeekWeek() || temTaggedToken.isTimeTime()
							|| temTaggedToken.isHalfDayHalfDay() || temTaggedToken.isNumeralNumeral()){
								
							int temBeginCharPosition = temTaggedToken.getBeginCharPosition();
							String[] items = temTaggedToken.getToken().split("-");
							String timex = text.substring(lastCharPosition, temBeginCharPosition + items[0].length());
							tmlSb.append(TimeML.getTIMEX3(tid, type, value, timex) + "-");
							lastCharPosition = temBeginCharPosition + items[0].length() + "-".length();
							tid ++;
						}
					}
					
					if(timexEndToken.endsWith("'s")){
						String timex = text.substring(lastCharPosition, endCharPosition - 2);
						tmlSb.append(TimeML.getTIMEX3(tid, type, value, timex));
						lastCharPosition = endCharPosition - 2;
					}else if(timexEndToken.endsWith("s") && (timexEndTokenPosition + 1 < taggedTokenList.size()) && taggedTokenList.get(timexEndTokenPosition + 1).getToken().equals("'")){
						String timex = text.substring(lastCharPosition, taggedTokenList.get(timexEndTokenPosition + 1).getEndCharPosition());
						tmlSb.append(TimeML.getTIMEX3(tid, type, value, timex));
						lastCharPosition = taggedTokenList.get(timexEndTokenPosition + 1).getEndCharPosition();
					}else{
						String timex = text.substring(lastCharPosition, endCharPosition);
						tmlSb.append(TimeML.getTIMEX3(tid, type, value, timex));
						lastCharPosition = endCharPosition;
					}
					tid ++;
						
					timexBeginTokenPosition = segmentBeginTokenPosiiton;
					timexEndTokenPosition = segmentEndTokenPosition;
				}
			}
				
			if(taggedTokenList.get(timexBeginTokenPosition).isComma() || taggedTokenList.get(timexBeginTokenPosition).isLinkage() || taggedTokenList.get(timexBeginTokenPosition).getTag().equals("IN"))
				timexBeginTokenPosition ++;
			if(taggedTokenList.get(timexEndTokenPosition).isComma() || taggedTokenList.get(timexEndTokenPosition).isLinkage())
				timexEndTokenPosition --;
			
			TaggedToken timexBeginTaggedToken = taggedTokenList.get(timexBeginTokenPosition);
			TaggedToken timexEndTaggedToken = taggedTokenList.get(timexEndTokenPosition);
			String timexEndToken = timexEndTaggedToken.getToken();
			int beginCharPosition = timexBeginTaggedToken.getBeginCharPosition();
			int endCharPosition = timexEndTaggedToken.getEndCharPosition();
			
				
			tmlSb.append(text.substring(lastCharPosition, beginCharPosition));
			lastCharPosition = beginCharPosition;

			for(int index = timexBeginTokenPosition; index <= timexEndTokenPosition; index ++){
				TaggedToken temTaggedToken = taggedTokenList.get(index);
				if(temTaggedToken.isYearYear() || temTaggedToken.isYearMonth() || temTaggedToken.isMonthMonth() || temTaggedToken.isWeekWeek() || temTaggedToken.isTimeTime()
					|| temTaggedToken.isHalfDayHalfDay() || temTaggedToken.isNumeralNumeral()){
						
					int temBeginCharPosition = temTaggedToken.getBeginCharPosition();
					String[] items = temTaggedToken.getToken().split("-");
					String timex = text.substring(lastCharPosition, temBeginCharPosition + items[0].length());
					tmlSb.append(TimeML.getTIMEX3(tid, type, value, timex) + "-");
					lastCharPosition = temBeginCharPosition + items[0].length() + "-".length();
					tid ++;
				}
			}
				
			if(timexEndToken.endsWith("'s")){
				String timex = text.substring(lastCharPosition, endCharPosition - 2);
				tmlSb.append(TimeML.getTIMEX3(tid, type, value, timex));
				lastCharPosition = endCharPosition - 2;
			}else if(timexEndToken.endsWith("s") && (timexEndTokenPosition + 1 < taggedTokenList.size()) && taggedTokenList.get(timexEndTokenPosition + 1).getToken().equals("'")){
				String timex = text.substring(lastCharPosition, taggedTokenList.get(timexEndTokenPosition + 1).getEndCharPosition());
				tmlSb.append(TimeML.getTIMEX3(tid, type, value, timex));
				lastCharPosition = taggedTokenList.get(timexEndTokenPosition + 1).getEndCharPosition();
			}else{
				String timex = text.substring(lastCharPosition, endCharPosition);
				tmlSb.append(TimeML.getTIMEX3(tid, type, value, timex));
				lastCharPosition = endCharPosition;
			}
			
			tid++;
		}
		
		tmlSb.append(text.substring(lastCharPosition));
		
		return tmlSb.toString();
	}
	
	private List<TimeSegment> identifyTimeSegment(List<TaggedToken> taggedTokenList, List<Integer> timeTokenList){
		List<TimeSegment> timeSegmentList = new ArrayList<TimeSegment>();
		int firstToken = 0;
		int lastToken = taggedTokenList.size() - 1;
		for (int i = 0; i < timeTokenList.size(); i++) {
			int timeTokenPosition = timeTokenList.get(i);

			int beginToken = timeTokenPosition;
			int endToken = timeTokenPosition;

			TaggedToken taggedTimeToken = taggedTokenList.get(timeTokenPosition);
			if (taggedTimeToken.isPeriod() || taggedTimeToken.isDuration()) {
				timeSegmentList.add(new TimeSegment(timeTokenPosition, beginToken, endToken));
				continue;
			}

			int leftBound = firstToken;
			int rightBound = lastToken;
			if (i > 0)
				leftBound = timeTokenList.get(i - 1) + 1;
			if (i < timeTokenList.size() - 1)
				rightBound = timeTokenList.get(i + 1) - 1;

			/** Search its left */
			boolean findLeftNewTimeToken = false;
			int leftTimeTokenPosition = -1;
			int leftBeginToken = leftTimeTokenPosition;
			int leftEndToken = leftTimeTokenPosition;
			for (int j = timeTokenPosition - 1; j >= leftBound; j--) {
				TaggedToken taggedPreMod = taggedTokenList.get(j);
				if (taggedPreMod.isPrefix() || taggedPreMod.isNumeral() || taggedPreMod.isInArticle())
					beginToken = j;
				else if (taggedPreMod.isComma()) {
					beginToken = j;
					break;
				} else if (taggedPreMod.isLinkage()) {
					if (j - 1 >= leftBound && taggedTimeToken.isTimeToken()
							&& taggedTokenList.get(j - 1).isNumeral()) {
						findLeftNewTimeToken = true;
						leftTimeTokenPosition = j - 1;
						leftBeginToken = leftTimeTokenPosition;
						leftEndToken = leftTimeTokenPosition;
						for (int k = leftTimeTokenPosition - 1; k >= leftBound; k--) {
							if (taggedPreMod.isPrefix() || taggedPreMod.isNumeral() || taggedPreMod.isInArticle())
								leftBeginToken = k;
							else
								break;
						}
						beginToken = j + 1;
					} else
						beginToken = j;
					break;
				} else
					break;
			}

			/** Search its right */
			boolean findRightNewTimeToken = false;
			int rightTimeTokenPosition = 0;
			int rightBeginToken = rightTimeTokenPosition;
			int rightEndToken = rightTimeTokenPosition;
			for (int j = timeTokenPosition + 1; j <= rightBound; j++) {
				TaggedToken taggedSufMod = taggedTokenList.get(j);
				if (taggedSufMod.isSuffix() || taggedSufMod.isNumeral())
					endToken = j;
				else if (taggedSufMod.isComma()) {
					endToken = j;
					break;
				} else if (taggedSufMod.isLinkage()) {
					if (j + 1 <= rightBound && taggedTimeToken.isTimeToken() && taggedTokenList.get(j + 1).isNumeral()) {
						findRightNewTimeToken = true;
						rightTimeTokenPosition = j + 1;
						rightBeginToken = rightTimeTokenPosition;
						rightEndToken = rightTimeTokenPosition;
						for (int k = rightTimeTokenPosition + 1; k <= rightBound; k++) {
							if (taggedSufMod.isSuffix() || taggedSufMod.isNumeral())
								rightEndToken = k;
							else
								break;
						}
						endToken = j - 1;
					} else
						endToken = j;
					break;
				} else
					break;
			}

			if (findLeftNewTimeToken)
				timeSegmentList.add(new TimeSegment(leftTimeTokenPosition, leftBeginToken, leftEndToken));
			timeSegmentList.add(new TimeSegment(timeTokenPosition, beginToken, endToken));
			if (findRightNewTimeToken)
				timeSegmentList.add(new TimeSegment(rightTimeTokenPosition, rightBeginToken, rightEndToken));
		}
		
		return timeSegmentList;
	}
	
	private List<Integer> identifyTimeToken(List<TaggedToken> taggedTokenList){
		List<Integer> timeTokenList = new ArrayList<Integer>();
		
		for(int i = 0; i < taggedTokenList.size(); i++){
			TaggedToken taggedToken = taggedTokenList.get(i);
			String token = taggedToken.getToken();
			String tag = taggedToken.getTag();
			
			Set<TokenType> tokenTypeSet = InduceTokenType.getTokenType(token, tag);
			taggedToken.setTokenTypeSet(tokenTypeSet);

			if(taggedToken.isHalfDay()){
				if(token.equals("am") || token.equals("pm")){
					boolean isHalfDay = true;
					if(i == 0 || (! InduceTokenType.isNumeral(taggedTokenList.get(i - 1)) && ! InduceTokenType.isTime(taggedTokenList.get(i - 1))))
						isHalfDay = false;
					
					if(! isHalfDay)
						taggedToken.removeTokenType();
				}	
			}else if(taggedToken.isTimeZone()){
				boolean isTimeZone = true;
				if(i == 0 || ! taggedTokenList.get(i - 1).isTime() && ! taggedTokenList.get(i - 1).isHalfDay() && ! taggedTokenList.get(i - 1).isNumeral())
					isTimeZone = false;
				
				if(! isTimeZone)
					taggedToken.removeTokenType();
			}else if(taggedToken.isEra()){
				boolean isEra = true;
				if(i == 0 || ! taggedTokenList.get(i - 1).isNumeral())
					isEra = false;
				
				if(! isEra)
					taggedToken.removeTokenType();
			} else if (taggedToken.isYear()) {
				if (i > 0 && (taggedTokenList.get(i - 1).isInArticle() || taggedTokenList.get(i - 1).getToken().toLowerCase().equals("the")))
					taggedTokenList.get(i - 1).removeTokenType();
			}
			
			if(taggedToken.isTimeToken())
				timeTokenList.add(Integer.valueOf(i));
		}
		
		return timeTokenList;
	}
}
