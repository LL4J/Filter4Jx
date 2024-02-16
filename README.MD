TextSafe
--

### 数据集来源：

时政与广告：https://github.com/observerss/textfilter  
辱骂与歧视：https://github.com/thu-coai/COLDataset

微调使用：https://www.kaggle.com/datasets/utmhikari/doubanmovieshortcomments  
（主要是脱敏）

### 运行流程

Wash，对数据集进行清洗合并。（有一些工作是用Excel完成的，可能代码中没有体现）  
BuildTokenizer，构建词表。  
Split，划分数据集。  
Train，训练模型。  
Tune，用更多的样本微调。本步骤的界限、参数需要根据“感觉”来调整。  
