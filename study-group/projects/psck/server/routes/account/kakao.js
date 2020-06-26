/**
 * Created by Minwoo on 2017. 2. 27..
 */
var express = require('express');
var router = express.Router();



/* GET home page. */
router.get('/', function(req, res, next) {
    res.render('index', { title: 'Express' });
});


module.exports = router;
