/******************************************************************************************
 * Copyright (c) 2017 Xiaoshi Zhong
 * All rights reserved. This program and the accompanying materials are made available
 * under the terms of the GNU lesser Public License v3 which accompanies this distribution,
 * and is available at http://www.gnu.org/licenses/lgpl.html
 * 
 * Contributors : Xiaoshi Zhong, zhongxiaoshi@gmail.com
 * ****************************************************************************************/

package ntu.scse.tool;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.regex.Pattern;

import ntu.scse.util.IOProcess;

public class SynTimeRegex {	
	/**
	 * From StanfordCoreNLP edu.stanford.nlp.ie.regexp.NumberSequenceClassifier.java
	 * */
	public static String MID_REGEX;

	/**POS: CD; Type: DATE*/
	public static String YEAR_REGEX_1;
	public static Pattern YEAR_PATTERN_1;
	public static String YEAR_REGEX_2;
	public static Pattern YEAR_PATTERN_2;
	public static String YEAR_MID_REGEX;
	public static Pattern YEAR_MID_PATTERN;
	
	public static String YEAR_YEAR_REGEX;
	public static Pattern YEAR_YEAR_PATTERN;
	
	/**POS: NNP; Type: DATE*/
	public static String MONTH_REGEX;
	public static Pattern MONTH_PATTERN;
	public static String MONTH_ABBR_REGEX;
	public static Pattern MONTH_ABBR_PATTERN;
	public static String MONTH_MID_REGEX;
	public static Pattern MONTH_MID_PATTERN;
	
	public static String MONTH_MONTH_REGEX;
	public static Pattern MONTH_MONTH_PATTERN;
	
	public static String YEAR_MONTH_REGEX_1;
	public static Pattern YEAR_MONTH_PATTERN_1;
	public static String YEAR_MONTH_REGEX_2;
	public static Pattern YEAR_MONTH_PATTERN_2;
	
	/**POS: NNP; Type: DATE*/
	public static String WEEK_REGEX;
	public static Pattern WEEK_PATTERN;
	public static String WEEK_ABBR_REGEX;
	public static Pattern WEEK_ABBR_PATTERN;
	
	public static String WEEK_WEEK_REGEX;
	public static Pattern WEEK_WEEK_PATTERN;

	/**POS: CD*/
	public static String BASIC_NUMBER_REGEX_1;
	public static Pattern BASIC_NUMBER_PATTERN_1;
	public static String BASIC_NUMBER_REGEX_2;
	public static Pattern BASIC_NUMBER_PATTERN_2;
	
	public static String BASIC_NUMBER_NUMBER_REGEX;
	public static Pattern BASIC_NUMBER_NUMBER_PATTERN;
	
	/**POS: CD*/
	public static String DIGIT_REGEX_1;
	public static Pattern DIGIT_PATTERN_1;
	public static String DIGIT_REGEX_2;
	public static Pattern DIGIT_PATTERN_2;
	
	public static String DIGIT_DIGIT_REGEX;
	public static Pattern DIGIT_DIGIT_PATTERN;
	
	/**POS: JJ, CD */
	public static String ORDINAL_REGEX_1;
	public static Pattern ORDINAL_PATTERN_1;
	public static String ORDINAL_REGEX_2;
	public static Pattern ORDINAL_PATTERN_2 ;
	
	public static String ORDINAL_ORDINAL_REGEX;
	public static Pattern ORDINAL_ORDINAL_PATTERN;
	
	public static String INARTICLE_REGEX;
	public static Pattern INARTICLE_PATTERN;
	
	/** Type: DATE*/
	public static String DATE_REGEX_1;
	public static Pattern DATE_PATTERN_1;
	public static String DATE_REGEX_2;
	public static Pattern DATE_PATTERN_2;
	public static String DATE_REGEX_3;
	public static Pattern DATE_PATTERN_3;
	
	/** Type: TIME*/
	public static String TIME_REGEX_1;
	public static Pattern TIME_PATTERN_1;
	public static String TIME_REGEX_2;
	public static Pattern TIME_PATTERN_2;
	public static String TIME_TIME_REGEX;
	public static Pattern TIME_TIME_PATTERN;
	
