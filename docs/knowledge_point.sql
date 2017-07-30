use knowledge_network;
SET FOREIGN_KEY_CHECKS=0;

CREATE TABLE IF NOT EXISTS knowledge_point (
  id          INT            NOT NULL AUTO_INCREMENT,
  name        VARCHAR(500)   NOT NULL,
  remark      VARCHAR(500)   DEFAULT NULL,          #简介
  memo        VARCHAR(1000)  DEFAULT NULL,          #备注
	learn_goal     INT,         #学习目标,1,2,3,4,5,6,7,8，记忆、了解、理解、掌握、运用、分析、评价、创造
  learn_advise   VARCHAR(300),                      #学习建议
	exam_frequency INT,					#考试出现频率,1,2,3,4，没考过、偶尔考、经常考、高频考点
  content_id     INT,                               #知识点具体内容
  video_id       INT,                               #学习视频 
	label_id       INT,   										#标签
  PRIMARY KEY (id),
	KEY FK_EMP_CONTENT(content_id),
  KEY FK_EMP_VIDEO(video_id),
	KEY FK_EMP_LABEL(label_id),
CONSTRAINT FK_EMP_CONTENT FOREIGN KEY (content_id) REFERENCES content(id),
CONSTRAINT FK_EMP_VIDEO FOREIGN KEY (video_id) REFERENCES learn_video(id),
CONSTRAINT FK_EMP_LABEL FOREIGN KEY (label_id) REFERENCES label(id)  
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8;


CREATE TABLE IF NOT EXISTS label (
  id          INT            NOT NULL AUTO_INCREMENT,
  name        VARCHAR(500)   NOT NULL,
  memo        VARCHAR(1000)  DEFAULT NULL,          #备注
  knowledge_point_id     INT ,                      #包含的知识点
  PRIMARY KEY (id),
	KEY FK_EMP_KP (knowledge_point_id),
  CONSTRAINT FK_EMP_KP FOREIGN KEY (knowledge_point_id) REFERENCES knowledge_point(id)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8;


CREATE TABLE IF NOT EXISTS content (#知识点的具体内容，word文档
  id          INT            NOT NULL AUTO_INCREMENT,
  name        VARCHAR(500)   NOT NULL,
  memo        VARCHAR(1000)  DEFAULT NULL,          #备注
  knowledge_point_id         INT ,                      #包含知识点
  PRIMARY KEY (id),
	KEY FK_KP (knowledge_point_id),
  CONSTRAINT FK_KP FOREIGN KEY (knowledge_point_id) REFERENCES knowledge_point(id)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8;


CREATE TABLE IF NOT EXISTS learn_video(
  id               INT            NOT NULL AUTO_INCREMENT,
  name             VARCHAR(200)   NOT NULL,
  remark           VARCHAR(500)   DEFAULT NULL,    #简介
	memo             VARCHAR(1000)  DEFAULT NULL,			#备注
  knowledge_point_id     INT ,                      #包含的知识点
  PRIMARY KEY (id),
  KEY FK_VIDEO (knowledge_point_id),
  CONSTRAINT FK_VIDEO FOREIGN KEY (knowledge_point_id) REFERENCES knowledge_point(id)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8;


CREATE TABLE IF NOT EXISTS unit (
  id          INT            NOT NULL AUTO_INCREMENT,
  name        VARCHAR(200)   NOT NULL,
  remark      VARCHAR(500)   DEFAULT NULL,     #简介
  symbol      VARCHAR(100)   NOT NULL,         #单位符号
	memo        VARCHAR(1000)   DEFAULT NULL,    #备注
  PRIMARY KEY (id)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8;


CREATE TABLE IF NOT EXISTS instrument (#实验仪器
  id               INT            NOT NULL AUTO_INCREMENT,
  name             VARCHAR(200)   NOT NULL,
  remark           VARCHAR(500)   DEFAULT NULL,    #简介
  instructions     VARCHAR(1000)  DEFAULT NULL,			#使用方法
	memo             VARCHAR(1000)  DEFAULT NULL,			#备注
	video_id         INT,                #学习视频
  PRIMARY KEY (id),
	KEY FK_VIDEO2 (video_id),
  CONSTRAINT FK_VIDEO2 FOREIGN KEY (video_id) REFERENCES learn_video(id)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8;


CREATE TABLE IF NOT EXISTS experiment (#实验
  id               INT            NOT NULL AUTO_INCREMENT,
  name             VARCHAR(500)   NOT NULL,
  remark           VARCHAR(500)   DEFAULT NULL,    #简介
  steps            VARCHAR(1000)  DEFAULT NULL,			#实验步骤
	memo             VARCHAR(1000)  DEFAULT NULL,			#备注
	tips             VARCHAR(1000)  DEFAULT NULL,      #注意事项
	video_id         INT,                #学习视频
	instrument_id    INT,                #实验仪器
  PRIMARY KEY (id),
	KEY FK_VIDEO3 (video_id),
	KEY 	FK_EMP_INSTRUMENT(instrument_id),
  CONSTRAINT FK_VIDEO3 FOREIGN KEY (video_id) REFERENCES learn_video(id),
  CONSTRAINT FK_EMP_INSTRUMENT FOREIGN KEY (instrument_id) REFERENCES instrument(id)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8;



