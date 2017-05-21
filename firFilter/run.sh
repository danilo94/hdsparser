#!/bin/bash

iverilog -o saida.vvp testbench.v

vvp saida.vvp

gtkwave out.vcd
