let mysql = require('../utility/mysql');

exports.getAppointmentTypes = (req, res) => {
    let query = "select distinct type_name from appointment_type order by type_name asc";
    mysql.query(query, (err, results) => {
        if (err) res.status(500).send({error: err});
        else {
            if (results.length > 0) {
                let typeList = [];
                results.forEach(result => typeList.push(result.type_name));
                res.status(200).send({appointmentTypes: typeList});
            }
            else res.status(400).send({error: "No Appointment Types found!"});
        }
    });
};

exports.getAppointmentReasons = (req, res) => {
    let query = "select distinct reason from appointment_reason order by reason asc";
    mysql.query(query, (err, results) => {
        if (err) res.status(500).send({error: err});
        else {
            if (results.length > 0) {
                let reasonList = [];
                results.forEach(result => reasonList.push(result.reason));
                res.status(200).send({appointmentReasons: reasonList});
            }
            else res.status(400).send({error: "No Appointment Reasons found"});
        }
    });
};

exports.getAvailableTimeSlots = (req, res) => {
    let query = "select slot_id as slotId, slot_time as slotTime, doctor_name as doctorName, dates as appointmentDate from appointment_slots\n" +
        "inner join doctors using (doctor_id)\n" +
        "join (select date_add(date(now()), interval 1 day) as dates \n" +
        "union\n" +
        "select date_add(date(now()), interval 2 day) as dates\n" +
        "union\n" +
        "select date_add(date(now()), interval 3 day) as dates\n" +
        "union\n" +
        "select date_add(date(now()), interval 4 day) as dates\n" +
        "union\n" +
        "select date_add(date(now()), interval 5 day) as dates\n" +
        "union\n" +
        "select date_add(date(now()), interval 6 day) as dates\n" +
        "union\n" +
        "select date_add(date(now()), interval 7 day) as dates) d \n" +
        "where (slot_id, dates) not in (select distinct slot_id, appointment_date from appointment_history)\n" +
        "order by dates,doctor_id asc";
    mysql.query(query, (err, results) => {
        if (err) res.status(500).send({error: err});
        else {
            if (results.length > 0) res.status(200).send({availableTimeSlots: results});
            else res.status(400).send({error: "No Time Slots Available for next week!!"});
        }
    });
};

exports.createNewAppointment = (req, res) => {
    let query = "insert into appointment_history (student_id, slot_id, type_id, reason_id, appointment_date) " +
        "values (?,?,?,?,?)";
    let params = [req.param("studentId"), req.param("slotId"), req.param("typeId"), req.param("reasonId"), req.param("appointmentDate")];
    mysql.query(query, params, (err, results) => {
        if (err) res.status(500).send({error: err});
        else res.status(200).send({status: "success", message: "Created New Appointment Successfully"});
    });
};

exports.getAppointmentHistory = (req, res) => {
    let query = "select appointment_id as appointmentId, student_id as studentId, " +
        "appointment_date as appointmentDate, slot_time as appointmentTime, doctor_name as doctorName, " +
        "type_name as appointmentType, reason as appointmentReason, appointment_status as appointmentStatus from appointment_history\n" +
        "inner join appointment_slots using (slot_id) \n" +
        "inner join doctors using (doctor_id)\n" +
        "inner join appointment_type using (type_id) \n" +
        "inner join appointment_reason using (reason_id)\n" +
        "inner join appointment_status using (status_id)\n" +
        "where student_id = ?";
    let params = [req.param("studentId")];
    mysql.query(query, params, (err, results) => {
        if (err) res.status(500).send({error: err});
        else {
            if (results.length > 0) res.status(200).send({appointmentHistory: results});
            else res.status(400).send({error: "No Appointments found!"});
        }
    });
};

exports.cancelAppointment = (req, res) => {
    let query = "update appointment_history set status_id = 2 where appointment_id = ?";
    let params = [req.param("appointmentId")];
    mysql.query(query, params, (err, results) => {
        if (err) res.status(500).send({error: err});
        else res.status(200).send({status: "success", message: "Appointment Cancelled Successfully"});
    });
};