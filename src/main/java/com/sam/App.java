package com.sam;

import com.sam.Client;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		if (args.length < 2) {
			System.out.println("Usage:");
			System.out.println("arg[0]: hostip");
			System.out.println("arg[1]: pub or sub");
			System.out.println("arg[2]: message for pub");
		} else {
			String serverURI = args[0];

			if (args[1].equals("pub")) {
				new Client(serverURI).pub(args[2]);
			} else {
				new Client(serverURI).sub();
			}
		}
	}
}
