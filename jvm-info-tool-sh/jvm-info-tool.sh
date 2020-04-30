#!/bin/sh
# BSD 3-Clause License
# 
# Copyright (c) 2020, Open Communications Security
# All rights reserved.
# 
# Redistribution and use in source and binary forms, with or without
# modification, are permitted provided that the following conditions are met:
# 
# 1. Redistributions of source code must retain the above copyright notice, this
#    list of conditions and the following disclaimer.
# 
# 2. Redistributions in binary form must reproduce the above copyright notice,
#    this list of conditions and the following disclaimer in the documentation
#    and/or other materials provided with the distribution.
# 
# 3. Neither the name of the copyright holder nor the names of its
#    contributors may be used to endorse or promote products derived from
#    this software without specific prior written permission.
# 
# THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
# AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
# IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
# DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
# FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
# DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
# SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
# CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
# OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
# OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

get_java_version(){
    java -jar jvm-info-tool.jar -version 2> /dev/null
}

get_java_home(){
    java -jar jvm-info-tool.jar -home 2> /dev/null
}

is_java_compatible() {
    java -jar jvm-info-tool.jar $1 2> /dev/null
}

JAVA_VERSION=$(get_java_version)
JAVA_HOME=$(get_java_home)

echo "JAVA_VERSION: $JAVA_VERSION"
echo "JAVA_VERSION: $JAVA_HOME"
printf "Java 1.5.0 compatible:"
if is_java_compatible 1.5.0; then
    echo "YES"
else
    echo "NO"
fi
printf "Java 100.0.0 compatible:"
if is_java_compatible 100.0.0; then
    echo "YES"
else
    echo "NO"
fi




