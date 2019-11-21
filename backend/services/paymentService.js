let mysql = require('../utility/mysql');

exports.getPendingPayments = (req, res) => {
    let query = "select payment_id as paymentId, appointment_date as appointmentDate, " +
        "doctor_name as doctorName, payment_amount as amountDue from payment_history \n" +
        "inner join appointment_history using (appointment_id)\n" +
        "inner join appointment_slots using (slot_id)\n" +
        "inner join doctors using (doctor_id)\n" +
        "where payment_done = 0 and appointment_id in\n" +
        "(select appointment_id from appointment_history where student_id = ?)";
    let params = [req.param("studentId")];
    mysql.query(query, params, (err, results) => {
        if (err) {
            console.log(err);
            res.status(500).send({error: err});
        }
        else {
            if (results.length > 0) res.status(200).send({pendingPayments: results});
            else res.status(400).send({error: "No Payments Pending"});
        }
    });
};

exports.getPaymentHistory = (req, res) => {
    let query = "select payment_id as paymentId, appointment_date as appointmentDate, " +
        "slot_time as appointmentTime, doctor_name as doctorName, payment_amount as amountDue, " +
        "(case payment_done when 1 then 'Paid' else 'Pending' end) as paymentStatus, " +
        "ifnull(payment_type, '') as paymentType, ifnull(payment_date, '') as paymentDate from payment_history \n" +
        "inner join appointment_history using (appointment_id)\n" +
        "inner join appointment_slots using (slot_id)\n" +
        "inner join doctors using (doctor_id)\n" +
        "where appointment_id in (select appointment_id from appointment_history where student_id = ?)";
    let params = [req.param("studentId")];
    mysql.query(query, params, (err, results) => {
        if (err) {
            console.log(err);
            res.status(500).send({error: err});
        }
        else {
            if (results.length > 0) res.status(200).send({paymentHistory: results});
            else res.status(400).send({error: "No Payments found!"});
        }
    });
};