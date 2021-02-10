#!/usr/bin/env bash

projectName="test-plugin"

rm -rf target && mkdir target

[ -f test.yml ] && cp test.yml target/concord.yml

cd target && zip -r payload.zip ./* > /dev/null && cd ..

curl -i -H "Authorization: auBy4eDWrKWsyhiDp3AQiw" \
 -F archive=@target/payload.zip \
 -F org="test-plugin-org" \
 -F project=${projectName} \
 http://localhost:8001/api/v1/process