	public static String TIME_ZONE_REGEX;
	public static Pattern TIME_ZONE_PATTERN;
	
	public static String ERA_REGEX;
	public static Pattern ERA_PATTERN;
	public static String ERA_YEAR_REGEX;
	public static Pattern ERA_YEAR_PATTERN;
	
	/**POS: NN, VBD; Type: TIME */
	public static String HALFDAY_REGEX_1;
	public static Pattern HALFDAY_PATTERN_1;
	
	public static String HALFDAY_REGEX_2;
	public static Pattern HALFDAY_PATTERN_2;
	
	public static String HALFDAY_HALFDAY_REGEX;
	public static Pattern HALFDAY_HALFDAY_PATTERN;
	
	/**
	 * From english.sutime.txt
	 * https://github.com/stanfordnlp/CoreNLP/tree/master/src/edu/stanford/nlp/time/rules
	 * */
	/**POS: NNS; Type: DATE*/
	public static String DECADE_REGEX;
	public static Pattern DECADE_PATTERN;
	public static String DECADE_MID_REGEX;
	public static Pattern DECADE_MID_PATTERN;
	
	
	/**POS: NN, NNS, NNP; Type: DURATION*/
	public static String TIME_UNIT_REGEX;
	public static Pattern TIME_UNIT_PATTERN;
	
	/**POS: NN, JJ; Type: DURATION*/
	public static String DURATION_REGEX;
	public static Pattern DURATION_PATTERN;
	
	public static String DURATION_DURATION_REGEX_1;
	public static Pattern DURATION_DURATION_PATTERN_1;
	public static String DURATION_DURATION_REGEX_2;
	public static Pattern DURATION_DURATION_PATTERN_2;
	
	/**POS: RB, JJ; Type: SET*/
	public static String PERIOD_REGEX;
	public static Pattern PERIOD_PATTERN;
	
	/**POS: NN, NNP, NNS, RB, JJ; Type: TIME*/
	public static String DAY_TIME_REGEX;
	public static Pattern DAY_TIME_PATTERN;
	public static String DAY_TIME_MID_REGEX;
	public static Pattern DAY_TIME_MID_PATTERN;
	
	/**POS: NN, NNS; Type: DATE*/
	public static String SEASON_REGEX;
	public static Pattern SEASON_PATTERN;
	public static String SEASON_MID_REGEX;
	public static Pattern SEASON_MID_PATTERN;
	
	/**POS: RB, NN; Type: DATE*/
	public static String TIMELINE_REGEX;
	public static Pattern TIMELINE_PATTERN;
	
	/**
	 * Five kinds of semantic modifiers: relative modifiers, frequency modifiers, early late modifiers, approximate modifiers, and operators.
	 * Relative modifier: the, next, following, last, previous, this, coming, past
	 * Frequency modifier: each, every, other, alternate, alternating
	 * Early late modifier: late, early, mid-?, beginning, start, dawn, middle, end, of, in, on
	 * Approximate modifier: about, around, some, exactly, precisely
	 * Operator: this, next, following, previous, last, this, the, coming, following, next, past, previous
	 * */
	/**POS: non-NN**/
	public static String PREFIX_REGEX_1;
	public static Pattern PREFIX_PATTERN_1;
	/**POS: NN**/
	public static String PREFIX_REGEX_2;
	public static Pattern PREFIX_PATTERN_2;
	
	public static String SUFFIX_REGEX;
	public static Pattern SUFFIX_PATTERN;
	
	public static String LINKAGE_REGEX;
	public static Pattern LINKAGE_PATTERN;
	
	public static String COMMA_REGEX;
	public static Pattern COMMA_PATTERN;
	
	/**
	 * From english.holidays.sutime.txt
	 * */
	public static String HOLIDAY_REGEX;
	public static Pattern HOLIDAY_PATTERN;
	
