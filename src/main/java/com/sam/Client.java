package com.sam;

import java.io.IOException;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class Client implements MqttCallback {
	MqttClient client;
	String serverURI = "";

	public Client(String serverURI) {
		this.serverURI = serverURI;
	}

	public void pub(String msg) {
		try {
			client = new MqttClient("tcp://" + serverURI + ":1883",
					"Sending");
			client.connect();
			System.out.println("is connected: " + client.isConnected());
			MqttMessage message = new MqttMessage();
			message.setPayload(msg.getBytes());
			client.publish("test", message);
			client.disconnect();
			client.close();
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}

	public void sub() {
		try {
			client = new MqttClient("tcp://" + serverURI + ":1883",
					"Subscribing");
			client.connect();
			client.setCallback(this);
			client.subscribe("test");

			System.out.println("Press <Enter> to exit");
			try {
				System.in.read();
			} catch (IOException e) {
				// If we can't read we'll just exit
			}

			client.disconnect();
			client.close();
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}

	public void connectionLost(Throwable cause) {
		// TODO Auto-generated method stub

	}

	public void messageArrived(String topic, MqttMessage message)
			throws Exception {
		System.out.println(message);
	}

	public void deliveryComplete(IMqttDeliveryToken token) {
		// TODO Auto-generated method stub

	}

}