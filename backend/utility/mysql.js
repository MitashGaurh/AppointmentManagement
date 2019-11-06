let mysql = require('mysql');

let pool = mysql.createPool({
    connectionLimit: 20, //important
    host     : 'database-1.cuplef7hbl2w.us-east-1.rds.amazonaws.com',
    user     : 'admin',
    password : 'Cmpe277password',
    database : 'cmpe277',
    port	 : 3306
});
module.exports = pool;

