#!/usr/bin/env bash

docker-compose build && docker-compose up -d
pip install -r tests/requirements.txt
python -m pytest