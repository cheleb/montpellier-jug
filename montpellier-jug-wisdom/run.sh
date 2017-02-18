#!/usr/bin/env bash

if [[ -z "$EVENTBRITE_TOKEN" ]]; then
   echo "Token must be provided check your email ;)"
   exit 1
fi

if type -p java; then
    _java=java
elif [[ -n "$JAVA_HOME" ]] && [[ -x "$JAVA_HOME/bin/java" ]];  then
    _java="$JAVA_HOME/bin/java"
else
    echo "no java"
fi

if [[ "$_java" ]]; then
    version=$("$_java" -version 2>&1 | awk -F '"' '/version/ {print $2}')
    if [[ "$version" < "1.8" ]]; then
       echo Java 8 needed :P read the manual.
       exit
    fi
    export DB_URL="jdbc:postgresql://localhost:5432/jug"
    export DB_USER=jug
    export DB_PASS=jug
    export OAUTH_ID="360777772993-2ltneffc156df2mq5db9civvma98vels.apps.googleusercontent.com"
    export OAUTH_SECRET="YsnPu9l7EfW66FIC87t4YziC"
    mvn clean wisdom:run -DskipTests -DskipITs -Doauth.secret=YsnPu9l7EfW66FIC87t4YziC -Doauth.callback=http://localhost:9000/oauth2/cb -Doauth.authenticated=http://localhost:9000/admin/ -Dtoken=$@
fi
