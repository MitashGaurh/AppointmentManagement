exports.ping = (req, res) => {
    let data = {
      message: "Hello World!"
    };
    res.status(200).json(data);
};