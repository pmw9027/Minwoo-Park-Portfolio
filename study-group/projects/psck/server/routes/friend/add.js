/**
 * Created by Minwoo on 2017. 2. 8..
 */
exports.post = function(req, res){
    console.log(req.body);

    // Assuming this is from a POST request and the body of the
    // request contained the JSON of the new "todo" item to be saved
    var Friend = require("../../models/friend");
    var Account = require("../../models/account");

    try{
        Account.findOne({ u_id: req.body.op_id}, function(err, account) {
            if(err) return res.status(500).json({
                error: err
            });
            console.log(account)
            if(account != null) {

                Friend.findOne({ my_id: req.body.my_id, op_id: req.body.op_id}, function(err, friend){
                    if(err) return res.status(500).json({
                        error: err
                    });

                    if(friend != null) {

                        return res.status(200).json({
                            success: false,
                            message: 'already exist'
                        });
                    }

                    var new_Friend = new Friend(req.body);


                    new_Friend.save(function (err, createdTodoObject) {
                        if (err) {
                            res.send({
                                success: false,
                                message: 'unknown error'
                            });
                        }
                        // This createdTodoObject is the same one we saved, but after Mongo
                        // added its additional properties like _id.
                        else {

                            res.send({
                                success: true
                            });
                        }
                    });
                })
            }
            else {
                return res.status(200).json({
                    success: false,
                    message:'No account'
                });

            }
        })


    }
    catch(e){
        console.log(e)
    }
};