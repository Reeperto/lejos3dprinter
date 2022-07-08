#!/usr/bin/env bash

scp ./out/artifacts/LegoPrint/LegoPrint.jar root@10.42.0.205:/home/lejos/programs/
ssh -t root@10.42.0.205 "jrun -jar /home/lejos/programs/LegoPrint.jar"