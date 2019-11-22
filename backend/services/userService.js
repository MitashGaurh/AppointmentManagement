let mysql = require('../utility/mysql');

exports.login = (req, res) => {
    let query = "select * from students where student_id = ?";
    let params = [req.param("studentId")];
    mysql.query(query, params, (err, results) => {
        if (err) {
            console.log(err);
            res.status(500).send({error: err});
        }
        else {
            if (results.length > 0) {
                if (results[0].password === req.param("password"))
                    res.status(200).send({status: "success", userDetails: results[0]});
                else
                    res.status(400).send({status: "failure", error: "Incorrect Password!"});
            }
            else res.status(400).send({status: "failure", error: "User is not registered!"});
        }
    });
};

exports.getUserDetails = (req, res) => {
    let query = "select student_id as studentId, first_name as firstName, last_name as lastName, email_id as emailId," +
        " mobile_number as mobileNumber, address, date_of_birth as dateOfBirth from students where student_id = ?";
    let params = [req.param("studentId")];
    mysql.query(query, params, (err, results) => {
        if (err) {
            console.log(err);
            res.status(500).send({error: err});
        }
        else {
            if (results.length > 0) res.status(200).send({userDetails: results[0]});
            else res.status(400).send({error: "No user found with given details!"});
        }
    });
};

exports.updateStudentProfile = (req, res) => {
    let query = "update students set first_name = ?, last_name = ?, email_id = ?, mobile_number = ?, " +
        "address = ?, date_of_birth = str_to_date(?, '%M %d, %Y') where student_id = ?";
    let params = [req.param("firstName"), req.param("lastName"), req.param("emailId"),
        req.param("mobileNumber"), req.param("address"), req.param("dateOfBirth"), req.param("studentId")];
    mysql.query(query, params, (err, results) => {
        if (err) {
            console.log(err);
            res.status(500).send({error: err});
        }
        else res.status(200).send({status: "success", message: "Update Successful"});
    });
};