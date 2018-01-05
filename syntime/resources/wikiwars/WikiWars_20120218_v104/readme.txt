Corpus version: 1.0.4
Corpus creation date: 2011-10-11
Corpus release date:  2012-02-18

Pawel Mazur, Pawel.Mazur@mq.edu.au
Robert Dale, Robert.Dale@mq.edu.au


                              The WikiWars Corpus


1. Introduction

This is a README file for the distribution of the WikiWars corpus, which is a
collection of documents describing the course of some military conflicts. 
These documents have been enriched with annotations of temporal expressions. 
The primary purpose of the corpus is to help to carry out research in the area 
of natural language processing (NLP) focused on extraction of temporal 
information.

This file presents information about licensing of the corpus, the methodology 
used for collecting and annotating data and some basic statistics concerning 
the content of the corpus. 


2. Licencing Terms

All the documents in the corpus are sources from English Wikipedia. As a 
consequence, the corpus is released under the Creative Commons 
Attribution-ShareAlike 3.0 License - see the licence at
http://en.wikipedia.org/wiki/Wikipedia:Text_of_Creative_Commons_Attribution-
ShareAlike_3.0_Unported_License.

See also the Terms of Use at http://wikimediafoundation.org/wiki/Terms_of_Use.

It is important to note that each time 'you distribute or publicly perform the 
work or a collection, the licensor offers to the recipient a license to the 
work on the same terms and conditions as the license granted to you under this 
license.'


3. How We Collected the Data

First, a query to Google for "most famous wars in history" returned as the top 
result a Yahoo Answers page titled ‘10 most famous wars in history?’ (located 
at http://answers.yahoo.com/question/index?qid=20090209222618AAauMN1, last 
accessed on 2009-12-20). It listed the wars presented in the table below, in 
which we also provide the years of the wars and links to the corresponding 
Wikipedia articles.

NAME OF THE WAR       YEAR SPAN     PAGE NAME AT http://en.wikipedia.org/wiki/
World War II          1939–1945     World_War_II
World War I           1914–1918     World_War_I
American Civil War    1861–1865     American_Civil_War
American Revolution   1775–1783     American_Revolutionary_War
Vietnam War           1955–1975     Vietnam_War
Korean War            1950–1953     Korean_War
Iraq War              2003–...      Iraq_War
French Revolution     1789–1799     French_Revolution
Persian Wars          499–450 BC    Greco-Persian_Wars
Punic Wars            264–146 BC    Punic_Wars

We then queried Google for "the biggest wars" and again we chose the top 
result, which was a page titled ‘The 20 biggest wars of the 20th century’ 
(located at http://users.erols.com/mwhite28/war-list.htm, last accessed on 
2009-12-20). It listed the biggest wars in the 20th century measured by the 
number of military victims. The names of these wars are presented in the table 
below along with years and links to the Wikipedia articles about them.

NAME OF THE WAR        YEAR SPAN    PAGE NAME AT http://en.wikipedia.org/wiki/
World War II           1939–1945    World_War_II
World War I            1914–1918    World_War_I
Korean War             1950–1953    Korean_War
Chinese Civil War      1945–1949    Chinese_Civil_War
Vietnam War            1955–1975    Vietnam_War
Iran-Iraq War          1980–1988    IranIraq_War
Russian Civil War      1917–1923    Russian_Civil_War
Chinese Civil War      1927–1937    Chinese_Civil_War
French Indochina War   1946–1954    First_Indochina_War
Mexican Revolution     1911–1920    Mexican_Revolution
Spanish Civil War      1936–1939    Spanish_Civil_War
French-Algerian War    1954–1962    Algerian_War
Soviet-Afghanistan War 1979–1989    Soviet_war_in_Afghanistan
Russo-Japanese War     1904–1905    Russo-Japanese_War
Riffian War            1921–1926    no page found
1st Sudanese Civil War 1955–1972    First_Sudanese_Civil_War
Russo-Polish War       1919–1921    PolishSoviet_War
Biafran War            1967–1970    Nigerian_Civil_War
Chaco War              1932–1935    Chaco_War
Abyssinian War         1935–1936    Second_Italo-Abyssinian_War

