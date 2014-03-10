/*
*	This file is part of ITGController.
* 
*	Copyright 2014 Duncan Eastoe <duncaneastoe@gmail.com>
*
*   ITGController is free software: you can redistribute it and/or modify
*   it under the terms of the GNU General Public License as published by
*   the Free Software Foundation, either version 3 of the License, or
*   (at your option) any later version.
*
*   ITGController is distributed in the hope that it will be useful,
*   but WITHOUT ANY WARRANTY; without even the implied warranty of
*   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*   GNU General Public License for more details.
*
*   You should have received a copy of the GNU General Public License
*   along with ITGController.  If not, see <http://www.gnu.org/licenses/>.
*/

package com.dje.itg.controller;

import java.io.File;

import com.dje.itg.api.ITGCatchMessage;
import com.dje.itg.api.ITGApi;

public class Main {
	
	private MessageReceiver messageReceiver;

	public Main(File file) {
		ITGApi itgApi = new ITGApi();

		/* Start the thread to receive response messages */
		messageReceiver = new MessageReceiver(itgApi);
		messageReceiver.start();
		
		Config config = new Config(file, itgApi);
		
		ConfigRunner configRunner = new ConfigRunner(config, messageReceiver, itgApi);
		configRunner.run();
	}
	
	/**
	 * Main method
	 */
	public static void main(String[] args) {
		try {
			new Main(new File(args[0]));
		} catch (ArrayIndexOutOfBoundsException e) {
			System.err.println("ITGController\n" +
					   "-------------\n\n" + 
					   "Arguments: <script>\n\n" +
					   "<script>	Path to D-ITG script");
			System.exit(1);
		}
	}
	
}
