/******************************************************************************************
 * Copyright (c) 2017 Xiaoshi Zhong
 * All rights reserved. This program and the accompanying materials are made available
 * under the terms of the GNU lesser Public License v3 which accompanies this distribution,
 * and is available at http://www.gnu.org/licenses/lgpl.html
 * 
 * Contributors : Xiaoshi Zhong, zhongxiaoshi@gmail.com
 * ****************************************************************************************/

package ntu.scse.tool;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;

import ntu.scse.struct.Article;
import ntu.scse.struct.TaggedToken;
import ntu.scse.tool.StanfordPipeline;

public class ParseTimeML {
	private HtmlCleaner cleaner;
	private StanfordPipeline pipeline;
	
	public ParseTimeML(){
		cleaner = new HtmlCleaner();
		pipeline = new StanfordPipeline();
	}
	
	public Article parseTimeML(String inputTmlFile){
		return parseTimeML(new File(inputTmlFile));
	}
	
	public Article parseTimeML(File tmlFile){
		List<TaggedToken> articleTaggedTokenList = new ArrayList<TaggedToken>();
		StringBuffer articleSb = new StringBuffer();
		
		try {
			TagNode node = cleaner.clean(tmlFile);
			Object[] textNodes = node.evaluateXPath("//TimeML//TEXT");
			if(textNodes == null || textNodes.length == 0){
				//throw new RuntimeException("Could not parse the XPath.");
				System.out.println("Cannot parse the XPath of file: " + tmlFile.getName());
				return null;
			}

			for(Object obj : textNodes){
				TagNode textNode = (TagNode) obj;
				
				String text = textNode.getText().toString();
				articleSb.append(text);
				
				List<TaggedToken> textTaggedTokenList = pipeline.tagging(text);
					
				articleTaggedTokenList.addAll(textTaggedTokenList);
			}
		} catch (IOException | XPatherException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally { }
		
		Article article = new Article(articleSb.toString(), articleTaggedTokenList);
		
		return article;
	}
}
