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

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class implements a JVM version comparator.  
 *
 * <p>This class implements the specification found in 
 * https://www.oracle.com/technetwork/java/javase/versioning-naming-139433.html</p>
 * 
 * @author Fabio Jun Takada Chino
 */
public class JavaVersion implements Comparable<JavaVersion> {

	/**
	 * This value is a 24-bit prime used to spread the hash value
	 */
	private static final int HASH_SPREAD_CONST = 169416019;

	/**
	 * This pattern parses the Java version string in the format 
	 * "major.minor.revision-identifier" as specified by
	 * https://www.oracle.com/technetwork/java/javase/versioning-naming-139433.html
	 */
	private static final Pattern VERSION_PATTERN = Pattern.compile("(\\d+)\\.(\\d+)\\.(\\d+)(.+)?");
	
	private static final int MAJOR = 0;
	
	private static final int MINOR = 1;
	
	private static final int REVISION = 2;
	
	private final int[] version = new int[3];
	
	private final String identifier;
	
	/**
	 * Creates a new instance of this class.
	 * 
	 * @param major Major.
	 * @param minor Minor.
	 * @param revision Revision.
	 * @param identifier Identifier. Can be null.
	 */
	public JavaVersion(int major, int minor, int revision, String identifier) {
		version[MAJOR] = major;
		version[MINOR] = minor;
		version[REVISION] = revision;
		this.identifier = (identifier != null)? identifier : "";
	}
	
	/**
	 * Creates a new instance of this class
	 * 	
	 * @param version
	 */
	public JavaVersion(String version) {
		Matcher m = VERSION_PATTERN.matcher(version);
		if (m.matches()) {
			this.version[MAJOR] = Integer.parseInt(m.group(1));
			this.version[MINOR] = Integer.parseInt(m.group(2));
			this.version[REVISION] = Integer.parseInt(m.group(3));
			this.identifier = (m.group(4) != null)? m.group(4) : "";
		} else {
			throw new IllegalArgumentException("'" + version + "' is not a valid JVM version.");
		}
	}

	public int getMajor() {
		return version[MAJOR];
	}
	
	public int getMinor() {
		return version[MINOR];
	}
	
	public int getRevision() {
		return version[REVISION];
	}
	
	public String getIdentifier() {
		return identifier;
	}

	@Override
	public String toString() {
		return getMajor() + "." + getMinor() + "." + getRevision() + getIdentifier();
	}

	public int compareTo(JavaVersion other) {

		for (int i = 0; i < version.length; i++) {
			int c = version[i] - other.version[i];
			if (c != 0) {
				return c;
			}
		}
		return getIdentifier().compareTo(other.getIdentifier());
	}

	@Override
	public boolean equals(Object obj) {
		JavaVersion other = (JavaVersion)obj;
		return Arrays.equals(version, other.version) && 
				getIdentifier().equals(other.getIdentifier());
	}

	@Override
	public int hashCode() {
		int c = 0;
		for (int i = 0; i < version.length; i++) {
			c += (version[i] * HASH_SPREAD_CONST);
		}
		return c + getIdentifier().hashCode();
	}
}
