# SynTime
SynTime is a Java tool for recognizing time expressions (timexes) from free text. This repo includes the source codes and datasets used in our ACL2017 paper indicated below.

The source codes and datasets of SynTime are uploaded directly through a Java project exported from Eclipse, therefore, they can be imported directly to Eclipse.

After importing the project, one needs to import the Stanford CoreNLP model before running it. In our implementation, we use the CoreNLP model in its 3.6.0 version, which can be got from [CoreNLP website](https://stanfordnlp.github.io/CoreNLP/history.html). The CoreNLP model is packaged into a .jar file and thus can be treated as a common .jar file.

To get started quickly, please run the examples in ''syntime/src/ntu.scse.examples/SynTimeExample.java''. You can change the setting in ''SynTimeExample.java'' to precessed .tml text or tml files.


### Clarification
A description to clarify the experimental setting in our paper. In EXPERIMENTS section, the “TimeBank” actually includes the TimeBank corpus as the training set and the Platinum corpus as the test set. TimeBank and Platinum corpora are described in [TempEval-3](http://www.derczynski.com/sheffield/papers/tempeval-3.pdf). So the results of “TimeBank” in Table 4 actually mean the results on the Platinum corpus. We would like to thank Dr. Jannik Strötgen for pointing out this.


### Publications
Xiaoshi Zhong, A Sun, and Erik Cambria. Time Expression Analysis and Recognition Using Syntactic Token Types and General Heuristic Rules. In *Proceedings of the 55th Annual Meeting of the Association for Computational Linguistics* (ACL), pages 420-429, 2017. [[pdf](http://aclweb.org/anthology/P/P17/P17-1039.pdf)] [[slides](https://xszhong.github.io/slides/SynTime-ACL2017-ZhongEtal-Slides.pdf)]
