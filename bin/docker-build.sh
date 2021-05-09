#!/bin/bash

docker build -f docker/Dockerfile -t jcamiel/quiz:latest .
docker tag jcamiel/quiz:latest ghcr.io/jcamiel/quiz:latest
