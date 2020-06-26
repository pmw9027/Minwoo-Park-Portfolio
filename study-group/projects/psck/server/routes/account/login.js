/**
 * Created by Minwoo on 2017. 2. 22..
 */
exports.get = function(req, res){

    // Assuming this is from a POST request and the body of the
    // request contained the JSON of the new "todo" item to be saved
    console.log(req.query)
    try{
        var Account = require("../../models/account");

        Account.findOne({ u_id: req.query.u_id, u_pw: req.query.u_pw}, function(err, account){
            // var sess;
            // sess = req.session;
            //
            // var users = JSON.parse(data);
            // var id = req.params.u_id;
            // var password = req.params.u_pw;
            // var result = {};

            if(err) return res.status(500).json({
                error: err
            });

            if(account != null) {
                return res.status(200).json({
                    success: true,
                });
                //
                // if(!users[id]){
                //     result["success"] = 0;
                //     result["error"] = "not found";
                //     res.json(result);
                //     return;
                // }
                //
                // if(users[id]["password"]==password){
                //     result["success"] = 1;
                //     sess.id = id;
                //     res.json(result);
                // }else{
                //     result["success"] = 0;
                //     result["error"] = "incorrect"
                //     res.json(result);
                // }
            }
            else {
                return res.status(200).json({
                    success: false,
                    reason:'틀려쪙'
                });

            }
        })
    }
    catch(e){
        console.log(e)
    }

};