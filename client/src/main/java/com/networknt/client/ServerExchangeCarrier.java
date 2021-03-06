package com.networknt.client;

import io.undertow.server.HttpServerExchange;
import io.undertow.util.HttpString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.Map;

public class ServerExchangeCarrier implements io.opentracing.propagation.TextMap {
    private final static Logger logger = LoggerFactory.getLogger(ServerExchangeCarrier.class);

    private final HttpServerExchange exchange;

    public ServerExchangeCarrier(HttpServerExchange exchange) {
        this.exchange = exchange;
    }

    @Override
    public Iterator<Map.Entry<String, String>> iterator() {
        throw new UnsupportedOperationException("carrier is write-only");
    }

    @Override
    public void put(String key, String value) {
        if(logger.isDebugEnabled()) logger.debug("key = " + key + " value = " + value);
        exchange.getRequestHeaders().put(new HttpString(key), value);
    }
}
