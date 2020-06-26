/**
 * Created by Minwoo on 2017. 2. 22..
 */
exports.post = function(req, res){
    console.log(req.body);

    // Assuming this is from a POST request and the body of the
    // request contained the JSON of the new "todo" item to be saved


    try{
        var Account = require("../../models/account");


        Account.findOne({ u_id: req.body.u_id}, function(err, account){
            if(err) return res.status(500).json({
                error: err
            });

            console.log(account)

            if(account != null) {

                return res.status(200).json({
                    success: false,
                    message: 'already exist'
                });
            }

            var new_account = new Account(req.body);


            new_account.save(function (err, createdTodoObject) {
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
    catch(e){
        console.log(e)
    }
};