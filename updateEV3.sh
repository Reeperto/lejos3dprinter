#!/usr/bin/env bash

scp ./out/artifacts/LegoPrint/LegoPrint.jar root@10.0.1.1:/home/lejos/programs/
ssh -t root@10.0.1.1 "jrun -jar /home/lejos/programs/LegoPrint.jar"