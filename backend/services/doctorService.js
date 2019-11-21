let mysql = require('../utility/mysql');

exports.getAvailableDoctors = (req, res) => {
    let query = "select doctor_id as doctorId, doctor_name as doctorName, specialization, " +
        "group_concat(slot_time) as officeHours from doctors\n" +
        "inner join appointment_slots using (doctor_id) group by doctor_id";
    mysql.query(query, (err, results) => {
        if (err) {
            console.log(err);
            res.status(500).send({error: err});
        }
        else {
            if (results.length > 0) res.status(200).send({availableDoctors: results});
            else res.status(400).send({error: "No Doctors found!"});
        }
    });
};