#!/bin/bash

psql -U myweek < myweek.ddl
psql -U myweek < initialData.sql