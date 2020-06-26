/**
 * Created by Sung on 2017. 3. 1..
 */


exports.get = function (req, res) {

    try {
        var Device = require("../../models/deviceinfo");
        Device.findOne({u_id: req.query.op_id}, function (err, device) {
            console.log(device)

            if(err) return res.status(500).json({
                error: err
            });

            if(device != null) {
                return res.status(200).json({
                    success: true,
                    device: device
                });

            }
            else {
                return res.status(200).json({
                    success: false,
                    reason:'error'
                });
            }

        })
    }
    catch
        (e) {
        console.log(e)
    }

};
