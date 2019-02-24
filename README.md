# home-credit
一个基于kaggle数据集[Home Credit Default Risk](https://www.kaggle.com/c/home-credit-default-risk)的线上预测服务<br/>
项目包括以下子服务<br/>
    
1，线上部署机器学习模型，通过将python model文件转换成pmml模型，提供实时预测结果 [model](https://github.com/raoqiangpeter/model)

2，spark-yarn离线数据分析，将原数据进行特征提取，并且入hbase提供实时查询 [sparkyarn](https://github.com/raoqiangpeter/sparkyarn)
    
3，phoenix，一个支持hbase二级索引的标准实时查询接口服务 [phoenix](https://github.com/raoqiangpeter/phoenix)
    
    