	public static enum TokenType{
		YEAR, YEAR_MID, YEAR_YEAR, MONTH, MONTH_ABBR, MONTH_MID, MONTH_MONTH, YEAR_MONTH, WEEK, WEEK_ABBR, WEEK_WEEK, DATE, TIME, TIME_TIME,
		DAY_TIME, SEASON, TIMELINE, PERIOD, DECADE, HALFDAY, HALFDAY_HALFDAY, TIME_UNIT, DURATION, DURATION_DURATION, HOLIDAY, TIME_ZONE, ERA, 
		PREFIX, SUFFIX, LINKAGE, COMMA, INARTICLE,
		BASIC_NUMBER, BASIC_NUMBER_NUMBER, ORDINAL, ORDINAL_ORDINAL, DIGIT, DIGIT_DIGIT, NUMERAL, NUMERAL_NUMERAL
	}
	
	private static String regexFile = "resources/syntimeregex/SynTimeRegex.txt";
	
	static {
		loadSynTimeRegex();
		inducePattern();
	}
	
	private static void loadSynTimeRegex(){
		BufferedReader br = IOProcess.newReader(regexFile);
		String line;
		try {
			while((line = br.readLine()) != null){
				if(line.trim().length() < 1)
					continue;
				
				String[] items = line.split("\t");
				String regexName = items[0].substring(1, items[0].length() - 1);
				
				if(regexName.equals("MID_REGEX"))
					MID_REGEX = items[1];
				else if(regexName.equals("YEAR_REGEX_1"))
					YEAR_REGEX_1 = items[1];
				else if(regexName.equals("YEAR_REGEX_2"))
					YEAR_REGEX_2 = items[1];
				else if(regexName.equals("MONTH_REGEX"))
					MONTH_REGEX = items[1];
				else if(regexName.equals("MONTH_ABBR_REGEX"))
					MONTH_ABBR_REGEX = items[1];
				else if(regexName.equals("WEEK_REGEX"))
					WEEK_REGEX = items[1];
				else if(regexName.equals("WEEK_ABBR_REGEX"))
					WEEK_ABBR_REGEX = items[1];
				else if(regexName.equals("BASIC_NUMBER_REGEX_1"))
					BASIC_NUMBER_REGEX_1 = items[1];
				else if(regexName.equals("BASIC_NUMBER_REGEX_2"))
					BASIC_NUMBER_REGEX_2 = items[1];
				else if(regexName.equals("DIGIT_REGEX_1"))
					DIGIT_REGEX_1 = items[1];
				else if(regexName.equals("ORDINAL_REGEX_1"))
					ORDINAL_REGEX_1 = items[1];
				else if(regexName.equals("ORDINAL_REGEX_2"))
					ORDINAL_REGEX_2 = items[1];
				else if(regexName.equals("INARTICLE_REGEX"))
					INARTICLE_REGEX = items[1];
				else if(regexName.equals("DATE_REGEX_1"))
					DATE_REGEX_1 = items[1];
				else if(regexName.equals("DATE_REGEX_2"))
					DATE_REGEX_2 = items[1];
				else if(regexName.equals("DATE_REGEX_3"))
					DATE_REGEX_3 = items[1];
				else if(regexName.equals("TIME_REGEX_1"))
					TIME_REGEX_1 = items[1];
				else if(regexName.equals("TIME_REGEX_2"))
					TIME_REGEX_2 = items[1];
				else if(regexName.equals("TIME_ZONE_REGEX"))
					TIME_ZONE_REGEX = items[1];
				else if(regexName.equals("HALFDAY_REGEX_1"))
					HALFDAY_REGEX_1 = items[1];
				else if(regexName.equals("ERA_REGEX"))
					ERA_REGEX = items[1];
				else if(regexName.equals("DECADE_REGEX"))
					DECADE_REGEX = items[1];
				else if(regexName.equals("TIME_UNIT_REGEX"))
					TIME_UNIT_REGEX = items[1];
				else if(regexName.equals("PERIOD_REGEX"))
					PERIOD_REGEX = items[1];
				else if(regexName.equals("DAY_TIME_REGEX"))
					DAY_TIME_REGEX = items[1];
				else if(regexName.equals("SEASON_REGEX"))
					SEASON_REGEX = items[1];
				else if(regexName.equals("TIMELINE_REGEX"))
					TIMELINE_REGEX = items[1];
				else if(regexName.equals("PREFIX_REGEX_1"))
					PREFIX_REGEX_1 = items[1];
				else if(regexName.equals("PREFIX_REGEX_2"))
					PREFIX_REGEX_2 = items[1];
				else if(regexName.equals("SUFFIX_REGEX"))
					SUFFIX_REGEX = items[1];
				else if(regexName.equals("LINKAGE_REGEX"))
					LINKAGE_REGEX = items[1];
				else if(regexName.equals("COMMA_REGEX"))
					COMMA_REGEX = items[1];
				else if(regexName.equals("HOLIDAY_REGEX"))
					HOLIDAY_REGEX = items[1];
			}
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally { }
	}
	
	private static void inducePattern(){
		/**POS: CD; Type: DATE*/
		YEAR_PATTERN_1 = Pattern.compile(YEAR_REGEX_1, Pattern.CASE_INSENSITIVE);
		YEAR_PATTERN_2 = Pattern.compile(YEAR_REGEX_2, Pattern.CASE_INSENSITIVE);
		YEAR_MID_REGEX = "(" + MID_REGEX + ")(" + YEAR_REGEX_1 + "|" + YEAR_REGEX_2 + ")";
		YEAR_MID_PATTERN = Pattern.compile(YEAR_MID_REGEX, Pattern.CASE_INSENSITIVE);
		
		YEAR_YEAR_REGEX = YEAR_REGEX_1 + "-(" + YEAR_REGEX_1 + "|[0-9]{2})";
		YEAR_YEAR_PATTERN = Pattern.compile(YEAR_YEAR_REGEX, Pattern.CASE_INSENSITIVE);
		
		/**POS: NNP; Type: DATE*/
		MONTH_PATTERN = Pattern.compile(MONTH_REGEX, Pattern.CASE_INSENSITIVE);
		MONTH_ABBR_PATTERN = Pattern.compile(MONTH_ABBR_REGEX, Pattern.CASE_INSENSITIVE);
		MONTH_MID_REGEX = "(" + MID_REGEX + ")(" + MONTH_REGEX + "|" + MONTH_ABBR_REGEX + ")";
		MONTH_MID_PATTERN = Pattern.compile(MONTH_MID_REGEX, Pattern.CASE_INSENSITIVE);
		
		MONTH_MONTH_REGEX = "(" + MONTH_REGEX + "|" + MONTH_ABBR_REGEX + ")-(" + MONTH_REGEX + "|" + MONTH_ABBR_REGEX + ")";
		MONTH_MONTH_PATTERN = Pattern.compile(MONTH_MONTH_REGEX, Pattern.CASE_INSENSITIVE);
		
		YEAR_MONTH_REGEX_1 = YEAR_REGEX_1 + "-(" + MONTH_REGEX + "|" + MONTH_ABBR_REGEX + ")";
		YEAR_MONTH_PATTERN_1 = Pattern.compile(YEAR_MONTH_REGEX_1, Pattern.CASE_INSENSITIVE);
		YEAR_MONTH_REGEX_2 = "(" + MONTH_REGEX + "|" + MONTH_ABBR_REGEX + ")-" + YEAR_REGEX_1;
		YEAR_MONTH_PATTERN_2 = Pattern.compile(YEAR_MONTH_REGEX_2, Pattern.CASE_INSENSITIVE);
		
		/**POS: NNP; Type: DATE*/
		WEEK_PATTERN = Pattern.compile(WEEK_REGEX, Pattern.CASE_INSENSITIVE);
		WEEK_ABBR_PATTERN = Pattern.compile(WEEK_ABBR_REGEX, Pattern.CASE_INSENSITIVE);
		
		WEEK_WEEK_REGEX = "(" + WEEK_REGEX + "|" + WEEK_ABBR_REGEX + ")-(" + WEEK_REGEX + "|" + WEEK_ABBR_REGEX + ")";
		WEEK_WEEK_PATTERN = Pattern.compile(WEEK_WEEK_REGEX, Pattern.CASE_INSENSITIVE);

		/**POS: CD*/
		BASIC_NUMBER_PATTERN_1 = Pattern.compile(BASIC_NUMBER_REGEX_1, Pattern.CASE_INSENSITIVE);
		BASIC_NUMBER_PATTERN_2 = Pattern.compile(BASIC_NUMBER_REGEX_2, Pattern.CASE_INSENSITIVE);
		
		BASIC_NUMBER_NUMBER_REGEX = "(" + BASIC_NUMBER_REGEX_1 + ")-(" + BASIC_NUMBER_REGEX_1 + ")";
		BASIC_NUMBER_NUMBER_PATTERN = Pattern.compile(BASIC_NUMBER_NUMBER_REGEX, Pattern.CASE_INSENSITIVE);
		
		/**POS: CD*/
		DIGIT_PATTERN_1 = Pattern.compile(DIGIT_REGEX_1, Pattern.CASE_INSENSITIVE);
		DIGIT_REGEX_2 = DIGIT_REGEX_1 + "[/\\.]" + DIGIT_REGEX_1;
		DIGIT_PATTERN_2 = Pattern.compile(DIGIT_REGEX_2, Pattern.CASE_INSENSITIVE);
		
		DIGIT_DIGIT_REGEX = DIGIT_REGEX_1 + "-" + DIGIT_REGEX_1;
		DIGIT_DIGIT_PATTERN = Pattern.compile(DIGIT_DIGIT_REGEX);
		
		/**POS: JJ, CD */
		ORDINAL_PATTERN_1 = Pattern.compile(ORDINAL_REGEX_1, Pattern.CASE_INSENSITIVE);
		ORDINAL_PATTERN_2 = Pattern.compile(ORDINAL_REGEX_2, Pattern.CASE_INSENSITIVE);
		
		ORDINAL_ORDINAL_REGEX = "(" + ORDINAL_REGEX_1 + ")-(" + ORDINAL_REGEX_1 + ")";
		ORDINAL_ORDINAL_PATTERN = Pattern.compile(ORDINAL_ORDINAL_REGEX, Pattern.CASE_INSENSITIVE);
		
		INARTICLE_PATTERN = Pattern.compile(INARTICLE_REGEX, Pattern.CASE_INSENSITIVE);
		
		/** Type: DATE*/
		DATE_PATTERN_1 = Pattern.compile(DATE_REGEX_1);
		DATE_PATTERN_2 = Pattern.compile(DATE_REGEX_2);
		DATE_PATTERN_3 = Pattern.compile(DATE_REGEX_3, Pattern.CASE_INSENSITIVE);
		
		/** Type: TIME*/
		TIME_PATTERN_1 = Pattern.compile(TIME_REGEX_1);
		TIME_PATTERN_2 = Pattern.compile(TIME_REGEX_2);
		TIME_TIME_REGEX = "(" + TIME_REGEX_1 + "|" + TIME_REGEX_2 + ")-(" + TIME_REGEX_1 + "|" + TIME_REGEX_2 + ")";
		TIME_TIME_PATTERN = Pattern.compile(TIME_TIME_REGEX);
		
		TIME_ZONE_PATTERN = Pattern.compile(TIME_ZONE_REGEX, Pattern.CASE_INSENSITIVE);
		
		ERA_PATTERN = Pattern.compile(ERA_REGEX, Pattern.CASE_INSENSITIVE);
		ERA_YEAR_REGEX = DIGIT_REGEX_1 + "(" + ERA_REGEX + ")";
		ERA_YEAR_PATTERN = Pattern.compile(ERA_YEAR_REGEX, Pattern.CASE_INSENSITIVE);
		
		/**POS: NN, VBD; Type: TIME */
		HALFDAY_PATTERN_1 = Pattern.compile(HALFDAY_REGEX_1, Pattern.CASE_INSENSITIVE);
		
		HALFDAY_REGEX_2 = "(" + DIGIT_REGEX_1 + "|" + DIGIT_REGEX_2 + "|" + TIME_REGEX_1 + "|" + TIME_REGEX_2 + ")(" + HALFDAY_REGEX_1 + ")";
		HALFDAY_PATTERN_2 = Pattern.compile(HALFDAY_REGEX_2, Pattern.CASE_INSENSITIVE);
		
		HALFDAY_HALFDAY_REGEX = HALFDAY_REGEX_2 + "-" + HALFDAY_REGEX_2;
		HALFDAY_HALFDAY_PATTERN = Pattern.compile(HALFDAY_HALFDAY_REGEX, Pattern.CASE_INSENSITIVE);
		
		/**
		 * From english.sutime.txt
		 * */
		/**POS: NNS; Type: DATE*/
		DECADE_PATTERN = Pattern.compile(DECADE_REGEX, Pattern.CASE_INSENSITIVE);
		DECADE_MID_REGEX = "(" + MID_REGEX + ")(" + DECADE_REGEX + ")";
		DECADE_MID_PATTERN = Pattern.compile(DECADE_MID_REGEX, Pattern.CASE_INSENSITIVE);
		
		
		/**POS: NN, NNS, NNP; Type: DURATION*/
		TIME_UNIT_PATTERN = Pattern.compile(TIME_UNIT_REGEX, Pattern.CASE_INSENSITIVE);
		
		/**POS: NN, JJ; Type: DURATION*/
		DURATION_REGEX = "(" + DIGIT_REGEX_1 + "|" + DIGIT_REGEX_2 + "|" + BASIC_NUMBER_REGEX_1 + "|" + BASIC_NUMBER_REGEX_2 + "|" + ORDINAL_REGEX_1 + "|" + ORDINAL_REGEX_2 + "|" + INARTICLE_REGEX + ")-?(" + TIME_UNIT_REGEX + ")";
		DURATION_PATTERN = Pattern.compile(DURATION_REGEX, Pattern.CASE_INSENSITIVE);
		
		DURATION_DURATION_REGEX_1 = "(" + DIGIT_DIGIT_REGEX + "|" + BASIC_NUMBER_NUMBER_REGEX + "|" + ORDINAL_ORDINAL_REGEX + ")-?(" + TIME_UNIT_REGEX +")";
		DURATION_DURATION_PATTERN_1 = Pattern.compile(DURATION_DURATION_REGEX_1, Pattern.CASE_INSENSITIVE);
		DURATION_DURATION_REGEX_2 = DURATION_REGEX + "-" + DURATION_REGEX;
		DURATION_DURATION_PATTERN_2 = Pattern.compile(DURATION_DURATION_REGEX_2, Pattern.CASE_INSENSITIVE);
		
		/**POS: RB, JJ; Type: SET*/
		PERIOD_PATTERN = Pattern.compile(PERIOD_REGEX, Pattern.CASE_INSENSITIVE);
		
		/**POS: NN, NNP, NNS, RB, JJ; Type: TIME*/
		DAY_TIME_PATTERN = Pattern.compile(DAY_TIME_REGEX, Pattern.CASE_INSENSITIVE);
		DAY_TIME_MID_REGEX = "(" + MID_REGEX + ")(" + DAY_TIME_REGEX + ")";
		DAY_TIME_MID_PATTERN = Pattern.compile(DAY_TIME_MID_REGEX, Pattern.CASE_INSENSITIVE);
		
		/**POS: NN, NNS; Type: DATE*/
		SEASON_PATTERN = Pattern.compile(SEASON_REGEX, Pattern.CASE_INSENSITIVE);
		SEASON_MID_REGEX = "(" + MID_REGEX + ")(" + SEASON_REGEX + ")";
		SEASON_MID_PATTERN = Pattern.compile(SEASON_MID_REGEX, Pattern.CASE_INSENSITIVE);
		
		/**POS: RB, NN; Type: DATE*/
		TIMELINE_PATTERN = Pattern.compile(TIMELINE_REGEX, Pattern.CASE_INSENSITIVE);
		
		/**
		 * Modifiers
		 * */
		/**POS: non-NN**/
		PREFIX_PATTERN_1 = Pattern.compile(PREFIX_REGEX_1, Pattern.CASE_INSENSITIVE);
		/**POS: NN**/
		PREFIX_PATTERN_2 = Pattern.compile(PREFIX_REGEX_2, Pattern.CASE_INSENSITIVE);
		
		SUFFIX_PATTERN = Pattern.compile(SUFFIX_REGEX, Pattern.CASE_INSENSITIVE);
		
		LINKAGE_PATTERN = Pattern.compile(LINKAGE_REGEX, Pattern.CASE_INSENSITIVE);
		
		COMMA_PATTERN = Pattern.compile(COMMA_REGEX);
		
		/**
		 * From english.holidays.sutime.txt
		 * */
		HOLIDAY_PATTERN = Pattern.compile(HOLIDAY_REGEX, Pattern.CASE_INSENSITIVE);
	}
}
