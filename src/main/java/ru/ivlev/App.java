package ru.ivlev;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import ru.ivlev.grpc.GreetingServiceGrpc;
import ru.ivlev.grpc.GreetingServiceOuterClass;

import java.util.Iterator;

public class App {
    public static void main(String[] argc) {
        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8080")
                .usePlaintext().build();

        GreetingServiceGrpc.GreetingServiceBlockingStub stub = GreetingServiceGrpc.newBlockingStub(channel);

        GreetingServiceOuterClass.HelloRequest request = GreetingServiceOuterClass.HelloRequest.newBuilder()
                .setName("Anri Malinka").build();

        Iterator<GreetingServiceOuterClass.HelloResponse> responses = stub.greeting(request);
        while (responses.hasNext()) {
            System.out.println(responses.next());
        }

        channel.shutdownNow();
    }
}
