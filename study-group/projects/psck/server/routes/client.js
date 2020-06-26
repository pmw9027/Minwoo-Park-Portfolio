/**
 * Created by Minwoo on 2017. 2. 5..
 */

var express = require('express');
var router = express.Router();

/* GET users listing. */
router.get('/', function(req, res, next) {
    console.log("Request handler random was called.");
    res.writeHead(200, {"Content-Type": "application/json"});
    var otherArray = ["item1", "item2"];
    var otherObject = { item1: "item1val", item2: "item2val" };
    var json = JSON.stringify({
        anObject: otherObject,
        anArray: otherArray,
        another: "item"
    });
    res.end(json);
});

module.exports = router;