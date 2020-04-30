/*
 *  BSD 3-Clause License
 * 
 * Copyright (c) 2020, Open Communications Security
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 * 
 * 3. Neither the name of the copyright holder nor the names of its
 *    contributors may be used to endorse or promote products derived from
 *    this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package br.com.opencs.jvm.tools.info;

/**
 * This class implements the jvm-info-tool.
 *  
 * @author Fabio Jun Takada Chino
 */
public class JVMInfo {

	private static String getVersion() {
		return System.getProperty("java.version").toString();
	}
	
	private static void printJavaHome() {
		System.out.println(System.getProperty("java.home").toString());
	}

	private static void printJavaVersion() {
		System.out.println(getVersion());
	}
	
	private static int isCompatible(String versionString) {
		JavaVersion candidate = new JavaVersion(versionString);
		JavaVersion current = new JavaVersion(getVersion());
		
		if (current.compareTo(candidate) >= 0) {
			return 0;
		} else {
			System.err.println(candidate.toString() + " > " + current.toString());
			return 3;
		}
	}
	
	public static void main(String [] args) {
		int errorCode = 0;
		
		if (args.length == 0) {
			System.err.println("Bad parameters.");
			errorCode = 1;
		} else {
			if (args[0].equals("-home")) {
				printJavaHome();
			} else if (args[0].equals("-version")) {
				printJavaVersion();
			} else {
				errorCode = isCompatible(args[0]);
			}
		}
		System.exit(errorCode);
	}
}
