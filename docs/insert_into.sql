
SET FOREIGN_KEY_CHECKS = 0;

INSERT INTO knowledge_point (
	id,
	NAME,
	remark,
	content,
	memo,
	learnGoal,
	learnAdvise,
	examFrequency,
	reference,
	doc,
	video,
	label_id
)
VALUES
	(
		1,
		"串联电路",
		"几个电路元件沿着单一路径互相连接，每个连接点最多只连接两个元件，此种连接方式称为串联",
		"以串联方式连接的电路称为串联电路",
		"备注",
		6,
		"建议学习时自己动手连接电路",
		4,
		"参考资料",
		1,
		2,
		3
	);

INSERT INTO label (id, NAME, knowledge_point_id)
VALUES
	(1, "电路", 1),
	(2, "电路", 2);

INSERT INTO learn_doc (
	id,
	title,
	filename,
	remark,
	knowledge_point_id
)
VALUES
	(
		1,
		"串联电路的学习文档",
		"series",
		"文档里讲述了知识点的具体内容",
		1
	),
	(
		2,
		"串联电路的学习文档",
		"series",
		"文档里讲述了知识点的具体内容",
		2
	);

INSERT INTO learn_video (
	id,
	title,
	filename,
	remark,
	knowledge_point_id
)
VALUES
	(
		1,
		"串联电路的学习视频",
		"series",
		"视频里讲述了知识点的具体内容",
		1
	),
	(
		2,
		"串联电路的学习视频",
		"series",
		"视频里讲述了知识点的具体内容",
		2
	);

INSERT INTO unit (id, NAME, remark, symbol)
VALUES
	(
		1,
		"伏特",
		"是电压的常用符号",
		"V"
	),
	(
		2,
		"安培",
		"是电流的常用符号",
		"A"
	);

INSERT INTO instrument (
	id,
	NAME,
	remark,
	instruction,
	memo,
	image,
	reference,
	doc,
	video
)
VALUES
	(
		1,
		"游标卡尺",
		"游标卡尺，又称为游标尺子或直游标尺子，是一种测量长度的仪器)",
		"使用方法",
		"备注",
		"youbiaokachi",
		"参考资料",
		1,
		2
	);

INSERT INTO experiment (
	id,
	NAME,
	remark,
	steps,
	memo,
	tip,
	reference,
	principle_id,
	doc,
	video,
	instrument_id
)
VALUES
	(
		1,
		"伏安法测电阻",
		"伏安法是初中物理的一个基本方法",
		"(1)连接电路；（2）操作；（3）处理数据",
		"备注",
		"注意事项",
		"参考资料",
		5,
		1,
		2,
		3
	);