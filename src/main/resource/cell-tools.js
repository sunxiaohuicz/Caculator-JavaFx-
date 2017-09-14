/**
 * Created by XD.Wang on 2017/9/13.
 * 单元格合并
 */

var rows = '[{"ORDER_ID":"a","PACKAGE_NAME":"包一","ELEMENT_NAME":"元素一","PRODUCT_NAME":"96元套餐"},' +
    '{"ORDER_ID":"a","PACKAGE_NAME":"包二","ELEMENT_NAME":"元素二","PRODUCT_NAME":"96元套餐"},' +
    '{"ORDER_ID":"a","PACKAGE_NAME":"包三","ELEMENT_NAME":"元素一","PRODUCT_NAME":"96元套餐"},' +
    '{"ORDER_ID":"a","PACKAGE_NAME":"包三","ELEMENT_NAME":"元素二","PRODUCT_NAME":"96元套餐"},' +
    '{"ORDER_ID":"a","PACKAGE_NAME":"包四","ELEMENT_NAME":"元素三","PRODUCT_NAME":"96元套餐"},' +
    '{"ORDER_ID":"b","PACKAGE_NAME":"包一","ELEMENT_NAME":"元素二","PRODUCT_NAME":"80元套餐"},' +
    '{"ORDER_ID":"b","PACKAGE_NAME":"包一","ELEMENT_NAME":"元素三","PRODUCT_NAME":"80元套餐"},' +
    '{"ORDER_ID":"b","PACKAGE_NAME":"包一","ELEMENT_NAME":"元素四","PRODUCT_NAME":"80元套餐"},' +
    '{"ORDER_ID":"b","PACKAGE_NAME":"包二","ELEMENT_NAME":"元素二","PRODUCT_NAME":"80元套餐"},' +
    '{"ORDER_ID":"c","PACKAGE_NAME":"包一","ELEMENT_NAME":"元素一","PRODUCT_NAME":"59元套餐"},' +
    '{"ORDER_ID":"c","PACKAGE_NAME":"包一","ELEMENT_NAME":"元素二","PRODUCT_NAME":"59元套餐"},' +
    '{"ORDER_ID":"c","PACKAGE_NAME":"包一","ELEMENT_NAME":"元素三","PRODUCT_NAME":"59元套餐"},' +
    '{"ORDER_ID":"d","PACKAGE_NAME":"包一","ELEMENT_NAME":"元素一","PRODUCT_NAME":"39元套餐"},' +
    '{"ORDER_ID":"d","PACKAGE_NAME":"包一","ELEMENT_NAME":"元素三","PRODUCT_NAME":"39元套餐"},' +
    '{"ORDER_ID":"d","PACKAGE_NAME":"包一","ELEMENT_NAME":"元素四","PRODUCT_NAME":"39元套餐"},' +
    '{"ORDER_ID":"d","PACKAGE_NAME":"包三","ELEMENT_NAME":"元素二","PRODUCT_NAME":"39元套餐"},' +
    '{"ORDER_ID":"d","PACKAGE_NAME":"包三","ELEMENT_NAME":"元素三","PRODUCT_NAME":"39元套餐"},' +
    '{"ORDER_ID":"d","PACKAGE_NAME":"包四","ELEMENT_NAME":"元素一","PRODUCT_NAME":"39元套餐"},' +
    '{"ORDER_ID":"d","PACKAGE_NAME":"包四","ELEMENT_NAME":"元素三","PRODUCT_NAME":"39元套餐"}]';

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
                            merge.rowspan++;
                            if (index == rowsJSON.length - 1) {
                                merges.push(merge);
                            }
                        } else {
                            var check = checkMerge(mergeList, merge, i);
                            if (merge.rowspan > ROW_SPAN_INIT && check != 0) {
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
function checkMerge(parentInterval, childrenInterval, index) {
    var childBegin = childrenInterval.index;
    var childEnd = childrenInterval.index + childrenInterval.rowspan - 1;
    if (index > 0) {
        parentInterval = parentInterval[index - 1];
        for (var i = 0; i < parentInterval.length; i++) {
            var parentBegin = parentInterval[i].index;
            var parentEnd = parentInterval[i].index + parentInterval[i].rowspan - 1;
            if (parentBegin <= childBegin && parentEnd >= childEnd) {
                return 1;
            }
            if (parentBegin >= childBegin && parentBegin <= childEnd) {
                return 2;
            }
        }
    } else {
        return 1;
    }
    return 0;
}