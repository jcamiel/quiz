#!/bin/bash
set -eu

wait_for_url () {
    echo "Testing $1"
    max_in_s=$2
    delay_in_s=1
    total_in_s=0
    while [ $total_in_s -le "$max_in_s" ]
    do
        echo "Wait ${total_in_s}s"
        if (echo -e "GET $1\nHTTP/* 200" | hurl > /dev/null 2>&1;) then
            return 0
        fi
        total_in_s=$(( total_in_s +  delay_in_s))
        sleep $delay_in_s
    done
    return 1
}

echo "Starting Quiz container"
docker run -dp 8080:8080 --name quiz ghcr.io/jcamiel/quiz:latest

echo "Starting Quiz instance to be ready"
wait_for_url 'http://localhost:8080' 60

echo "Running Hurl tests"
hurl integration/*.hurl --test

echo "Stopping Quiz instance"
docker stop quiz
