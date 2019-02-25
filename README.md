# home-credit
一个基于kaggle数据集[Home Credit Default Risk](https://www.kaggle.com/c/home-credit-default-risk)的线上预测服务<br/>
项目包括以下子服务<br/>
    
1，线上部署机器学习模型，通过将python model文件转换成pmml模型，提供实时预测结果 [model](https://github.com/raoqiangpeter/model)

2，spark-yarn离线数据分析，将原数据进行特征提取，并且入hbase提供实时查询 [sparkyarn](https://github.com/raoqiangpeter/sparkyarn)
    
3，phoenix，一个支持hbase二级索引的标准实时查询接口服务 [phoenix](https://github.com/raoqiangpeter/phoenix)


## Home Credit Default Risk 介绍

kaggle提供的一个贷款审批[数据集](https://www.kaggle.com/c/home-credit-default-risk/data)，通过数据集预测申请者是否能正常支付贷款。<br/>
1,```application_{train|test}.csv```数据集，训练数据和预测数据集；每一条记录代表样本中的一个贷款申请信息。<br/>
2,```bureau.csv```数据集，信用机构统计的所有客户历史申请数据。对于以上贷款申请，该数据集可能包含多笔客户以往的申请数据。<br/>
    
    