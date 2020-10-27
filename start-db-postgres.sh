#!/bin/bash

docker run --name postgres -e POSTGRES_PASSWORD=postgres -e POSTGRES_USER=postgres -e POSTGRES_DB=auth-service -p 5432:5432 -d postgres:12.4-alpine