We then combined the two lists, eliminating duplicates; this resulted in 25 
distinct Wikipedia pages (note that the second list considered two periods of 
the Chinese Civil War separately; because both these events are described at 
the same URL in Wikipedia and because there were also other periods of the 
civil war in China in the 20th century, we treated all these periods as part 
of one war). 
As we were unable to find any article on Wikipedia on the Riffian War, we 
dropped it from the list. We also considered the articles about the First 
Sudanese Civil War and the Chaco War to be too short, with very few temporal 
expressions and no presentation of the course of the events. Consequently, we 
decided these were not interesting or useful for our experiments, and we 
dropped them too. This resulted in a total of 22 articles being included in 
the corpus.


4. Text Extraction and Preprocessing

To prepare the corpus, we first manually copied text from those sections of 
the web pages that described the course of the wars. This involved manual 
removal of picture captions and cross-page links. We then ran a script over 
the results of this extraction process to convert some Unicode characters into 
ASCII (ligatures, spaces, apostrophes, hyphens and other punctuation marks), 
and to remove citation links and a variety of other Wikipedia annotations. 
Finally, we converted each of the text files into an SGML file: each document 
was wrapped in one DOC tag, inside which there are DOCID, DOCTYPE and DATETIME 
tags. The document time stamp is the date and time at which we downloaded the 
page from Wikipedia to our local repository. The proper content of the article 
is wrapped in a TEXT tag. This document structure intentionally follows that 
of the ACE 2005 and 2007 documents, so as to make the processing and 
evaluation of the WikiWars data highly compatible with the tools used to 
process the ACE corpora.


5. The Annotation Process

Having prepared the input SGML documents, we then processed them with the 
DANTE temporal expression tagger (see [1]). DANTE outputs the original SGML 
documents augmented with an inline TIMEX2 annotation for each temporal 
expression found. These output files can be imported to Callisto [2], an 
annotation tool that supports TIMEX2 annotations. Using a temporal expression 
tagger as a first-pass annotation tool not only significantly reduces the 
amount of human annotation effort required (creating a tag from scratch 
requires a number of clicks in the annotation tool), but also helps to 
minimize the number of errors that arise from overlooking markable expressions 
through 'annotator blindness'. The annotations produced by DANTE were then 
manually corrected in Callisto via the following process. First, Annotator #1 
corrected all the annotations produced by DANTE, both in terms of extent and 
the values provided for TIMEX2 attributes. This process also included the 
annotation of any temporal expressions missed by the automatic tagger, and the 
removal of spurious matches. Then, Annotator #2 checked all the revised 
annotations and prepared a list of errors found and doubts or queries raised 
in regard to potentially problematic annotations. Annotator #1 then verified 
and fixed the errors, after discussion in the case of disagreements. The final 
SGML files containing inline annotations were then transformed into ACE APF 
XML annotation files, this being the stand-off markup format developed for the 
ACE evaluations. This transformation was carried out using the tern2apf [3] 
tool developed by NIST for the ACE 2004 evaluations, with some modifications 
introduced by us to adjust the tool to support ACE 2005 documents and to add a 
document ID as part of the ID of a TIMEX2 annotation (so that all annotations 
would have corpus-wide unique IDs). The resulting gold standard annotations 
are thus available in two formats: one contains the original documents 
enriched with inline annotations, and the other consists of stand-off 
annotations in the ACE APF format.
The changes introduced in the subsequent versions of the corpus are described 
in Section 9.


6. Corpus Statistics

The corpus contains 22 documents with a total of almost 120,000 tokens (these 
were counted using GATE’s default English tokeniser; hyphenated words, e.g. 
'British-held' and 'co-operation', were treated as single tokens. For more 
information on GATE see [4].) and 2,681 temporal expressions annotated in the 
TIMEX2 (September 2005) format [5]. Below are statistics on the individual 
documents that make up the corpus:

