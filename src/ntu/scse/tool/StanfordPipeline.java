/******************************************************************************************
 * Copyright (c) 2017 Xiaoshi Zhong
 * All rights reserved. This program and the accompanying materials are made available
 * under the terms of the GNU lesser Public License v3 which accompanies this distribution,
 * and is available at http://www.gnu.org/licenses/lgpl.html
 * 
 * Contributors : Xiaoshi Zhong, zhongxiaoshi@gmail.com
 * ****************************************************************************************/

package ntu.scse.tool;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;
import ntu.scse.struct.BasicTaggedToken;
import ntu.scse.struct.TaggedToken;

public class StanfordPipeline {
	/** We use StanfordCoreNLP to obtain the POS tags, token position, and character position */
	private Properties props;
	private StanfordCoreNLP pipeline;
	
	public StanfordPipeline(){
		props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, pos, lemma");
        pipeline = new StanfordCoreNLP(props);
	}
	
	public List<BasicTaggedToken> basicTagging(String text){
		Annotation annotation = new Annotation(text);
		pipeline.annotate(annotation);
		
		List<BasicTaggedToken> taggedTokenList = new ArrayList<BasicTaggedToken>();
		
		List<CoreMap> sentences = annotation.get(CoreAnnotations.SentencesAnnotation.class);
		for(CoreMap sentence : sentences){
			List<CoreLabel> tokenList = sentence.get(CoreAnnotations.TokensAnnotation.class);
			for(CoreLabel token : tokenList){
				String word = token.word();
				String lemma = token.lemma();
				String posTag = token.tag();
				int beginCharPosition = token.beginPosition();
				int endCharPosition = token.endPosition();
				
				taggedTokenList.add(new BasicTaggedToken(word, lemma, posTag, beginCharPosition, endCharPosition));
			}
		}
		return taggedTokenList;
	}
	
	public List<TaggedToken> tagging(String text){
		Annotation annotation = new Annotation(text);
		pipeline.annotate(annotation);
		
		List<TaggedToken> taggedTokenList = new ArrayList<TaggedToken>();
		List<CoreMap> sentences = annotation.get(CoreAnnotations.SentencesAnnotation.class);
		for(CoreMap sentence : sentences){
			List<CoreLabel> tokenList = sentence.get(CoreAnnotations.TokensAnnotation.class);
			for(CoreLabel token : tokenList){
				String word = token.word();
				String lemma = token.lemma();
				String posTag = token.tag();
				int tokenPosition = taggedTokenList.size();
				int beginCharPosition = token.beginPosition();
				int endCharPosition = token.endPosition();
				
				taggedTokenList.add(new TaggedToken(word, lemma, posTag, tokenPosition, beginCharPosition, endCharPosition));
			}
		}
		return taggedTokenList;
	}
	
	public List<TaggedToken> tagging(String text, int initialTokenIndex, int initialCharIndex){
		Annotation annotation = new Annotation(text);
		pipeline.annotate(annotation);
		
		List<TaggedToken> taggedTokenList = new ArrayList<TaggedToken>();
		List<CoreMap> sentences = annotation.get(CoreAnnotations.SentencesAnnotation.class);
		for(CoreMap sentence : sentences){
			List<CoreLabel> tokenList = sentence.get(CoreAnnotations.TokensAnnotation.class);
			for(CoreLabel token : tokenList){
				String word = token.word();
				String lemma = token.lemma();
				String posTag = token.tag();
				int tokenPosition = initialTokenIndex + taggedTokenList.size();
				int beginCharPosition = initialCharIndex + token.beginPosition();
				int endCharPosition = initialCharIndex + token.endPosition();
				
				taggedTokenList.add(new TaggedToken(word, lemma, posTag, tokenPosition, beginCharPosition, endCharPosition));
			}
		}
		return taggedTokenList;
	}
}
