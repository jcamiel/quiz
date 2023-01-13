#!/bin/bash
set -eu

wait_for_url () {
    echo "Testing $1..."
    echo -e "GET $1\nHTTP/* 200" | hurl --retry --retry-max-count "$2" > /dev/null;
    return 0
}

echo "Starting Quiz container"
#docker run --rm --detach --publish 8080:8080 --name quiz ghcr.io/jcamiel/quiz:latest

echo "Starting Quiz instance to be ready"
wait_for_url 'http://localhost:8080' 60

echo "Running Hurl tests"
hurl integration/*.hurl --test

echo "Stopping Quiz instance"
#docker stop quiz
