'use strict';

//引用操作資料庫的物件
const query = require('./asyncDB');

// 查詢
//----------------------------------
var select = async function (s_id) {
    var result;

    await query(`select funeral_name,funeral_address,funeral_phonenum,funeral_price,funeral_way from funeral_merchant where funeral_price is not null and funeral_price<5000 and funeral_address like '%新竹縣%'`)
        .then((data) => {
            result = data
        }, (error) => {
            console.log(error)
            result = -1;
        });

    return result;
}

// 更新
//----------------------------------
var update = async function (Data) {
    var result;

    await query(`update example set value=${Data.value} where keyword=${Data.keyword}`)
        .then((data) => {
            result = data;
        }, (error) => {
            console.log(error)
            result = -1;
        });

    return result;
}
// 新增
//----------------------------------
var insert = async function (Data) {
    var result = -1;

    // if(eData.certUrl==undefined)
    await query(`insert into example(keyword,value) values("${Data.keyword}", "${Data.value}")`)
        .then(() => result = 0);
    return result;
}
//匯出
module.exports = {
   select,update,insert
}