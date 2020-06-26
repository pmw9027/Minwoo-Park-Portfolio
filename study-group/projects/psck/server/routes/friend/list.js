/**
 * Created by Minwoo on 2017. 2. 24..
 */


exports.get = function(req, res){
    readData(req, res)
};

var readData = function (req, res) {
    var array1 =[]
    // Assuming this is from a POST request and the body of the
    // request contained the JSON of the new "todo" item to be saved
    try{
        var Friend = require("../../models/friend");
        var Account = require("../../models/account");

        Friend.find({my_id: req.query.my_id}, function(err, friends) {


            console.log(friends)

            return res.status(200).json({
                success: false,
                message:'틀려쪙',
                friends:friends
            });

            friends.forEach(function (entry) {

                Account.findOne({u_id: entry.op_id}, function (err, account) {

                    array1.push(account.u_id)
                    console.log(array1)
                    callback();

                })
            })

            return array1
        })

    }
    catch(e){
        console.log(e)
    }
}

