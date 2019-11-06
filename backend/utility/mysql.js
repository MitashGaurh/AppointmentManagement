let mysql = require('mysql');

let pool = mysql.createPool({
    connectionLimit: 20, //important
    host     : 'localhost',
    user     : 'root',
    password : 'password',
    database : 'cmpe277',
    port	 : 3306
});
module.exports = pool;

