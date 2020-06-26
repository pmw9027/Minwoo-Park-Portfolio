/**
 * Created by Minwoo on 2017. 2. 16..
 */
var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {
    console.log(req.query.m_id)
    res.render('chat', { title: 'Express',m_id: req.query.m_id });
});


module.exports = router;