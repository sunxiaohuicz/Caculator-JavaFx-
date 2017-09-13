/**
 * Created by XD.Wang on 2017/9/13.
 * 单元格合并
 */

var rows = '[{"productId":"001","packageId":"aaa","元素标识":"001","元素描述":"元素描述001"},' +
    '{"productId":"001","packageId":"aaa","元素标识":"002","元素描述":"元素描述002"},' +
    '{"productId":"001","packageId":"bbb","元素标识":"001","元素描述":"元素描述001"},' +
    '{"productId":"004","packageId":"aaa","元素标识":"001","元素描述":"元素描述001"},' +
    '{"productId":"005","packageId":"aaa","元素标识":"001","元素描述":"元素描述001"},' +
    '{"productId":"005","packageId":"bbb","元素标识":"001","元素描述":"元素描述001"},' +
    '{"productId":"005","packageId":"bbb","元素标识":"002","元素描述":"元素描述002"},' +
    '{"productId":"005","packageId":"ccc","元素标识":"001","元素描述":"元素描述001"},' +
    '{"productId":"009","packageId":"aaa","元素标识":"001","元素描述":"元素描述001"},' +
    '{"productId":"010","packageId":"ccc","元素标识":"001","元素描述":"元素描述001"}]';

var merges = getMergeInfo(rows);
console.log(merges);

function getMergeInfo(rows) {
    // 初始化数据
    var cols = [];
    var merges = [];
    var mergeList = [];
    var ROW_SPAN_INIT = 1;
    var rowsJSON = JSON.parse(rows);
    // 获取列信息
    if (rowsJSON.length < 1) return;
    for (var key in rowsJSON[0]) {
        cols.push(key);
    }
    // 按列匹配
    for (var i = 0; i < cols.length; i++) {
        var col = {};
        var merge = {};
        rowsJSON.forEach(function (val, index) {
            for (var key in val) {
                if (cols[i] == key) {
                    if (index == 0) {
                        col[key] = val[key];
                        merge.index = 0;
                        merge.rowspan = ROW_SPAN_INIT;
                    } else {
                        if (col[key] == val[key]) {
                            merge.rowspan++
                        } else {
                            if (merge.rowspan > ROW_SPAN_INIT && isMerge(mergeList, merge, i)) {
                                merges.push(merge);
                                merge = {};
                            }
                            col[key] = val[key];
                            merge.index = index;
                            merge.rowspan = ROW_SPAN_INIT;
                        }
                    }
                }
            }
        });
        mergeList.push(merges);
        merges = [];
    }

    return mergeList;
}

// 前列区间不能比后列区间大
function isMerge(parentInterval, childrenInterval, index) {
    var childBegin = childrenInterval.index;
    var childEnd = childrenInterval.index + childrenInterval.rowspan - 1;
    if (index > 0) {
        parentInterval = parentInterval[index - 1];
        for (var i = 0; i < parentInterval.length; i++) {
            var parentBegin = parentInterval[i].index;
            var parentEnd = parentInterval[i].index + parentInterval[i].rowspan - 1;
            if (parentBegin <= childBegin && parentEnd >= childEnd) {
                return true;
            }
        }
    } else {
        return true;
    }
    return false;
}