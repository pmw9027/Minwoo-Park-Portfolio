/**
 * Created by Minwoo on 2017. 2. 8..
 */

exports.post = function (req, res) {

    var Device = require("../../models/deviceinfo");
    res.writeHead(200, {"Content-Type": "application/json"});
    var json = JSON.stringify(req.body);


    try {
        Device.findOne({u_id: req.body.u_id}, function (err, device) {
            if (err) return res.status(500).json({
                error: err
            });

            var device_info = new Device(req.body);

            if (device == null) {
                device_info.save(function (err, createdTodoObject) {
                    if (err) return res.status(500).json({
                        error: err
                    });
                });
            }
            else {
                Device.update(
                    {u_id: req.body.u_id},
                    {
                        $set: {
                            d_cpu_per: req.body.d_cpu_per,
                            d_mem_per: req.body.d_mem_per,
                            d_mem_avail: req.body.d_mem_avail,
                            d_mem_total: req.body.d_mem_total,
                            d_name: req.body.d_name,
                            d_boot_time: req.body.d_boot_time,
                            d_ip: req.body.d_ip,
                            d_mac: req.body.d_mac,

                        }
                    },
                    function (err, result) {
                        if (err) throw err;
                    })
            }
        })
    }
    catch
        (e) {
        console.log(e)
    }

    res.end(Device);
};
