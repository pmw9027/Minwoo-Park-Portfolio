/**
 * Created by Sung on 2017. 2. 27..
 */

var mongoose = require('mongoose');
mongoose.Promise = global.Promise;

var Schema = mongoose.Schema;

var ThingSchema = new Schema({

    'u_id': String,
    'u_name': String,
    'd_name': String,
    'd_ip': String,
    'd_mac': String,
    'd_boot_t': String,
    'd_cpu_per': String,
    'd_mem_per': String,
    'd_mem_total': String,
    'd_mem_avail': String,

});

module.exports = mongoose.model('device_info', ThingSchema, 'device_info');


