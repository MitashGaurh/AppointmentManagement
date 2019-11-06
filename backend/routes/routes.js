module.exports = app => {
    let pingService = require('../services/ping');
    let userService = require('../services/userService');
    let appointmentService = require('../services/appointmentService');
    let doctorService = require('../services/doctorService');
    let paymentService = require('../services/paymentService');

    app.get('/ping', pingService.ping);

    app.post('/login', userService.login);

    app.post('/getUserDetails', userService.getUserDetails);

    app.post('/updateStudentProfile', userService.updateStudentProfile);

    app.get('/getAppointmentTypes', appointmentService.getAppointmentTypes);

    app.get('/getAppointmentReasons', appointmentService.getAppointmentReasons);

    app.get('/getAvailableTimeSlots', appointmentService.getAvailableTimeSlots);

    app.post('/createNewAppointment', appointmentService.createNewAppointment);

    app.post('/getAppointmentHistory', appointmentService.getAppointmentHistory);

    app.post('/cancelAppointment', appointmentService.cancelAppointment);

    app.get('/getAvailableDoctors', doctorService.getAvailableDoctors);

    app.post('/getPendingPayments', paymentService.getPendingPayments);

    app.post('/getPaymentHistory', paymentService.getPaymentHistory);
};