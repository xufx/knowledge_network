use knowledge_network;
SET FOREIGN_KEY_CHECKS=0;

CREATE TABLE IF NOT EXISTS knowledge_point (
  id          INT            NOT NULL AUTO_INCREMENT,
  name        VARCHAR(500)   NOT NULL,
  remark      VARCHAR(500)   DEFAULT NULL,          #简介
	content      VARCHAR(100)   DEFAULT NULL,          #定义
  memo        VARCHAR(1000)  DEFAULT NULL,          #备注
	learnGoal     INT,         #学习目标,1,2,3,4,5,6,7,8，记忆、了解、理解、掌握、运用、分析、评价、创造
  learnAdvise   VARCHAR(500),                      #学习建议
	examFrequency INT,					#考试频率,1,2,3,4，没考过、偶尔考、经常考、高频考点
	reference  VARCHAR(500), #参考资料
  doc     INT,                               #知识点具体内容
  video       INT,                               #学习视频
	label_id       INT,   										#标签
  PRIMARY KEY (id),
	KEY FK_EMP_DOC(doc),
  KEY FK_EMP_VIDEO(video),
	KEY FK_EMP_LABEL(label_id),
	CONSTRAINT FK_EMP_DOC FOREIGN KEY (doc) REFERENCES learn_doc(id)
		ON DELETE CASCADE
    ON UPDATE CASCADE,
	CONSTRAINT FK_EMP_VIDEO FOREIGN KEY (video) REFERENCES learn_video(id)
		ON DELETE CASCADE
    ON UPDATE CASCADE,
	CONSTRAINT FK_EMP_LABEL FOREIGN KEY (label_id) REFERENCES label(id)
		ON DELETE CASCADE
    ON UPDATE CASCADE
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8;


CREATE TABLE IF NOT EXISTS label (
  id          INT            NOT NULL AUTO_INCREMENT,
  name        VARCHAR(500)   NOT NULL,
  #remark      VARCHAR(500)  DEFAULT NULL,          #备注
  knowledge_point_id     INT ,                      #包含的知识点
  PRIMARY KEY (id),
	KEY FK_EMP_KP (knowledge_point_id),
  CONSTRAINT FK_EMP_KP FOREIGN KEY (knowledge_point_id) REFERENCES knowledge_point(id)
		ON DELETE CASCADE
    ON UPDATE CASCADE
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8;


CREATE TABLE IF NOT EXISTS learn_doc (#知识点的具体内容，word文档
  id           INT            NOT NULL AUTO_INCREMENT,
  title        VARCHAR(500)   NOT NULL,
	filename     VARCHAR(500)   NOT NULL,               #文件名
  remark        VARCHAR(1000)  DEFAULT NULL,          #简介
  knowledge_point_id         INT ,                      #包含知识点
  PRIMARY KEY (id),
	KEY FK_KP (knowledge_point_id),
  CONSTRAINT FK_KP FOREIGN KEY (knowledge_point_id) REFERENCES knowledge_point(id)
		ON DELETE CASCADE
    ON UPDATE CASCADE
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8;


CREATE TABLE IF NOT EXISTS learn_video(
  id               INT        NOT NULL AUTO_INCREMENT,
  title        VARCHAR(500)   NOT NULL,
	filename     VARCHAR(500)   NOT NULL,               #文件名
  remark       VARCHAR(500)   DEFAULT NULL,    #简介
  knowledge_point_id     INT ,                      #包含的知识点
  PRIMARY KEY (id),
  KEY FK_VIDEO (knowledge_point_id),
  CONSTRAINT FK_VIDEO FOREIGN KEY (knowledge_point_id) REFERENCES knowledge_point(id)
		ON DELETE CASCADE
    ON UPDATE CASCADE
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8;


CREATE TABLE IF NOT EXISTS unit (
  id          INT            NOT NULL AUTO_INCREMENT,
  name        VARCHAR(200)   NOT NULL,
	remark           VARCHAR(500)   DEFAULT NULL,    #简介
  symbol      VARCHAR(100)   NOT NULL,         #单位符号
  PRIMARY KEY (id)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8;


CREATE TABLE IF NOT EXISTS instrument (#实验仪器
  id               INT            NOT NULL AUTO_INCREMENT,
  name             VARCHAR(200)   NOT NULL,
  remark           VARCHAR(500)   DEFAULT NULL,    #简介
  instruction             VARCHAR(1000)  DEFAULT NULL,      #使用方法
	memo             VARCHAR(1000)  DEFAULT NULL,      #注意事项
  image            VARCHAR(100)   DEFAULT NULL, #图片
	reference  VARCHAR(500), #参考资料
  doc       INT,
	video         INT,                #学习视频
  PRIMARY KEY (id),
  KEY FK_DOC_INSTRUMENT (doc),
	KEY FK_VIDEO2 (video),
CONSTRAINT FK_DOC_INSTRUMENT FOREIGN KEY (doc) REFERENCES learn_doc(id)
		ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT FK_VIDEO2 FOREIGN KEY (video) REFERENCES learn_video(id)
		ON DELETE CASCADE
    ON UPDATE CASCADE
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8;


CREATE TABLE IF NOT EXISTS experiment (#实验
  id               INT            NOT NULL AUTO_INCREMENT,
  name             VARCHAR(500)   NOT NULL,
  remark           VARCHAR(500)   DEFAULT NULL,    #简介
  steps            VARCHAR(1000)  DEFAULT NULL,			#实验步骤
	memo             VARCHAR(1000)  DEFAULT NULL,			#备注
	tip              VARCHAR(1000)  DEFAULT NULL,      #注意事项
	reference  VARCHAR(500), #参考资料
	principle_id		 INT,    #实验原理
	doc           INT,
	video         INT,                #学习视频
	instrument_id    INT,                #实验仪器
  PRIMARY KEY (id),
	KEY FK_EXPERIMENT_PRINCIPLE(principle_id),
	KEY FK_DOC_EXPERIMENT(doc),
	KEY FK_VIDEO3 (video),
	KEY FK_EMP_INSTRUMENT(instrument_id),
	CONSTRAINT FK_EXPERIMENT_PRINCIPLE FOREIGN KEY (principle_id)REFERENCES knowledge_network(id)
		ON DELETE CASCADE
    ON UPDATE CASCADE,
	CONSTRAINT FK_DOC_EXPERIMENT FOREIGN KEY (doc)REFERENCES learn_doc(id)
		ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT FK_VIDEO3 FOREIGN KEY (video) REFERENCES learn_video(id)
		ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT FK_EMP_INSTRUMENT FOREIGN KEY (instrument_id) REFERENCES instrument(id)
		ON DELETE CASCADE
    ON UPDATE CASCADE
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8;