Document ID                   Tokens        TIMEX2    Tokens/TIMEX2
01_WW2                         5,593          170        32.9
02_WW1                        10,370          265        39.1
03_AmCivWar                    3,529           75        47.1
04_AmRevWar                    5,695          147        38.7
05_VietnamWar                 11,640          245        47.5
06_KoreanWar                   5,992          149        40.2
07_IraqWar                     8,404          247        34.0
08_FrenchRev                   9,631          175        55.0
09_GrecoPersian                7,393          129        57.3
10_PunicWars                   3,475           57        61.0
11_ChineseCivWar               3,905          102        38.3
12_IranIraq                    4,508           98        46.0
13_RussianCivWar               3,924          104        37.7
14_FirstIndochinaWar           3,085           71        43.5
15_MexicanRev                  3,910           78        50.1
16_SpanishCivilWar             1,455           63        23.1
17_AlgerianWar                 7,716          130        59.4
18_SovietsInAfghanistan        5,306          110        48.2
19_RussoJap                    2,760           62        44.5
20_PolishSoviet                5,137          106        48.5
21_NigerianCivilWar            2,091           29        72.1
22_2ndItaloAbyssinianWar       3,949           69        57.2
--------------------------------------------------------------
Total for the whole corpus   119,468        2,681        44.6
Average per document           5,430          122         –
Standard deviation             2,663           64         –


7. Acknowledgements

