-- 把三个户型的 layoutJson 更新为带坐标/尺寸的差异化布局（房间坐标为左上角，单位米）
-- 用 id 匹配，避免中文 WHERE 的编码问题；此脚本幂等，可重复执行。

UPDATE kjl_house_type SET layout_json = '{"rooms":[{"name":"客厅","x":0,"z":0,"w":4,"d":3},{"name":"厨房","x":4,"z":0,"w":2,"d":3},{"name":"卫生间","x":6,"z":0,"w":2,"d":3},{"name":"主卧","x":0,"z":3,"w":2.7,"d":3},{"name":"次卧","x":2.7,"z":3,"w":2.7,"d":3},{"name":"书房","x":5.4,"z":3,"w":2.6,"d":3}]}' WHERE id = 1;

UPDATE kjl_house_type SET layout_json = '{"rooms":[{"name":"客厅","x":0,"z":0,"w":4,"d":3},{"name":"厨房","x":4,"z":0,"w":2.5,"d":3},{"name":"卫生间","x":6.5,"z":0,"w":1.5,"d":3},{"name":"主卧","x":0,"z":3,"w":4,"d":3},{"name":"次卧","x":4,"z":3,"w":4,"d":3}]}' WHERE id = 2;

UPDATE kjl_house_type SET layout_json = '{"rooms":[{"name":"客厅","x":0,"z":0,"w":5,"d":3.5},{"name":"卧室","x":0,"z":3.5,"w":3.5,"d":2.5},{"name":"厨房","x":5,"z":0,"w":3,"d":3},{"name":"卫生间","x":3.5,"z":3.5,"w":2,"d":2.5}]}' WHERE id = 3;
