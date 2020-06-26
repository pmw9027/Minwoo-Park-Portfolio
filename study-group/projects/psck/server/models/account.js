/**
 * Created by Minwoo on 2017. 2. 22..
 */

var mongoose = require('mongoose');
mongoose.Promise = global.Promise;

var Schema = mongoose.Schema;

var ThingSchema = new Schema({

    'u_id': String,
    'u_pw': String

});

module.exports = mongoose.model('user', ThingSchema, 'user');


