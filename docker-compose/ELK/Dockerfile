FROM sebp/elk:670

ENV ES_HOME /opt/elasticsearch

WORKDIR ${ES_HOME}

RUN yes | CONF_DIR=/etc/elasticsearch gosu elasticsearch bin/elasticsearch-plugin \
    install -b analysis-nori
