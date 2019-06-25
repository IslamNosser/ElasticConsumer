package AIM.ElasticConsumer;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		Properties properties = new Properties();
		properties.put("bootstrap.servers", "");
		properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		properties.put("group.id", "test-group");

		KafkaConsumer kafkaConsumer = new KafkaConsumer(properties);

		List topics = new ArrayList();
		topics.add("Elastic");
		kafkaConsumer.subscribe(topics);

		try {
			while (true) {
				try {
					ConsumerRecords<String, String> records = kafkaConsumer.poll(1000);
					for (ConsumerRecord record : records) {
						//The consumed json string from the topic
						String json = record.value().toString();
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			kafkaConsumer.close();
		}

	}
}
