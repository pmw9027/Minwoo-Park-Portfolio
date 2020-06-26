package com.rival.hs.mongodb;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Minwoo on 2017. 4. 24..
 */

@Document(collection = "SEQUENCE_TB")
public class CounterDo {

    @Id
    String _id;
    Long sequence_value;

    public CounterDo(String _id, Long sequence_value) {
        this._id = _id;
        this.sequence_value = sequence_value;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Long getSequence_value() {
        return sequence_value;
    }

    public void setSequence_value(Long sequence_value) {
        this.sequence_value = sequence_value;
    }
}
