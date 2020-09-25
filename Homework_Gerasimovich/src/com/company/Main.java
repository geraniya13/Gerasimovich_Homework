package com.company;

import ru.pflb.mq.dummy.exception.DummyException;
import ru.pflb.mq.dummy.implementation.ConnectionImpl;
import ru.pflb.mq.dummy.interfaces.Connection;
import ru.pflb.mq.dummy.interfaces.Destination;
import ru.pflb.mq.dummy.interfaces.Producer;
import ru.pflb.mq.dummy.interfaces.Session;
import java.util.ArrayList;


public class Main {

    public static void main(String[] args) throws DummyException, InterruptedException {
        Connection connection = new ConnectionImpl();

        Session session = connection.createSession(true);

        Destination destination = session.createDestination("Queue");

        Producer producer = session.createProducer(destination);

        ArrayList<String> list = new ArrayList<>();

        list.add("1");
        list.add("2");
        list.add("3");

        for (int i = 0; i < list.size(); i++) {
            producer.send(list.get(i));
            Thread.sleep(2000);
        }

        session.close();

        connection.close();
    }
}