In the course of preparation of this corpus we used the GATE framework (see 
http://gate.ac.uk) and the Callisto tool (see http://callisto.mitre.org).


If you publish any work concerning WikiWars and would like to cite this 
resource, please use the following details:

Pawel Mazur and Robert Dale [2010] WikiWars: A New Corpus for Research on 
Temporal Expressions. In the Proceedings of the Conference on Empirical 
Methods in Natural Language Processing (EMNLP), pages 913–922,
9th-11th October 2010, Massachusetts, USA.

The PDF and BibTex files for this paper are available for download from:
http://web.science.mq.edu.au/~mpawel/publications.php

To facilitate the comparison of results also indicate which version of the 
corpus you use in your work. 



8. References

[1] Pawel Mazur and Robert Dale [2007] The DANTE Temporal Expression Tagger. 
In Zygmunt Vetulani, editor, Proceedings of the 3rd Language And Technology 
Conference (LTC), October, Poznan, Poland.
http://www.springerlink.com/content/02t3500p0273385t/

[2] http://callisto.mitre.org

[3] http://www.itl.nist.gov/iad/mig//tests/ace/2004/software.html

[4] http://gate.ac.uk

[5] http://timex2.mitre.org/annotation_guidelines/timex2_annotation_
guidelines.html


9. Change Log


*** WikiWars v1.0.4 ***
Corpus creation date: 2011-10-11
Corpus release date:  2012-02-18

Removed all TIMEX2 attributes with empty values. 

Changes in values of some attributes in 24 annotations:

- 01_WW2:
* late September (start offset: 871)
  VAL="1938-09" --> "1939-09"
* January (start offset: 13821)
  VAL="1941-01" --> "1942-01"

- 02_WW1:
* four days later (start offset: 15140)
  COMMENT="" --> "EC:EVENT-BASED"
* modern day (start offset: 15385)
  VAL="2009-12-19" --> "PRESENT_REF" ANCHOR_DIR="" --> "AS_OF" ANCHOR_VAL="" --> "2009-12-19"
* each day (start offset: 47354)
  VAL="1918-08-XX" --> "1918-08-XXTDT"
* nights (start offset: 47441)
  SET="" --> SET="YES"
* now (start offset: 51080)
  VAL="" --> "PRESENT_REF"
* the last week of October (start offset: 54005)
  VAL="" --> "P1W" ANCHOR_DIR="" --> "ENDING" ANCHOR_VAL="" --> "1918-10-31"

- 04_AmRevWar:
* winter (start offset: 2271)
  VAL="1775-WI" --> "1776-WI"
* winter (start offset: 7902)
  VAL=""1777-WI" --> "XXXX-WI"

- 06_KoreanWar:
* Thirteen days later (start offset: 945)
  comment="" --> "EC:EVENT-BASED"
  
- 08_FrenchRev:
* this time (start offset: 6565)
  VAL="1789" --> "1789-06-27"
* the days of the Committee (start offset: 31571)
  VAL="" --> "XXXX-XX-XX"

- 09_IraqWar:
* This year (start offset: 36440)
  comment="" --> "we need to interpret this with respect to the DCD"
* the summer (start offset: 37023)
  val="XXXX-SU" --> "2009-SU"
  comment="" --> "we need to interpret this with respect to the DCD"

- 12_IranIraq:
* the next day (start offset: 5117)
  VAL="" --> "1980-09-23"
* a few days (start offset: 5183)
  ANCHOR_DIR="" --> "STARTING" ANCHOR_VAL="" --> "1980-09-23"

- 18_sovietsInAfghanistan:
* the second week (start offset: 8511)
  VAL="1980-W01" --> "P1W" ANCHOR_DIR="" --> "STARTING" ANCHOR_VAL="" --> "1980-01-03"
  
- 20_PolishSoviet:
* 12 October (start offset: 27813)
  VAL="2010-10-12" --> "1920-10-12"
* 18 October (start offset: 27858)
  VAL="2010-10-18" --> "1920-10-18"
* November 11 (start offset: 28085)
  VAL="2010-11-11" --> "1920-11-11"
* November 10 (start offset: 28136)
  VAL="2010-11-10" --> "1920-11-10"
* November 21 (start offset: 28152)
  VAL="2010-11-21" --> "1920-11-21"

- 21_NigerianCivilWar:
* present day (start offset: 2184)
  ANCHOR_VAL="2009-12-19T17:00:00" --> "2009-12-19"



*** WikiWars v1.0.3 ***
Corpus creation date: 2011-06-15
Corpus release date:  2011-06-15

4 extent corrections:

- 07_IraqWar:
* the Muslim holy month of Ramadan                          <-- Old Extent
  the beginning of the Muslim holy month of Ramadan         <-- New Extent
* the first seven weeks of the "surge
  the first seven weeks of the "surge" in security activity
  
- 09_GrecoPersian:
* two full days of Persian attacks
  two full days of Persian attacks, including those by the elite Persian Immortals

- 14_FirstIndochinaWar:
* a period of success for the French
  a period of success



*** WikiWars v1.0.2 ***
Corpus creation date: 2011-01-30
Corpus release date:  2011-05-16

3 annotations were removed:

- 11_ChineseCivWar:      "this time"
- 15_MexicanRev:         "the last time"
- 16_SpanishCivWar:      "this time"

13 new annotations were added: 

- 01_WW2:
  * "now" VAL="PRESENT_REF"  (in "now-isolated")
- 02_WW1:
  * "nights" VAL="1918-08-XXTNI" (in "nights passed without sleep")
- 04_AmRevWar:
  * "recently" VAL="PAST_REF" ANCHOR_DIR="BEFORE" ANCHOR_VAL="1781-04"
    (in "recently-turned")
- 05_VietnamWar:
  * "a period of extreme political instability" 
    ANCHOR_DIR="AFTER" ANCHOR_VAL="1963-11-02"
  * "a period of twelve to eighteen months following Phase 2" 
    no TIMEX2 attributes
- 06_KoreanWar:
  * "night" VAL="XXXX-XX-XXTNI" (in "night-marched")
  * "invasion time" VAL="1950-06-25" MOD="START"
- 08_FrenchRev:
  * "Year One of the French Republican Calendar" VAL="1792-09-21"
- 13_RussianCivWar:      
  * "night" VAL="1918-04-XXTNI" (in "a night-raid")
- 14_FirstIndochinaWar:
  * "a period of success for the French" no TIMEX2 attributes
- 15_MexicanRev:
  * "The era of Porfirio Díaz's government from 1876-1910" 
    VAL="P35Y" ANCHOR_DIR="STARTING" ANCHOR_VAL="1876"
  * "The new president's period in office"
    VAL="P4Y"  ANCHOR_DIR-"STARTING" ANCHOR_VAL="1880"
- 16_SpanishCivWar:
  * "the new year" VAL="1939-01-01"

59 extent corrections 
(in 4 cases with changes of values of some TIMEX2 attributes):

- 01_WW2
* two months                                                <-- Old Extent
  two months of fierce battles                              <-- New Extent
  also: ANCHOR_DIR="" --> "STARTING"
        ANCHOR_VAL="" --> "1941-10"

- 02_WW1
* two weeks after the armistice took effect in Europe
  only two weeks after the armistice took effect in Europe
* ten brutal months
  ten brutal months of the Battle of Verdun
* months
  many more months before they would arrive
* nearly four weeks
  nearly four weeks of fighting	since 8 August
* Black Day of the German army
  the "Black Day of the German army
* Black day
  Another "Black day

- 04_AmRevWar
* two years
  two years of semi-secret support
* two months
  two months of siege

- 05_VietnamWar
* the future
  the future of the monarchy
* two months
  two months of good weather remaining until the onset of the monsoon
* years
  years of conflict and neglect
* a time
  a time when Vietnam was deteriorating, especially in places like the Mekong Delta, because of the recent coup against Diem
  also: VAL="" --> "PRESENT_REF"
        ANCHOR_DIR="" --> "AS_OF"
        ANCHOR_VAL="" --> "1963-11-24"
* the time
  the time Johnson left office
* the time
  the time he left office
* the final years of "Vietnamization"
  the final years of "Vietnamization

- 06_KoreanWar
* days
  days of the invasion
* a months-long
  months-long

- 07_IraqWar
* the period
  the period of the CPA's inception	on April 21, 2003
* month of Ramadan
  the Muslim holy month of Ramadan
* over a decade
  over a decade of sanctions, US and UK bombing, corruption, and decaying infrastructure
* months
  relatively peaceful months
* period
  the same 28-day period
* 2008
  the latter half of 2008
  also: VAL="2008" --> "2008-H2"
* more than 40 days
  more than 40 days of fighting, which left between 500-1,000 people dead
* the first seven weeks of the "surge"
  the first seven weeks of the "surge

- 08_FrenchRev
* several hours
  several hours of combat

- 09_GrecoPersian
* roughly four years
  roughly four years of preparation
* two full days
  two full days of Persian attacks
* several days
  several days of maneuver and stalemate
* the subsequent half-century
  the subsequent half-century of internecine conflict in Greece
* good old days
  the "good old days"
* time of arrival at Thermopylae
  Xerxes's estimated time of arrival at Thermopylae

- 10_PunicWars
* more than 20 years
  more than 20 years of war
* two years later
  a mere two years later
* some fifty years
  some fifty years of this condition

- 11_ChineseCivWar
* days
  odd-numbered days
* late 2009
  as recently as late 2009

- 12_IranIraq
* about a year
  about a year after the Iraqi offensive stalled in March 1981

- 14_FirstIndochinaWar
* a week
  a week of intense fighting
* 57 Days
  57 Days of Hell

- 15_MexicanRev
* ten days
  ten days of sporadic fighting in a faked battle
* nearly a year
  nearly a year of pursuing Villa
* a few years
  only a few years of limited education

- 16_SpanishCivilWar
* two months
  two months of bitter fighting in Asturias
  also: ANCHOR_VAL="" --> "1937-10"
        ANCHOR_DIR="" --> "ENDING"

- 17_AlgerianWar
* a few months before
  only a few months before
* the three years
  the three years (1957-60) during which the regroupement program was followed
* the three months
  the three months between the cease-fire and the French referendum on Algeria
* eight years
  eight years of savage warfare

- 18_SovietsInAfghanistan
* four years
  four years of war

- 19_RussoJap
* winter
  Harsh winter
* winter
  the severe Manchurian winter
* days
  days of harsh fighting
* three weeks
  three weeks of fighting

- 20_PolishSoviet
* one week
  only one week

- 21_NigerianCivilWar
* a few days earlier
  Only a few days earlier

- 22_SecondItaloAbyssinianWar
* Black period
  Black period of the war
* Black Period
  the "Black Period" of the war
* the thirty centuries
  the thirty centuries of our history
* hour of glory
  Mussolini's hour of glory



*** WikiWars v1.0.1 ***
Corpus creation date: 2010-11-15
Corpus release date:  not released to the public

Changes not concerning temporal expressions:
- 05_VietnamWar: 
  the string
  "Foreign Minister Lê Ð�&amp;#169;c Thọ and U.S. Secretary of State" 
  replaced with
  "Foreign Minister Lê Ðức Thọ and U.S. Secretary of State"
- 05_VietnamWar: 
  the string
  "would be turned over to General Văn Tiến D�&amp;#169;ng and that Pleiku" 
  replaced with 
  "would be turned over to General Văn Tiến Dũng and that Pleiku"
- 07_IraqWar:
  the string
  "سلط�&amp;#169; الائتلاف الموحدة"
  replaced with
  "سلطة الائتلاف الموحدة"

Changes concerning TIMEX2 attributes' values (19 documents):
- 01_WW2:                       
  "the same time as the battle in Poland"
  VAL="" --> "1939-09-17"
- 02_WW1:
  "the first hour of the attack" 
  VAL="TXX" --> "PT1H"
- 04_AmRevWar:
  "Three weeks after the siege of Boston began" 
  VAL="" --> "1775-W19"
- 04_AmRevWar:
  "the time Arnold reached Quebec City in early November"
  MOD="" --> "START"
- 05_VietnamWar:
  "the ceasefire period" 
  (this period started on 1973-01-27 and finished on 1974-01-04)
  VAL="" --> "P343D" 
  ANCHOR_DIR="" --> "STARTING" 
  ANCHOR_VAL="" --> "1973-01-27"
- 05_VietnamWar:
  "the time of the peace agreement"
  VAL="" --> "1973-01-27"
- 07_IraqWar:
  "January 1"
  VAL="XXXX-01-01" --> "2009-01-01"
- 07_IraqWar:
  "Sovereignty Day"
  VAL="XXXX-XX-XX" --> "2009-01-01"
- 08_FrenchRev:
  "the time of the Revolution"
  (this refers to 1789-1799)
  VAL="" --> "P11Y" 
  ANCHOR_DIR="" --> "STARTING" 
  ANCHOR_VAL="" --> "1789" 
- 08_FrenchRev:
  "the tumultuous time of the Revolution" this refers to 1789-1799
  VAL="" --> "P11Y" 
  ANCHOR_DIR="" --> "STARTING" 
  ANCHOR_VAL="" --> "1789" 
- 11_ChineseCivilWar:
  "the last month of World War II in East Asia" 
  VAL="" --> "P1M"
  ANCHOR_DIR="" --> "ENDING"
  ANCHOR_VAL="" --> "1946-01"
- 12_IranIraq:
  "the first day of the war" 
  VAL="" --> "1980-09-22"
- 17_AlgerianWar:
  "the years of the War of Independence"   
  (1 November 1954 – 19 March 1962)
  VAL="" --> "P9Y" 
  ANCHOR_DIR="" --> "STARTING" 
  ANCHOR_VAL="" --> "1954" 
- 17_AlgerianWar:
  "fifteen hours before the projected launch of Resurrection" 
  VAL="1958-05-29" --> "1958-05-29TXX"
- 17_AlgerianWar:
  "four days after the vote" 
  VAL="" --> "1962-07-05"
- 18_SovietsInAfghanistan:
  "six months before the Soviet deployment" 
  VAL="1978-12" --> "1979-07"
- 18_SovietsInAfghanistan:
  "The day that the Soviets officially crossed the border" 
  VAL="1979-06-16" --> "1979-12-24"
  (also see: http://www.globalresearch.ca/articles/BRZ110A.html)
- 18_SovietsInAfghanistan:
  "this day"
  ANCHOR_VAL="2009-12-19T17:00:00" --> "2009-12-19"
- 22_SecondItaloAbyssinianWar:
  "the time when the operations for the encircling of Makale were taking place" 
  VAL="" --> "1935-12"

Also, a number of changes in most documents concerning the value of the TIMEX2 
"comment" attribute.



*** WikiWars v1.0.0 ***
Corpus creation date: 2010-06-01
Corpus release date:  2010-10-04

The first version of the corpus.
