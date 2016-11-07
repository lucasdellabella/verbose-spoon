#!/bin/bash
# Reinit Database
# Run from project root

DIR="$( cd "$( dirname "$BASH_SOURCE[0]}" )" && pwd )"
mysql -u root -p phase3 < $DIR/db/phase3.sql
