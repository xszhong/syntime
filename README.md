# SynTime
SynTime is a Java tool for recognizing time expressions. It is the resource of our ACL2017 paper indicated below.

The source codes and datasets of SynTime are uploaded directly from a Java project exported from Eclipse, therefore, they can be imported directly to Eclipse.

After importing the project, you need to import Stanford CoreNLP model before running it. In our implementation, we use CoreNLP model in its 3.6.0 version and it can be got from [CoreNLP website](https://stanfordnlp.github.io/CoreNLP/history.html). The CoreNLP model is packaged into a .jar file and thus can be treated as a common .jar file.

To get started quickly, please run the examples in 'syntime/src/ntu.scse.examples/SynTimeExample.java.' You can change the setting in 'SynTimeExample.java' to produce the result: .tml text or tml files.


### Clarification
A description to clarify the experiment setting in our paper. In EXPERIMENTS section, the “TimeBank” actually includes the TimeBank corpus as training set and the Platinum corpus as test set; the TimeBank and Platinum corpora are described in [TempEval-3](http://www.derczynski.com/sheffield/papers/tempeval-3.pdf). So the results of “TimeBank” in Table 4 actually means the results on the Platinum corpus. Thank Dr. Jannik Strötgen for pointing out this.


### Publications
Xiaoshi Zhong, Aixin Sun, and Erik Cambria. Time Expression Analysis and Recognition Using Syntactic Token Types and General Heuristic Rules. In *Proceedings of the 55th Annual Meeting of the Association for Computational Linguistics* (ACL), pages 420-429, 2017. [[pdf](http://aclweb.org/anthology/P/P17/P17-1039.pdf)] [[slides](https://drive.google.com/file/d/0B4MkuquLjWvpV2d2dmZpU0VmbGs/view)]
