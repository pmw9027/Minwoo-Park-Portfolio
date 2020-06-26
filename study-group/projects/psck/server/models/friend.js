/**
 * Created by Minwoo on 2017. 2. 10..
 */
var mongoose = require('mongoose');
mongoose.Promise = global.Promise;

var Schema = mongoose.Schema;

var ThingSchema = new Schema({

    'my_id':String,
    'op_id':String
});

module.exports = mongoose.model('friend', ThingSchema, 'friend');





