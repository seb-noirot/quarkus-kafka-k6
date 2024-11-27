import { check } from 'k6';
import { Kafka } from 'k6/x/kafka';

const kafka = new Kafka({
    brokers: ['localhost:9092'],
    clientId: 'k6-client',
});

const topic = 'incoming-messages';
const producer = kafka.producer();
const consumer = kafka.consumer({ groupId: 'k6-group' });

export default function () {
    producer.produce({
        topic: topic,
        messages: [
            { value: 'Hello Kafka' },
        ],
    });

    consumer.subscribe({ topic: topic });
    consumer.run({
        eachMessage: ({ topic, partition, message }) => {
            check(message, {
                'message value is correct': (msg) => msg.value.toString() === 'Hello Kafka',
            });
        },
    });
}
