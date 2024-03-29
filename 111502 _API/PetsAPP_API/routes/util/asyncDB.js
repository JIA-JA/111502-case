'use strict';

//引用mysql模組
var mysql = require('mysql');
var fs = require('fs');
var path = require('path');
// const serverCa = [fs.readFileSync(path.resolve("./routes/util/DigiCertGlobalRootCA.crt.pem"), "utf8")];
//建立資料庫連接池
var pool  = mysql.createPool({
  user: "mysql111502user",
  password: "@Imd17&18&25&35&36",
    host: '140.131.114.242',
    database: '111- 111502project'     ,
    // ssl: {
    //     rejectUnauthorized: true,
    //     // ca: serverCa
    // }
});

//產生可同步執行query物件的函式
function query(sql, value) {
    return new Promise((resolve, reject) => {
        pool.query(sql, value, function (error, results, fields) {
            if (error){
                reject(error);
            }else{
                resolve(results);
            }
        });
    });
}

//匯出
module.exports = query;